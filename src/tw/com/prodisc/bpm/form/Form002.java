package tw.com.prodisc.bpm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMP01;
import tw.com.prodisc.bpm.service.IsForm002;

import net.sf.json.JSONObject;

public class Form002 extends Form000 implements IsForm {
	@SuppressWarnings("rawtypes")
	public IsForm002 S_Form002;
	
	@SuppressWarnings("rawtypes")
	public Form002(HttpServletRequest req){
		request = req;
		session = req.getSession();
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext()
	                        );
		S_Form002	= (IsForm002)	context.getBean("Form002");
	}
	
	public void createComeIn(BBPMF03 f03){
		super.createComeIn(f03);
	}
	public BBPMP01 createEnter(JSONObject obj,BBPMF03 f03) {
		return null;
	}
	
	public void approveComeIn(BBPMF04 f04){}
	public BBPMP01 approveEnter(JSONObject obj,BBPMF04 f04){
		return null;
	}
	
	public BBPMP01 denyEnter(JSONObject obj,BBPMF04 f04){
		return null;
	}
	public BBPMP01 returnEnter(JSONObject obj,BBPMF04 f04,String f05id){
		return null;
	}
	
	public void cancelComeIn(BBPMF04 f04){}
	public BBPMP01 cancelEnter(JSONObject obj,BBPMF04 f04){
		return null;
	}
	
	public void displayComeIn(BBPMF04 f04){}
}
