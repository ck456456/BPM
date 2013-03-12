package tw.com.prodisc.bpm.service;

import tw.com.prodisc.bpm.bean.BForm002;

public interface IsForm002 <T extends BForm002> extends IsBASE<T> {
	public String sqlF04ID = " select p from BForm002 p "   
			+ " where lower(p.f04.id) = lower(:ID) "
			+ " and p.deleted = false ";
}
