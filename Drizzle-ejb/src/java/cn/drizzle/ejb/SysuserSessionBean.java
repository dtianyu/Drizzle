/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Sysuser;
import cn.drizzle.sbi.EJBOperate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class SysuserSessionBean implements EJBOperate<Sysuser> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Sysuser entity) {
        try {
            if (em.contains(entity)) {
                em.remove(entity);
            } else {
                em.remove(em.merge(entity));
            }
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    public String getBizNo(String value) {
        String maxid, newid;
        int id;
        if (value != null) {
            int n = value.toString().length();
            Query query = em.createNativeQuery("SELECT max(s.userid) FROM Sysuser s WHERE left(s.userid," + n + ") ='" + value + "'");
            if (query.getSingleResult() != null) {
                maxid = query.getSingleResult().toString();
                int m = maxid.toString().length();
                id = Integer.parseInt(maxid.substring(n, m)) + 1;
                newid = value + String.format("%04d", id);
            } else {
                newid = value + "0001";
            }
            return newid;
        } else {
            return "";
        }
    }

    @Override
    public Sysuser getById(String value) {
        Query query = em.createNamedQuery("Sysuser.findByUserid");
        query.setParameter("userid", value);
        try {
            return (Sysuser) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Sysuser> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Sysuser getByIdAndPwd(String id, String pwd) {
        Query query = em.createNamedQuery("Sysuser.findByIdAndPwd");
        query.setParameter("userid", id);
        query.setParameter("pwd", pwd);
        try {
            return (Sysuser) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public List<Sysuser> retrieve() {
        Query query = em.createNamedQuery("Sysuser.findAll");
        return query.getResultList();
    }

    @Override
    public Sysuser update(Sysuser entity) {
        try {
            Sysuser u = em.merge(entity);
            return u;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
