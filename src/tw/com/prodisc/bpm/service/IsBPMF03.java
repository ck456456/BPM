package tw.com.prodisc.bpm.service;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.service.IsBASE;

public interface IsBPMF03 <T extends BBPMF03> extends IsBASE<T> {
	/**  全抓 */
	public List<T> getAll();
	/**  全抓   Ret 第一筆ID */
	public List<T> getAll(StringBuffer ret);
	/**  總筆數 */
	public int getCount();
	/**  用id抓一筆資料 */
	public T getID(String upid);
	/**  用"表單代號"抓一筆資料 */
	public T get001(String S01);

	public int  getBoxCount(String S01,String S02);
	
	public List<T> getBox(int firstResult, int maxResults,String S01,String S02,String S03,String S04,StringBuffer ret);
}
