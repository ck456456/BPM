package tw.com.prodisc.bpm.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import tw.com.prodisc.bpm.bean.BBPMF10;
import tw.com.prodisc.bpm.bean.BBPMF09;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF09;
import tw.com.prodisc.bpm.service.IsSYSF01;
import tw.com.prodisc.util.KC_Pub;


import com.opensymphony.xwork2.ActionSupport;

public class JBPMF09 extends ActionSupport{
	private static final long serialVersionUID = -3239999780149836522L;
	private String action;
	private String Msg;
	private String upid;
	
	private Integer page = 1;  	// 第幾頁
    private Integer total = 1; 	// 共幾頁
    private Integer rp = 10;    // 每頁顯示
	private String query;
	private String qtype;
	private String sortname;
	private String sortorder;

	private BBPMF09 f09 = new BBPMF09();
	
	private String j_f10;

	@SuppressWarnings("rawtypes")
	private IsBPMF09 S_BPMF09;
	@SuppressWarnings("rawtypes")
	private IsSYSF01 S_SYSF01;
	
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
	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRp() {
		return rp;
	}
	public void setRp(Integer rp) {
		this.rp = rp;
	}
	public BBPMF09 getF09() {
		return f09;
	}
	public void setF09(BBPMF09 f09) {
		this.f09 = f09;
	}
	public String getJ_f10() {
		return j_f10;
	}
	public void setJ_f10(String j_f10) {
		this.j_f10 = j_f10;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF09 getS_BPMF09() {
		return S_BPMF09;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF09(IsBPMF09 s_BPMF09) {
		S_BPMF09 = s_BPMF09;
	}
	@SuppressWarnings("rawtypes")
	public IsSYSF01 getS_SYSF01() {
		return S_SYSF01;
	}
	@SuppressWarnings("rawtypes")
	public void setS_SYSF01(IsSYSF01 s_SYSF01) {
		S_SYSF01 = s_SYSF01;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void T01(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = S_BPMF09.getCount();
		List<BBPMF09> f09s = S_BPMF09.getMAPlst(SB01);
		request.setAttribute("upid"  , SB01);
		upid = SB01.toString();
        for(int i=0;i<f09s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"			, ""+(i+1));
			obj02.put("id"			, ((Map)f09s.get(i)).get("id"));			   
		 	obj02.put("bpm09001"    , ((Map)f09s.get(i)).get("bpm09001"));		   
		 	obj02.put("bpm09002"	, ((Map)f09s.get(i)).get("bpm09002"));		   
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void T02(){
		int rowid = 0;
		HttpServletResponse  response = ServletActionContext.getResponse();
		IDao D_Dao = S_BPMF09.getDao();
		Session ss01 = D_Dao.openSession();
		
		List<BBPMF09> f09s = 
				ss01.createQuery(" select p from BBPMF09 p where lower(p.id) = lower(:ID) ")
				.setParameter("ID", upid)
				.list();
		BBPMF09 f09 = f09s.get(0);
		
		JSONObject obj=new JSONObject();
		JSONArray  arr = new JSONArray();
        for(int i=0;i<f09.getF10s().size();i++){
        	if (f09.getF10s().get(i).isDeleted()) continue;
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+(++rowid));
			obj02.put("id"           , f09.getF10s().get(i).getId());			   
		 	obj02.put("bpm10002"     , f09.getF10s().get(i).getSf01().getId());
		 	obj02.put("bpm10002_n"	 , f09.getF10s().get(i).getSf01().getSys01004());
		 	obj02.put("bpm10003"    , KC_Pub.kc_Date2Str("yyyy/MM/dd",f09.getF10s().get(i).getBpm10003()));		   
		 	obj02.put("bpm10004"    , f09.getF10s().get(i).getBpm10004());		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", rowid);
		obj.put("jqcmd", "");
		
		obj.put("bpm09001", f09.getBpm09001());   
		obj.put("bpm09002", f09.getBpm09002());   
		obj.put("bpm09003", f09.getBpm09003());   
		obj.put("bpm09004", f09.getBpm09004());   
		obj.put("id", 		f09.getId());
		
		KC_Pub.kc_JSONSnd(obj.toString(),response);
		D_Dao.closeSession(ss01);
	}
	@SuppressWarnings("unchecked")
	public void add(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BBPMF09 t09 = new BBPMF09();
		t09.setBpm09001(f09.getBpm09001());
		t09.setBpm09002(f09.getBpm09002());
		t09.setBpm09003(f09.getBpm09003());
		t09.setBpm09004(f09.getBpm09004());
		t09.setDateCreated(new Date());
		t09.setIdCreated((Integer)session.getAttribute("G_ID"));

		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f10);
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			BBPMF10 t10 = new BBPMF10();
			t10.setSf01(S_SYSF01.getID(o2.getString("bpm10002")));
			t10.setBpm10003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm10003")));
			t10.setBpm10004(o2.getString("bpm10004"));
			t09.getF10s().add(t10);
		}
		this.S_BPMF09.save(t09);
		Msg = "新增完成 .....";
	}
	
	@SuppressWarnings("unchecked")
	public void mdy(){
		int flag = 0; // 0:新增 1:修改 2:刪除
		int mid = -1;
		int j01;
		int J_id , G_id;
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f10);
		BBPMF09 t09 = new BBPMF09();
		
		t09 = S_BPMF09.getID(upid);
		t09.setBpm09001(f09.getBpm09001());
		t09.setBpm09002(f09.getBpm09002());
		t09.setBpm09003(f09.getBpm09003());
		t09.setBpm09004(f09.getBpm09004());
		
		for (int i = 0; i < t09.getF10s().size() ; i++){
        	if (t09.getF10s().get(i).isDeleted()) continue;
			G_id = t09.getF10s().get(i).getId();
			flag = 2;
			for (int j = 0; j < Array.size(); j++){
				JSONObject o1 = Array.getJSONObject(j);
				JSONObject o2 = o1.getJSONObject("cell");
				J_id = o2.getInt("id");
				if (J_id == G_id){ 
					flag = 0;
					break; // continue; 
				}	
			}
			if (flag != 2) continue;
			// 刪除
			t09.getF10s().get(i).setDateDeleted(new Date());
			t09.getF10s().get(i).setIdDeleted((Integer)session.getAttribute("G_ID"));
			t09.getF10s().get(i).setDeleted(true);
		}
		j01 = t09.getF10s().size();
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			J_id = o2.getInt("id");
			flag = 0;
			for (int j = 0; j < j01 ; j++){
	        	if (t09.getF10s().get(j).isDeleted()) continue;
				G_id = t09.getF10s().get(j).getId();
				if (J_id == G_id){ 
					flag = 1;
					mid = j;
					break; // continue; 
				}	
			}
			switch (flag){
			case 0: {   // 新增
					BBPMF10 t10 = new BBPMF10();
					
					t10.setSf01(S_SYSF01.getID(o2.getString("bpm10002")));
					t10.setBpm10003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm10003")));
					t10.setBpm10004(o2.getString("bpm10004"));
					t10.setDateCreated(new Date());
					t10.setIdCreated((Integer)session.getAttribute("G_ID"));
					t09.getF10s().add(t10);
					break;
					}
			default:{	// 修改
					t09.getF10s().get(mid).setSf01(S_SYSF01.getID(o2.getString("bpm10002")));
					t09.getF10s().get(mid).setBpm10003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm10003")));
					t09.getF10s().get(mid).setBpm10004(o2.getString("bpm10004"));
					t09.getF10s().get(mid).setDateModified(new Date());
					t09.getF10s().get(mid).setIdModified((Integer)session.getAttribute("G_ID"));
					break;
					}
			}
		}
		this.S_BPMF09.save(t09);
		Msg = "修改完成 .....";
	}
	
	@SuppressWarnings("unchecked")
	public void del(){
		BBPMF09 t09 = new BBPMF09();
		t09 = S_BPMF09.getID(upid);
        this.S_BPMF09.delete(t09);
		Msg = "刪除完成 .....";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void box(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		total = S_BPMF09.getBoxCount(qtype, query);
		List<BBPMF09> f09s = S_BPMF09.getBox(((page-1)*rp),rp,qtype,query,sortname,sortorder);
		
        for(int i=0;i<f09s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , ((Map)f09s.get(i)).get("id"));			   
		 	obj02.put("bpm09001"     , ((Map)f09s.get(i)).get("bpm09001"));		   
		 	obj02.put("bpm09002"     , ((Map)f09s.get(i)).get("bpm09002"));		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", getTotal());
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	
	@Override
    public  String execute()throws Exception{
		Msg = "";
		if (action == null) action = "";
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
		if (action.equals("box")) {
			box();
		}
		return null;
	}
}
