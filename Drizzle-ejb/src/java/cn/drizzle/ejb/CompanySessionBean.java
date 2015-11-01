/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Company;
import cn.drizzle.sbi.EJBOperate;
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
public class CompanySessionBean implements EJBOperate<Company> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Company entity) {
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
    public Company getById(String value) {
        int id = Integer.parseInt(value);
        Query query = em.createNamedQuery("Company.findById");
        query.setParameter("id", id);
        try {
            return (Company) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    public Company getByCode(String value) {
        Query query = em.createNamedQuery("Company.findByCompanycode");
        query.setParameter("companycode", value);
        try {
            return (Company) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Company> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> retrieve() {
        Query query = em.createNamedQuery("Company.findAll");
        return query.getResultList();
    }

    @Override
    public Company update(Company entity) {
        try {
            Company c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
