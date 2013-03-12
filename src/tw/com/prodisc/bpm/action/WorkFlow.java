package tw.com.prodisc.bpm.action;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.form.IsForm;
import tw.com.prodisc.bpm.service.IsBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF04;

import com.opensymphony.xwork2.ActionSupport;

public class WorkFlow extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String action = "blank";
	private String status;
	private String formid; // 表單代號
	private String formnm; // 表單名稱
	private String formsn;
	private int    f04_id;
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
	@SuppressWarnings("rawtypes")
	public IsBPMF04 getS_BPMF04() {
		return S_BPMF04;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF04(IsBPMF04 s_BPMF04) {
		S_BPMF04 = s_BPMF04;
	}
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getFormsn() {
		return formsn;
	}

	public void setFormsn(String formsn) {
		this.formsn = formsn;
	}
	
	public int getF04_id() {
		return f04_id;
	}

	public void setF04_id(int f04_id) {
		this.f04_id = f04_id;
	}
	
	public String getFormnm() {
		return formnm;
	}

	public void setFormnm(String formnm) {
		this.formnm = formnm;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IsForm getProc(BBPMF03 f03){
		try {
			ServletActionContext.getRequest().setAttribute("R_F03", f03);
			Class c = Class.forName(f03.getBpm03998());
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
	private void createComeIn(HttpServletRequest request){
		BBPMF03 f03 = S_BPMF03.get001(formid);
		request.setAttribute("f_status",status);
		IsForm proc = getProc(f03);
		proc.createComeIn(f03);
	}
	
	private void cancelComeIn(HttpServletRequest request){
		BBPMF04 f04 = S_BPMF04.getID(f04_id+"");
		request.setAttribute("f_status",status);
		IsForm proc = getProc(f04.getF03());
		proc.cancelComeIn(f04);
	}
	private void approveComeIn(HttpServletRequest request){
		BBPMF04 f04 = S_BPMF04.getID(f04_id+"");
		switch (f04.getF05().getBpm05003()){
			case 1:  setStatus("approve"); 
					 break;
			default: setStatus("execute");
		}		
		request.setAttribute("f_status",status);
		IsForm proc = getProc(f04.getF03());
		proc.approveComeIn(f04);
	}
	
	private void displayComeIn(HttpServletRequest request){
		BBPMF04 f04 = S_BPMF04.getID(f04_id+"");
		request.setAttribute("f_status",status);
		IsForm proc = getProc(f04.getF03());
		proc.displayComeIn(f04);
	}
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if (status.toLowerCase().equals("create" ))createComeIn(request);
		if (status.toLowerCase().equals("approve"))approveComeIn(request);
		if (status.toLowerCase().equals("cancel" ))cancelComeIn(request);
		if (status.toLowerCase().equals("display" ))displayComeIn(request);
		return action;
		// WorkFlow.action?action=main&formid=Test001&status=create
		// WorkFlow.action?action=main&f04_id='+upid+'&status=approve
	}

}
