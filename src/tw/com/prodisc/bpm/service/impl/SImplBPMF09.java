package tw.com.prodisc.bpm.service.impl;

import java.util.List;
import java.util.Map;

import tw.com.prodisc.bpm.bean.BBPMF09;
import tw.com.prodisc.bpm.service.IsBPMF09;

public class SImplBPMF09<T extends BBPMF09> extends SImplBASE<T>
implements IsBPMF09<T> {
	@SuppressWarnings("unchecked")
	public T get001(String s01)
	{
		List<T> f09 = this.getDao().createQuery(
				" select p from BBPMF09 p "
						+ " where lower(p.bpm09001) = lower(:bpm09001) "
		                  + " and p.deleted = false ")
				.setParameter("bpm09001", s01.trim()).list();
		if (f09.size() > 0) return f09.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T getID(String ID)
	{
		List<T> f09 = this.getDao().createQuery(
				" select p from BBPMF09 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID.trim()).list();
		if (f09.size() > 0) return f09.get(0);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<T> getMAPlst(StringBuffer ret){
		String hql = "select new map(p.id as id,p.bpm09001 as bpm09001,p.bpm09002 as bpm09002) from BBPMF09 p where p.deleted = false ";
		List<T> list = this.getDao().list(hql);
		if (list.size() > 0){
			ret.append(((Map)list.get(0)).get("id"));
		}
		return list;
	}
	
	public int getCount(){
		String hql = "select count(p) from BBPMF09 p where p.deleted = false ";
		return getTotalCount(hql);
	}
	
	public int  getBoxCount(String S01,String S02){
		String hql = "select count(p) from BBPMF09 p "+
				 "where p."+S01+" like '%'||:P1||'%'"+
				  " and p.deleted = false ";
		return getTotalCount(hql,S02);
	}
	public List<T> getBox(int firstResult, int maxResults, String S01,String S02,String S03,String S04){
		String hql = "Select new map(p.id as id,p.bpm09001 as bpm09001,p.bpm09002 as bpm09002) "+
				 "from BBPMF09 p where p."+S01+" like '%'||:P1||'%'"+
				  				" and p.deleted = false " +
				  				"Order by "+S03+" "+S04;
		List<T> f09s = this.getDao().list(hql, firstResult, maxResults,S02);
		return f09s;
	}
}
