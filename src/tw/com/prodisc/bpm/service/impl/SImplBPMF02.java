package tw.com.prodisc.bpm.service.impl;

import java.util.List;
import java.util.Map;

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.impl.SImplBASE;

public class SImplBPMF02<T extends BBPMF02> extends SImplBASE<T>
	implements IsBPMF02<T> {

	public enum Select
	{
		account,name,bpm02001,bpm02002
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql = "Select p "+
				"from BBPMF02 p where deleted = false "+
				  				"Order by bpm02001";
		List<T> USER = this.getDao().createQuery(hql).list();
		return USER;
	}
	
	@SuppressWarnings("unchecked")
	public T getID(String ID){
		List<T> f02 = this.getDao().createQuery(sqlID)
								.setParameter("ID", ID).list();
		if (f02.size() > 0) return f02.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T get001(String s001){
		List<T> USER = this.getDao().createQuery(
				" select p from BBPMF02 p "
						+ " where p.bpm02001 = :ID "
		                  + " and p.deleted = false ")
				.setParameter("ID", s001).list();
		if (USER.size() > 0) return USER.get(0);
		return null;
	}
	
	public int  getBoxCount(String S01,String S02){
		
		String hql = "select count(p) from BBPMF02 p "+
				 "where p.deleted = false "+
				 " and p.bpm02004 is null "+
				 " and p."+S01+" like '%'||:P1||'%'";
		 
		return getTotalCount(hql,S02);
	}
	@SuppressWarnings("rawtypes")
	public List<T> getBox(int firstResult, int maxResults, String S01,String S02,String S03,String S04,StringBuffer ret){
		String hql = "Select new map(p.id as id,p.bpm02001 as bpm02001,p.bpm02002 as bpm02002) "+
				 "from BBPMF02 p where p."+S01+" like '%'||:P1||'%'"+
				 				" and p.bpm02004 is null "+
				  				" and p.deleted = false " +
				  				"Order by "+S03+" "+S04;
		List<T> f02s = this.getDao().list(hql, firstResult, maxResults,S02);
		if (f02s.size() > 0){
			ret.append(((Map)f02s.get(0)).get("id"));
		}
		return f02s;
	}
	
}
