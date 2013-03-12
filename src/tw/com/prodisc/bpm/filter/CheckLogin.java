package tw.com.prodisc.bpm.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF06;
import tw.com.prodisc.bpm.bean.BBPMF12;
import tw.com.prodisc.bpm.bean.BBPMF13;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF04;
import tw.com.prodisc.util.KC_Pub;

public class CheckLogin extends HttpServlet implements Filter {
	private static final long serialVersionUID = -8499175560946934672L;
	String[] uriPass = {
			"/index.jsp",
			"/err00.jsp",
			"/err01.jsp",
			"/err02.jsp"
	};
	private boolean uriPass(HttpServletRequest req){
		String uri = req.getServletPath();
		for (int i=0;i<uriPass.length;i++)
			if ( uri.equals(uriPass[i]) ) return true;
		return false;
	}
	private boolean chk00(HttpServletRequest req){
		HttpSession session = req.getSession();
		if (session.getAttribute("G_F02")==null) return true;
		return false;
	}
	private boolean chk01(HttpServletRequest req){
		String uri = req.getServletPath();
		String cc  = uri.substring(uri.lastIndexOf(".")
					              ,uri.length());
		
		boolean bb = true;
		if (!cc.equals(".jsp") && !cc.equals(".action")) bb = false;
		bb = false;
		return bb;
	}
	
	@SuppressWarnings("rawtypes")
	private int chk02(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int f04id = -1;
		boolean b1 = false,b2 = false;
		int bb = 0;  // 0: 進入  1: 無簽核權限   2: 已完成轉 display 
		String S1 = req.getServletPath();
		String S2 = req.getRequestURI()+"?"+req.getQueryString();
		HttpSession session = req.getSession();
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");

		if (S1.contains("WorkFlow")) { // 判斷是否進入 WorkFlow.action?action=main%26status=approve
			Map<String, String[]> map = req.getParameterMap();
			Set<Entry<String,String[]>> set= map.entrySet();
			Iterator<Entry<String,String[]>> it= set.iterator();		// System.out.printf(req.getParameterMap());
			while(it.hasNext()){
				Entry<String, String[]> entry= it.next();
				entry.getKey();
				for(String i:entry.getValue()){
					// System.out.println(entry.getKey()+":"+i);
					if (entry.getKey().equals("action") && i.equals("main"))
						b1=true;
					if (entry.getKey().equals("status") && i.equals("approve"))
						b2=true;
					if (entry.getKey().equals("f04_id"))
						f04id = Integer.parseInt(i);
				}
			}
			bb = (b1&&b2) ? 1 : 0; 
		}
		if (bb != 1) return bb; // 不是要簽核直接離開
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Spring.xml"));
		IsBPMF04 S_BPMF04 = (IsBPMF04) factory.getBean("BPMF04");
		Session ss01 = S_BPMF04.getDao().openSession();
		BBPMF04 f04 = (BBPMF04) ss01.createQuery(IsBPMF04.sqlID)
				.setParameter("ID", f04id)
				.list().get(0);
		BBPMF05 f05 = f04.getF05();				// 抓未簽關卡
		if (f04.getF05s().size() == 0) bb = 2;  // 判定是否無關卡 
		if (f04.getBpm04101() != 1) bb = 2;		// 判定是否未完成
		if (f05==null) bb = 2;
		
		switch (bb){ // 檢查是否有權簽核此表單
			case 1 : {							// 表單未簽完判斷是否可以簽核
				List<BBPMF12> f12s = f05.getF12s();
				for (int j = 0;j < f12s.size();j++){
					List<BBPMF06> f06s = f12s.get(j).getF06s();
					// 判斷本人可簽核 bb=0
					for (int k = 0;k < f06s.size();k++){
						if ((f02.getId() ==  f06s.get(k).getF02().getId()))  
							bb = 0;			 
					}	
					if (bb == 0) break;
					
					// 簽核人已離職, 判斷離職代理人可簽核 bb=0
					for (int k = 0;k < f06s.size();k++){
						if ((null != f06s.get(k).getF02().getBpm02004())&&
							(f02.getId() ==  f06s.get(k).getF02().getF02_AGENT().getId()))
							bb = 0;			 
					}	
					if (bb == 0) break;
					
					// 判斷代理人可簽核 bb=0
					for (int k = 0;k < f06s.size();k++){
						List<BBPMF13> f13s = f06s.get(k).getF02().getF1301s();
				        for(int i=0;i<f13s.size();i++){
				        	if (f13s.get(i).isDeleted()) 
				        		continue;
				        	if (f02.getId() != f13s.get(i).getF02().getId()) 
				        		continue;
				        	if ((f13s.get(i).getBpm13002()!=null) &&
				        		(f13s.get(i).getBpm13002().after(KC_Pub.kc_today())))
				        		continue;
				        	if ((f13s.get(i).getBpm13003()!=null) &&
				           		(f13s.get(i).getBpm13003().before(KC_Pub.kc_today())))
				           		continue;
			        		String[] ids = f13s.get(i).getBpm13999().split(",");
			        		for(String nid:ids)
			        			if (nid.equals(f04.getF03().getId().toString())) bb = 0;  
				        }
				        if (bb == 0) break;
					}
					if (bb == 0) break;
				}
				break;
			}
			case 2 : {							// 表單已簽完判斷是否可以查詢
				List<BBPMF05> f05s = f04.getF05s();
				for (int i = 0;i < f05s.size();i++){
					List<BBPMF12> f12s = f05s.get(i).getF12s();
					for (int j = 0;j < f12s.size();j++){
						List<BBPMF06> f06s = f12s.get(j).getF06s();
						for (int k = 0;k < f06s.size();k++){
							if ((f02.getId() ==  f06s.get(k).getF02().getId()) || 
								(f02.getId() ==  f06s.get(k).getF0201().getId())){
								bb = 3;				 // 可查詢 bb=3
							}
							if (bb == 3) break;
						}
						if (bb == 3) break;
					}
					if (bb == 3) break;
				}
				if (bb == 3) { // 轉 display	
					S2 = S2.replaceFirst("approve", "display");
					res.sendRedirect(S2);
				}
				break;
			}
		}
		S_BPMF04.getDao().closeSession(ss01);
		return bb;
	}
	
