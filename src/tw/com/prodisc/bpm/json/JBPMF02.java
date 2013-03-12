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

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF11;
import tw.com.prodisc.bpm.bean.BBPMF13;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF01;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF09;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPMF02 extends ActionSupport{
	private static final long serialVersionUID = -2310732060820825139L;
	private String action;
	private String Msg;
	private String upid="null";
	private String bpm02004;
	
	private Integer page = 1;  	// 第幾頁
    private Integer total; 		// 共幾頁
    private Integer rp=20; 		// 每頁顯示
	private String query="";
	private String qtype="bpm02001";
	private String sortname="bpm02001";
	private String sortorder="asc";
	
	private String j_rows;
	private String j_form;
	private String j_f11;
	private BBPMF02 f02 = new BBPMF02();
    
	@SuppressWarnings("rawtypes")
	private IsBPMF09 S_BPMF09;
	@SuppressWarnings("rawtypes")
	private IsBPMF02 S_BPMF02;
	@SuppressWarnings("rawtypes")
	private IsBPMF03 S_BPMF03;
	@SuppressWarnings("rawtypes")
	private IsBPMF01 S_BPMF01;

	public String getJ_rows() {
		return j_rows;
	}
	public void setJ_rows(String j_rows) {
		this.j_rows = j_rows;
	}
	public String getJ_form() {
		return j_form;
	}
	public void setJ_form(String j_form) {
		this.j_form = j_form;
	}
	public String getBpm02004() {
		return bpm02004;
	}
	public void setBpm02004(String bpm02004) {
		this.bpm02004 = bpm02004;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF03 getS_BPMF03() {
		return S_BPMF03;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF03(IsBPMF03 s_BPMF03) {
		S_BPMF03 = s_BPMF03;
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
	public IsBPMF01 getS_BPMF01() {
		return S_BPMF01;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF01(IsBPMF01 s_BPMF01) {
		S_BPMF01 = s_BPMF01;
	}
	public String getJ_f11() {
		return j_f11;
	}
	public void setJ_f11(String j_f11) {
		this.j_f11 = j_f11;
	}
	public BBPMF02 getF02() {
		return f02;
	}
	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}
	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF02 getS_BPMF02() {
		return S_BPMF02;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF02(IsBPMF02 s_BPMF02) {
		S_BPMF02 = s_BPMF02;
	}
	private String f13chk(BBPMF13 t13,Integer id){
		if (null == t13) return "";
		String[] ids = t13.getBpm13999().split(",");
		for(String nid:ids)
			if (id.toString().equals(nid)) return "checked = \"checked\"";   
		return "";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f13id(){
		int rowid = 0;
		HttpServletResponse  response = ServletActionContext.getResponse();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		List<BBPMF13> t13s =  ss01.createQuery(" select p from BBPMF13 p where lower(p.id) = lower(:ID) ") 
					.setParameter("ID", upid)
					.list();
		BBPMF13 t13 = t13s.size() == 0?null:t13s.get(0);
		
		List<BBPMF03> f03s = S_BPMF03.getAll(); 
		JSONObject obj=new JSONObject();
		JSONArray  arr = new JSONArray();
        for(int i=0;i<f03s.size();i++){
        	if (f03s.get(i).isDeleted()) continue;
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+(++rowid));
			obj02.put("id"           , f03s.get(i).getId());			   
		 	obj02.put("check"     	 , "<input class=\"datacb\" type=\"checkbox\" value=\""+f03s.get(i).getId()+"\" "+f13chk(t13,f03s.get(i).getId())+"/>"); // f13chk(t13,f03s.get(i).getId())
		 	obj02.put("bpm03001"     , f03s.get(i).getBpm03001());
		 	obj02.put("bpm03002"	 , f03s.get(i).getBpm03002());
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", rowid);
		obj.put("jqcmd", "");
		
		if (null != t13){
			obj.put("bpm13001", t13.getBpm13001());   
			obj.put("bpm13002", KC_Pub.kc_Date2Str("yyyy/MM/dd",t13.getBpm13002()));   
			obj.put("bpm13003", KC_Pub.kc_Date2Str("yyyy/MM/dd",t13.getBpm13003()));   
		 	obj.put("f02_id2" , t13.getF02().getId());		   
			obj.put("f02_0002", t13.getF02().getBpm02001()+"-"+t13.getF02().getBpm02002());   
			obj.put("id", 		t13.getId());
			obj.put("f02_id2",	t13.getF02().getId());
		}
		
		KC_Pub.kc_JSONSnd(obj.toString(),response);
		D_Dao.closeSession(ss01);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f13box(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest 	 request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		BBPMF02 f02= (BBPMF02) session.getAttribute("G_F02");
		List<BBPMF02> f02s = ss01.createQuery(IsBPMF02.sqlID)
									.setParameter("ID", f02.getId().toString()).list();
		List<BBPMF13> f13s = f02s.get(0).getF13s();
		/*
		Collections.sort(f13s,
		        new Comparator<BBPMF13>() {
		            public int compare(BBPMF13 o1, BBPMF13 o2) {
		                return o1.getBpm13001().compareTo(o2.getBpm13001());
		            }
		        });
		*/
		int ii=0;
        for(int i=0;i<f13s.size();i++){
        	if (f13s.get(i).isDeleted()) continue;
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
    		obj01.put("id"		, ""+(++ii));
			obj02.put("id"		, f13s.get(i).getId());
		 	obj02.put("f02_id2"	, f13s.get(i).getF02().getId());		   
		 	obj02.put("bpm13999", f13s.get(i).getBpm13999());
		 	obj02.put("bpm13001", f13s.get(i).getBpm13001());
		 	obj02.put("f13_02"	, f13s.get(i).getF02().getBpm02001()+"-"+f13s.get(i).getF02().getBpm02002());
		 	obj02.put("bpm13002", KC_Pub.kc_Date2Str("yyyy/MM/dd",f13s.get(i).getBpm13002()));		   
		 	obj02.put("bpm13003", KC_Pub.kc_Date2Str("yyyy/MM/dd",f13s.get(i).getBpm13003()));		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("total", ii);
		
		obj.put("Msg", Msg);
		D_Dao.closeSession(ss01);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f02box(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletResponse  response = ServletActionContext.getResponse();
		total = S_BPMF02.getBoxCount(qtype, query);
		List<BBPMF02> f02s = S_BPMF02.getBox(((page-1)*rp),rp,qtype,query,sortname,sortorder,SB01);
		upid = SB01.toString();
		// total = f02s.size();
		
        for(int i=0;i<f02s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , ((Map)f02s.get(i)).get("id"));			   
		 	obj02.put("bpm02001"     , ((Map)f02s.get(i)).get("bpm02001"));		   
		 	obj02.put("bpm02002"     , ((Map)f02s.get(i)).get("bpm02002"));		   
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
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		List<BBPMF02> f02s = 
				ss01.createQuery(" select p from BBPMF02 p where lower(p.id) = lower(:ID) ")
				.setParameter("ID", upid)
				.list();
		BBPMF02 f02 = f02s.get(0);
		
		JSONObject obj=new JSONObject();
		JSONArray  arr = new JSONArray();
        for(int i=0;i<f02.getF11s().size();i++){
        	if (f02.getF11s().get(i).isDeleted()) continue;
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+(++rowid));
			obj02.put("id"           , f02.getF11s().get(i).getId());			   
		 	obj02.put("bpm11002"     , f02.getF11s().get(i).getF09().getId());
		 	obj02.put("bpm11002_n"	 , f02.getF11s().get(i).getF09().getBpm09001()+"-"+
		 							   f02.getF11s().get(i).getF09().getBpm09002());
		 	obj02.put("bpm11003"    , KC_Pub.kc_Date2Str("yyyy/MM/dd",f02.getF11s().get(i).getBpm11003()));		   
		 	obj02.put("bpm11004"    , f02.getF11s().get(i).getBpm11004());		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", rowid);
		obj.put("jqcmd", "");
		
		obj.put("bpm02001", f02.getBpm02001());   
		obj.put("bpm02002", f02.getBpm02002());   
		obj.put("bpm02003", f02.getBpm02003());   
		obj.put("bpm02004", f02.getDep().getId());   
		obj.put("bpm02004_n", f02.getDep().getBpm01002()+'-'+f02.getDep().getBpm01003());   
		obj.put("id", 		f02.getId());
		
		KC_Pub.kc_JSONSnd(obj.toString(),response);
		D_Dao.closeSession(ss01);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f13del(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF13 t13 = (BBPMF13) ss01.createQuery(" select p from BBPMF13 p where lower(p.id) = lower(:ID) ") 
						.setParameter("ID", upid)
						.list()
						.get(0);
		t13.setDateDeleted(new Date());
		t13.setIdDeleted((Integer)session.getAttribute("G_ID"));
		t13.setDeleted(true);
		this.S_BPMF02.save(t13);
		D_Dao.closeSession(ss01);
		Msg = "刪除完成 .....";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void f13mdy(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF13 t13 = (BBPMF13) ss01.createQuery(" select p from BBPMF13 p where lower(p.id) = lower(:ID) ") 
						.setParameter("ID", upid)
						.list()
						.get(0);
		
		JSONObject o1 = ((JSONObject) JSONSerializer.toJSON(j_form)).getJSONObject("ef01");
		t13.setF02(S_BPMF02.getID(o1.getString("f13.f02_id2")));
		t13.setBpm13001(o1.getString("f13.bpm13001"));
		t13.setBpm13002(KC_Pub.kc_Str2Date("yyyy/MM/dd",o1.getString("f13.bpm13002")));
		t13.setBpm13003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o1.getString("f13.bpm13003")));
		t13.setBpm13999(o1.getString("f13.bpm13999"));
		t13.setDateModified(new Date());
		t13.setIdModified((Integer)session.getAttribute("G_ID"));
		this.S_BPMF02.save(t13);
		D_Dao.closeSession(ss01);
		Msg = "修改完成 .....";
	}
	@SuppressWarnings("unchecked")
	public void f13add(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BBPMF02 t02 = (BBPMF02) session.getAttribute("G_F02");
		BBPMF13 t13 = new BBPMF13();
		JSONObject o1 = ((JSONObject) JSONSerializer.toJSON(j_form)).getJSONObject("ef01");
		t13.setSrc(t02);
		t13.setBpm13001(o1.getString("f13.bpm13001"));
		t13.setBpm13002(KC_Pub.kc_Str2Date("yyyy/MM/dd",o1.getString("f13.bpm13002")));
		t13.setBpm13003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o1.getString("f13.bpm13003")));
		t13.setBpm13999(o1.getString("f13.bpm13999"));
		t13.setF02(S_BPMF02.getID(o1.getString("f13.f02_id2")));
		t13.setDateCreated(new Date());
		t13.setIdCreated(t02.getId());
		this.S_BPMF02.save(t13);
		Msg = "新增完成 .....";
	}
	@SuppressWarnings("unchecked")
	public void add(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		BBPMF02 t02 = new BBPMF02();
		t02.setBpm02001(f02.getBpm02001());
		t02.setBpm02002(f02.getBpm02002());
		t02.setBpm02003(f02.getBpm02003());
		t02.setDep     (S_BPMF01.getID(getBpm02004()));
		t02.setDateCreated(new Date());
		t02.setIdCreated((Integer)session.getAttribute("G_ID"));

		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f11);
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			BBPMF11 t11 = new BBPMF11();
			t11.setF09(S_BPMF09.getID(o2.getString("bpm11002")));
			t11.setBpm11003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm11003")));
			t11.setBpm11004(o2.getString("bpm11004"));
			t02.getF11s().add(t11);
		}
		this.S_BPMF02.save(t02);
		Msg = "新增完成 .....";
		 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void mdy(){
		int flag = 0; // 0:新增 1:修改 2:刪除
		int mid = -1;
		int j01;
		int J_id , G_id;
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f11);
		// BBPMF02 t02 = new BBPMF02();
		
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF02 t02 = (BBPMF02) ss01.createQuery(" select p from BBPMF02 p where lower(p.id) = lower(:ID) ") 
						.setParameter("ID", upid).list().get(0);
		t02.setBpm02001(f02.getBpm02001());
		t02.setBpm02002(f02.getBpm02002());
		t02.setBpm02003(f02.getBpm02003());
		t02.setDep     (S_BPMF01.getID(getBpm02004()));
		
		for (int i = 0; i < t02.getF11s().size() ; i++){
        	if (t02.getF11s().get(i).isDeleted()) continue;
			G_id = t02.getF11s().get(i).getId();
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
			t02.getF11s().get(i).setDateDeleted(new Date());
			t02.getF11s().get(i).setIdDeleted((Integer)session.getAttribute("G_ID"));
			t02.getF11s().get(i).setDeleted(true);
		}
		j01 = t02.getF11s().size();
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			J_id = o2.getInt("id");
			flag = 0;
			for (int j = 0; j < j01 ; j++){
	        	if (t02.getF11s().get(j).isDeleted()) continue;
				G_id = t02.getF11s().get(j).getId();
				if (J_id == G_id){ 
					flag = 1;
					mid = j;
					break; // continue; 
				}	
			}
			switch (flag){
			case 0: {   // 新增
					BBPMF11 t11 = new BBPMF11();
					
					t11.setF09(S_BPMF09.getID(o2.getString("bpm11002")));
					t11.setBpm11003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm11003")));
					t11.setBpm11004(o2.getString("bpm11004"));
					t02.getF11s().add(t11);
					break;
					}
			default:{	// 修改
				
					t02.getF11s().get(mid).setF09(S_BPMF09.getID(o2.getString("bpm11002")));
					t02.getF11s().get(mid).setBpm11003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm11003")));
					t02.getF11s().get(mid).setBpm11004(o2.getString("bpm11004"));
					t02.getF11s().get(mid).setDateModified(new Date());
					t02.getF11s().get(mid).setIdModified((Integer)session.getAttribute("G_ID"));
					break;
					}
			}
		}
		this.S_BPMF02.save(t02);
		D_Dao.closeSession(ss01);
		Msg = "修改完成 .....";
	}
	@SuppressWarnings("unchecked")
	public void del(){
		BBPMF02 t02 = new BBPMF02();
		t02 = S_BPMF02.getID(upid);
        this.S_BPMF02.delete(t02);
		Msg = "刪除完成 .....";
	}
	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void f13save(){
		int flag = 0; // 0:新增 1:修改 2:刪除
		int mid = -1;
		int j01;
		int J_id , G_id;
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f13);
		// BBPMF02 t02 = new BBPMF02();
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		BBPMF02 t02 = (BBPMF02) ss01.createQuery(IsBPMF02.sqlID)
									.setParameter("ID", f02.getId().toString()).list().get(0);
		// 刪除
		for (int i = 0; i < t02.getF13s().size() ; i++){
        	if (t02.getF13s().get(i).isDeleted()) continue;
			G_id = t02.getF13s().get(i).getId();
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
			BBPMF13 t13 = t02.getF13s().get(i);
			t13.setDateDeleted(new Date());
			t13.setIdDeleted((Integer)session.getAttribute("G_ID"));
			t13.setDeleted(true);
			this.S_BPMF02.save(t13);
		}
		
		j01 = t02.getF13s().size();
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			J_id = o2.getInt("id");
			flag = 0;
			for (int j = 0; j < j01 ; j++){
	        	if (t02.getF13s().get(j).isDeleted()) continue;
				G_id = t02.getF13s().get(j).getId();
				if (J_id == G_id){ 
					flag = 1;
					mid  = j;
					break; // continue; 
				}	
			}
			switch (flag){
			case 0: {   // 新增
					BBPMF13 t13 = new BBPMF13();
					t13.setSrc(t02);
					t13.setF02(S_BPMF02.getID(o2.getString("f02_id2")));
					t13.setBpm13001(o2.getString("bpm13001"));
					t13.setBpm13002(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13002")));
					t13.setBpm13003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13003")));
					t13.setDateCreated(new Date());
					t13.setIdCreated((Integer)session.getAttribute("G_ID"));
					this.S_BPMF02.save(t13);
					break;
					}
			default:{	// 修改
					BBPMF13 t13 = t02.getF13s().get(mid);
					t13.setF02(S_BPMF02.getID(o2.getString("f02_id2")));
					t13.setBpm13001(o2.getString("bpm13001"));
					t13.setBpm13002(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13002")));
					t13.setBpm13003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13003")));
					t13.setDateModified(new Date());
					t13.setIdModified((Integer)session.getAttribute("G_ID"));
					this.S_BPMF02.save(t13);
					//  *
					t02.getF13s().get(mid).setF02(S_BPMF02.getID(o2.getString("f02_id2")));
					t02.getF13s().get(mid).setF03(S_BPMF03.getID(o2.getString("f03_id")));
					t02.getF13s().get(mid).setBpm13001(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13001")));
					t02.getF13s().get(mid).setBpm13002(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm13002")));
					t02.getF13s().get(mid).setBpm13003(o2.getString("bpm13003"));
					t02.getF13s().get(mid).setDateModified(new Date());
					t02.getF13s().get(mid).setIdModified((Integer)session.getAttribute("G_ID"));
					// *
					break;
					}
			}
		}
		Msg = "完成 .....";
		D_Dao.closeSession(ss01);
	}
	*/
	@Override
    public  String execute()throws Exception{
		if (action == null) action = "";
		if (action.equals("f02box")) {
			f02box();
		}
		if (action.equals("T02")) {
			T02();
		}
		if (action.equals("add")){
			add();
			f02box();
		}	
		if (action.equals("mdy")){
			mdy();
			f02box();
		}
		if (action.equals("del")){
			del();
			f02box();
		}	
		if (action.equals("f13box")) {
			f13box();
		}
		if (action.equals("f13id")) {
			f13id();
		}
		if (action.equals("f13add")){
			f13add();
			f13box();
		}
		if (action.equals("f13mdy")){
			f13mdy();
			f13box();
		}
		if (action.equals("f13del")){
			f13del();
			f13box();
		}
		
		if (action.equals("f13save")){
			// f13save();
			f13box();
		}	
		return null;
	}
}
