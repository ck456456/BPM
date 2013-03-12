package tw.com.prodisc.bpm.service.impl;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.service.IsBPMF01;
import tw.com.prodisc.bpm.service.impl.SImplBASE;

public class SImplBPMF01<T extends BBPMF01> extends SImplBASE<T>
	implements IsBPMF01<T> {
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql = "Select p "+
				"from BBPMF01 p where deleted = false "+
				  				"Order by bpm01002";
		List<T> DEPT = this.getDao().createQuery(hql).list();
		return DEPT;
	}
	@SuppressWarnings("unchecked")
	public List<T> getf01null(){
		String hql = "Select p "+
				"from BBPMF01 p where deleted = false "+
								"and bpm01001 = true "+
								"and f01 is null "+
				  				"Order by bpm01002";
		List<T> DEPT = this.getDao().createQuery(hql).list();
		return DEPT;
	}
	/*
	@SuppressWarnings("unchecked")
	public List<T> get001(int ID){
		String hql = "Select p "+
				"from BBPMF01 p where deleted = false "+
								"and bpm01001 = "+ID+
				  				"Order by bpm01002";
		List<T> DEPT = this.getDao().createQuery(hql).list();
		return DEPT;
	}
	*/
	@SuppressWarnings("unchecked")
	public T getID(String ID){
		List<T> DEPT = this.getDao().createQuery(
				" select p from BBPMF01 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID).list();
		if (DEPT.size() > 0) return DEPT.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T get999(int ID){
		List<T> DEPT = this.getDao().createQuery(
				" select p from BBPMF01 p "
						+ " where p.bpm01999 = :ID "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID).list();
		if (DEPT.size() > 0) return DEPT.get(0);
		return null;
	}

	public int  getBoxCount(String S01,String S02){
		String hql = "select count(p) from BBPMF01 p "+
				 "where p."+S01+" like '%'||:P1||'%'"+
				  " and p.deleted = false ";
		return getTotalCount(hql,S02);
	}
	public List<T> getBox(int firstResult, int maxResults, String S01,String S02,String S03,String S04){
		String hql = "Select new map(p.id as id,p.bpm01002 as bpm01002,p.bpm01003 as bpm01003) "+
				 "from BBPMF01 p where p."+S01+" like '%'||:P1||'%'"+
				  				" and p.deleted = false " +
				  				"Order by "+S03+" "+S04;
		List<T> f02s = this.getDao().list(hql, firstResult, maxResults,S02);
		return f02s;
	}
}
