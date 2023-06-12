package com.dao;

import com.model.equipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("equipmentDAO")
public class equipmentDAO extends HibernateDaoSupport {
    /**
     * 保存数据到数据库
     *
     * @param equip
     */
    public void save(equipment equip) {
        getHibernateTemplate().save(equip);
    }

    /**
     * 更新数据到数据库
     *
     * @param equip
     */
    public void update(equipment equip) {
        getHibernateTemplate().update(equip);
    }

    /**
     * 删除数据
     *
     * @param equip
     */
    public void delete(equipment equip) {
        getHibernateTemplate().delete(equip);
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    public equipment findById(Integer id) {
        return getHibernateTemplate().get(equipment.class, id);
    }

    /**
     * 查询数据
     *
     * @param equip
     * @param offset
     * @param pagesize
     * @return
     */
    public List<equipment> findAll(equipment equip) {
        return findAll(equip, null, null);
    }

    /**
     * 查询数据
     *
     * @param equip
     * @param offset
     * @param pagesize
     * @return
     */
    public List<equipment> findAll(equipment equip, Integer offset, Integer pagesize) {
        List<equipment> list = new ArrayList<equipment>();
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            String hsql = " from equipment where 1=1 ";
            hsql = getSQL(equip, hsql);
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
     * @param equip
     * @return
     */
    public int getCount(equipment equip) {
        int count = 0;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            String hsql = " select count(*) from equipment where 1=1 ";
            hsql = getSQL(equip, hsql);
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

    public String getSQL(equipment equip, String sql) {
        if (equip.getId() != null && equip.getId() != 0) {
            sql = sql + " and  id = " + equip.getId();
        }
        if (StringUtils.isNotBlank(equip.getUname())) {
            sql = sql + " and (uname like '%" + equip.getUname() + "%')";
        }
        if (StringUtils.isNotBlank(equip.getState())) {
            sql = sql + " and state = '" + equip.getState() + "'";
        }
        if (StringUtils.isNotBlank(equip.getSituation())) {
            sql = sql + " and situation = '" + equip.getSituation() + "'";
        }
        return sql;
    }
}
