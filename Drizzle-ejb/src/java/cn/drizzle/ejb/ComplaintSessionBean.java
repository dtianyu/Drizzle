/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.comm.Lib;
import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.ComplaintDetail;
import cn.drizzle.entity.Customer;
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
public class ComplaintSessionBean implements EJBOperate<Complaint>, EJBOperateWithDetail<Complaint> {

    @EJB
    private ComplaintDetailSessionBean complaintDetailSessionBean;
    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(Complaint entity) {
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
            Query query = em.createNativeQuery("select max(complaintid) from complaint "
                    + "where company=" + company + " and  substring(complaintid," + 1 + "," + (c + f) + ")='" + (code + d) + "'");
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
    public Complaint getById(String value) {
        Query query = em.createNamedQuery("Complaint.findByComplaintid");
        query.setParameter("complaintid", value);
        try {
            return (Complaint) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Complaint> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Complaint> retrieve() {
        Query query = em.createNamedQuery("Complaint.findAll");
        return query.getResultList();
    }

    @Override
    public List<Complaint> retrieve(String company) {
        int id = Integer.parseInt(company);
        Query query = em.createNamedQuery("Complaint.findByCompany");
        query.setParameter("company", id);
        return query.getResultList();
    }

    @Override
    public List<Complaint> retrieve(String company, Date daybegin, Date dayend) {
        int id = Integer.parseInt(company);
        Query query = em.createNamedQuery("Complaint.findByCompanyAndComplaintDate");
        query.setParameter("company", id);
        query.setParameter("daybegin", daybegin);
        query.setParameter("dayend", dayend);
        return query.getResultList();
    }

    public List<Complaint> retrieve(Customer entity) {
        Query query = em.createNamedQuery("Complaint.findByCustomerid");
        query.setParameter("customerid", entity);
        return query.getResultList();
    }

    @Override
    public Complaint update(Complaint entity) {
        try {
            Complaint c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    public Complaint update(Complaint entity, List<ComplaintDetail> entities) {
        try {
            Complaint c = em.merge(entity);
            for (ComplaintDetail complaintDetail : entities) {
                complaintDetailSessionBean.update(complaintDetail);
            }
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
