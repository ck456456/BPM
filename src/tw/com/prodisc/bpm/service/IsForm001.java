package tw.com.prodisc.bpm.service;

import tw.com.prodisc.bpm.bean.BForm001;

public interface IsForm001 <T extends BForm001> extends IsBASE<T> {
	public String sqlF04ID = " select p from BForm001 p "   
			+ " where lower(p.f04.id) = lower(:ID) "
			+ " and p.deleted = false ";

}
