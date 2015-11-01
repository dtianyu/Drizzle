/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.Employee;
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
public class EmployeeSessionBean implements EJBOperate<Employee> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Employee entity) {
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
    public Employee getById(String value) {
        Query query = em.createNamedQuery("Employee.findByEmployeeid");
        query.setParameter("employeeid", value);
        try {
            return (Employee) query.getSingleResult();
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Employee> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> retrieve() {
        Query query = em.createNamedQuery("Employee.findAll");
        return query.getResultList();
    }

    @Override
    public Employee update(Employee entity) {
        try {
            Employee e = em.merge(entity);
            return e;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
