package tw.com.prodisc.bpm.json;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tw.com.prodisc.bpm.bean.BSYSF01;
import tw.com.prodisc.bpm.service.IsSYSF01;
import tw.com.prodisc.util.KC_Pub;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class JSYSF01 extends ActionSupport{
	private static final long serialVersionUID = 8792856740846503283L;
	private Integer page = 1;  	// 第幾頁
    private Integer total; 		// 共幾頁
    private Integer rp;    		// 每頁顯示
	private String query;
	private String qtype;
	private String sortname;
	private String sortorder;
    
	private String Msg;
	private String action;
	private String upid;
	private int level = 0;
    
	@SuppressWarnings("rawtypes")
	private IsSYSF01 S_SYSF01;
	private BSYSF01 M01 = new BSYSF01();

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUpid() {
		return upid;
	}

	public void setUpid(String upid) {
		this.upid = upid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@SuppressWarnings("rawtypes")
	public IsSYSF01 getS_SYSF01() {
		return S_SYSF01;
	}

	@SuppressWarnings("rawtypes")
	public void setS_SYSF01(IsSYSF01 s_SYSF01) {
		S_SYSF01 = s_SYSF01;
	}

	public BSYSF01 getM01() {
		return M01;
	}

	public void setM01(BSYSF01 m01) {
		M01 = m01;
	}
	@SuppressWarnings("unchecked")
	public void T01(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = S_SYSF01.getMENUCount(level);
		List<BSYSF01> MENUs = S_SYSF01.getMainMenu(level,SB01);
		request.setAttribute("upid"  , SB01);
		
        for(int i=0;i<MENUs.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , MENUs.get(i).getId());			   
		 	obj02.put("sys01001"     , MENUs.get(i).getSys01001());		   
		 	obj02.put("sys01002"     , MENUs.get(i).getSys01002());		 
		 	obj02.put("sys01003"     , MENUs.get(i).getSys01003());		 
		 	obj02.put("sys01004"     , MENUs.get(i).getSys01004());		 
		 	obj02.put("sys01005"     , MENUs.get(i).getSys01005());		  
		 	obj02.put("sys01006"     , MENUs.get(i).getSys01006());		  
		 	obj02.put("DateModified" , KC_Pub.kc_Date2Str("yyyy/MM/dd",MENUs.get(i).getDateModified()));	  
		 	obj02.put("IdModified"   , MENUs.get(i).getIdModified());	 
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", getTotal());
		obj.put("level", getLevel());
		obj.put("Msg", Msg);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	} 
	
	public void T02(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		JSONObject obj=new JSONObject();
		setM01(S_SYSF01.getID(upid));
		obj.put("id"		, M01.getId());
		obj.put("level"		, M01.getSys01001());   
		obj.put("sys01001"	, M01.getSys01001());   
		obj.put("sys01002"	, M01.getSys01002());    
		obj.put("sys01003"	, M01.getSys01003());
		obj.put("sys01004"	, M01.getSys01004());
		obj.put("sys01005"	, M01.getSys01005());
		obj.put("sys01006"	, M01.getSys01006());
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	
	public void up(){
		if (level == 0) 
			Msg = "最上階 !!";
		else
		{	
			M01   = this.S_SYSF01.getID(String.valueOf(level));
			level = M01.getSys01001();
		}
	}
	
	public void dwn(){
		setM01(S_SYSF01.getID(upid));
		if (M01 == null) {
			Msg = "最下階 !!";
			return;
		}
		level = M01.getId();
	}
	
	@SuppressWarnings("unchecked")
	public void mdy() {
		BSYSF01 M02 = new BSYSF01();
		M02 = S_SYSF01.getID(upid);
		M02.setSys01001(M01.getSys01001()); 
		M02.setSys01002(M01.getSys01002()); 
		M02.setSys01003(M01.getSys01003()); 
		M02.setSys01004(M01.getSys01004()); 
		M02.setSys01005(M01.getSys01005()); 
		M02.setSys01006(M01.getSys01006()); 
		this.S_SYSF01.save(M02);
		Msg = "修改完成 .....";
	}
	
	@SuppressWarnings("unchecked")
	public void add() {
		M01.setSys01001(level);
		this.S_SYSF01.create(M01);
		Msg = "新增完成 .....";
	}
	
	@SuppressWarnings("unchecked")
	public void del() {
		BSYSF01 M02 = new BSYSF01();
		M02 = this.S_SYSF01.getID(M01.getId().toString());
		this.S_SYSF01.delete(M02);
		Msg = "刪除完成 .....";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void box() {
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = S_SYSF01.getBoxCount(qtype, query);
		List<BSYSF01> sf01s = S_SYSF01.getBox(((page-1)*rp),rp,qtype,query,sortname,sortorder);
		
        for(int i=0;i<sf01s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+i);
			obj02.put("id"           , ((Map)sf01s.get(i)).get("id"));			   
		 	obj02.put("sys01003"     , ((Map)sf01s.get(i)).get("sys01003"));		   
		 	obj02.put("sys01004"     , ((Map)sf01s.get(i)).get("sys01004"));		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", getTotal());
		obj.put("Msg", Msg);
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
		if (action.equals("box")) {
			box();
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
		if (action.equals("up")) {
			up();
			T01();
		}
		if (action.equals("dwn")) {
			dwn();
			T01();
		}
		return null;
	}
}
//http://localhost:8081/BPM/json/JSYSF01.action?action=T01&rp=10&qtype=SYSUR001&query=&sortname=SYSUR001&sortorder=asc