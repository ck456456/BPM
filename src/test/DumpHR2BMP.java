package test;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF01;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.dao.IDao;
import tw.com.prodisc.util.KC_Pub;

public class DumpHR2BMP {
	
	@SuppressWarnings({ "rawtypes"})
	public List gg(IDao dd,String ss)
	{
		Session ss01 = dd.openSession();
			List users = ss01.createSQLQuery("select EMPID,HECNAME,GRADE,DEPT_NO from HRUSERSVIEW where DEPT_NO = "+ss)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
					.list();
			dd.closeSession(ss01);
			// for (Map user : (List<Map>) users){
			 	// System.out.println("2: "+user);
			// }
			return 	users;	
	}
	
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public void setF02(IsBPMF01 mF01,IsBPMF02 mF02,Map row)
	{
		BBPMF01 t01 = null;
		BBPMF02 t02 = mF02.get001((String) row.get("EMPID"));
		if (null != row.get("DEPT_NO"))
	        t01 = mF01.get999((Integer) row.get("DEPT_NO"));
		t02.setDep(t01);
		mF02.save(t02);
	}
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public void setF01(IsBPMF01 mF01,IsBPMF02 mF02,Map row)
	{
		BBPMF01 t01 = mF01.get999((Integer) row.get("DEP_NO"));
		BBPMF01 t02 = null;
		BBPMF02 t03 = null;
		if (null != row.get("PARENT_NO")) t02 = mF01.get999((Integer) row.get("PARENT_NO"));
		if (null != row.get("DEP_CHIEF")) t03 = mF02.get001((String) row.get("DEP_CHIEF"));
		
		if (null != t02) t01.setF01(t02);
		// setBpm01001(t02.getId());
		t01.setBoss(t03);
		mF01.save(t01);
	}
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public void appF01(IsBPMF01 mF01,Map row)
	{
		BBPMF01 t01 = mF01.get999((Integer) row.get("DEP_NO"));
		if (t01 == null) {
			BBPMF01 f01 = new BBPMF01();
			f01.setBpm01002((String) row.get("DEP_CODE"));
			f01.setBpm01003((String) row.get("DEP_NAME"));
			f01.setBpm01999((Integer) row.get("DEP_NO"));
			mF01.create(f01);
			return;
		}
		t01.setBpm01002((String) row.get("DEP_CODE"));
		t01.setBpm01003((String) row.get("DEP_NAME"));
		t01.setBpm01999((Integer) row.get("DEP_NO"));
		mF01.save(t01);
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void delF02(IsBPMF02 mF02,List<Map> users){
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
	public void delF01(IsBPMF01 mF01,List<Map> depts){
		List<BBPMF01> f01s = mF01.getAll();
		for (BBPMF01 f01 : (List<BBPMF01>) f01s){ 
			boolean bb = false;
			for (Map row: (List<Map>) depts){
				if ((Integer)row.get("DEP_NO")==f01.getBpm01999()) bb = true; 
			}
			if (!bb) mF01.delete(f01);
		}	
	}
	
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public void appF02(IsBPMF02 mF02,IsBPMF01 mF01,Map row)
	{
		BBPMF02 t02 = mF02.get001((String) row.get("EMPID"));
		
		if (t02 == null) {
			BBPMF02 f02 = new BBPMF02();
			f02.setBpm02001((String) row.get("EMPID"));
			f02.setBpm02002((String) row.get("HECNAME"));
			f02.setBpm02003((String) row.get("GRADE"));
			mF02.create(f02);
			return;
		}
		// System.out.println(row.get("DEPT_NO"));
		t02.setBpm02001((String) row.get("EMPID"));
		t02.setBpm02002((String) row.get("HECNAME"));
		t02.setBpm02003((String) row.get("GRADE"));
		mF02.save(t02);
		return;
	}
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		// System.out.println(System.getProperty("java.class.path"));
		KC_Pub.kc001 = 1;
		Resource res = new ClassPathResource("Spring.xml");   
	    XmlBeanFactory factory = new XmlBeanFactory(res);
		DumpHR2BMP d = new DumpHR2BMP();
		IDao bpm_Dao = (IDao) factory.getBean("dao");
		IDao hr_Dao  = (IDao) factory.getBean("hr_dao");
		IsBPMF01 mF01 = (IsBPMF01) factory.getBean("BPMF01");
		IsBPMF02 mF02 = (IsBPMF02) factory.getBean("BPMF02");
                   
		Session ss01 = hr_Dao.openSession();
		Session ss02 = hr_Dao.openSession();
		List depts = ss01.createSQLQuery("select PARENT_NO,DEP_CODE,DEP_NAME,DEP_CHIEF,DEP_NO from HRUSER_DEPT_BAS Where DEP_DISABLE = 'N'")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		List users = ss02.createSQLQuery("select EMPID,HECNAME,GRADE,DEPT_NO from HRUSERSVIEW")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		hr_Dao.closeSession(ss01);
		hr_Dao.closeSession(ss02);
		
		for (Map row : (List<Map>) depts) d.appF01(mF01,row);     // 倒入所有部門資料
		for (Map row : (List<Map>) users) d.appF02(mF02,mF01,row);// 倒入所有人員資料
		
		for (Map row : (List<Map>) depts) d.setF01(mF01,mF02,row);// 設定部門關係
		for (Map row : (List<Map>) users) d.setF02(mF01,mF02,row);// 設定人員關係
		
		d.delF01(mF01,depts); // 刪除部門資料
		d.delF02(mF02,users);
		System.out.println(" ...ok... ");
	}
}
