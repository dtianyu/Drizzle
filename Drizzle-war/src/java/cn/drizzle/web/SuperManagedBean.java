/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.web;

import cn.drizzle.comm.SuperEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kevintung
 * @param <T>
 */
public abstract class SuperManagedBean<T> implements Serializable {

    protected SuperEJB sessionBean;

    protected T currentEntity;
    protected T newEntity;
    protected List<T> entityList;
    protected LazyDataModel model;

    public SuperManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getEntityList() != null) {
            getEntityList().clear();
            setEntityList(null);
        }
        setCurrentEntity(null);
        setNewEntity(null);
    }

    public abstract void init();

    public abstract void create();

    public abstract void verify();

    public abstract void unverify();

    public void delete(T entity) {
        if (entity != null) {
            try {
                getSessionBean().delete(entity);
                if (getEntityList().contains(entity)) {
                    getEntityList().remove(entity);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("错误", e.getMessage()));
            }
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "删除成功！"));
        }
    }

    public void edit(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    public void persist() {
        if (getNewEntity() != null) {
            try {
                getSessionBean().persist(getNewEntity());
                if (!getEntityList().contains(getNewEntity())) {
                    getEntityList().add(getNewEntity());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("错误", e.getMessage()));
            }
            setNewEntity(null);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "更新成功！"));
        }
    }

    public void sendNotification(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update() {
        if (currentEntity != null) {
            try {
                getSessionBean().update(currentEntity);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("错误", e.toString()));
            }
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "更新成功！"));
        }
    }

    public void view(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    /**
     * @return the currentEntity
     */
    public T getCurrentEntity() {
        return currentEntity;
    }

    /**
     * @param currentEntity the currentEntity to set
     */
    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }

    /**
     * @return the newEntity
     */
    public T getNewEntity() {
        return newEntity;
    }

    /**
     * @param newEntity the newEntity to set
     */
    public void setNewEntity(T newEntity) {
        this.newEntity = newEntity;
    }

    /**
     * @return the entityList
     */
    public List<T> getEntityList() {
        return entityList;
    }

    /**
     * @param entityList the entityList to set
     */
    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    /**
     * @return the sessionBean
     */
    public SuperEJB getSessionBean() {
        return sessionBean;
    }

    /**
     * @param sessionBean the sessionBean to set
     */
    public void setSessionBean(SuperEJB sessionBean) {
        this.sessionBean = sessionBean;
    }

    /**
     * @return the model
     */
    public LazyDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LazyDataModel model) {
        this.model = model;
    }

}
