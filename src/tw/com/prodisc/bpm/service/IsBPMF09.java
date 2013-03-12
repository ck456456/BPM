package tw.com.prodisc.bpm.service;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF09;

public interface IsBPMF09 <T extends BBPMF09> extends IsBASE<T> {
	/**  用id抓一筆資料 */
	public T getID(String ID);
	public T get001(String s01);
	/**  總筆數 */
	public int getCount();
	public List<T> getMAPlst(StringBuffer ret);
	
	public int  getBoxCount(String S01,String S02);
	public List<T> getBox(int firstResult, int maxResults,String S01,String S02,String S03,String S04);
}
