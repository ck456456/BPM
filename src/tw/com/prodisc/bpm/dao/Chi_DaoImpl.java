package tw.com.prodisc.bpm.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tw.com.prodisc.bpm.dao.IDao;


public class Chi_DaoImpl<T> extends HibernateDaoSupport implements IDao<T> {
	
	/*
	public String SQL_CustGUID= "select GUID,ID,Name,ShortName "+
			 "from CHI_Customer "+
		   " where lower(Flag.id) = lower(:Flag)";
	*/
	public T find(Class<T> clazz, int id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public void create(T baseBean) {
		getHibernateTemplate().persist(baseBean);
	}

	public SQLQuery createSQLQuery(String hql) {
		return getSession().createSQLQuery(hql);
	}
	
	public Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}

	public void delete(T baseBean) {
		getHibernateTemplate().delete(baseBean);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getTotalCount(String hql, Object... params) {
		int ret = 0;
		Session ss01 = this.openSession();
		Query query = ss01.createSQLQuery(hql);
		List<Map> result = query
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
			.list();
		this.closeSession(ss01);
		
		for(Map row: (List<Map>) result)
			ret = (int) row.get("");
		
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int firstResult, int maxResults,
			Object... params) {
		Query query = createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++)
			if (params[i] != null){
				query.setParameter("P"+(i+1), params[i]);
			}	
		List<T> list = query
						.setFirstResult(firstResult)
						.setMaxResults(maxResults).list();
		return list;
	}

	public Session openSession(){
		return this.getHibernateTemplate().getSessionFactory().openSession();
	}
	
	public void closeSession(Session session){
		session.close();
	}
	
	public T save01(T baseBean) {
		getHibernateTemplate().merge(baseBean);
		return baseBean;
	}	
	public T save(T baseBean) {
		getHibernateTemplate().merge(baseBean);
		T ret =(T)getHibernateTemplate().merge(baseBean);
		getHibernateTemplate().flush();
		return ret;
		// getHibernateTemplate().saveOrUpdate(baseBean);
		// getHibernateTemplate().update(baseBean);
		// getHibernateTemplate().save(baseBean);
	}
}
