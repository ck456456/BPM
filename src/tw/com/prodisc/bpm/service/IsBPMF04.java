package tw.com.prodisc.bpm.service;

import tw.com.prodisc.bpm.bean.BBPMF04;

public interface IsBPMF04 <T extends BBPMF04> extends IsBASE<T> {
	/**  用id抓一筆資料 */
	public String sqlID = " select p from BBPMF04 p "   
				+ " where lower(p.id) = lower(:ID) "
				+ " and p.deleted = false ";
	public String sqlSN = " select p from BBPMF04 p "   
			+ " where p.f03.bpm03001 = :FID "
			  + " and p.bpm04001 = :FSN "
			  + " and p.deleted = false ";
	
	public T getID(String upid);
	public T getSN(String fid,String fsn);
}
