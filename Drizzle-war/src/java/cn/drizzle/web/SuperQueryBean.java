/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kevintung
 * @param <T>
 */
public abstract class SuperQueryBean<T> {

    protected T currentEntity;
    protected List<T> topList;
    protected T prev;
    protected T next;
    protected boolean hasPrev;
    protected boolean hasNext;
    protected LazyDataModel model;
    @ManagedProperty(value = "#{param.id}")
    protected String id;
    
    public SuperQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getTopList() != null) {
            getTopList().clear();
            setTopList(null);
        }
        setCurrentEntity(null);
    }

    public abstract void init();

    public abstract String viewList();

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
     * @return the topNews
     */
    public List<T> getTopList() {
        return this.topList;
    }

    /**
     * @param topList the topNews to set
     */
    public void setTopList(List<T> topList) {
        this.topList = topList;
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

    /**
     * @return the prev
     */
    public T getPrev() {
        return prev;
    }

    /**
     * @param prev the prev to set
     */
    public void setPrev(T prev) {
        this.prev = prev;
    }

    /**
     * @return the next
     */
    public T getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(T next) {
        this.next = next;
    }

    /**
     * @return the hasPrev
     */
    public boolean isHasPrev() {
        return hasPrev;
    }

    /**
     * @param hasPrev the hasPrev to set
     */
    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    /**
     * @return the hasNext
     */
    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * @param hasNext the hasNext to set
     */
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
