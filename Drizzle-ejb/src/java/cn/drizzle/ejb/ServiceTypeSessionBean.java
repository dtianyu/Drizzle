/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ServiceType;
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
public class ServiceTypeSessionBean implements EJBOperate<ServiceType> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(ServiceType entity) {
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
    public ServiceType getById(String value) {
        Query query = em.createNamedQuery("ServiceType.findByTypeid");
        query.setParameter("typeid", value);
        try {
            return (ServiceType) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<ServiceType> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ServiceType> retrieve() {
        Query query = em.createNamedQuery("ServiceType.findAll");
        return query.getResultList();
    }

    @Override
    public ServiceType update(ServiceType entity) {
        try {
            ServiceType c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
