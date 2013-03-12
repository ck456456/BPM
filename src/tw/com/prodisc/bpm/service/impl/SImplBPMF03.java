package tw.com.prodisc.bpm.service.impl;

import java.util.List;
import java.util.Map;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.service.IsBPMF03;

public class SImplBPMF03<T extends BBPMF03> extends SImplBASE<T>
implements IsBPMF03<T> {
	@SuppressWarnings("unchecked")
	public T get001(String S01){
		List<T> f03 = this.getDao().createQuery(
				" select p from BBPMF03 p "
						+ " where p.bpm03001 = :ID "
		                  + " and p.deleted = false ")
				.setParameter("ID", S01.trim()).list();
		if (f03.size() > 0) return f03.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql = "Select p "+
				"from BBPMF03 p where deleted = false "+
				  				"Order by bpm03001";
		List<T> USER = this.getDao().createQuery(hql).list();
		return USER;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(StringBuffer ret){
		String hql = "Select p "+
				"from BBPMF03 p where deleted = false "+
				  				"Order by bpm03001";
		List<T> f03 = this.getDao().createQuery(hql).list();
		if (f03.size() > 0)
			ret.append(f03.get(0).getId().toString());
		return f03;
	}
	
	public int getCount(){
		String hql = "select count(p) from BBPMF03 p where p.deleted = false ";
		return getTotalCount(hql);
	}
	@SuppressWarnings("unchecked")
	public T getID(String ID){
		List<T> f03 = this.getDao().createQuery(
				" select p from BBPMF03 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID.trim()).list();
		if (f03.size() > 0) return f03.get(0);
		return null;
	}
	public int  getBoxCount(String S01,String S02){
		
		String hql = "select count(p) from BBPMF03 p "+
				 "where p.deleted = false "+
				 " and p."+S01+" like '%'||:P1||'%'";
		 
		return getTotalCount(hql,S02);
	}
	@SuppressWarnings("rawtypes")
	public List<T> getBox(int firstResult, int maxResults, String S01,String S02,String S03,String S04,StringBuffer ret){
		String hql = "Select new map(p.id as id,p.bpm03001 as bpm03001,p.bpm03002 as bpm03002,p.bpm03003 as bpm03003) "+
				 "from BBPMF03 p where p."+S01+" like '%'||:P1||'%'"+
				  				" and p.deleted = false " +
				  				"Order by "+S03+" "+S04;
		List<T> f03s = this.getDao().list(hql, firstResult, maxResults,S02);
		if (f03s.size() > 0){
			ret.append(((Map)f03s.get(0)).get("id"));
		}
		return f03s;
	}

}
