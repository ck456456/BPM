package tw.com.prodisc.sys.service;

import java.util.List;

import tw.com.prodisc.sys.dao.IDao;

public interface IsBASE<T> {

	public IDao<T> getDao();
	
	public T find(Class<T> clazz, int id);

	public void create(T baseBean);

	public void save(T baseBean);

	public void delete(T baseBean);
	
	public void del_T(T baseBean);

	public List<T> list(String hql);

	public int getTotalCount(String hql, Object... params);

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params);
}
