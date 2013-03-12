package tw.com.prodisc.bpm.service.impl;

import java.util.List;

import tw.com.prodisc.bpm.bean.BTest001;
import tw.com.prodisc.bpm.service.IsTest001;

public class SImplTest001<T extends BTest001> extends SImplBASE<T>
	implements IsTest001<T> {
	@SuppressWarnings("unchecked")
	public T getF04ID(String ID){
		List<T> tf = this.getDao().createQuery(sqlF04ID).setParameter("ID", ID.trim()).list();
		if (tf.size() > 0) return tf.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T getID(String ID){
		List<T> tf = this.getDao().createQuery(
				" select p from BTest001 p "
						+ " where lower(p.id) = lower(:ID) "
		                  + " and p.deleted = false ")
				.setParameter("ID", ID.trim()).list();
		if (tf.size() > 0) return tf.get(0);
		return null;
	}
}