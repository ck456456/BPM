package tw.com.prodisc.bpm.action;

import com.opensymphony.xwork2.ActionSupport;

public class Manager extends ActionSupport{
	private static final long serialVersionUID = 119476627792479371L;
	private String action = "blank";
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String execute(){
		if (action.equals("BPMF01")) 	{}  
		if (action.equals("BPMF02")) 	{}
		if (action.equals("BPMF02v02"))	{}
		if (action.equals("BPMF03")) 	{}  
		if (action.equals("BPMF07")) 	{}
		if (action.equals("BPMF09")) 	{}
		if (action.equals("BPMF04")) 	{}
		if (action.equals("BPMF04v02")) {}
		if (action.equals("BPMF04v03")) {}
		return action;
	}
}
