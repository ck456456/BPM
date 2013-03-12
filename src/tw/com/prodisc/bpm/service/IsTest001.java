package tw.com.prodisc.bpm.service;

import tw.com.prodisc.bpm.bean.BTest001;

public interface IsTest001 <T extends BTest001> extends IsBASE<T> {
	/*
	public String sqlF04ID = " select p from BForm001 p "   
				+ " where lower(p.f04.id) = lower(:ID) "
				+ " and p.deleted = false ";
	*/			
	public String sqlF04ID = " select p from BTest001 p "   
			+ " where lower(p.f04.id) = lower(:ID) "
			+ " and p.deleted = false ";
	public T getF04ID(String ID);
	public T getID(String ID);
}
