package tw.com.prodisc.bpm.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.prodisc.bpm.dao.IDao;

import tw.com.prodisc.util.KC_Pub;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import tw.com.prodisc.bpm.bean.BBPMF07;
import tw.com.prodisc.bpm.bean.BBPMF08;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF07;

import com.opensymphony.xwork2.ActionSupport;

public class JBPMF07 extends ActionSupport{
	private static final long serialVersionUID = 2849610697375144558L;
	private String action;
	private String Msg;
	private String upid;
	
	private Integer page = 1;  	// 第幾頁
    private Integer total = 1; 	// 共幾頁
    private Integer rp = 10;    // 每頁顯示

	private BBPMF07 f07 = new BBPMF07();
	
	private String j_f08;
	
	@SuppressWarnings("rawtypes")
	private IsBPMF07 S_BPMF07;
	@SuppressWarnings("rawtypes")
	private IsBPMF02 S_BPMF02;
	
	@SuppressWarnings("rawtypes")
	public IsBPMF02 getS_BPMF02() {
		return S_BPMF02;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF02(IsBPMF02 s_BPMF02) {
		S_BPMF02 = s_BPMF02;
	}
	public String getJ_f08() {
		return j_f08;
	}
	public void setJ_f08(String j_f08) {
		this.j_f08 = j_f08;
	}
	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
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

	public BBPMF07 getF07() {
		return f07;
	}

	public void setF07(BBPMF07 f07) {
		this.f07 = f07;
	}

	@SuppressWarnings("rawtypes")
	public IsBPMF07 getS_BPMF07() {
		return S_BPMF07;
	}

	public void setS_BPMF07(@SuppressWarnings("rawtypes") IsBPMF07 s_BPMF07) {
		S_BPMF07 = s_BPMF07;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void T01(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		StringBuffer SB01 = new StringBuffer();
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		total = S_BPMF07.getCount();
		List<BBPMF07> f07s = S_BPMF07.getMAPlst(SB01);
		request.setAttribute("upid"  , SB01);
		upid = SB01.toString();
        for(int i=0;i<f07s.size();i++){
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"			, ""+(i+1));
			obj02.put("id"			, ((Map)f07s.get(i)).get("id"));			   
		 	obj02.put("bpm07001"    , ((Map)f07s.get(i)).get("bpm07001"));		   
		 	obj02.put("bpm07002"	, ((Map)f07s.get(i)).get("bpm07002"));		   
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
		IDao D_Dao = S_BPMF07.getDao();
		Session ss01 = D_Dao.openSession();
		
		List<BBPMF07> f07s = 
				ss01.createQuery(" select p from BBPMF07 p where lower(p.id) = lower(:ID) ")
				.setParameter("ID", upid)
				.list();
		BBPMF07 f07 = f07s.get(0);
		
		JSONObject obj=new JSONObject();
		JSONArray  arr = new JSONArray();
        for(int i=0;i<f07.getF08s().size();i++){
        	if (f07.getF08s().get(i).isDeleted()) continue;
        	JSONObject obj01 = new JSONObject();   
        	JSONObject obj02 = new JSONObject();   
    		obj01.put("id"           , ""+(++rowid));
			obj02.put("id"           , f07.getF08s().get(i).getId());			   
		 	obj02.put("bpm08002"     , f07.getF08s().get(i).getF02().getId());
		 	obj02.put("bpm08002_n"	 , f07.getF08s().get(i).getF02().getBpm02001()+"-"+
		 							   f07.getF08s().get(i).getF02().getBpm02002());		   
		 	obj02.put("bpm08003"    , KC_Pub.kc_Date2Str("yyyy/MM/dd",f07.getF08s().get(i).getBpm08003()));		   
		 	obj02.put("bpm08004"    , f07.getF08s().get(i).getBpm08004());		   
    		obj01.put("cell",obj02);
    		arr.add(obj01);
        }
		obj.put("rows" , arr);
		obj.put("page" , getPage());
		obj.put("total", rowid);
		obj.put("jqcmd", "");
		
		obj.put("bpm07001", f07.getBpm07001());   
		obj.put("bpm07002", f07.getBpm07002());   
		obj.put("bpm07003", f07.getBpm07003());   
		obj.put("bpm07004", f07.getBpm07004());   
		obj.put("id", 		f07.getId());
		KC_Pub.kc_JSONSnd(obj.toString(),response);
		D_Dao.closeSession(ss01);
	}
	
	@SuppressWarnings("unchecked")
	public void add(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BBPMF07 t07 = new BBPMF07();
		t07.setBpm07001(f07.getBpm07001());
		t07.setBpm07002(f07.getBpm07002());
		t07.setBpm07003(f07.getBpm07003());
		t07.setBpm07004(f07.getBpm07004());
		t07.setDateCreated(new Date());
		t07.setIdCreated((Integer)session.getAttribute("G_ID"));

		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f08);
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			BBPMF08 t08 = new BBPMF08();
			t08.setF02(S_BPMF02.getID(o2.getString("bpm08002")));
			t08.setBpm08003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm08003")));
			t08.setBpm08004(o2.getString("bpm08004"));
			t07.getF08s().add(t08);
		}
		this.S_BPMF07.save(t07);
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
		JSONArray Array = (JSONArray) JSONSerializer.toJSON(j_f08);
		BBPMF07 t07 = new BBPMF07();
		
		t07 = S_BPMF07.getID(upid);
		t07.setBpm07001(f07.getBpm07001());
		t07.setBpm07002(f07.getBpm07002());
		t07.setBpm07003(f07.getBpm07003());
		t07.setBpm07004(f07.getBpm07004());
		
		for (int i = 0; i < t07.getF08s().size() ; i++){
        	if (t07.getF08s().get(i).isDeleted()) continue;
			G_id = t07.getF08s().get(i).getId();
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
			t07.getF08s().get(i).setDateDeleted(new Date());
			t07.getF08s().get(i).setIdDeleted((Integer)session.getAttribute("G_ID"));
			t07.getF08s().get(i).setDeleted(true);
		}
		j01 = t07.getF08s().size();
		for (int i = 0; i < Array.size(); i++){
			JSONObject o1 = Array.getJSONObject(i);
			JSONObject o2 = o1.getJSONObject("cell");
			J_id = o2.getInt("id");
			flag = 0;
			for (int j = 0; j < j01 ; j++){
	        	if (t07.getF08s().get(j).isDeleted()) continue;
				G_id = t07.getF08s().get(j).getId();
				if (J_id == G_id){ 
					flag = 1;
					mid  = j;
					break; // continue; 
				}	
			}
			switch (flag){
			case 0: {   // 新增
					BBPMF08 t08 = new BBPMF08();
					
					t08.setF02(S_BPMF02.getID(o2.getString("bpm08002")));
					t08.setBpm08003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm08003")));
					t08.setBpm08004(o2.getString("bpm08004"));
					t08.setDateCreated(new Date());
					t08.setIdCreated((Integer)session.getAttribute("G_ID"));
					t07.getF08s().add(t08);
					break;
					}
			default:{	// 修改
					t07.getF08s().get(mid).setF02(S_BPMF02.getID(o2.getString("bpm08002")));
					t07.getF08s().get(mid).setBpm08003(KC_Pub.kc_Str2Date("yyyy/MM/dd",o2.getString("bpm08003")));
					t07.getF08s().get(mid).setBpm08004(o2.getString("bpm08004"));
					t07.getF08s().get(mid).setDateModified(new Date());
					t07.getF08s().get(mid).setIdModified((Integer)session.getAttribute("G_ID"));
					break;
					}
			}
		}
		this.S_BPMF07.save(t07);
		Msg = "修改完成 .....";
	}
	
	@SuppressWarnings("unchecked")
	public void del(){
		BBPMF07 t07 = new BBPMF07();
		t07 = S_BPMF07.getID(upid);
        this.S_BPMF07.delete(t07);
		Msg = "刪除完成 .....";
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
		return null;
	}
}
