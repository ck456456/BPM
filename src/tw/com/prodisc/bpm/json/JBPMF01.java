package tw.com.prodisc.bpm.json;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF01;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPMF01 extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3256199938795961842L;

	private String action;
	
	private Integer page = 1;  	// 第幾頁
    private Integer total; 		// 共幾頁
    private Integer rp;    		// 每頁顯示
	private String query;
	private String qtype;
	private String sortname;
	private String sortorder;
	
	@SuppressWarnings("rawtypes")
	private IsBPMF01 S_BPMF01;

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
	@SuppressWarnings("rawtypes")
	public IsBPMF01 getS_BPMF01() {
		return S_BPMF01;
	}

	@SuppressWarnings("rawtypes")
	public void setS_BPMF01(IsBPMF01 s_BPMF01) {
		S_BPMF01 = s_BPMF01;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	private JSONArray gettree(List<BBPMF01> f01s,List<BBPMF02> f02s){
		JSONArray  arr00 = new JSONArray();
		if (f02s != null)
		for (BBPMF02 f02 : f02s) {
			if (null!=f02.getBpm02004()) continue;
			JSONObject obj90=new JSONObject();
			JSONObject obj91=new JSONObject();
			JSONObject obj92=new JSONObject();
    		obj91.put("title" 	, f02.getBpm02001()+"("+f02.getBpm02002()+")");
    		obj91.put("icon" 	, "img/man.gif");
    		obj90.put("data" 	, obj91);
    		obj92.put("id"   	, "emp_"+f02.getId());
    		obj92.put("rel"   	, "emp");
    		obj90.put("attributes",obj92);
    		arr00.add(obj90);
		}
		for (BBPMF01 t01 : (List<BBPMF01>) f01s) {
			if (!t01.isBpm01001()) continue;
			String s01 = ""; 
			JSONObject obj00=new JSONObject();
			JSONObject obj01=new JSONObject();
			JSONObject obj02=new JSONObject();
			if (t01.getBoss() != null) s01 = " , "+t01.getBoss().getBpm02001();   
    		obj01.put("title" 	, t01.getBpm01002()+"("+t01.getBpm01003()+s01+")");
    		obj01.put("icon" 	, "img/folder.png");
    		obj00.put("data" 	, obj01);
    		obj02.put("id"   	, "dep_"+t01.getId());
    		obj02.put("rel"   	, "dep");
    		obj00.put("attributes",obj02);
    		JSONArray arr99 = gettree(t01.getF01s(),t01.getUsers());
    		if (arr99.size() != 0){
    			obj00.put("children", arr99);
    		}	
    		arr00.add(obj00);
		}
		return arr00;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void jstree(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		IDao D_Dao = S_BPMF01.getDao();
		Session ss01 = D_Dao.openSession();
		List<BBPMF01> f01s = ss01.createQuery(IsBPMF01.nullf01s).list();
		KC_Pub.kc_JSONSnd(gettree(f01s,null).toString(),response);
		D_Dao.closeSession(ss01);
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void box(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		total = S_BPMF01.getBoxCount(qtype, query);
		List<BBPMF01> f01s = S_BPMF01.getBox(((page-1)*rp),rp,qtype,query,sortname,sortorder);
		// total = f02s.size();
		
        for(int i=0;i<f01s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , ((Map)f01s.get(i)).get("id"));			   
		 	obj02.put("bpm01002"     , ((Map)f01s.get(i)).get("bpm01002"));		   
		 	obj02.put("bpm01003"     , ((Map)f01s.get(i)).get("bpm01003"));		   
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
		if (action == null) action = "";
		if (action.equals("jstree")) {
			jstree();
		}
		if (action.equals("box")) {
			box();
		}
		return null;
	}
}