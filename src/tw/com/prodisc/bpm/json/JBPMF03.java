package tw.com.prodisc.bpm.json;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF03;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPMF03 extends ActionSupport{
	private static final long serialVersionUID = -7964565080021119832L;
	private String action;
	private String upid;
	private String Msg;
    
	private Integer page = 1;  	// 第幾頁
    private Integer rp=20; 		// 每頁顯示
    private Integer total; 		// 共幾頁
	private String sortname="bpm02001";
	private String sortorder="asc";
	private String query="";
	private String qtype="bpm03001";
    
	private String bpm03101;
	private String bpm03102;
	private String bpm03103;
	
	private BBPMF03 f03 = new BBPMF03();
    
	@SuppressWarnings("rawtypes")
	private IsBPMF03 S_BPMF03;
	
	// ----------------------------------------------
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRp() {
		return rp;
	}
	public void setRp(Integer rp) {
		this.rp = rp;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getBpm03102() {
		return bpm03102;
	}
	public void setBpm03102(String bpm03102) {
		this.bpm03102 = bpm03102;
	}
	public String getBpm03101() {
		return bpm03101;
	}
	public void setBpm03101(String bpm03101) {
		this.bpm03101 = bpm03101;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF03 getS_BPMF03() {
		return S_BPMF03;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF03(IsBPMF03 s_BPMF03) {
		S_BPMF03 = s_BPMF03;
	}

	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
	}

	public BBPMF03 getF03() {
		return f03;
	}
	public void setF03(BBPMF03 f03) {
		this.f03 = f03;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	@SuppressWarnings("unchecked")
	public void add(){
		f03.setBpm03101((getBpm03101()!=null));
		f03.setBpm03102((getBpm03102()!=null));
		f03.setBpm03103((getBpm03103()!=null));
		this.S_BPMF03.create(f03);
		Msg = "新增完成 .....";
		
	}
	
	@SuppressWarnings("unchecked")
	public void del() {
		BBPMF03 tf03 = new BBPMF03();
		tf03 = this.S_BPMF03.getID(f03.getId().toString());
		this.S_BPMF03.del_T(tf03);
		// this.S_BPMF03.delete(tf03);
		Msg = "刪除完成 .....";
	}
	@SuppressWarnings("unchecked")
	public void mdy() {
		BBPMF03 tf03 = new BBPMF03();
		tf03 = S_BPMF03.getID(upid);
		tf03.setBpm03001(f03.getBpm03001()); 
		tf03.setBpm03002(f03.getBpm03002()); 
		tf03.setBpm03003(f03.getBpm03003()); 
		// tf03.setBpm03101(f03.isBpm03101());
		tf03.setBpm03101((getBpm03101()!=null));
		tf03.setBpm03102((getBpm03102()!=null));
		tf03.setBpm03103((getBpm03103()!=null));
		tf03.setBpm03998(f03.getBpm03998()); 
		tf03.setBpm03999(f03.getBpm03999()); 
		this.S_BPMF03.save(tf03);
		Msg = "修改完成 .....";
	}
	
	public void T02(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		JSONObject obj=new JSONObject();
		setF03(S_BPMF03.getID(upid));
		obj.put("id"		, f03.getId());
		obj.put("bpm03001"	, f03.getBpm03001());   
		obj.put("bpm03002"	, f03.getBpm03002());    
		obj.put("bpm03003"	, f03.getBpm03003());
		obj.put("bpm03101"	, f03.isBpm03101());
		obj.put("bpm03102"	, f03.isBpm03102());
		obj.put("bpm03103"	, f03.isBpm03103());
		obj.put("bpm03998"	, f03.getBpm03998());
		obj.put("bpm03999"	, f03.getBpm03999());
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings("unchecked")
	public void T01(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		setTotal(S_BPMF03.getCount());
		List<BBPMF03> f03s = S_BPMF03.getAll(SB01);
		request.setAttribute("upid"  , SB01);
		
        for(int i=0;i<f03s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , f03s.get(i).getId());			   
		 	obj02.put("bpm03001"     , f03s.get(i).getBpm03001());		   
		 	obj02.put("bpm03002"     , f03s.get(i).getBpm03002());		 
		 	obj02.put("bpm03003"     , f03s.get(i).getBpm03003());		 
		 	obj02.put("bpm03101"     , f03s.get(i).isBpm03101());		 
		 	obj02.put("bpm03102"     , f03s.get(i).isBpm03102());		 
		 	obj02.put("bpm03103"     , f03s.get(i).isBpm03103());		 
		 	obj02.put("bpm03999"     , f03s.get(i).getBpm03999());		  
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", getTotal());
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	} 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f03box(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletResponse  response = ServletActionContext.getResponse();
		total = S_BPMF03.getBoxCount(qtype, query);
		List<BBPMF03> f03s = S_BPMF03.getBox(((page-1)*rp),rp,qtype,query,sortname,sortorder,SB01);
		upid = SB01.toString();
		// total = f02s.size();
		
        for(int i=0;i<f03s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , ((Map)f03s.get(i)).get("id"));			   
		 	obj02.put("bpm03001"     , ((Map)f03s.get(i)).get("bpm03001"));		   
		 	obj02.put("bpm03002"     , ((Map)f03s.get(i)).get("bpm03002"));		   
		 	obj02.put("bpm03003"     , ((Map)f03s.get(i)).get("bpm03003"));		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", getTotal());
		
		obj.put("jqid" , upid);
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@Override
    public  String execute()throws Exception{
		setMsg("");
		if (action == null) action = "";
		if (action.equals("f03box")) {
			f03box();
		}
		if (action.equals("T01")) {
			T01();
		}
		if (action.equals("T02")) {
			T02();
		}
		if (action.equals("add")){
			add();
			T01();
		}
		if (action.equals("mdy")){
			mdy();
			T01();
		}
		if (action.equals("del")){
			del();
			T01();
		}	
		return null;
	}
	public String getBpm03103() {
		return bpm03103;
	}
	public void setBpm03103(String bpm03103) {
		this.bpm03103 = bpm03103;
	}
}
