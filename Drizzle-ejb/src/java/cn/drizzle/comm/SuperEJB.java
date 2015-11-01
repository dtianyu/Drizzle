/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperEJB<T> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    protected EntityManager em;

    protected String className;

    public abstract List<T> getByPId(String value);

    public void delete(T entity) {
        try {
            if (em.contains(entity)) {
                em.remove(entity);
            } else {
                em.remove(em.merge(entity));
            }
        } catch (Exception e) {
            throw new Error(e.toString());
        }
    }

    public void persist(T entity) {
        em.persist(entity);
    }

    public int getRowCount() {
        Query query = em.createNamedQuery(getClassName() + ".findRowCount");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public List<T> findAll(int first, int pageSize) {
        Query query = em.createNamedQuery(getClassName() + ".findAll").setFirstResult(first).setMaxResults(first + pageSize);
        return query.getResultList();
    }

    public T getById(String value) {
        Query query = em.createNamedQuery(getClassName() + ".findById");
        query.setParameter("id", Integer.parseInt(value));
        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public T getNextById(String value) {
        Query query = em.createNamedQuery(className + ".findNextById").setFirstResult(0).setMaxResults(1);
        query.setParameter("id", Integer.parseInt(value));
        List<T> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public T getPrevById(String value) {
        Query query = em.createNamedQuery(className + ".findPrevById").setFirstResult(0).setMaxResults(1);
        query.setParameter("id", Integer.parseInt(value));
        List<T> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public T update(T entity) {
        try {
            T e = em.merge(entity);
            return e;
        } catch (Exception e) {
            throw new Error(e.toString());
        }
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    public void persist1(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>Drizzle-ejbPU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            em.joinTransaction();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
