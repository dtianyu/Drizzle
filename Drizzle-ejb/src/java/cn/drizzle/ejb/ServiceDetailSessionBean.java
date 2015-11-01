/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ServiceDetail;
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
public class ServiceDetailSessionBean implements EJBOperate<ServiceDetail> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(ServiceDetail entity) {
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
    public ServiceDetail getById(String value) {
        try {
            Query query = em.createNamedQuery("ServiceDetail.findByServiceidd");
            query.setParameter("serviceidd", value);
            return (ServiceDetail) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<ServiceDetail> getByPId(String value) {
        try {
            Query query = em.createNamedQuery("ServiceDetail.findBySourceidd");
            query.setParameter("sourceidd", value);
            return query.getResultList();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<ServiceDetail> retrieve() {
        Query query = em.createNamedQuery("ServiceDetail.findAll");
        return query.getResultList();
    }

    @Override
    public ServiceDetail update(ServiceDetail entity) {
        try {
            ServiceDetail e = em.merge(entity);
            return e;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
