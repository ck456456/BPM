package tw.com.prodisc.sys.service.impl;

import java.util.Date;
import java.util.List;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;

// import org.apache.struts2.ServletActionContext;

import tw.com.prodisc.sys.bean.BSYSP01;
import tw.com.prodisc.sys.dao.IDao;
import tw.com.prodisc.sys.service.IsBASE;

public abstract class SImplBASE<T extends BSYSP01> implements IsBASE<T> {

	protected IDao<T> dao;

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	public T find(Class<T> clazz, int id) {
		return dao.find(clazz, id);
	}

	public void create(T baseBean){  // 新增
		// HttpServletRequest  request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();
		
		baseBean.setDateCreated(new Date());
		// baseBean.setIdCreated((Integer)session.getAttribute("G_ID"));
		dao.create(baseBean);
	}

	public void save(T baseBean) {  // 修改
		// HttpServletRequest  request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();
		
		baseBean.setDateModified(new Date());
		// baseBean.setIdModified((Integer)session.getAttribute("G_ID"));
		dao.save(baseBean);
	}

	public void del_T(T baseBean){
		dao.delete(baseBean);
	}
	
	public void delete(T baseBean) {  // 刪除
		// HttpServletRequest  request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();
		
		baseBean.setDateDeleted(new Date());
		// baseBean.setIdDeleted((Integer)session.getAttribute("G_ID"));
		baseBean.setDeleted(true);
		dao.save(baseBean);
	}

	public int getTotalCount(String hql, Object... params) {
		return dao.getTotalCount(hql, params);
	}

	public List<T> list(String hql) {
		return dao.list(hql);
	}

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params) {
		return dao.list(hql, firstResult, maxSize, params);
	}

}
