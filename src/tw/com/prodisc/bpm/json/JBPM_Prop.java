package tw.com.prodisc.bpm.json;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF06;
import tw.com.prodisc.bpm.bean.BBPMF08;
import tw.com.prodisc.bpm.bean.BBPMF12;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF04;
import tw.com.prodisc.bpm.util.BPM_PUB;
import tw.com.prodisc.util.KC_Pub;

import com.opensymphony.xwork2.ActionSupport;

public class JBPM_Prop extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String action;
	private String status;
	private String f04id; 
	private String rpath;
	private String rext;
	private String tempath;
	
	private String fileDataFileName;
	private String fileDataContentType;
	private File   fileData;
	@SuppressWarnings("rawtypes")
	private IsBPMF04 S_BPMF04;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTempath() {
		return tempath;
	}
	public void setTempath(String tempath) {
		this.tempath = tempath;
	}
	public String getFileDataFileName() {
		return fileDataFileName;
	}
	public void setFileDataFileName(String fileDataFileName) {
		this.fileDataFileName = fileDataFileName;
	}
	public String getFileDataContentType() {
		return fileDataContentType;
	}
	public void setFileDataContentType(String fileDataContentType) {
		this.fileDataContentType = fileDataContentType;
	}
	public File getFileData() {
		return fileData;
	}
	public void setFileData(File fileData) {
		this.fileData = fileData;
	}
	public String getRext() {
		return rext;
	}
	public void setRext(String rext) {
		this.rext = rext;
	}
	public String getRpath() {
		return rpath;
	}
	public void setRpath(String rpath) {
		this.rpath = rpath;
	}
	@SuppressWarnings("rawtypes")
	public IsBPMF04 getS_BPMF04() {
		return S_BPMF04;
	}
	@SuppressWarnings("rawtypes")
	public void setS_BPMF04(IsBPMF04 s_BPMF04) {
		S_BPMF04 = s_BPMF04;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		if (action == null) action = "";
		this.action = action.toUpperCase();
	}
	public String getF04id() {
		return f04id;
	}
	public void setF04id(String f04id) {
		this.f04id = f04id;
	}
	
	private JSONArray getTreeF06s(List<BBPMF06> f06s,int i01){
		JSONArray  arr00 = new JSONArray();
		for (int i=0;i<f06s.size();i++){
			JSONObject obj90=new JSONObject();
			JSONObject obj91=new JSONObject();
			obj91.put("title" 	, f06s.get(i).getF02().getBpm02001()+
								"("+f06s.get(i).getF02().getBpm02002()+(null==f06s.get(i).getF02().getBpm02004()?"":"-<font color=\"red\">已離職-代理人"+f06s.get(i).getF02().getF02_AGENT().getBpm02001()+"</font>")+
								") : <font color=\"blue\">"+BPM_PUB.get06001(i01,f06s.get(i))+
								(KC_Pub.isNull(f06s.get(i).getBpm06002())?"":"<b> 意見 : "+f06s.get(i).getBpm06002()+"</b>")+"</font>");
			obj91.put("icon" 	, "images/16x16/People_035.gif");
			obj90.put("data" 	, obj91);
			/*
			if (!KC_Pub.isNull(f06s.get(i).getBpm06002())){
		    	obj90.put("state", "open");
				JSONArray  arr02 = new JSONArray();
				JSONObject obj80=new JSONObject();
				JSONObject obj81=new JSONObject();
				obj81.put("title" 	, "意見 : "+f06s.get(i).getBpm06002());
				obj80.put("data" 	, obj81);
		    	arr02.add(obj80);
		    	obj90.put("children", arr02);
			}
			*/
			arr00.add(obj90);
		}
		return arr00;
	}
	private JSONArray getTreeF08s(List<BBPMF08> f08s){
		JSONArray  arr00 = new JSONArray();
		for (int i=0;i<f08s.size();i++){
			JSONObject obj90=new JSONObject();
			JSONObject obj91=new JSONObject();
			obj91.put("title" 	, f08s.get(i).getF02().getBpm02001()+"("+f08s.get(i).getF02().getBpm02002()+(null==f08s.get(i).getF02().getBpm02004()?"":"-<font color=\"red\">已離職-代理人"+f08s.get(i).getF02().getF02_AGENT().getBpm02001()+"</font>")+") : 待簽核");
			obj91.put("icon" 	, "images/16x16/People_035.gif");
			obj90.put("data" 	, obj91);
			arr00.add(obj90);
		}
		return arr00;
	}
	private JSONArray getTreeF12(BBPMF05 f05){
		JSONArray  arr00 = new JSONArray();
		if (0 == f05.getF12s().size()) return arr00;
		JSONObject obj90=new JSONObject();
		JSONObject obj91=new JSONObject();
		JSONObject obj92=new JSONObject();
		
		BBPMF12 f12 = null;
		for (int i=0;i<f05.getF12s().size();i++)
			if (0==f05.getF12s().get(i).getBpm12001())
				f12 = f05.getF12s().get(i);
		if (null == f12) return arr00;
		
		if (null != f12.getF07()){
			obj91.put("title" 	, f12.getF07().getBpm07001()+"-"+f12.getF07().getBpm07002());
			obj91.put("icon" 	, "images/16x16/group.gif");
			obj90.put("data" 	, obj91);
			obj92.put("rel"   	, "f07");
			obj90.put("attributes",obj92);
			JSONArray arr02 = getTreeF06s(f12.getF06s(),f05.getBpm05003());
		    if (arr02.size() != 0){
		    	obj90.put("state", "open");
		    	obj90.put("children", arr02);
		    }	
			arr00.add(obj90);
		}
		if (null == f12.getF07()) 
			return getTreeF06s(f12.getF06s(),f05.getBpm05003());
		return arr00;
	}
	private JSONArray getTreeF07(BBPMF05 f05){
		JSONArray  arr00 = new JSONArray();
		if (0 != f05.getF12s().size()) return arr00;
		JSONObject obj90=new JSONObject();
		JSONObject obj91=new JSONObject();
		JSONObject obj92=new JSONObject();
		if (null != f05.getF07()) { // 如果有組群
			obj91.put("title" 	, f05.getF07().getBpm07001()+"-"+f05.getF07().getBpm07002());
			obj91.put("icon" 	, "images/16x16/group.gif");
			obj90.put("data" 	, obj91);
			// obj92.put("id"   	, "f07_"+f05.getId());
			obj92.put("rel"   	, "f07");
			obj90.put("attributes",obj92);
			JSONArray arr02 = getTreeF08s(f05.getF07().getF08s());
		    if (arr02.size() != 0){
		    	obj90.put("state", "open");
		    	obj90.put("children", arr02);
		    }	
			arr00.add(obj90);
		}
		if (null != f05.getF02()) { // 如果有人員
			obj91.put("title" 	, f05.getF02().getBpm02001()+"("+f05.getF02().getBpm02002()+(null==f05.getF02().getBpm02004()?"":"-<font color=\"red\">已離職-代理人"+f05.getF02().getF02_AGENT().getBpm02001()+")</font>")+") : 待簽核"); 
			obj91.put("icon" 	, "images/16x16/People_035.gif");
			obj90.put("data" 	, obj91);
			arr00.add(obj90);
		}
		return arr00;
	}
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private void treeFlow(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		JSONArray  arr00 = new JSONArray();
		IDao D_Dao = S_BPMF04.getDao();
		Session ss01 = D_Dao.openSession();
		List<BBPMF04> f04s = ss01.createQuery(IsBPMF04.sqlID)
					.setParameter("ID", f04id).list();
		List<BBPMF05> f05s = f04s.get(0).getF05s();
		Collections.sort(f05s,
		        new Comparator<BBPMF05>() {
		            public int compare(BBPMF05 o1, BBPMF05 o2) {
		                return o1.getBpm05001().compareTo(o2.getBpm05001());
		            }
		        });
		for (int i=0; i < f05s.size();i++){
			JSONObject obj90=new JSONObject();
			JSONObject obj91=new JSONObject();
			JSONObject obj92=new JSONObject();
			obj91.put("title" 	, f05s.get(i).getBpm05001()+"-<b>"+f05s.get(i).getBpm05002()+"</b>("+BPM_PUB.get05004(f05s.get(i).getBpm05003(),f05s.get(i).getBpm05004())+")");
			obj91.put("icon" 	, BPM_PUB.jpg05003(f05s.get(i).getBpm05003()));
			obj90.put("data" 	, obj91);
			obj92.put("id"   	, "emp_"+f05s.get(i).getId());
			obj92.put("rel"   	, "emp");
			obj90.put("attributes",obj92);
			JSONArray arr99 = getTreeF07(f05s.get(i));
		    if (arr99.size() != 0){
		    	obj90.put("state", "open");
		    	obj90.put("children", arr99);
		    }	
			arr99 = getTreeF12(f05s.get(i));
		    if (arr99.size() != 0){
		    	obj90.put("state", "open");
		    	obj90.put("children", arr99);
		    }	
			arr00.add(obj90);
		}
		KC_Pub.kc_JSONSnd(arr00.toString(),response);
		D_Dao.closeSession(ss01);
	}
	@SuppressWarnings("rawtypes")
	private void history(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		String S1 = "";
		int i01=1;
		JSONArray  arr00 = new JSONArray();
		List<BBPMF06> f06s = new ArrayList<BBPMF06>();
		IDao D_Dao = S_BPMF04.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF04 f04 = (BBPMF04) ss01.createQuery(IsBPMF04.sqlID)
					.setParameter("ID", f04id).list().get(0);
		for (BBPMF12 f12 : f04.getF12s()) 
			for (BBPMF06 f06 : f12.getF06s())
				f06s.add(f06);
		/*
		for (BBPMF05 f05 : f04.getF05s()) {
			for (BBPMF12 f12 : f05.getF12s()) {
				BBPMF12 f12t;
				if (0 == f12.getBpm12001())
					for (BBPMF06 f06 : f12.getF06s()){
						f06s.add(f06);
					} 
				if (1 == f12.getBpm12001()){
					f12t = f12;
					do{
						for (BBPMF06 f06 : f12t.getF06s()) f06s.add(f06);
						f12t = 	f12t.getF12n();						
					}while ((null != f12t));
				}
			}	
		}
		*/
		Collections.sort(f06s,
		        new Comparator<BBPMF06>() {
		            public int compare(BBPMF06 o1, BBPMF06 o2) {
		            	int i = o1.getBpm06003().compareTo(o2.getBpm06003());
		            	if (0==i) 
		            		i = o1.getF12().getBpm12002().compareTo(o2.getF12().getBpm12002());
		                return i;
		            }
		        });
		S1 = "";
		for (BBPMF06 f06 : f06s) {
			JSONObject obj90=new JSONObject();
			obj90.put("rowspan" ,1);
			obj90.put("bpm12001", (0==f06.getF12().getBpm12001())?"有效":"無效");
			obj90.put("bpm12002", (S1!=f06.getF12().getBpm12002())?f06.getF12().getBpm12002()+":"+f06.getF12().getBpm12003():"");
			obj90.put("f02",f06.getF02().getBpm02001()+"("+f06.getF02().getBpm02002()+")");
			obj90.put("bpm06001",BPM_PUB.get06001(f06.getF12().getBpm12004(),f06));
			obj90.put("bpm06007",f06.getBpm06007());
			obj90.put("bpm06003",KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f06.getBpm06003()));
			obj90.put("bpm06004",KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",f06.getBpm06004()));
			obj90.put("bpm06002",f06.getBpm06002());
			S1 = f06.getF12().getBpm12002(); 
			arr00.add(obj90);
		}
		for (int i=(arr00.size()-1);i>=0;i--){
			S1 = (String)((JSONObject)arr00.get(i)).get("bpm12002");
			if (S1 != "") {
				((JSONObject)arr00.get(i)).put("rowspan", i01);
				i01 = 1;
			}
			else{
				((JSONObject)arr00.get(i)).put("rowspan", 0);
				i01++;
			}  
		}	
		for (int i=0;i<arr00.size();i++){
			// System.out.println(((JSONObject)arr00.get(i)).get("rowspan")+":"+((JSONObject)arr00.get(i)).get("bpm12002"));
		}	
		KC_Pub.kc_JSONSnd(arr00.toString(),response);
		D_Dao.closeSession(ss01);
	}
	@SuppressWarnings("rawtypes")
	private void attaDisplay(){
		JSONObject obj = new JSONObject();
		JSONArray  arr = new JSONArray();
		HttpServletResponse  response = ServletActionContext.getResponse();
		
		String s01 = "uploadtmp";
		String s02 = tempath;
		if (status.toUpperCase().equals("CREATE")) {
			s01 = "uploadtmp";
			s02 = tempath;
		} else {
			IDao D_Dao = S_BPMF04.getDao();
			Session ss01 = D_Dao.openSession();
			BBPMF04 f04 = (BBPMF04) ss01.createQuery(IsBPMF04.sqlID)
						.setParameter("ID", f04id).list().get(0);
			s01 = f04.getF03().getBpm03001();
			s02 = f04.getBpm04001();
			D_Dao.closeSession(ss01);
		}
		
		ServletContext pp = ServletActionContext.getServletContext();
		File root = new File(pp.getRealPath(pp.getInitParameter("upload folder")));
		if (!root.exists()) root.mkdir();
		File dir01 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+s01));
		if (!dir01.exists()) dir01.mkdir();
		File dir02 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+s01+"\\"+s02));
		
		int i=0;
		if (dir02.exists()) {
			File[] files = dir02.listFiles();  
			for (int fileInList = 0; fileInList < files.length; fileInList++)
			{	
				File ff=files[fileInList];
				if (!ff.exists() || !ff.isFile()) continue;
				String nn = ff.toString();
				int dot = nn.lastIndexOf('.');
			    int sep = nn.lastIndexOf('\\');
			    Long lastModified = ff.lastModified();
			    Date date = new Date(lastModified);
				
	        	JSONObject obj01 = new JSONObject();   
	        	JSONObject obj02 = new JSONObject();
	    		obj01.put("id"		, (++i)+"");
				obj02.put("name"	, nn.substring(sep + 1, sep<dot?dot:nn.length()));			   
				obj02.put("ext"		, sep<dot?nn.substring(dot + 1):"");			   
				obj02.put("size"	, ff.length());			   
				obj02.put("date"	, KC_Pub.kc_Date2Str("yyyy/MM/dd",date));
				obj02.put("rpath"	, nn);
	    		obj01.put("cell",obj02);
	    		arr.add(obj01);
			}
		}
		obj.put("rows" , arr);
		obj.put("total", i);
		KC_Pub.kc_JSONSnd(obj.toString(),response);
	}
	public static boolean kc_appSnd(byte[] bytes,HttpServletResponse  response) {
		try {
			response.setContentType( "application/octet-stream" );
			DataOutput output = new DataOutputStream( response.getOutputStream() );
			response.setContentLength(bytes.length);
			for( int i = 0; i < bytes.length; i++ ) { 
				output.writeByte( bytes[i] );
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void attaDownLoad(){
		KC_Pub.kc_filedown(rpath,ServletActionContext.getResponse(),ServletActionContext.getRequest());
	}
	private void attaDelete(){
    	try{
    		HttpServletResponse  response = ServletActionContext.getResponse();
    		JSONObject obj = new JSONObject();
    		File file = new File(rpath);
    		obj.put("Msg" , file.delete() ? "succeed":"failed");
    		KC_Pub.kc_JSONSnd(obj.toString(),response);
    	}catch(Exception e){
    		e.printStackTrace();
    	}	
    }
	@SuppressWarnings("rawtypes")
	private void attaUpload(){
		ServletContext pp = ServletActionContext.getServletContext();
		if (status.toUpperCase().equals("CREATE")) {
			File root = new File(pp.getRealPath(pp.getInitParameter("upload folder")));
			if (!root.exists()) root.mkdir();
			File dir01 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\uploadtmp"));
			if (!dir01.exists()) dir01.mkdir();
			File dir02 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\uploadtmp\\"+tempath));
			if (!dir02.exists()) dir02.mkdir();
			KC_Pub.kc_copyFile(fileData, new File(dir02.getPath()+"\\"+fileDataFileName));
			return;
		}
		IDao D_Dao = S_BPMF04.getDao();
		Session ss01 = D_Dao.openSession();
		BBPMF04 f04 = (BBPMF04) ss01.createQuery(IsBPMF04.sqlID)
					.setParameter("ID", f04id).list().get(0);
		
		File root = new File(pp.getRealPath(pp.getInitParameter("upload folder")));
		if (!root.exists()) root.mkdir();
		File dir01 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+f04.getF03().getBpm03001()));
		if (!dir01.exists()) dir01.mkdir();
		File dir02 = new File(pp.getRealPath(pp.getInitParameter("upload folder")+"\\"+f04.getF03().getBpm03001()+"\\"+f04.getBpm04001()));
		if (!dir02.exists()) dir02.mkdir();
		
		KC_Pub.kc_copyFile(fileData, new File(dir02.getPath()+"\\"+fileDataFileName));
		/*
		System.out.println(dir02.toString());
		System.out.println(dir02.getPath()+"\\"+fileDataFileName);
		System.out.println(dir02.getName());
		System.out.println(fileData.getName());
		System.out.println(fileData.length());
		System.out.println(fileDataFileName);
		System.out.println(fileDataContentType);
		*/
		D_Dao.closeSession(ss01);
	}
	public  String execute()throws Exception{
		if (action.equals("TREEFLOW")) {  //
			treeFlow();
		}
		if (action.equals("HISTORY")) {  // 
			history();
		}
		if (action.equals("ATTADISPLAY")) {
			attaDisplay();
		}
		if (action.equals("ATTADOWNLOAD")) {
			attaDownLoad();
		}
		if (action.equals("ATTAUPLOAD")) {
			attaUpload();
		}
		if (action.equals("ATTADELETE")) {
			attaDelete();
		}
		
		return null;
	}
}
