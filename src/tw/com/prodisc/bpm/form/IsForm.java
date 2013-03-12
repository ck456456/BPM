package tw.com.prodisc.bpm.form;

import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMP01;

import net.sf.json.JSONObject;

public interface IsForm  {

	public void createComeIn(BBPMF03 f03);
	public BBPMP01 createEnter(JSONObject obj,BBPMF03 f03);
	
	public void approveComeIn(BBPMF04 f04);
	public BBPMP01 approveEnter(JSONObject obj,BBPMF04 f04);

	public void cancelComeIn(BBPMF04 f04);
	public BBPMP01 cancelEnter(JSONObject obj,BBPMF04 f04);
	
	public BBPMP01 denyEnter(JSONObject obj,BBPMF04 f04);
	public BBPMP01 returnEnter(JSONObject obj,BBPMF04 f04,String f05id);
	
	public void displayComeIn(BBPMF04 f04);
}
