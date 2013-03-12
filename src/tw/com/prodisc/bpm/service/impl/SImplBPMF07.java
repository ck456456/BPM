package tw.com.prodisc.bpm.service.impl;

import java.util.List;
import java.util.Map;

import tw.com.prodisc.bpm.bean.BBPMF07;
import tw.com.prodisc.bpm.service.IsBPMF07;

public class SImplBPMF07<T extends BBPMF07> extends SImplBASE<T>
implements IsBPMF07<T> {
	@SuppressWarnings("unchecked")
	public T get001(String s001)
	{
		List<T> f07 = this.getDao().createQuery(
				" select p from BBPMF07 p "
						+ " where p.bpm07001 = :ID "
		                  + " and p.deleted = false ")
				.setParameter("ID", s001.trim()).list();
		if (f07.size() > 0) return f07.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T getID(String ID)
	{
		List<T> f07 = this.getDao().createQuery(
				" select p from BBPMF07 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID.trim()).list();
		if (f07.size() > 0) return f07.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql = "Select p "+
				"from BBPMF07 p where deleted = false "+
				  				"Order by bpm07001";
		List<T> f07 = this.getDao().createQuery(hql).list();
		return f07;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(StringBuffer ret){
		String hql = "Select p "+
				"from BBPMF07 p where deleted = false "+
				  				"Order by bpm07001";
		List<T> f07 = this.getDao().createQuery(hql).list();
		if (f07.size() > 0)
			ret.append(f07.get(0).getId().toString());
		return f07;
	}
	
	@SuppressWarnings("rawtypes")
	public List<T> getMAPlst(StringBuffer ret){
		String hql = "select new map(p.id as id,p.bpm07001 as bpm07001,p.bpm07002 as bpm07002) from BBPMF07 p Where p.deleted = false";
		List<T> list = this.getDao().list(hql);
		if (list.size() > 0){
			ret.append(((Map)list.get(0)).get("id"));
		}
		return list;
	}
	
	public int getCount(){
		String hql = "select count(p) from BBPMF07 p where p.deleted = false ";
		return getTotalCount(hql);
	}
	
}
