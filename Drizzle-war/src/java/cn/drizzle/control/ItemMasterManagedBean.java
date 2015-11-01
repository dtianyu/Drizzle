/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.ItemClassSessionBean;
import cn.drizzle.ejb.ItemMasterSessionBean;
import cn.drizzle.entity.ItemClass;
import cn.drizzle.entity.ItemMaster;
import cn.drizzle.lazy.ItemMasterModel;
import cn.drizzle.web.SuperManagedBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class ItemMasterManagedBean extends SuperManagedBean<ItemMaster> {

    @EJB
    protected ItemMasterSessionBean itemMasterSessionBean;
    @EJB
    private ItemClassSessionBean itemClassSessionBean;
    private List<ItemClass> itemClassOnes;
    private List<ItemClass> itemClassTwos;
    private List<ItemClass> itemClassThrees;
    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    /**
     * Creates a new instance of ItemMasterManagedBean
     */
    public ItemMasterManagedBean() {

    }

    @Override
    public void init() {
        setSessionBean(itemMasterSessionBean);
        setModel(new ItemMasterModel(itemMasterSessionBean));
        setItemClassOnes(itemClassSessionBean.getByClassLevel("1"));
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            ItemMaster entity = new ItemMaster();
            entity.setStatus("N");
            entity.setCreator(userManagedBean.getUserid());
            entity.setCredate(userManagedBean.getDate());
            entity.setOptuser(userManagedBean.getUserid());
            entity.setOptdate(userManagedBean.getDate());
            setNewEntity(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentEntity() != null) {
            getCurrentEntity().setStatus("V");
            getCurrentEntity().setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
            getCurrentEntity().setCfmdate(getUserManagedBean().getDate());
            update();
        }
    }

    @Override
    public void unverify() {
        if (getCurrentEntity() != null) {
            getCurrentEntity().setStatus("M");
            getCurrentEntity().setOptuser(getUserManagedBean().getCurrentUser().getUserid());
            getCurrentEntity().setOptdate(getUserManagedBean().getDate());
            getCurrentEntity().setCfmuser(null);
            getCurrentEntity().setCfmdate(null);
            update();
        }
    }

    public void handleNewItemClassOneChanged() {
        if (getNewEntity() != null) {
            newEntity.setProperty(getNewEntity().getClass1().getProperty());
            newEntity.setMaketype(getNewEntity().getClass1().getMaketype());
            newEntity.setPotype(getNewEntity().getClass1().getPotype());
            newEntity.setAutono(getNewEntity().getClass1().getAutono());
            newEntity.setInvtype(getNewEntity().getClass1().getInvtype());
            newEntity.setUnittype(getNewEntity().getClass1().getUnittype());
            newEntity.setBbstype(getNewEntity().getClass1().getBbstype());
            newEntity.setClass2(null);
            newEntity.setClass3(null);
            if (getItemClassTwos() != null) {
                getItemClassTwos().clear();
            }
            setItemClassTwos(getItemClassSessionBean().getByPId(getNewEntity().getClass1().getClassid()));
            if (getItemClassThrees() != null) {
                getItemClassThrees().clear();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息","未选择任何明细资料"));
        }
    }

    public void handleNewItemClassTwoChanged() {
        if (getNewEntity() != null) {
            newEntity.setProperty(getNewEntity().getClass2().getProperty());
            newEntity.setMaketype(getNewEntity().getClass2().getMaketype());
            newEntity.setPotype(getNewEntity().getClass2().getPotype());
            newEntity.setAutono(getNewEntity().getClass2().getAutono());
            newEntity.setInvtype(getNewEntity().getClass2().getInvtype());
            newEntity.setUnittype(getNewEntity().getClass2().getUnittype());
            newEntity.setBbstype(getNewEntity().getClass2().getBbstype());
            newEntity.setClass3(null);
            if (getItemClassThrees() != null) {
                getItemClassThrees().clear();
            }
            setItemClassThrees(getItemClassSessionBean().getByPId(getNewEntity().getClass2().getClassid()));
        }
    }

    public void handleNewItemClassThreeChanged() {
        if (getNewEntity() != null) {
            newEntity.setProperty(getNewEntity().getClass3().getProperty());
            newEntity.setMaketype(getNewEntity().getClass3().getMaketype());
            newEntity.setPotype(getNewEntity().getClass3().getPotype());
            newEntity.setAutono(getNewEntity().getClass3().getAutono());
            newEntity.setInvtype(getNewEntity().getClass3().getInvtype());
            newEntity.setUnittype(getNewEntity().getClass3().getUnittype());
            newEntity.setBbstype(getNewEntity().getClass3().getBbstype());
        }
    }

    public void handleNewItemUnitTypeChanged() {
        if (getNewEntity() != null) {
        }
    }

    /**
     * @return the itemClassSessionBean
     */
    public ItemClassSessionBean getItemClassSessionBean() {
        return itemClassSessionBean;
    }

    /**
     * @param itemClassSessionBean the itemClassSessionBean to set
     */
    public void setItemClassSessionBean(ItemClassSessionBean itemClassSessionBean) {
        this.itemClassSessionBean = itemClassSessionBean;
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

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    /**
     * @return the itemMasterSessionBean
     */
    public ItemMasterSessionBean getItemMasterSessionBean() {
        return itemMasterSessionBean;
    }

    /**
     * @param itemMasterSessionBean the itemMasterSessionBean to set
     */
    public void setItemMasterSessionBean(ItemMasterSessionBean itemMasterSessionBean) {
        this.itemMasterSessionBean = itemMasterSessionBean;
    }

}
