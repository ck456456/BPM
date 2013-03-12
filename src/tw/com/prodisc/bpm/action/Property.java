package tw.com.prodisc.bpm.action;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF03;

import com.opensymphony.xwork2.ActionSupport;

public class Property extends ActionSupport{
	private static final long serialVersionUID = -5821632267083078331L;
	
	String action;
	String formid;
	String f04id;
	@SuppressWarnings("rawtypes")
	private IsBPMF03 S_BPMF03;
	private BBPMF03 f03 = new BBPMF03();
	
	public String getF04id() {
		return f04id;
	}
	public void setF04id(String f04id) {
		this.f04id = f04id;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF03 getS_BPMF03() {
		return S_BPMF03;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF03(IsBPMF03 s_BPMF03) {
		S_BPMF03 = s_BPMF03;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	
	public BBPMF03 getF03() {
		return f03;
	}
	public void setF03(BBPMF03 f03) {
		this.f03 = f03;
	}
	
	public String prop() {
		setF03(S_BPMF03.get001(formid));
		return action;
	}
	
	public String execute(){
		if (action.equals("Prop")) {
			return prop();
		}
		if (action.equals("TreeFlow")) {}
		return action;
	}
}
