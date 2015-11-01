/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.mbi.ManagedBeanDataQuery;
import cn.drizzle.ejb.ItemMasterSessionBean;
import cn.drizzle.entity.ItemMaster;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author StevenTung
 */
@ManagedBean
@RequestScoped
public class ItemMasterQueryBean implements ManagedBeanDataQuery<ItemMaster> {

    @EJB
    private ItemMasterSessionBean itemMasterSessionBean;
    private List<ItemMaster> itemMasters;
    private List<ItemMaster> itemMasterList;

    public ItemMasterQueryBean() {
    }

    @PostConstruct
    public void construct() {
        itemMasterList = new ArrayList<>();
        init();
    }

    @PreDestroy
    public void destory() {
        if (getItemMasterList() != null) {
            getItemMasterList().clear();
            setItemMasterList(null);
        }
        if (getItemMasters() != null) {
            getItemMasters().clear();
            setItemMasters(null);
        }
    }

    @Override
    public void init() {
        setItemMasters(itemMasterSessionBean.findAll(0, 15));
    }

    public List<ItemMaster> autoCompleteItemMasters(String query) {
        for (ItemMaster itemmaster : itemMasters) {
            if (itemmaster.getItemno().startsWith(query) || itemmaster.getItemcode().startsWith(query) || itemmaster.getItemdesc().startsWith(query)) {
                getItemMasterList().add(itemmaster);
            }
        }
        return itemMasterList;
    }

    /**
     * @return the itemMasterList
     */
    public List<ItemMaster> getItemMasterList() {
        return itemMasterList;
    }

    /**
     * @param itemMasterList the itemMasterList to set
     */
    public void setItemMasterList(List<ItemMaster> itemMasterList) {
        this.itemMasterList = itemMasterList;
    }

    /**
     * @return the itemMasters
     */
    public List<ItemMaster> getItemMasters() {
        return itemMasters;
    }

    /**
     * @param itemMasters the itemMasters to set
     */
    public void setItemMasters(List<ItemMaster> itemMasters) {
        this.itemMasters = itemMasters;
    }
}