	@SuppressWarnings("rawtypes")
	private void setSession(HttpServletRequest req){
		HttpSession session = req.getSession();
		if (session.getAttribute("G_F02")==null) {
			XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Spring.xml"));
			IsBPMF02 S_BPMF02 = (IsBPMF02) factory.getBean("BPMF02");
			String[] G_account = req.getRemoteUser().toUpperCase().split("\\\\");
			BBPMF02 f02 = S_BPMF02.get001(G_account[G_account.length-1]);
			if (f02==null) return;
			
			// 轉為此員工進入系統  測試用 
			if (!KC_Pub.isNull(f02.getBpm02101())) 
				f02 = S_BPMF02.get001(f02.getBpm02101());
			f02 = S_BPMF02.get001("86407");//88103 88293 88252 81022
			session.setAttribute("G_F02",f02);
		}
		// System.out.println("-> "+req.getRequestURI());
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		setSession(req);		// 設定 Session
		if (uriPass(req)) {chain.doFilter(request,response);return;} // 檢查是否有在例外名單
		if (chk00(req)) {res.sendRedirect("/BPM/err00.jsp");return;} // 檢查是否有帳號
		if (chk01(req)) {res.sendRedirect("/BPM/err01.jsp");return;} // 檢查是否合法進入 		
		switch (chk02(req,res)){ // 檢查是否有權簽核此表單
			case 1 : { res.sendRedirect("/BPM/err02.jsp"); return;}
			case 2 : { res.sendRedirect("/BPM/err03.jsp"); return;} 
			case 3 : return;
		}
		chain.doFilter(request,response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {}
}