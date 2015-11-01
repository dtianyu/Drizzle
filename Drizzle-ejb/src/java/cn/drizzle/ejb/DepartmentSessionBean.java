/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Department;
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
public class DepartmentSessionBean implements EJBOperate<Department> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Department entity) {
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
    public Department getById(String value) {
        int id = Integer.parseInt(value);
        Query query = em.createNamedQuery("Department.findById");
        query.setParameter("id", id);
        try {
            return (Department) query.getSingleResult();
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    public Department getByCode(String value) {
        Query query = em.createNamedQuery("Department.findByDeptcode");
        query.setParameter("deptcode", value);
        try {
            return (Department) query.getSingleResult();
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Department> getByPId(String pid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> retrieve() {
        Query query = em.createNamedQuery("Department.findAll");
        return query.getResultList();
    }

    public List<Department> retrieve(String company) {
        int id = Integer.parseInt(company);
        Query query = em.createNamedQuery("Department.findByCompany");
        query.setParameter("companyid", id);
        return query.getResultList();
    }

    @Override
    public Department update(Department entity) {
        try {
            Department d = em.merge(entity);
            return d;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
