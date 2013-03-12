package tw.com.prodisc.bpm.action;

import com.opensymphony.xwork2.ActionSupport;

public class Error extends ActionSupport{
	private static final long serialVersionUID = -2020912172631468439L;
	String url;
	String param;
	String reqtxt; 
	String textStatus; 
	String errorThrown;
	
	public String getReqtxt() {
		return reqtxt;
	}

	public void setReqtxt(String reqtxt) {
		this.reqtxt = reqtxt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getTextStatus() {
		return textStatus;
	}

	public void setTextStatus(String textStatus) {
		this.textStatus = textStatus;
	}

	public String getErrorThrown() {
		return errorThrown;
	}

	public void setErrorThrown(String errorThrown) {
		this.errorThrown = errorThrown;
	}

	public String execute(){
		return "error";
	}
}
