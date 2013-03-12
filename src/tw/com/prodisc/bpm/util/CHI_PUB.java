package tw.com.prodisc.bpm.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tw.com.prodisc.bpm.dao.IDao;

public class CHI_PUB {
	@SuppressWarnings("rawtypes")
	private static IDao chi_dao;
	
	@SuppressWarnings({ "rawtypes"})
	public static IDao getchi_dao(){
		if (null!=chi_dao) return chi_dao; 
		Resource res = new ClassPathResource("Spring.xml");   
	    XmlBeanFactory factory = new XmlBeanFactory(res);
		return (IDao) factory.getBean("chi_dao");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONArray getCurrency(){
		chi_dao = getchi_dao(); 
		JSONArray  arr = new JSONArray();
		String sql ="Select  a.GUID as a_GUID,"+
    						"a.ID as a_ID,"+
			                "a.Name as a_Name "+
			           " From CHI_Currency a "+
			           " Order by a.ID";
		Session ss01 = chi_dao.openSession();
		List ordcls = ss01.createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
        for(Map row: (List<Map>) ordcls){
    		JSONObject obj = new JSONObject();
    		obj.put("GUID", row.get("a_GUID"));
    		obj.put("ID"  , row.get("a_ID"));
    		obj.put("Name", row.get("a_Name"));
    		arr.add(obj);
        }
		return arr;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONArray getOrderClass(int Flag){
		chi_dao = getchi_dao(); 
		JSONArray  arr = new JSONArray();
		String sql ="Select  a.GUID as a_GUID,"+
                			"a.ID as a_ID,"+
			                "a.Name as a_Name "+
			           " From CHI_OrderClass a "+
			           " Where a.Flag = "+Flag+ // 旗標(1.批次變更訂單 2.批次變更採單 10.訂單 20.採購 41:發貨 42:發退)     
			           " Order by a.ID";
				
		Session ss01 = chi_dao.openSession();
		List ordcls = ss01.createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
        for(Map row: (List<Map>) ordcls){
    		JSONObject obj = new JSONObject();
    		obj.put("GUID", row.get("a_GUID"));
    		obj.put("ID"  , row.get("a_ID"));
    		obj.put("Name", row.get("a_Name"));
    		arr.add(obj);
        }
		return arr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getChiCust(String guid){
		chi_dao = getchi_dao(); 
		String ret = ""; 
		String sql ="Select a.ID as a_ID,"+
                "a.ShortName as a_ShortName,"+
                "a.Name as a_Name "+
           "From CHI_Customer a "+
			  "Where a.GUID = '"+guid+"'";
		Session ss01 = chi_dao.openSession();
		List custs = ss01.createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		chi_dao.closeSession(ss01);
        for(Map row: (List<Map>) custs){
        	ret = row.get("a_ID")+":"+row.get("a_ShortName");
        }
		return ret;
	}
	@SuppressWarnings("rawtypes")
	public static void UpDateChiAccountLine(BigDecimal dd,String guid){
		Resource res = new ClassPathResource("Spring.xml");   
	    XmlBeanFactory factory = new XmlBeanFactory(res);
		IDao chi_dao = (IDao) factory.getBean("chi_dao");
		String sql ="Update CHI_Customer Set AccountLine = "+dd+" Where GUID = '"+guid+"'";
		Session ss01 = chi_dao.openSession();
		SQLQuery qq = ss01.createSQLQuery(sql);
		qq.executeUpdate();
		chi_dao.closeSession(ss01);
	}
}
