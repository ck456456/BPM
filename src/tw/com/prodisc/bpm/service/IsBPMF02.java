package tw.com.prodisc.bpm.service;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.service.IsBASE;

public interface IsBPMF02 <T extends BBPMF02> extends IsBASE<T> {
	public String sqlID = " select p from BBPMF02 p "   
						+ " where lower(p.id) = lower(:ID) "
						+ " and p.deleted = false ";
	/**  全抓 */
	public List<T> getAll();

	public T getID(String ID);
	
	public T get001(String s001);
	
	public int  getBoxCount(String S01,String S02);
	
	public List<T> getBox(int firstResult, int maxResults,String S01,String S02,String S03,String S04,StringBuffer ret);
}
