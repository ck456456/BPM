package tw.com.prodisc.bpm.service;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.service.IsBASE;

public interface IsBPMF01 <T extends BBPMF01> extends IsBASE<T> {
	public String sqlID = " select p from BBPMF01 p "   
			+ " where lower(p.id) = lower(:ID) "
			+ " and p.deleted = false ";
	public String nullf01s = "Select p "+
			"from BBPMF01 p where deleted = false "+
							"and bpm01001 = true "+
							"and f01 is null "+
			  				"Order by bpm01002";
	/**  全抓 */
	public List<T> getAll();
	/**  抓上階組織 */
	// public List<T> get001(int ID);
	public List<T> getf01null();
	
	public T getID(String ID);
	
	public T get999(int ID);
	
	public int  getBoxCount(String S01,String S02);
	
	public List<T> getBox(int firstResult, int maxResults,String S01,String S02,String S03,String S04);
}
