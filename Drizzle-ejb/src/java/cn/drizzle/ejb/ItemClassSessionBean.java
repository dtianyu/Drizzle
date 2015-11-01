/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ItemClass;
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
public class ItemClassSessionBean implements EJBOperate<ItemClass> {

    @PersistenceContext(unitName = "Drizzle-ejbPU")
    private EntityManager em;

    @Override
    public void delete(ItemClass entity) {
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
    public ItemClass getById(String value) {
        Query query = em.createNamedQuery("ItemClass.findByClassid");
        query.setParameter("classid", value);
        try {
            return (ItemClass) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    public List<ItemClass> getByClassLevel(String levelid) {
        Query query = em.createNamedQuery("ItemClass.findByClasslevel");
        query.setParameter("classlevel", levelid);
        return query.getResultList();
    }

    @Override
    public List<ItemClass> getByPId(String value) {
        Query query = em.createNamedQuery("ItemClass.findByParent");
        query.setParameter("parent", value);
        return query.getResultList();
    }

    @Override
    public List<ItemClass> retrieve() {
        Query query = em.createNamedQuery("ItemClass.findAll");
        return query.getResultList();
    }

    @Override
    public ItemClass update(ItemClass entity) {
        try {
            ItemClass c = em.merge(entity);
            return c;
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }
}
