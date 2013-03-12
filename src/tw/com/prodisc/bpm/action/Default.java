package tw.com.prodisc.bpm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tw.com.prodisc.bpm.bean.BSYSF01;
import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF09;
import tw.com.prodisc.bpm.bean.BBPMF10;
import tw.com.prodisc.bpm.bean.BBPMF11;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsSYSF01;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF09;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

public class Default extends ActionSupport{
	private static final long serialVersionUID = 6338362322878489171L;
	private String action;
	private String formID = "sys/blank.jsp";
	@SuppressWarnings("rawtypes")
	private IsSYSF01 S_SYSF01;
	@SuppressWarnings("rawtypes")
	private IsBPMF02 S_BPMF02;
	@SuppressWarnings("rawtypes")
	private IsBPMF09 S_BPMF09;
	List<BSYSF01> AllMenu;

	@SuppressWarnings("rawtypes")
	public IsBPMF09 getS_BPMF09() {
		return S_BPMF09;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF09(IsBPMF09 s_BPMF09) {
		S_BPMF09 = s_BPMF09;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF02 getS_BPMF02() {
		return S_BPMF02;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF02(IsBPMF02 s_BPMF02) {
		S_BPMF02 = s_BPMF02;
	}
	@SuppressWarnings("rawtypes")
	public IsSYSF01 getS_SYSF01() {
		return S_SYSF01;
	}

	@SuppressWarnings("rawtypes")
	public void setS_SYSF01(IsSYSF01 s_SYSF01) {
		S_SYSF01 = s_SYSF01;
	}
	
	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getSUBID(int I01){
		String Ret = "";
		int II = I01;
		if (II == 0) return Ret;
		// Ret = "_"+String.format("%05d", II);
		Ret = "_"+II;
		for (int i=0;i<AllMenu.size();i++){
			if (AllMenu.get(i).getId() == II){
				Ret = ((AllMenu.get(i).getSys01001() == 0) 
						? Ret.replaceFirst("_"+AllMenu.get(i).getId(), "submenu"+AllMenu.get(i).getSys01002().substring(8)) 
						: getSUBID(AllMenu.get(i).getSys01001())+Ret);
				break;
			}	
				
		}
		return Ret;
	}
	
	public void findMenu(List<BSYSF01> um,BSYSF01 sf01){
		boolean bb = true; 					 // 是否 UserMenu 已存在
		BSYSF01 t01 = null;
		if (0 == sf01.getSys01001()) return; // 檢查上階  Menu id 是否為 0 
		for(int i=0;i<um.size();i++){		 // 檢查要加入的Menu 是否 UserMenu 已存在  
			if (um.get(i).getId() == sf01.getId()) bb = false;   
		}
		for(int i=0;i<AllMenu.size();i++){
			if (bb &&(AllMenu.get(i).getId() == sf01.getId())){ // 加入 UserMenu
				um.add(AllMenu.get(i));
			}
			if (AllMenu.get(i).getId() == sf01.getSys01001())	// 取的上階  Menu 
			   t01 = AllMenu.get(i);
		}
		findMenu(um,t01);	// 再來一次
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		JSONArray  arr = new JSONArray();
		
		AllMenu 				= S_SYSF01.getAllMenu();
		List<BSYSF01> MainMenu 	= S_SYSF01.getMainMenu(0);  // 取得主要Menu 
		// List<BSYSF01> SubMenu 	= S_SYSF01.getSubMenu();
		// List<BSYSF01> UserMenu  = S_SYSF01.getSubMenu(); 
		List<BSYSF01> UserMenu  = new ArrayList<BSYSF01>(); 
		request.setAttribute("MainMenu", MainMenu);
		
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		List<BBPMF02> f02s = ss01.createQuery(
				" select p from BBPMF02 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ((BBPMF02)session.getAttribute("G_F02")).getId().toString()).list();
		if (f02s.size() > 0){ 
			BBPMF02 f02 = f02s.get(0);
			List<BBPMF11> f11s = f02.getF11s();
			for(int i=0;i<f11s.size();i++){
				if (f11s.get(i).isDeleted()) continue;
				if (f11s.get(i).getF09().isDeleted()) continue;
				List<BBPMF10> f10s = f11s.get(i).getF09().getF10s();
				for(int j=0;j<f10s.size();j++){
					if (f10s.get(j).isDeleted()) continue;
					if (f10s.get(j).getSf01().isDeleted()) continue;
					BSYSF01 sf01 = f10s.get(j).getSf01();
					findMenu(UserMenu,sf01);
				}
			}
		}
		
		List<BBPMF09> f09s = ss01.createQuery(
				" select p from BBPMF09 p "
						+ " where lower(p.bpm09001) = lower(:bpm09001) "
		                  + " and p.deleted = false ")
				.setParameter("bpm09001", "Default").list();
		if (f09s.size() > 0){ 
			List<BBPMF10> f10s = f09s.get(0).getF10s();
			for(int j=0;j<f10s.size();j++){
				if (f10s.get(j).isDeleted()) continue;
				if (f10s.get(j).getSf01().isDeleted()) continue;
				BSYSF01 sf01 = f10s.get(j).getSf01();
				findMenu(UserMenu,sf01);
			}
		}
		D_Dao.closeSession(ss01);
		for(int i=0;i<UserMenu.size();i++){
		    // System.out.println(UserMenu.get(i).getSys01004());
		}
		
		arr.clear();
		for(int i=0;i<UserMenu.size();i++){
			if (UserMenu.get(i).isDeleted()) continue;
			JSONObject obj = new JSONObject();
			// String SS = getSUBID(SubMenu.get(i).getSys01001())+"_"+String.format("%05d", SubMenu.get(i).getId()); // SubMenu.get(i).getSys01006()+"-"+
			// SS = _上階_上階 .... _上階
			String SS = getSUBID(UserMenu.get(i).getSys01001()); // SubMenu.get(i).getSys01006()+"-"+
    		obj.put("SUBID"   , SS+"_"+UserMenu.get(i).getId());
    		obj.put("Sort"    , SS+" "+UserMenu.get(i).getSys01006());
    		obj.put("SYS01001", UserMenu.get(i).getSys01001());
    		obj.put("SYS01002", UserMenu.get(i).getSys01002());
    		obj.put("SYS01003", UserMenu.get(i).getSys01003());
    		obj.put("SYS01004", UserMenu.get(i).getSys01004());
    		obj.put("SYS01005", UserMenu.get(i).getSys01005());
    		arr.add(obj);
		}
		session.setAttribute("G_SubMenu",arr);  // 將 UserMenu 送到前端
		session.setAttribute("formID"   , formID);
		return SUCCESS;
	}
	public String execute(){
		if (action == null) action = "login";
		if (formID == null) formID = "sys/blank.jsp";
		
		if (action.equals("login")){
			return login();
		}
		return "again";
	}

}
