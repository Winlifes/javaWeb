package com.dao;

import com.model.Tadmin;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("tadminDAO")
public class TadminDAO extends HibernateDaoSupport {
	/**
	 * 保存数据到数据库
	 * 
	 * @param tadmin
	 */
	public void save(Tadmin tadmin) {
		getHibernateTemplate().save(tadmin);
	}

	/**
	 * 更新数据到数据库
	 * 
	 * @param tadmin
	 */
	public void update(Tadmin tadmin) {
		getHibernateTemplate().update(tadmin);
	}

	/**
	 * 删除数据
	 * 
	 * @param tadmin
	 */
	public void delete(Tadmin tadmin) {
		getHibernateTemplate().delete(tadmin);
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param id
	 * @return
	 */
	public Tadmin findById(Integer id) {
		return getHibernateTemplate().get(Tadmin.class, id);
	}

	/**
	 * 查询数据
	 * 
	 * @param tadmin
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public List<Tadmin> findAll(Tadmin tadmin) {
		return findAll(tadmin, null, null);
	}

	/**
	 * 查询数据
	 * 
	 * @param tadmin
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public List<Tadmin> findAll(Tadmin tadmin, Integer offset, Integer pagesize) {
		List<Tadmin> list = new ArrayList<Tadmin>();
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			String hsql = " from Tadmin where 1=1 ";
			hsql = getSQL(tadmin, hsql);
			hsql = hsql + " order by id ";
			Query query = session.createQuery(hsql);
			if (offset != null && pagesize != null) {
				query.setFirstResult(offset);
				query.setMaxResults(pagesize);
			}
			list = query.list();
		} catch (Exception re) {
			re.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/**
	 * 查询记录总条数
	 * 
	 * @param tadmin
	 * @return
	 */
	public int getCount(Tadmin tadmin) {
		int count = 0;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			String hsql = " select count(*) from Tadmin where 1=1 ";
			hsql = getSQL(tadmin, hsql);
			Query query = session.createQuery(hsql);
			count = ((Long) query.uniqueResult()).intValue();
		} catch (Exception re) {
			re.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	public String getSQL(Tadmin tadmin, String sql) {
		if (tadmin.getId() != null && tadmin.getId() != 0) {
			sql = sql + " and  id = " + tadmin.getId();
		}
		if (StringUtils.isNotBlank(tadmin.getUname())) {
			sql = sql + " and (uname like '%" + tadmin.getUname() + "%')";
		}
		if (StringUtils.isNotBlank(tadmin.getUpwd())) {
			sql = sql + " and upwd = '" + tadmin.getUpwd() + "'";
		}
		if (StringUtils.isNotBlank(tadmin.getName())) {
			sql = sql + " and (name like '%" + tadmin.getName() + "%')";
		}
		return sql;
	}
}
