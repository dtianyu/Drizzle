/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.comm.Lib;
import cn.drizzle.entity.Service;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.sbi.EJBOperate;
import cn.drizzle.sbi.EJBOperateWithDetail;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
public class ServiceSessionBean implements EJBOperate<Service>, EJBOperateWithDetail<Service> {

    @EJB
    private ServiceDetailSessionBean serviceDetailSessionBean;
    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Service entity) {
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
    public String getBizNo(int company, Date day, String code, String format, int len) {
        String maxid, newid;
        int id;
        if (day != null && code != null && format != null && len > 0) {
            String d = Lib.formatDate(format, day);
            int c = code.toString().length();
            int f = d.toString().length();
            Query query = em.createNativeQuery("select max(serviceid) from service "
                    + "where company=" + company + " and  substring(serviceid," + 1 + "," + (c + f) + ")='" + (code + d) + "'");
            if (query.getSingleResult() != null) {
                maxid = query.getSingleResult().toString();
                int m = maxid.toString().length();
                id = Integer.parseInt(maxid.substring(m - len, m)) + 1;
                newid = code + d + String.format("%0" + len + "d", id);
            } else {
                newid = code + d + String.format("%0" + len + "d", 1);
            }
            return newid;
        } else {
            return "";
        }
    }

    @Override
    public Service getById(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> retrieve() {
        Query query = em.createNamedQuery("Service.findAll");
        return query.getResultList();
    }

    @Override
    public List<Service> retrieve(String company) {
        int id = Integer.parseInt(company);
        Query query = em.createNamedQuery("Service.findByCompany");
        query.setParameter("company", id);
        return query.getResultList();
    }

    @Override
    public List<Service> retrieve(String company, Date daybegin, Date dayend) {
        int id = Integer.parseInt(company);
        Query query = em.createNamedQuery("Service.findByCompanyAndServiceDate");
        query.setParameter("company", id);
        query.setParameter("daybegin", daybegin);
        query.setParameter("dayend", dayend);
        return query.getResultList();
    }

    @Override
    public Service update(Service entity) {
        try {
            Service c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    public Service update(Service entity, List<ServiceDetail> entities) {
        try {
            Service c = em.merge(entity);
            for (ServiceDetail serviceDetail : entities) {
                serviceDetailSessionBean.update(serviceDetail);
            }
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
