/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.mbi.ManagedBeanDataQuery;
import cn.drizzle.ejb.ItemClassSessionBean;
import cn.drizzle.entity.ItemClass;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class ItemClassQueryBean implements ManagedBeanDataQuery<ItemClass> {

    @EJB
    private ItemClassSessionBean itemClassSessionBean;
    private List<ItemClass> itemClassOnes;
    private List<ItemClass> itemClassTwos;
    private List<ItemClass> itemClassThrees;

    /**
     * Creates a new instance of ItemClassOneManagedBean
     */
    public ItemClassQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getItemClassOnes() != null) {
            getItemClassOnes().clear();
        }
        if (getItemClassTwos() != null) {
            getItemClassTwos().clear();
        }
        if (getItemClassThrees() != null) {
            getItemClassThrees().clear();
        }
    }

    @Override
    public void init() {
        itemClassOnes = itemClassSessionBean.getByClassLevel("1");
        itemClassTwos = itemClassSessionBean.getByClassLevel("2");
        itemClassThrees = itemClassSessionBean.getByClassLevel("3");
    }

    /**
     * @return the itemClassOnes
     */
    public List<ItemClass> getItemClassOnes() {
        return itemClassOnes;
    }

    /**
     * @param itemClassOnes the itemClassOnes to set
     */
    public void setItemClassOnes(List<ItemClass> itemClassOnes) {
        this.itemClassOnes = itemClassOnes;
    }

    /**
     * @return the itemClassTwos
     */
    public List<ItemClass> getItemClassTwos() {
        return itemClassTwos;
    }

    /**
     * @param itemClassTwos the itemClassTwos to set
     */
    public void setItemClassTwos(List<ItemClass> itemClassTwos) {
        this.itemClassTwos = itemClassTwos;
    }

    /**
     * @return the itemClassThrees
     */
    public List<ItemClass> getItemClassThrees() {
        return itemClassThrees;
    }

    /**
     * @param itemClassThrees the itemClassThrees to set
     */
    public void setItemClassThrees(List<ItemClass> itemClassThrees) {
        this.itemClassThrees = itemClassThrees;
    }
}
