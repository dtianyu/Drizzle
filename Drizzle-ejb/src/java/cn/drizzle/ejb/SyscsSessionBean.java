/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Syscs;
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
public class SyscsSessionBean implements EJBOperate<Syscs> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Syscs entity) {
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

    @Override
    public Syscs getById(String value) {
        int id = Integer.parseInt(value);
        Query query = em.createNamedQuery("Syscs.findById");
        query.setParameter("id", id);
        try {
            return (Syscs) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public List<Syscs> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Syscs> retrieve() {
        Query query = em.createNamedQuery("Syscs.findAll");
        return query.getResultList();
    }

    @Override
    public Syscs update(Syscs entity) {
        try {
            Syscs s = em.merge(entity);
            return s;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
