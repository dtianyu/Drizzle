/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ComplaintDetail;
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
public class ComplaintDetailSessionBean implements EJBOperate<ComplaintDetail> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(ComplaintDetail entity) {
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
    public ComplaintDetail getById(String value) {
        Query query = em.createNamedQuery("ComplaintDetail.findByComplaintidd");
        query.setParameter("complaintidd", value);
        try {
            return (ComplaintDetail) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<ComplaintDetail> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ComplaintDetail> retrieve() {
        Query query = em.createNamedQuery("ComplaintDetail.findAll");
        return query.getResultList();
    }

    @Override
    public ComplaintDetail update(ComplaintDetail entity) {
        try {
            ComplaintDetail c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
