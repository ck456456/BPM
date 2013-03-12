package tw.com.prodisc.bpm.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONObject;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMP01;
import tw.com.prodisc.bpm.bean.BTest001;

import tw.com.prodisc.bpm.service.IsTest001;
import tw.com.prodisc.util.KC_Pub;


public class Test001 extends Form000 implements IsForm {
	@SuppressWarnings("rawtypes")
	public IsTest001 S_Test001;
	
	@SuppressWarnings("rawtypes")
	public Test001(HttpServletRequest req){
		request = req;
		session = req.getSession();
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext()
	                        );
		S_Test001	= (IsTest001)	context.getBean("Test001");
	}
	
	private void setchkpoint(String point,BBPMP01 bpm){
		if (point.equals("create")){
			// 關號,流程角色,屬性 1=簽核(同不同意, 退單)2=辦理(退單), 3=通知
			flowBoss("0005",1,0,4    ,"0000","主管",bpm);
			flowGrup("0010","A001" ,3,"0000","處理通知",bpm);
			flowRole("0020","86407",1,"0000","處理核准",bpm);
			flowGrup("0030","A001" ,2,"0000","表單處理",bpm);
			flowRole("0040","86407",1,"0000","完成核准",bpm);
			flowRole("0050","86407",3,"0000","完成通知",bpm);
		}
		if (point.equals("0020")){
			flowGrup("0022","A001" ,1,"0020","處理核准-展開關卡22",bpm);
			flowGrup("0024","A001" ,1,"0020","處理核准-展開關卡24",bpm);
			flowRole("0026","86407",2,"0020","處理核准-展開關卡26",bpm);
		}
		if (point.equals("0022")){
			flowRole("0023","86407",1,"0022","0022-展開關卡",bpm);
		}
		if (point.equals("0024")){
			flowRole("0025","86407",1,"0024","0024-展開關卡25",bpm);
			flowRole("0027","86407",1,"0024","0024-展開關卡27",bpm);
		}
		
		flowSort(bpm);   
	}
	
	public void createComeIn(BBPMF03 f03){
		super.createComeIn(f03);
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 createEnter(JSONObject obj,BBPMF03 f03){
		BTest001 fm = new BTest001();
		KC_Pub.kc_json2obj(fm,"ef01",obj);
		super.createEnter(fm,obj,f03);
		setchkpoint("create",fm);
		flowSend(fm,obj);  // 會執行"建構及同意"程序
		fm = (BTest001)this.S_Test001.save(fm);
		
		return fm;
	}
	
	@SuppressWarnings("unchecked")
	public void cancelComeIn(BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0); 
		super.cancelComeIn(fm);
		request.setAttribute("f_json",KC_Pub.kc_obj2json(fm,"ef01"));
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 cancelEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0);
		flowCancel(fm,obj);  // 會執行"抽單"程序
		S_Test001.getDao().closeSession(ss01);
		this.S_Test001.save(fm);
		return fm;
	}
	@SuppressWarnings("unchecked")
	public void approveComeIn(BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0); 
		super.approveComeIn(fm);
		request.setAttribute("f_json",KC_Pub.kc_obj2json(fm,"ef01"));
		S_Test001.getDao().closeSession(ss01);
		this.S_Test001.save(fm);
	}

	@SuppressWarnings("unchecked")
	public BBPMP01 approveEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0); 
		KC_Pub.kc_json2obj(fm,"ef01",obj);
		super.approveEnter(fm,obj);
		setchkpoint(f04.getF05().getBpm05001(),fm);
		flowSend(fm,obj);  // 會執行"建構及同意"程序
		// mailApprove(fm);
		S_Test001.getDao().closeSession(ss01);
		this.S_Test001.save(fm);
		return fm;
	}
	
	@SuppressWarnings("unchecked")
	public BBPMP01 denyEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0);
		flowDeny(fm,obj);  // 會執行"不同意"程序
		S_Test001.getDao().closeSession(ss01);
		this.S_Test001.save(fm);
		return fm;
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 returnEnter(JSONObject obj,BBPMF04 f04,String f05id){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0);
		flowReturn(fm,obj,f05id);  // 會執行"退件"程序
		S_Test001.getDao().closeSession(ss01);
		this.S_Test001.save(fm);
		delf05null();
		return fm;
	}
	
	@SuppressWarnings("unchecked")
	public void displayComeIn(BBPMF04 f04){
		Session ss01 = S_Test001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsTest001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0); 
		super.displayComeIn(fm);
		request.setAttribute("f_json",KC_Pub.kc_obj2json(fm,"ef01"));
		S_Test001.getDao().closeSession(ss01);
	}
}