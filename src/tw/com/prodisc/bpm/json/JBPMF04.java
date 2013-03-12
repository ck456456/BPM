package tw.com.prodisc.bpm.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF13;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF04;
import tw.com.prodisc.bpm.util.BPM_PUB;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPMF04 extends ActionSupport{
	private static final long serialVersionUID = 1511030670061557212L;
	private String action;
	private String b_03001;
	private String e_03001;
	private String b_04001;
	private String e_04001;
	private String b_04004;
	private String e_04004;
	@SuppressWarnings("rawtypes")
	private IsBPMF02 S_BPMF02;
	@SuppressWarnings("rawtypes")
	private IsBPMF03 S_BPMF03;
	@SuppressWarnings("rawtypes")
	private IsBPMF04 S_BPMF04;

	@SuppressWarnings("rawtypes")
	public IsBPMF03 getS_BPMF03() {
		return S_BPMF03;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF03(IsBPMF03 s_BPMF03) {
		S_BPMF03 = s_BPMF03;
	}

	public String getB_03001() {
		return b_03001;
	}

	public void setB_03001(String b_03001) {
		this.b_03001 = b_03001;
	}

	public String getE_03001() {
		return e_03001;
	}

	public void setE_03001(String e_03001) {
		this.e_03001 = e_03001;
	}

	public String getB_04001() {
		return b_04001;
	}

	public void setB_04001(String b_04001) {
		this.b_04001 = b_04001;
	}

	public String getE_04001() {
		return e_04001;
	}

	public void setE_04001(String e_04001) {
		this.e_04001 = e_04001;
	}

	public String getB_04004() {
		return b_04004;
	}

	public void setB_04004(String b_04004) {
		this.b_04004 = b_04004;
	}

	public String getE_04004() {
		return e_04004;
	}

	public void setE_04004(String e_04004) {
		this.e_04004 = e_04004;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@SuppressWarnings("rawtypes")
	public IsBPMF02 getS_BPMF02() {
		return S_BPMF02;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF02(IsBPMF02 s_BPMF02) {
		S_BPMF02 = s_BPMF02;
	}

	
	@SuppressWarnings("rawtypes")
	public IsBPMF04 getS_BPMF04() {
		return S_BPMF04;
	}

	public boolean chkf03(JSONArray arr,BBPMF04 f04,List<BBPMF03> f03s){
		for (int i=0;i < arr.size();i++)
			if (f04.getId().equals(((JSONObject)arr.getJSONObject(i).get("cell")).get("id")))
				return false;
		
		for (int i=0;i < f03s.size();i++){
			if (null==f03s.get(i)) return  true; 
			if (f04.getF03().getId()==f03s.get(i).getId())
				return  true;
		}	
		return false;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF04(IsBPMF04 s_BPMF04) {
		S_BPMF04 = s_BPMF04;
	}
	
	private String getprevf05(BBPMF04 f04){
		if (null==f04.getF05()) return "";
		BBPMF05 f05=f04.getF05();
		do{
			f05 = f05.getF05l();
			if (null==f05) return f04.getF02().getBpm02002();
		} while(2!=f05.getBpm05003());
		return f05.getF02().getBpm02002();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void BPMCancel(){
		String S01,S02;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest 	 request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF04.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF02 f02= (BBPMF02) session.getAttribute("G_F02");
		List<BBPMF04> f04s = ss01.createQuery(
					" select p from BBPMF04 p "
				+ " where lower(p.f02.id) = lower(:F02ID) "
				  + " and p.bpm04101 = 1 "
				  + " and p.deleted = false ")
				.setParameter("F02ID", f02.getId().toString()).list();
		Collections.sort(f04s,
		        new Comparator<BBPMF04>() {
		            public int compare(BBPMF04 o1, BBPMF04 o2) {
		                return o1.getBpm04006().compareTo(o2.getBpm04006());
		            }
		        });
		
        for(int i=0;i<f04s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
        	S01 = BPM_PUB.get04003(f04s.get(i).getBpm04003());
        	S02 = getprevf05(f04s.get(i));
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , f04s.get(i).getId());			   
		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04006()));		   
		 	obj02.put("bpm04003"     , S01);
		 	obj02.put("prev"     	 , S02);
		 	obj02.put("bpm05002"     , f04s.get(i).getF05().getBpm05002());
		 	obj02.put("bpm03002"     , f04s.get(i).getF03().getBpm03002());
		 	obj02.put("bpm04001"     , f04s.get(i).getBpm04001());
		 	obj02.put("bpm04002"     , f04s.get(i).getBpm04002());
		 	obj02.put("bpm02002"     , f04s.get(i).getF02().getBpm02002());
		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04004()));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", f04s.size());
		
		D_Dao.closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings("rawtypes")
	public void BPMLeave(){
		String S01,S02;
		int    icount = 0;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest 	 request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		BBPMF02 t02 = (BBPMF02) session.getAttribute("G_F02");
		BBPMF02 f02 = (BBPMF02) ss01.createQuery(IsBPMF02.sqlID)
				.setParameter("ID", t02.getId().toString()).list().get(0);
		List<BBPMF02> f02s = f02.getF02_AGENTs();
		List<BBPMF04> f04s = null;
        for(int i=0;i<f02s.size();i++){
        	if (null==f02s.get(i).getBpm02004()) continue;
        	f04s = f02s.get(i).getF04s();
            for(int j=0;j<f04s.size();j++){
            	JSONObject obj01 = new JSONObject();   
            	JSONObject obj02 = new JSONObject();
            	S01 = BPM_PUB.get04003(f04s.get(j).getBpm04003());
            	S02 = getprevf05(f04s.get(j));
        		obj01.put("id"           , ""+(++icount));
    			obj02.put("id"           , f04s.get(j).getId());
    			obj02.put("src02002"     , f02s.get(i).getBpm02002());
    		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04006()));		   
    		 	obj02.put("bpm04003"     , S01);
    		 	obj02.put("prev"     	 , S02);
    		 	obj02.put("bpm05002"     , f04s.get(j).getF05().getBpm05002());
    		 	obj02.put("bpm03002"     , f04s.get(j).getF03().getBpm03002());
    		 	obj02.put("bpm04001"     , f04s.get(j).getBpm04001());
    		 	obj02.put("bpm04002"     , f04s.get(j).getBpm04002());
    		 	obj02.put("bpm02002"     , f04s.get(j).getF02().getBpm02002());
    		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04004()));
        		obj01.put("cell",obj02);
        		arr.add(obj01);
            }
        }
		obj.put("rows" , arr);
		obj.put("total", (icount));
		D_Dao.closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings("rawtypes")
	public void BPMProxy(){
		String S01,S02;
		int    icount = 0;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest 	 request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		BBPMF03 t03 = null;
		BBPMF02 t02 = (BBPMF02) session.getAttribute("G_F02");
		BBPMF02 f02 = (BBPMF02) ss01.createQuery(IsBPMF02.sqlID)
				.setParameter("ID", t02.getId().toString()).list().get(0);
		// 取得被代理人 f13s
		List<BBPMF13> f13s = f02.getF1301s();
		Collections.sort(f13s,
		        new Comparator<BBPMF13>() {
		            public int compare(BBPMF13 o1, BBPMF13 o2) {
		                return o1.getSrc().getBpm02001().compareTo(o2.getSrc().getBpm02001());
		            }
		        });
		
		List<BBPMF03> f03_list = new ArrayList<BBPMF03>();
		List<BBPMF04> f04s = null;
		BBPMF02 lf02=null;
        for(int i=0;i<f13s.size();i++){
        	if (f13s.get(i).isDeleted()) 
        		continue;
        	if ((f13s.get(i).getBpm13002()!=null) &&
        		(f13s.get(i).getBpm13002().after(KC_Pub.kc_today())))
        		continue;
        	if ((f13s.get(i).getBpm13003()!=null) &&
           		(f13s.get(i).getBpm13003().before(KC_Pub.kc_today())))
           		continue;
        	if (lf02 == null){
        		lf02 = f13s.get(i).getSrc();
        		// System.out.println(lf02.getBpm02001()+"??");
        	}	
        	if (lf02==f13s.get(i).getSrc()){
        		String[] ids = f13s.get(i).getBpm13999().split(",");
        		for(String nid:ids){
        			t03 = S_BPMF03.getID(nid);
        			if (null!=t03) f03_list.add(t03); 
        		}	
        		continue;
        	}
        	f04s = lf02.getF04s();
            for(int j=0;j<f04s.size();j++){
            	if (!chkf03(arr,f04s.get(j),f03_list)) continue; 
            	JSONObject obj01 = new JSONObject();   
            	JSONObject obj02 = new JSONObject();
            	S01 = BPM_PUB.get04003(f04s.get(j).getBpm04003());
            	S02 = getprevf05(f04s.get(j));
        		obj01.put("id"           , ""+(++icount));
    			obj02.put("id"           , f04s.get(j).getId());
    			obj02.put("src02002"     , lf02.getBpm02002());
    		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04006()));		   
    		 	obj02.put("bpm04003"     , S01);
    		 	obj02.put("prev"     	 , S02);
    		 	obj02.put("bpm05002"     , f04s.get(j).getF05().getBpm05002());
    		 	obj02.put("bpm03002"     , f04s.get(j).getF03().getBpm03002());
    		 	obj02.put("bpm04001"     , f04s.get(j).getBpm04001());
    		 	obj02.put("bpm04002"     , f04s.get(j).getBpm04002());
    		 	obj02.put("bpm02002"     , f04s.get(j).getF02().getBpm02002());
    		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04004()));
        		obj01.put("cell",obj02);
        		arr.add(obj01);
            }
        	lf02=f13s.get(i).getSrc();
        	f03_list.clear();
        	// f03_list.add(f13s.get(i).getF03());
        }
    	f04s = lf02.getF04s();
        for(int j=0;j<f04s.size();j++){
        	if (!chkf03(arr,f04s.get(j),f03_list)) continue; 
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
        	S01 = BPM_PUB.get04003(f04s.get(j).getBpm04003());
        	S02 = getprevf05(f04s.get(j));
    		obj01.put("id"           , ""+(++icount));
			obj02.put("id"           , f04s.get(j).getId());			   
			obj02.put("src02002"     , lf02.getBpm02002());
		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04006()));		   
		 	obj02.put("bpm04003"     , S01);
		 	obj02.put("prev"     	 , S02);
		 	obj02.put("bpm05002"     , f04s.get(j).getF05().getBpm05002());
		 	obj02.put("bpm03002"     , f04s.get(j).getF03().getBpm03002());
		 	obj02.put("bpm04001"     , f04s.get(j).getBpm04001());
		 	obj02.put("bpm04002"     , f04s.get(j).getBpm04002());
		 	obj02.put("bpm02002"     , f04s.get(j).getF02().getBpm02002());
		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(j).getBpm04004()));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", (icount));
		D_Dao.closeSession(ss01);
		/*
		System.out.println(f13s.get(0).getSrc().getBpm02001());
		System.out.println(f02.getF1301s().size());
		*/
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void BPMPending(){
		String S01,S02;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest 	 request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		BBPMF02 f02= (BBPMF02) session.getAttribute("G_F02");
		List<BBPMF02> f02s = ss01.createQuery(IsBPMF02.sqlID)
									.setParameter("ID", f02.getId().toString()).list();
		List<BBPMF04> f04s = f02s.get(0).getF04s();
		Collections.sort(f04s,
		        new Comparator<BBPMF04>() {
		            public int compare(BBPMF04 o1, BBPMF04 o2) {
		                return o1.getBpm04006().compareTo(o2.getBpm04006());
		            }
		        });
		
		
        for(int i=0;i<f04s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
        	S01 = BPM_PUB.get04003(f04s.get(i).getBpm04003());
        	S02 = getprevf05(f04s.get(i));
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , f04s.get(i).getId());			   
		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04006()));		   
		 	obj02.put("bpm04003"     , S01);
		 	obj02.put("prev"     	 , S02);
		 	obj02.put("bpm05002"     , f04s.get(i).getF05().getBpm05002());
		 	obj02.put("bpm03002"     , f04s.get(i).getF03().getBpm03002());
		 	obj02.put("bpm04001"     , f04s.get(i).getBpm04001());
		 	obj02.put("bpm04002"     , f04s.get(i).getBpm04002());
		 	obj02.put("bpm02002"     , f04s.get(i).getF02().getBpm02002());
		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04004()));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", f04s.size());
		D_Dao.closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void BPMDisplay(){
		String S01,S02;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		IDao D_Dao = S_BPMF04.getDao();
		Session ss01 = D_Dao.openSession();
		S01 = "";
		S01 = S01 +(KC_Pub.isNull(b_03001)?"":" and f03.bpm03001 >= '"+b_03001+"'");
		S01 = S01 +(KC_Pub.isNull(e_03001)?"":" and f03.bpm03001 <= '"+e_03001+"z'");
		S01 = S01 +(KC_Pub.isNull(b_04001)?"":" and bpm04001 >= '"+b_04001+"'");
		S01 = S01 +(KC_Pub.isNull(e_04001)?"":" and bpm04001 <= '"+e_04001+"z'");
		S01 = S01 +(KC_Pub.isNull(b_04004)?"":" and bpm04004 >= :beginDate");
		S01 = S01 +(KC_Pub.isNull(e_04004)?"":" and bpm04004 <= :endDate");
		S01 = S01 +" order by bpm04004";
		Query query = ss01.createQuery(" select p from BBPMF04 p where p.deleted = false "+ S01);
		if (!KC_Pub.isNull(b_04004)) query.setDate("beginDate",KC_Pub.kc_Str2Date("yyyy/MM/dd",b_04004));     
		if (!KC_Pub.isNull(e_04004)) query.setDate("endDate"  ,KC_Pub.kc_addDate(KC_Pub.kc_Str2Date("yyyy/MM/dd",e_04004),1));     
		List<BBPMF04> f04s = query.list();
		Collections.sort(f04s,
		        new Comparator<BBPMF04>() {
		            public int compare(BBPMF04 o1, BBPMF04 o2) {
		                return o1.getBpm04006().compareTo(o2.getBpm04006());
		            }
		        });
        for(int i=0;i<f04s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
        	S01 = BPM_PUB.get04003(f04s.get(i).getBpm04003());
        	S02 = getprevf05(f04s.get(i));
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , f04s.get(i).getId());			   
		 	obj02.put("bpm04006"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04006()));		   
		 	obj02.put("bpm04003"     , S01);
		 	obj02.put("prev"     	 , S02);
		 	obj02.put("bpm05002"     , (null == f04s.get(i).getF05()) ? "" : f04s.get(i).getF05().getBpm05002());
		 	obj02.put("bpm03002"     , f04s.get(i).getF03().getBpm03002());
		 	obj02.put("bpm04001"     , f04s.get(i).getBpm04001());
		 	obj02.put("bpm04002"     , f04s.get(i).getBpm04002());
		 	obj02.put("bpm02002"     , f04s.get(i).getF02().getBpm02002());
		 	obj02.put("bpm04004"     , KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f04s.get(i).getBpm04004()));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", f04s.size());
		
		D_Dao.closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	
	@Override
    public  String execute()throws Exception{
		if (action == null) action = "";
		if (action.equals("BPMCancel")) {
			BPMCancel();
		}
		if (action.equals("BPMPending")) {
			BPMPending();
		}
		if (action.equals("BPMProxy")) {
			BPMProxy();
		}
		if (action.equals("BPMLeave")) {
			BPMLeave();
		}
		
		if (action.equals("BPMDisplay")) {
			BPMDisplay();
		}
	return null;
	}
}
