package tw.com.prodisc.bpm.json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.util.CHI_PUB;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JChiBox extends ActionSupport{

	private static final long serialVersionUID = -5676470624256366601L;
	private String action;
	private String Msg;
	private String guid;
	
	private Integer page = 1;  	// 第幾頁
    private Integer total = 1; 	// 共幾頁
    private Integer rp = 10;    // 每頁顯示
	private String query;
	private String qtype;
	private String sortname;
	private String sortorder;
	
	@SuppressWarnings("rawtypes")
	private IDao   chi_dao;	

	@SuppressWarnings("rawtypes")
	public IDao getChi_dao() {
		return chi_dao;
	}
	@SuppressWarnings("rawtypes")
	public void setChi_dao(IDao chi_dao) {
		this.chi_dao = chi_dao;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
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
	
	private String getSQLdeppage(){
		String tsort = (sortorder.equals("asc")?"desc":"asc");
		String hql = 
		"SELECT * FROM ("+ 
				"SELECT TOP "+rp+" * FROM ("+
					" SELECT TOP "+((page)*rp)+" GUID,ID,Name"+ 
						" FROM CHI_Department"+ 
						" Where "+qtype+" like '%"+query+"%' "+ 
						// " and Flag = 1 "+ 
						" Order by "+sortname+" "+sortorder+
				") t1 Order by "+sortname+" "+tsort+ 
			") t2 Order by "+sortname+" "+sortorder; 
		return hql;
	}
	private String getSQLPersonpage(){
		String tsort = (sortorder.equals("asc")?"desc":"asc");
		String hql = 
		"SELECT * FROM ("+ 
				"SELECT TOP "+rp+" * FROM ("+
					" SELECT TOP "+((page)*rp)+" GUID,ID,Name"+ 
						" FROM CHI_PersonNew"+ 
						" Where "+qtype+" like '%"+query+"%' "+ 
						// " and Flag = 1 "+ 
						" Order by "+sortname+" "+sortorder+
				") t1 Order by "+sortname+" "+tsort+ 
			") t2 Order by "+sortname+" "+sortorder; 
		return hql;
	}
	private String getSQLcustpage(){
		String tsort = (sortorder.equals("asc")?"desc":"asc");
		String hql = 
		"SELECT * FROM ("+ 
			"SELECT TOP "+rp+" * FROM ("+
				" SELECT TOP "+((page)*rp)+" a.GUID,a.ID,a.Name,a.ShortName,b.BCustomerGUID"+ 
					" from CHI_Customer a left Join CHI_BCustomer b On a.GUID = b.CustomerGUID"+ 
					" Where a."+qtype+" like '%"+query+"%' "+ 
					" and a.Flag = 1 "+ 
					" Order by a."+sortname+" "+sortorder+
			") t1 Order by "+sortname+" "+tsort+ 
		") t2 Order by "+sortname+" "+sortorder; 
					
	return hql;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void departmentbox(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = chi_dao.getTotalCount("select count(*) from CHI_Department where "+qtype+" like '%"+query+"%'");
		Session ss01 = chi_dao.openSession();
		List deps = ss01.createSQLQuery(getSQLdeppage())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
		int ii=0;
        for(Map row: (List<Map>) deps){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
    		obj01.put("id"			, ""+(++ii));
			obj02.put("id"			, row.get("ID"));
			obj02.put("guid"		, row.get("GUID"));
			obj02.put("name"		, row.get("Name"));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
		}
		obj.put("rows" , arr);
		obj.put("total", total);
		obj.put("page" , getPage());
		
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void personnewbox(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = chi_dao.getTotalCount("select count(*) from CHI_PersonNew where "+qtype+" like '%"+query+"%'");
		Session ss01 = chi_dao.openSession();
		List custs = ss01.createSQLQuery(getSQLPersonpage())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
		int ii=0;
        for(Map row: (List<Map>) custs){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
    		obj01.put("id"			, ""+(++ii));
			obj02.put("id"			, row.get("ID"));
			obj02.put("guid"		, row.get("GUID"));
			obj02.put("name"		, row.get("Name"));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
		}
		obj.put("rows" , arr);
		obj.put("total", total);
		obj.put("page" , getPage());
		
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void customerbox(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = chi_dao.getTotalCount("select count(*) from CHI_Customer where "+qtype+" like '%"+query+"%'");
		Session ss01 = chi_dao.openSession();
		List custs = ss01.createSQLQuery(getSQLcustpage())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
		int ii=0;
        for(Map row: (List<Map>) custs){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();
    		obj01.put("id"			, ""+(++ii));
			obj02.put("id"			, row.get("ID"));
			obj02.put("guid"		, row.get("GUID"));
			obj02.put("shortname"	, row.get("ShortName"));
			obj02.put("name"		, row.get("Name"));
			// BGUID 為收款客戶
			obj02.put("BGUID"		, (null==row.get("BCustomerGUID"))?row.get("GUID"):row.get("BCustomerGUID"));
    		obj01.put("cell",obj02);
    		arr.add(obj01);
			System.out.println(row.get("Name"));
		}
		obj.put("rows" , arr);
		obj.put("total", total);
		obj.put("page" , getPage());
		
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void customerguid(){
		String sql ="Select a.ID as a_ID,"+
	                       "a.ShortName as a_ShortName,"+
	                       "a.Name as a_Name,"+
	                       "a.TradeCondition as a_TradeCondition,"+
	                       "a.AccountLine as a_AccountLine,"+
	                       "a.CheckLimit as a_CheckLimit,"+
	                       "b.ID as b_ID,"+
	                       "b.Name as b_Name,"+
	                       "c.ID as c_ID,"+
	                       "c.Name as c_Name "+
	                  "From CHI_Customer a,"+
	                       "CHI_PersonNew b,"+
	                       "CHI_PayType c "+
					  "Where a.PersonGUID = b.GUID"+
					   " and a.PayTypeGUID = c.GUID"+
					   " and a.GUID = '"+guid+"'";
		HttpServletResponse  response = ServletActionContext.getResponse();
		Session ss01 = chi_dao.openSession();
		List custs = ss01.createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
		
		JSONObject obj = new JSONObject();
        for(Map row: (List<Map>) custs){
        	if(row == null || row.isEmpty()) continue;
			Iterator it = row.entrySet().iterator();   
			while (it.hasNext()){   
				Map.Entry entry = (Map.Entry)it.next();   
				obj.put(entry.getKey(),entry.getValue());
			}
			break;
        }
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	private void form002ini(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		JSONObject obj = new JSONObject();
		obj.put("ClassList", CHI_PUB.getOrderClass(10));
		obj.put("Currency", CHI_PUB.getCurrency());
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	@Override
    public  String execute()throws Exception{
		Msg = "";
		if (action == null) action = "";
		if (action.equals("customerbox")) {
			customerbox();
		}
		if (action.equals("departmentbox")) {
			departmentbox();
		}
		if (action.equals("personnewbox")) {
			personnewbox();
		}
		if (action.equals("customerguid")) {
			customerguid();
		}
		if (action.equals("form002ini")) {
			form002ini();
		}
		return null;
	}
}
