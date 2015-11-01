/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.mbi.ManagedBeanDataOperation;
import cn.drizzle.ejb.ItemClassSessionBean;
import cn.drizzle.entity.ItemClass;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class ItemClassManagedBean implements ManagedBeanDataOperation<ItemClass> {

    @EJB
    private ItemClassSessionBean itemClassSessionBean;
    private ItemClass currentItemClass;
    private ItemClass newItemClass;
    private List<ItemClass> itemClasses;
    private List<ItemClass> parentItemClasses;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ItemClassManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getItemClasses() != null) {
            getItemClasses().clear();
            setItemClasses(null);
        }
        setCurrentItemClass(null);
        setNewItemClass(null);
    }

    @Override
    public void init() {
        setItemClasses(retrieve());
        if (!getItemClasses().isEmpty()) {
            setCurrentItemClass(getItemClasses().get(0));
        }
        parentItemClasses = itemClassSessionBean.retrieve();
    }

    @Override
    public void create() {
        ItemClass entity = new ItemClass();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewItemClass(entity);
    }

    @Override
    public void delete(ItemClass entity) {
        if (entity != null) {
            try {
                itemClassSessionBean.delete(entity);
                if (getItemClasses().contains(entity)) {
                    getItemClasses().remove(entity);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("没有选中任何资料！"));
        }
    }

    @Override
    public void edit(ItemClass entity) {
        if (entity != null) {
            setCurrentItemClass(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewItemClass() != null) {
            try {
                itemClassSessionBean.update(getNewItemClass());
                if (!getItemClasses().contains(getNewItemClass())) {
                    getItemClasses().add(getNewItemClass());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ItemClass> retrieve() {
        return itemClassSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentItemClass() != null) {
            try {
                getCurrentItemClass().setStatus("M");
                getCurrentItemClass().setOptuser(userManagedBean.getUserid());
                getCurrentItemClass().setOptdate(userManagedBean.getDate());
                itemClassSessionBean.update(getCurrentItemClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ItemClass entity) {
        if (entity != null) {
            setCurrentItemClass(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentItemClass() != null) {
            try {
                getCurrentItemClass().setStatus("V");
                getCurrentItemClass().setCfmuser(userManagedBean.getUserid());
                getCurrentItemClass().setCfmdate(userManagedBean.getDate());
                itemClassSessionBean.update(getCurrentItemClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentItemClass() != null) {
            try {
                getCurrentItemClass().setStatus("M");
                getCurrentItemClass().setOptuser(userManagedBean.getUserid());
                getCurrentItemClass().setOptdate(userManagedBean.getDate());
                getCurrentItemClass().setCfmuser(null);
                getCurrentItemClass().setCfmdate(null);
                itemClassSessionBean.update(getCurrentItemClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    public void handleNewItemClassLevelChanged() {
        if (getNewItemClass() != null) {
            switch (getNewItemClass().getClasslevel()) {
                case "1":
                    parentItemClasses.clear();
                    break;
                case "2":
                    parentItemClasses = itemClassSessionBean.getByClassLevel("1");
                    break;
                case "3":
                    parentItemClasses = itemClassSessionBean.getByClassLevel("2");
                    break;
            }
        }
    }

    public void handleCurItemClassLevelChanged() {
        if (getCurrentItemClass() != null) {
            switch (getCurrentItemClass().getClasslevel()) {
                case "1":
                    parentItemClasses.clear();
                    break;
                case "2":
                    parentItemClasses = itemClassSessionBean.getByClassLevel("1");
                    break;
                case "3":
                    parentItemClasses = itemClassSessionBean.getByClassLevel("2");
                    break;
            }
        }
    }

    /**
     * @return the currentItemClass
     */
    public ItemClass getCurrentItemClass() {
        return currentItemClass;
    }

    /**
     * @param currentItemClass the currentItemClass to set
     */
    public void setCurrentItemClass(ItemClass currentItemClass) {
        this.currentItemClass = currentItemClass;
    }

    /**
     * @return the newItemClass
     */
    public ItemClass getNewItemClass() {
        return newItemClass;
    }

    /**
     * @param newItemClass the newItemClass to set
     */
    public void setNewItemClass(ItemClass newItemClass) {
        this.newItemClass = newItemClass;
    }

    /**
     * @return the itemClasses
     */
    public List<ItemClass> getItemClasses() {
        return itemClasses;
    }

    /**
     * @param itemClasses the itemClasses to set
     */
    public void setItemClasses(List<ItemClass> itemClasses) {
        this.itemClasses = itemClasses;
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
     * @return the parentItemClasses
     */
    public List<ItemClass> getParentItemClasses() {
        return parentItemClasses;
    }

    /**
     * @param parentItemClasses the parentItemClasses to set
     */
    public void setParentItemClasses(List<ItemClass> parentItemClasses) {
        this.parentItemClasses = parentItemClasses;
    }

    @Override
    public void sendNotification(ItemClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
