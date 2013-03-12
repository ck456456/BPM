package tw.com.prodisc.bpm.service;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF07;

public interface IsBPMF07 <T extends BBPMF07> extends IsBASE<T> {
	/**  全抓 */
	public List<T> getAll();
	/**  全抓   Ret 第一筆ID */
	public List<T> getAll(StringBuffer ret);
	public List<T> getMAPlst(StringBuffer ret);
	/**  用id抓一筆資料 */
	public T getID(String ID);
	public T get001(String s001);
	/**  總筆數 */
	public int getCount();
}
