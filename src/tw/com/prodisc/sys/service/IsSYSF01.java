package tw.com.prodisc.sys.service;

import java.util.List;

import tw.com.prodisc.sys.bean.BSYSF01;

public interface IsSYSF01<T extends BSYSF01> extends IsBASE<T> {

	/** 根據ID尋找MENU */
	public T getID(String ID);

	/** 上階筆數  */
	public int getMENUCount(int ID);
	
	/**  取上層Menu */
	public List<T> getMainMenu(int ID);
	public List<T> getMainMenu(int ID,StringBuffer ret);
	/**  除最上層其他全抓 */
	public List<T> getSubMenu();
	/**  Menu box */
	public List<T> getMenuBox();
	/**  全抓 */
	public List<T> getAllMenu();
	/**  取上層Menu */
	public List<T> getMenulist(int firstResult, int maxResults, int ID, StringBuffer ret); 
}
