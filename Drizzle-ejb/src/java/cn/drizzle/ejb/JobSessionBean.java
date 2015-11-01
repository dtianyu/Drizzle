/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Job;
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
public class JobSessionBean implements EJBOperate<Job> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Job entity) {
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
    public Job getById(String value) {
        int id = Integer.parseInt(value);
        Query query = em.createNamedQuery("Job.findById");
        query.setParameter("id", id);
        try {
            return (Job) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Job> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Job> retrieve() {
        Query query = em.createNamedQuery("Job.findAll");
        return query.getResultList();
    }

    @Override
    public Job update(Job entity) {
        try {
            Job j = em.merge(entity);
            return j;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
