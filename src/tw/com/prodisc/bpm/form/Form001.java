package tw.com.prodisc.bpm.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONObject;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMP01;
import tw.com.prodisc.bpm.bean.BForm001;
import tw.com.prodisc.bpm.bean.BTest001;
import tw.com.prodisc.bpm.service.IsForm001;
import tw.com.prodisc.bpm.util.CHI_PUB;
import tw.com.prodisc.util.KC_Pub;

public class Form001 extends Form000 implements IsForm {
	@SuppressWarnings("rawtypes")
	public IsForm001 S_Form001;
	
	@SuppressWarnings("rawtypes")
	public Form001(HttpServletRequest req){
		request = req;
		session = req.getSession();
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext()
	                        );
		S_Form001	= (IsForm001)	context.getBean("Form001");
	}
	
	private void setchkpoint(String point,BBPMP01 bpm){
		// 關號,流程角色,屬性 1=簽核(同不同意, 退單)2=辦理(退單), 3=通知
		if (point.equals("create")){
			flowRole("0100","86407",1,"0000","財務處理",bpm);
		}
		flowSort(bpm);   
	}
	
	@SuppressWarnings("unchecked")
	private BForm001 getfmbyid(BBPMF04 f04){
		Session ss01 = S_Form001.getDao().openSession();
		List<BForm001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BForm001 fm = tf.get(0); 
		S_Form001.getDao().closeSession(ss01);
		return fm;
	}
	public void createComeIn(BBPMF03 f03){
		super.createComeIn(f03);
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 createEnter(JSONObject obj,BBPMF03 f03) {
		BForm001 fm = new BForm001();
		KC_Pub.kc_json2obj(fm,"ef01",obj);
		super.createEnter(fm,obj,f03);
		setchkpoint("create",fm);
		flowSend(fm,obj);  // 會執行"建構及同意"程序
		fm = (BForm001)this.S_Form001.save(fm);
		return fm;
	}
	
	@SuppressWarnings("unchecked")
	public void approveComeIn(BBPMF04 f04){
		Session ss01 = S_Form001.getDao().openSession();
		List<BForm001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BForm001 fm = tf.get(0); 
		super.approveComeIn(fm);
		JSONObject obj = KC_Pub.kc_obj2json(fm,"ef01");
		((JSONObject)obj.get("ef01")).put("form001001_n",CHI_PUB.getChiCust(fm.getForm001001()));
		request.setAttribute("f_json",obj);
		S_Form001.getDao().closeSession(ss01);
		this.S_Form001.save(fm);
	}
	
	@SuppressWarnings("unchecked")
	public BBPMP01 approveEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Form001.getDao().openSession();
		List<BForm001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BForm001 fm = tf.get(0);
		String chkpnt = fm.getF04().getF05().getBpm05001();
		KC_Pub.kc_json2obj(fm,"ef01",obj);
		super.approveEnter(fm,obj);
		setchkpoint(f04.getF05().getBpm05001(),fm);
		flowSend(fm,obj);  // 會執行"建構及同意"程序
		S_Form001.getDao().closeSession(ss01);
		if (chkpnt.equals("0100")) {
			CHI_PUB.UpDateChiAccountLine(fm.getForm001105(),fm.getForm001001());
			// Update Chi data
		}
		this.S_Form001.save(fm);
		// 0100 財會確認後 , 將結果倒入 CHI
		return fm;
	}
	
	public void displayComeIn(BBPMF04 f04){
		BForm001 fm = getfmbyid(f04);
		super.displayComeIn(fm);
		JSONObject obj = KC_Pub.kc_obj2json(fm,"ef01");
		((JSONObject)obj.get("ef01")).put("form001001_n",CHI_PUB.getChiCust(fm.getForm001001()));
		request.setAttribute("f_json",obj);
	}
	
	@SuppressWarnings("unchecked")
	public BBPMP01 denyEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Form001.getDao().openSession();
		List<BForm001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BForm001 fm = tf.get(0); 
		flowDeny(fm,obj);  // 會執行"不同意"程序
		S_Form001.getDao().closeSession(ss01);
		this.S_Form001.save(fm);
		return fm;
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 returnEnter(JSONObject obj,BBPMF04 f04,String f05id){
		Session ss01 = S_Form001.getDao().openSession();
		List<BTest001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BTest001 fm = tf.get(0);
		flowReturn(fm,obj,f05id);  // 會執行"退件"程序
		S_Form001.getDao().closeSession(ss01);
		this.S_Form001.save(fm);
		delf05null();
		return fm;
	}
	
	public void cancelComeIn(BBPMF04 f04){
		BForm001 fm = getfmbyid(f04);
		super.cancelComeIn(fm);
		JSONObject obj = KC_Pub.kc_obj2json(fm,"ef01");
		((JSONObject)obj.get("ef01")).put("form001001_n",CHI_PUB.getChiCust(fm.getForm001001()));
		request.setAttribute("f_json",obj);
	}
	@SuppressWarnings("unchecked")
	public BBPMP01 cancelEnter(JSONObject obj,BBPMF04 f04){
		Session ss01 = S_Form001.getDao().openSession();
		List<BForm001> tf = ss01.createQuery(IsForm001.sqlF04ID)
						.setParameter("ID", f04.getId().toString()).list();
		BForm001 fm = tf.get(0); 
		flowCancel(fm,obj);  // 會執行"抽單"程序
		S_Form001.getDao().closeSession(ss01);
		this.S_Form001.save(fm);
		return fm;
	}
	
}