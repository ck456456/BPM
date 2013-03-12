package tw.com.prodisc.bpm.json;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF06;
import tw.com.prodisc.bpm.bean.BBPMF12;
import tw.com.prodisc.bpm.bean.BBPMP01;
import tw.com.prodisc.bpm.form.IsForm;
import tw.com.prodisc.bpm.service.IsBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF04;
import tw.com.prodisc.bpm.util.BPM_PUB;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPM000 extends ActionSupport{
	private static final long serialVersionUID = -7916425633858276572L;
	private String status;
	private String json;
	private String f05id; 
	private String f04id; 
	private String formid;
	private String tempath;
	@SuppressWarnings("rawtypes")
	private IsBPMF03 S_BPMF03;
	@SuppressWarnings("rawtypes")
	private IsBPMF04 S_BPMF04;
	private JavaMailSender mailSender;
	
	public String getTempath() {
		return tempath;
	}

	public void setTempath(String tempath) {
		this.tempath = tempath;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public String getF05id() {
		return f05id;
	}

	public void setF05id(String f05id) {
		this.f05id = f05id;
	}
	
	public String getF04id() {
		return f04id;
	}

	public void setF04id(String f04id) {
		this.f04id = f04id;
	}
	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	@SuppressWarnings("rawtypes")
	public IsBPMF04 getS_BPMF04() {
		return S_BPMF04;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF04(IsBPMF04 s_BPMF04) {
		S_BPMF04 = s_BPMF04;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF03 getS_BPMF03() {
		return S_BPMF03;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF03(IsBPMF03 s_BPMF03) {
		S_BPMF03 = s_BPMF03;
	}
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status == null) status = "";
		this.status = status.toUpperCase();
	}
	public String sendOK(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		JSONObject oret = new JSONObject();
		oret.put("status" , "ok");
		KC_Pub.kc_JSONSnd(oret.toString(),response);
		return status;
		/*
		JSONObject o1 = (JSONObject) JSONSerializer.toJSON(json);
		Iterator i = o1.keys();
		while (i.hasNext()) {
	        try {
	            String key = i.next().toString();
	            System.out.println(o1.getString(key));
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }
	    */		
		// o1.get(key)
	}
	
	private void mailHTMLSend(String S01,String S02,String S03) {
		MimeMessage mailHTML = mailSender.createMimeMessage(); 
        MimeMessageHelper HTMLHelper = new MimeMessageHelper(mailHTML);
        try {
        	HTMLHelper.setTo(S01);
        	HTMLHelper.setFrom("86407@prodisc.com.tw"); 
        	HTMLHelper.setSubject(S02);
        	HTMLHelper.setText(S03,true);
	        mailSender.send(mailHTML);
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
	
	public void retMail(BBPMF04 f04){
		String mTo,mSubject,mText;
		mTo 	 = f04.getF02().getBpm02001()+"@prodisc.com.tw";
		mSubject = "退單通知 : "+f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03002()+"  單號 :"+f04.getBpm04001()+"  主旨 :"+f04.getBpm04002();
		mText	 = "<a href='http://172.16.11.16:8081/BPM/Default.action?formID=WorkFlow.action?action=main%26f04_id="+f04.getId()+"%26status=cancel'>執行退單</a>";
		mailHTMLSend(mTo,mSubject,mText);
	}

	@SuppressWarnings("unchecked")
	public void retBox(){
		int total=0;
		String S01,S02,S03,S04;
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		Session ss01 = S_BPMF04.getDao().openSession();
		List<BBPMF04> f04 = ss01.createQuery(IsBPMF04.sqlID)
						.setParameter("ID", f04id).list();
		List<BBPMF05> f05s = f04.get(0).getF05s();
		Collections.sort(f05s,
		        new Comparator<BBPMF05>() {
		            public int compare(BBPMF05 o1, BBPMF05 o2) {
		                return o1.getBpm05001().compareTo(o2.getBpm05001());
		            }
		        });
		
        for(int i=0;i<f05s.size();i++){
        	if (3==f05s.get(i).getBpm05003()) continue; 
        	if (2!=f05s.get(i).getBpm05004()) continue;
            total++;
        	
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
        	S01 = BPM_PUB.get05003(f05s.get(i).getBpm05003());
        	S02 = "--";
        	S03 = "--";
        	S04 = "--";
    		List<BBPMF12> f12s = f05s.get(i).getF12s();
            for(int j=0;j<f12s.size();j++){
            	if (0!=f12s.get(j).getBpm12001()) continue;
        		List<BBPMF06> f06s = f12s.get(j).getF06s();
                for(int k=0;k<f06s.size();k++){
                	if (2!=f06s.get(k).getBpm06001()) continue;
                	S02 = f06s.get(k).getF02().getBpm02001()+"("+f06s.get(k).getF02().getBpm02002()+")";
                	S03 = KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f06s.get(k).getBpm06004());
                	S04 = f06s.get(k).getBpm06002();
                }
            }
    		obj01.put("id"		, ""+i);
			obj02.put("id"		, f05s.get(i).getId());
			obj02.put("bpm05001", f05s.get(i).getBpm05001()); // 關號 
			obj02.put("bpm05003", S01); // 屬性 
			obj02.put("bpm02001", S02); // 簽核人員 
			obj02.put("bpm06004", S03); // 簽核時間 
			obj02.put("bpm06002", S04); // 簽核意見 
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", total);
		S_BPMF04.getDao().closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IsForm getProc(BBPMF03 f03){
		try {
			ServletActionContext.getRequest().setAttribute("R_F03", f03);
			Class c;
			c = Class.forName(f03.getBpm03998());
			Class[] params = new Class[1];
			params[0] = HttpServletRequest.class;
			Constructor constructor = c.getConstructor(params);
			Object[] paramObjs = new Object[1];
			paramObjs[0] = ServletActionContext.getRequest();
			return (IsForm)constructor.newInstance(paramObjs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** 發出已簽核  mail
	 * @param bpm : 表單物件
	 */
	@SuppressWarnings("unchecked")
	public void mailApprove(BBPMF04 tf04){
		String mTo,mSubject,mText;
		HttpServletRequest  request = ServletActionContext.getRequest();
		
		Session ss01 = S_BPMF04.getDao().openSession();
		List<BBPMF04> f04s = ss01.createQuery(IsBPMF04.sqlID)
				.setParameter("ID", tf04.getId().toString())
				.list();
		BBPMF04 f04 = f04s.get(0);
		
		List<BBPMF02> f02s = f04.getF02s();
		for (int i=0;i<f02s.size();i++){
			mTo 	 = f02s.get(i).getBpm02001()+"@prodisc.com.tw";
			mSubject = "簽核 : "+f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03002()+"  單號 :"+f04.getBpm04001()+"  主旨 :"+f04.getBpm04002();
			mText	 = "<a href='http://"+request.getServerName()+":"+request.getServerPort()+"/BPM/Default.action?formID=WorkFlow.action?action=main%26f04_id="+f04.getId()+"%26status=approve'>進入簽核</a>";
			mailHTMLSend(mTo,mSubject,mText);
		}
		BBPMF05 f05 = f04.getF05().getF05l();
		while ((null != f05) && (3==f05.getBpm05003())){
			for (int i=0;i<f05.getF12s().size();i++){
				if (0!=f05.getF12s().get(i).getBpm12001()) continue;
				List<BBPMF06> f06s = f05.getF12s().get(i).getF06s();
				for (int j=0;j<f06s.size();j++){
					mTo 	 = f06s.get(i).getF02().getBpm02001()+"@prodisc.com.tw";
					mSubject = "通知 : "+f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03002()+"  單號 :"+f04.getBpm04001()+"  主旨 :"+f04.getBpm04002();
					mText	 = "<a href='http://"+request.getServerName()+":"+request.getServerPort()+"/BPM/Default.action?formID=WorkFlow.action?action=main%26f04_id="+f04.getId()+"%26status=display'>查詢檢視</a>";
					mailHTMLSend(mTo,mSubject,mText);
				}
			}
			f05 = f05.getF05l();
		}
		S_BPMF04.getDao().closeSession(ss01);
	}
	/**
	 * 搬移附件路徑
	 */
	private void attaremove(BBPMF04 f04){
		ServletContext pp = ServletActionContext.getServletContext();
		File root = new File(pp.getRealPath(pp.getInitParameter("upload folder")));
		if (!root.exists()) root.mkdir();
		File dir01 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+f04.getF03().getBpm03001()));
		if (!dir01.exists()) dir01.mkdir();
		File dir02 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+f04.getF03().getBpm03001()+"\\"+f04.getBpm04001()));
		if (!dir02.exists()) dir02.mkdir();
		
		File sdir = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\uploadtmp\\"+tempath));
		if (!sdir.exists()) return;
		File[] files = sdir.listFiles();  
		for (int fileInList = 0; fileInList < files.length; fileInList++)
		{	
			File ff=files[fileInList];
			if (!ff.exists() || !ff.isFile()) continue;
			String nn = ff.toString();
		    int sep = nn.lastIndexOf('\\');
			KC_Pub.kc_copyFile(ff, new File(dir02.getPath()+"\\"+nn.substring(sep + 1)));
		}
		for (int fileInList = 0; fileInList < files.length; fileInList++)
			files[fileInList].delete();
		sdir.delete();
		// System.out.println(tempath);
	}
	public  String execute()throws Exception{
		if (status.equals("CREATE")) { // 新建
			BBPMF03 f03 = S_BPMF03.get001(formid);
			IsForm proc = getProc(f03);
			BBPMP01 fm = proc.createEnter((JSONObject) JSONSerializer.toJSON(json),f03);
			attaremove(fm.getF04());
			// mailApprove(fm.getF04());
			sendOK();
		}
		if (status.equals("APPROVE") || status.equals("EXECUTE")) { // 簽核 || 處理執行
			BBPMF04 f04 = S_BPMF04.getID(f04id);
			IsForm proc = getProc(f04.getF03());
			BBPMP01 fm = proc.approveEnter((JSONObject) JSONSerializer.toJSON(json),f04);
			// mailApprove(fm.getF04());
			sendOK();
		}
		if (status.equals("DENY")) {	// 不准
			BBPMF04 f04 = S_BPMF04.getID(f04id);
			IsForm proc = getProc(f04.getF03());
			proc.denyEnter((JSONObject) JSONSerializer.toJSON(json),f04);
			sendOK();
		}
		if (status.equals("RETBOX")) {	// 可退關列表
			retBox();
		}
		if (status.equals("RETMAIL")) { // 通知抽單
			BBPMF04 f04 = S_BPMF04.getID(f04id);
			retMail(f04);
			sendOK();
		}
		if (status.equals("CANCEL")) {  // 抽單
			BBPMF04 f04 = S_BPMF04.getID(f04id);
			IsForm proc = getProc(f04.getF03());
			proc.cancelEnter((JSONObject) JSONSerializer.toJSON(json),f04);
			sendOK();
		}
		if (status.equals("RETURN")) {  // 退單
			BBPMF04 f04 = S_BPMF04.getID(f04id);
			IsForm proc = getProc(f04.getF03());
			proc.returnEnter((JSONObject) JSONSerializer.toJSON(json),f04,f05id);
			sendOK();
		}
		return null;
	}
}