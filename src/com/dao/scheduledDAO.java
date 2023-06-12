package com.dao;

import com.model.scheduled;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("scheduledDAO")
public class scheduledDAO extends HibernateDaoSupport {
    /**
     * 保存数据到数据库
     *
     * @param sche
     */
    public void save(scheduled sche) {
        getHibernateTemplate().save(sche);
    }

    /**
     * 更新数据到数据库
     *
     * @param sche
     */
    public void update(scheduled sche) {
        getHibernateTemplate().update(sche);
    }

    /**
     * 删除数据
     *
     * @param sche
     */
    public void delete(scheduled sche) {
        getHibernateTemplate().delete(sche);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    public scheduled findById(int id) {
        return getHibernateTemplate().get(scheduled.class, id);
    }

    /**
     * 查询数据
     *
     * @param sche
     * @param offset
     * @param pagesize
     * @return
     */
    public List<scheduled> findAll(scheduled sche) {
        return findAll(sche, null, null);
    }

    /**
     * 查询数据
     *
     * @param sche
     * @param offset
     * @param pagesize
     * @return
     */
    public List<scheduled> findAll(scheduled sche, Integer offset, Integer pagesize) {
        List<scheduled> list = new ArrayList<scheduled>();
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            String hsql = " from scheduled where 1=1 ";
            hsql = getSQL(sche, hsql);
            hsql = hsql + " order by id desc";
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
     * @param sche
     * @return
     */
    public int getCount(scheduled sche) {
        int count = 0;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            String hsql = " select count(*) from scheduled where 1=1 ";
            hsql = getSQL(sche, hsql);
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

    public String getSQL(scheduled sche, String sql) {
        if (sche.getId() != 0) {
            sql = sql + " and  id = " + sche.getId();
        }
        if (StringUtils.isNotBlank(sche.getUid())) {
            sql = sql + " and  uid = '" + sche.getUid() + "'";
        }
        if (StringUtils.isNotBlank(sche.getUname())) {
            sql = sql + " and (uname like '%" + sche.getUname() + "%')";
        }
        if (StringUtils.isNotBlank(sche.getName())) {
            sql = sql + " and (name like '%" + sche.getName() + "%')";
        }
        if (StringUtils.isNotBlank(sche.getTime())) {
            sql = sql + " and time = '" + sche.getTime() + "'";
        }
        if (StringUtils.isNotBlank(sche.getStanding())) {
            sql = sql + " and standing = '" + sche.getStanding() + "'";
        }
        if (StringUtils.isNotBlank(sche.getReason())) {
            sql = sql + " and reason = '" + sche.getReason() + "'";
        }
        if (StringUtils.isNotBlank(sche.getState())) {
            sql = sql + " and state = '" + sche.getState() + "'";
        }
        return sql;
    }
}
