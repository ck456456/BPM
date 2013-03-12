package tw.com.prodisc.bpm.action;

import com.opensymphony.xwork2.ActionSupport;

public class ASYS01 extends ActionSupport{
	private static final long serialVersionUID = 8119487494263809150L;
	private String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String execute(){
		if (action == null) action = "SYS";  
		if (action.equals("SYSF01")){
			return "SYSF01";
		}
		return action;
	}

}
