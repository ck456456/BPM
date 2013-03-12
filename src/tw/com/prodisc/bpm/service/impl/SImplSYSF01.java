package tw.com.prodisc.bpm.service.impl;

import java.util.List;

import tw.com.prodisc.bpm.bean.BSYSF01;
import tw.com.prodisc.bpm.service.IsSYSF01;

public class SImplSYSF01<T extends BSYSF01> extends SImplBASE<T>
implements IsSYSF01<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T getID(String ID){
		List<T> MENU = this.getDao().createQuery(
				" select p from BSYSF01 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID.trim()).list();
		if (MENU.size() > 0) return MENU.get(0);
		return null;
	}
	
	public List<T> getMenulist(int firstResult, int maxResults, int ID, StringBuffer ret){
		String hql = "select p from BSYSF01 p where sys01001 = :P1  and p.deleted = false ";
		List<T> list = this.getDao().list(hql, firstResult, maxResults, ID);
		if (list.size() > 0)
			ret.append(list.get(0).getId().toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllMenu(){
		String hql = "Select p "+
				"from BSYSF01 p where deleted = false "+
				  				"Order by sys01001,sys01006,sys01004";
		List<T> MENU = this.getDao().createQuery(hql).list();
		return MENU;
	}
	@SuppressWarnings("unchecked")
	public List<T> getSubMenu(){
		String hql = "Select p "+
				"from BSYSF01 p where deleted = false "+
				  				"and sys01001 <> 0 "+
				  				// "and sys01003 <> '#' "+
				  				"Order by sys01001,sys01006,sys01004";
		List<T> MENU = this.getDao().createQuery(hql).list();
		return MENU;
	}
	@SuppressWarnings("unchecked")
	public List<T> getMainMenu(int ID,StringBuffer ret) {
		String hql = " from BSYSF01 where sys01001 = :sys01001 and deleted = false Order by sys01006";
		List<T> MENU = this.getDao().createQuery(hql).setParameter("sys01001",ID).
				list();
		if (MENU.size() > 0)
			ret.append(MENU.get(0).getId().toString());
		return MENU;
	}
	@SuppressWarnings("unchecked")
	public List<T> getMainMenu(int ID) {
		String hql = " from BSYSF01 where sys01001 = :sys01001 and deleted = false Order by sys01006";
		List<T> MENU = this.getDao().createQuery(hql).setParameter("sys01001",ID).
				list();
		return MENU;
	}
	
	public int getMENUCount(int ID){
		String hql = "select count(p) from BSYSF01 p where sys01001 = :P1 and p.deleted = false ";
		return getTotalCount(hql,ID);
	}

	public List<T> getBox(int firstResult, int maxResults,String S01,String S02,String S03,String S04){
		String hql = "Select new map(p.id as id,p.sys01003 as sys01003,p.sys01004 as sys01004) "+
				 "from BSYSF01 p where p."+S01+" like '%'||:P1||'%'"+
				  				" and p.deleted = false " +
				  				"Order by sys01001,"+S03+" "+S04;
		List<T> sf01s = this.getDao().list(hql, firstResult, maxResults,S02);
		return sf01s;
	}
	public int  getBoxCount(String S01,String S02){
		String hql = "select count(p) from BSYSF01 p "+
				 "where p."+S01+" like '%'||:P1||'%'"+
				  " and p.deleted = false ";
		return getTotalCount(hql,S02);
	}
}
