package tw.com.prodisc.bpm.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.bpm.service.IsBPMF01;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.util.KC_Pub;

public class Listen_001 extends TimerTask{
	private ServletContext context = null;
	private static Date l_001_SCHEDULE = KC_Pub.kc_Str2Date("HH:mm:ss", "13:00:50");

	public Listen_001(ServletContext context) {
		this.context = context;
	}
	private Date addScheduleDate(Date d01){
		Calendar c01 = Calendar.getInstance();
		String s01= KC_Pub.kc_Date2Str("yyyyMMdd",new Date())+
					KC_Pub.kc_Date2Str(" HH:mm:ss",d01);
		c01.setTime(KC_Pub.kc_Str2Date("yyyyMMdd HH:mm:ss",s01));
		c01.add(Calendar.DATE, 1);
		return c01.getTime(); 
	}
	@SuppressWarnings( { "rawtypes", "unchecked"})
	private void setF01(IsBPMF01 mF01,IsBPMF02 mF02,List<Map> depts){// 設定部門關係
		List<BBPMF01> f01s = mF01.getAll();
		for (Map row: (List<Map>) depts){
			BBPMF01 t01 = null;
			BBPMF01 t02 = null;
			BBPMF02 t03 = null;
			boolean bb = false;
			
			if (null==row.get("DEP_DISABLE")) row.put("DEP_DISABLE","Y");
			if (null!=row.get("PARENT_NO")&&(0 == ((Integer)row.get("PARENT_NO")))) row.put("PARENT_NO",null);
			for (BBPMF01 f01 : (List<BBPMF01>) f01s){
				if ((Integer)row.get("DEP_NO")==f01.getBpm01999()) {
					t01 = f01;
					if (null == row.get("DEP_CHIEF") && null != t01.getBoss()) {bb = true; break;}
					if (null != row.get("DEP_CHIEF")){
						if (null == t01.getBoss()) {bb = true; break;}
						if (!t01.getBoss().getBpm02001().equals(row.get("DEP_CHIEF"))){bb = true; break;};
					}
					if (null == row.get("PARENT_NO") && null != t01.getF01()) {bb = true; break;}
					if (null != row.get("PARENT_NO")){
						if (null == t01.getF01()) {bb = true; break;}
						if (t01.getF01().getBpm01999() != ((Integer)row.get("PARENT_NO"))){bb = true; break;};
					}
					break;
				}
			}
			if ((!bb)||(t01==null))	 continue;
			if (!((String) row.get("DEP_DISABLE")).equals("N")) continue;
			BBPMF01 ttt = mF01.get999((Integer) row.get("DEP_NO"));
			// System.out.println("save:"+row.get("DEP_CODE")+"-"+row.get("DEP_NAME")+"-"+row.get("DEP_CHIEF")+"-"+row.get("PARENT_NO"));
			if (null != row.get("PARENT_NO")) t02 = mF01.get999((Integer) row.get("PARENT_NO"));
			if (null != row.get("DEP_CHIEF")) t03 = mF02.get001((String) row.get("DEP_CHIEF"));
			ttt.setF01(t02);
			ttt.setBoss(t03);
			mF01.save(ttt);
			// row.get("PARENT_NO")
			// row.get("DEP_CHIEF")
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setF02(IsBPMF02 mF02,IsBPMF01 mF01,List<Map> users){
		List<BBPMF02> f02s = mF02.getAll();
		for (Map row: (List<Map>) users){
			BBPMF02 t01 = null;
			boolean bb = false;
			if (null==row.get("EMPID")) row.put("EMPID","");
			for (BBPMF02 f02 : (List<BBPMF02>) f02s){
				if (((String)row.get("EMPID")).equals(f02.getBpm02001())) {
					t01 = f02;
					if (null == row.get("DEP_CHIEF") && null != t01.getF02_Boss()) {bb = true; break;}
					if (null != row.get("DEP_CHIEF")){
						if (null == t01.getF02_Boss()) {bb = true; break;}
						if (!t01.getF02_Boss().getBpm02001().equals(row.get("DEP_CHIEF"))){bb = true; break;};
					}
					if (null == row.get("AGENT") && null != t01.getF02_AGENT()) {bb = true; break;}
					if (null != row.get("AGENT")){
						if (null == t01.getF02_AGENT()) {bb = true; break;}
						if (!t01.getF02_AGENT().getBpm02001().equals(row.get("AGENT"))){bb = true; break;};
					}
					if (null == row.get("DEPT_NO") && null != t01.getDep()) {bb = true; break;}
					if (null != row.get("DEPT_NO")){
						if (null == t01.getDep()) {bb = true; break;}
						if (t01.getDep().getBpm01999() != ((Integer)row.get("DEPT_NO"))){bb = true; break;};
					}
					break;
				}
			}
			if (null != t01.getBpm02004()) continue;  
			if ((!bb)||(t01==null))	 continue;
			BBPMF02 ttt = mF02.get001((String) row.get("EMPID"));
			BBPMF01 t02 = null;
			BBPMF02 t03 = null;
			BBPMF02 t04 = null;
			// System.out.println("save:"+row.get("EMPID")+"-"+row.get("HECNAME")+"-"+row.get("DEPT_NO")+"-"+row.get("AGENT")+"-"+row.get("DEP_CHIEF"));
			if (null != row.get("DEPT_NO"))   t02 = mF01.get999((Integer) row.get("DEPT_NO"));
			if (null != row.get("AGENT"))     t03 = mF02.get001((String) row.get("AGENT"));
			if (null != row.get("DEP_CHIEF")) t04 = mF02.get001((String) row.get("DEP_CHIEF"));
			ttt.setDep(t02);
			ttt.setF02_AGENT(t03);
			ttt.setF02_Boss(t04);
			mF02.save(ttt);
		}
	}
	/**
	 * 倒入所有部門資料
	 * @param mF01  : BPMF01操作物件
	 * @param depts : 來自 HR 資料
	 */
	@SuppressWarnings( { "rawtypes", "unchecked"})
	private void appF01(IsBPMF01 mF01,List<Map> depts){
		List<BBPMF01> f01s = mF01.getAll();
		for (Map row: (List<Map>) depts){
			BBPMF01 t01 = null;
			boolean bb = false;
			if (null==row.get("DEP_CODE")) row.put("DEP_CODE","");
			if (null==row.get("DEP_NAME")) row.put("DEP_NAME","");
			if (null==row.get("DEP_DISABLE")) row.put("DEP_DISABLE","Y");
			// if (null!=row.get("PARENT_NO")&&(0 == ((Integer)row.get("PARENT_NO")))) row.put("PARENT_NO",null);
			for (BBPMF01 f01 : (List<BBPMF01>) f01s){
				if ((Integer)row.get("DEP_NO")==f01.getBpm01999()) {
					t01 = f01;
					if ((t01.isBpm01001()!=((String)row.get("DEP_DISABLE")).equals("N"))) bb = true;
					if (!t01.getBpm01002().equals((String)  row.get("DEP_CODE"))) bb = true;
					if (!t01.getBpm01003().equals((String)  row.get("DEP_NAME"))) bb = true;
					if (t01.getBpm01999()!=((Integer) row.get("DEP_NO"))) bb = true;
					break;
				}
			}
			if (null == t01) {
				BBPMF01 t02 = new BBPMF01();
				t02.setBpm01001(((String)row.get("DEP_DISABLE")).equals("N"));
				t02.setBpm01002((String) row.get("DEP_CODE"));
				t02.setBpm01003((String) row.get("DEP_NAME"));
				t02.setBpm01999((Integer) row.get("DEP_NO"));
				mF01.create(t02);
			}
			else{
				if (!bb) continue;
				BBPMF01 tt = mF01.get999((Integer) row.get("DEP_NO"));
				tt.setBpm01001(((String)row.get("DEP_DISABLE")).equals("N"));
				tt.setBpm01002((String) row.get("DEP_CODE"));
				tt.setBpm01003((String) row.get("DEP_NAME"));
				tt.setBpm01999((Integer) row.get("DEP_NO"));
				mF01.save(tt);
			}
		}
	}
	/**
	 * 倒入所有人員資料
	 * @param mF01  : BPMF02操作物件
	 * @param users : 來自 HR 資料
	 */
	@SuppressWarnings( { "rawtypes", "unchecked"})
	private void appF02(IsBPMF02 mF02,List<Map> users)
	{
		List<BBPMF02> f02s = mF02.getAll();
		for (Map row: (List<Map>) users){
			BBPMF02 t01 = null;
			boolean bb = false;
			if (null==row.get("EMPID")) row.put("EMPID","");
			if (null==row.get("HECNAME")) row.put("HECNAME","");
			if (null==row.get("GRADE")) row.put("GRADE","0");
			if (null==row.get("QUITDATE")) row.put("QUITDATE","");
			for (BBPMF02 f02 : (List<BBPMF02>) f02s){
				if (((String)row.get("EMPID")).equals(f02.getBpm02001())) {
					t01 = f02; 
					if (!t01.getBpm02001().equals((String)  row.get("EMPID"))) bb = true;
					if (!t01.getBpm02002().equals((String)  row.get("HECNAME"))) bb = true;
					if (!t01.getBpm02003().equals((String)  row.get("GRADE"))) bb = true;
					if (!KC_Pub.kc_Date2Str("yyyyMMdd",t01.getBpm02004()).equals((String) row.get("QUITDATE"))) bb = true;
					break;
				}
			}
			if (null == t01) {
				BBPMF02 t02 = new BBPMF02();
				// System.out.println("create:"+row.get("EMPID")+":"+row.get("HECNAME"));
				t02.setBpm02001((String) row.get("EMPID"));
				t02.setBpm02002((String) row.get("HECNAME"));
				t02.setBpm02003((String) row.get("GRADE"));
				t02.setBpm02004(KC_Pub.kc_Str2Date("yyyyMMdd",(String) row.get("QUITDATE")));
				mF02.create(t02);
			}
			else{
				if (!bb) continue;
				// System.out.println("save:"+row.get("EMPID")+":"+row.get("HECNAME"));
				BBPMF02 tt = mF02.getID(t01.getId()+"");
				tt.setBpm02001((String) row.get("EMPID"));
				tt.setBpm02002((String) row.get("HECNAME"));
				tt.setBpm02003((String) row.get("GRADE"));
				tt.setBpm02004(KC_Pub.kc_Str2Date("yyyyMMdd",(String) row.get("QUITDATE")));
				mF02.save(tt);
			}
		}
		return;
	}
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void delF02(IsBPMF02 mF02,List<Map> users){
		List<BBPMF02> f02s = mF02.getAll();
		for (BBPMF02 f02 : (List<BBPMF02>) f02s) {
			boolean bb = false;
			String s01 = f02.getBpm02001();
			for (Map row: (List<Map>) users){
				if (s01.equals(row.get("EMPID").toString())) bb = true;
			}
			if (!bb) {
				// System.out.println(f02.getBpm02001());
				mF02.delete(f02); 
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void delF01(IsBPMF01 mF01,List<Map> depts){
		List<BBPMF01> f01s = mF01.getAll();
		for (BBPMF01 f01 : (List<BBPMF01>) f01s){ 
			boolean bb = false;
			for (Map row: (List<Map>) depts){
				if ((Integer)row.get("DEP_NO")==f01.getBpm01999()) bb = true; 
			}
			if (!bb) {
				// System.out.println("delete:"+f01.getBpm01002()+":"+f01.getBpm01003()+f01.getBpm01999());
				mF01.delete(f01);
			}
		}	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void run() {
		if(l_001_SCHEDULE.before(new Date())) {
			l_001_SCHEDULE=addScheduleDate(l_001_SCHEDULE);
			
			KC_Pub.kc001 = 1;
			Resource res = new ClassPathResource("Spring.xml");   
		    XmlBeanFactory factory = new XmlBeanFactory(res);
			IDao bpm_Dao = (IDao) factory.getBean("dao");
			IDao hr_Dao  = (IDao) factory.getBean("hr_dao");
			IsBPMF01 mF01 = (IsBPMF01) factory.getBean("BPMF01");
			IsBPMF02 mF02 = (IsBPMF02) factory.getBean("BPMF02");
			Session ss01 = hr_Dao.openSession();
			Session ss02 = hr_Dao.openSession();
			List depts = ss01.createSQLQuery("select PARENT_NO,DEP_CODE,DEP_NAME,DEP_CHIEF,DEP_NO,DEP_DISABLE from HRUSER_DEPT_BAS Where DEP_NO is not null")// Where DEP_DISABLE = 'N'")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
					.list();
			List users = ss02.createSQLQuery("select a.EMPID,a.HECNAME,a.GRADE,a.DEPT_NO,a.QUITDATE,a.AGENT,b.DEP_CHIEF from HRUSER a,HRUSER_DEPT_BAS b Where (b.DEP_NO = a.DEPT_NO)")//HRUSERSVIEW")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
					.list();
			hr_Dao.closeSession(ss01);
			hr_Dao.closeSession(ss02);
			
			appF01(mF01,depts);     // 倒入所有部門資料
			appF02(mF02,users);		// 倒入所有人員資料
			
			setF01(mF01,mF02,depts);// 設定部門關係
			setF02(mF02,mF01,users);// 設定人員關係
			
			delF01(mF01,depts); 	// 刪除部門資料
			delF02(mF02,users); 	// 刪除人員資料
			System.out.println("-完成-");
			context.log(KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",l_001_SCHEDULE)+" | "
					+	KC_Pub.kc_Date2Str("yyyy/MM/dd HH:mm:ss",new Date()));
		}
	}
}
