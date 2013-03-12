package tw.com.prodisc.bpm.service.impl;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.service.IsBPMF04;

public class SImplBPMF04<T extends BBPMF04> extends SImplBASE<T>
implements IsBPMF04<T> {
	@SuppressWarnings("unchecked")
	public T getID(String ID){
		List<T> f04 = this.getDao().createQuery(sqlID)
				.setParameter("ID", ID.trim()).list();
		if (f04.size() > 0) return f04.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public T getSN(String fid,String fsn){
		List<T> f04 = this.getDao().createQuery(sqlSN)
				.setParameter("FID", fid.trim())
				.setParameter("FSN", fsn.trim())
				.list();
		if (f04.size() > 0) return f04.get(0);
		return null;
	}
}
