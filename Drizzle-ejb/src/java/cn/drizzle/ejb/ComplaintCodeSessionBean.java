/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ComplaintCode;
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
public class ComplaintCodeSessionBean implements EJBOperate<ComplaintCode> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(ComplaintCode entity) {
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
    public ComplaintCode getById(String value) {
        Query query = em.createNamedQuery("ComplaintCode.findByComplaintcode");
        query.setParameter("complaintcode", value);
        try {
            return (ComplaintCode) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<ComplaintCode> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ComplaintCode> retrieve() {
        Query query = em.createNamedQuery("ComplaintCode.findAll");
        return query.getResultList();
    }

    @Override
    public ComplaintCode update(ComplaintCode entity) {
        try {
            ComplaintCode c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
