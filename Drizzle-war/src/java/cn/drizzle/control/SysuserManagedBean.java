/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.mbi.ManagedBeanDataOperation;
import cn.drizzle.ejb.SysuserSessionBean;
import cn.drizzle.entity.Sysuser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SysuserManagedBean implements ManagedBeanDataOperation<Sysuser> {

    @EJB
    private SysuserSessionBean sysuserSessionBean;
    private Sysuser currentSysuser;
    private Sysuser newSysuser;
    private List<Sysuser> sysusers;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public SysuserManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destroy() {
        if (getSysusers() != null) {
            getSysusers().clear();
            setSysusers(null);
        }
        setCurrentSysuser(null);
        setNewSysuser(null);
    }

    @Override
    public void init() {
        try {
            setSysusers(retrieve());
            if (!sysusers.isEmpty()) {
                setCurrentSysuser(getSysusers().get(0));
            }
        } catch (Exception e) {
            throw new Error(e.getLocalizedMessage());
        }
    }

    @Override
    public void create() {
        Sysuser entity = new Sysuser();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewSysuser(entity);
    }

    @Override
    public void delete(Sysuser entity) {
        if (entity != null) {
            try {
                sysuserSessionBean.delete(entity);
                if (getSysusers().contains(entity)) {
                    getSysusers().remove(entity);
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
    public void edit(Sysuser entity) {
        if (entity != null) {
            setCurrentSysuser(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewSysuser() != null) {
            try {
                sysuserSessionBean.update(getNewSysuser());
                if (!getSysusers().contains(getNewSysuser())) {
                    getSysusers().add(getNewSysuser());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Sysuser> retrieve() {
        return sysuserSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentSysuser() != null) {
            try {
                getCurrentSysuser().setStatus("M");
                getCurrentSysuser().setOptuser(userManagedBean.getUserid());
                getCurrentSysuser().setOptdate(userManagedBean.getDate());
                sysuserSessionBean.update(getCurrentSysuser());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getMessage());
            }
            init();
        }
    }

    @Override
    public void view(Sysuser entity) {
        if (entity != null) {
            setCurrentSysuser(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentSysuser() != null) {
            try {
                getCurrentSysuser().setStatus("V");
                getCurrentSysuser().setCfmuser(userManagedBean.getUserid());
                getCurrentSysuser().setCfmdate(userManagedBean.getDate());
                sysuserSessionBean.update(getCurrentSysuser());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentSysuser() != null) {
            try {
                getCurrentSysuser().setStatus("M");
                getCurrentSysuser().setOptuser(userManagedBean.getUserid());
                getCurrentSysuser().setOptdate(userManagedBean.getDate());
                getCurrentSysuser().setCfmuser(null);
                getCurrentSysuser().setCfmdate(null);
                sysuserSessionBean.update(getCurrentSysuser());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getMessage());
            }
        }
    }

    /**
     * @return the currentSysuser
     */
    public Sysuser getCurrentSysuser() {
        return currentSysuser;
    }

    /**
     * @param currentSysuser the currentSysuser to set
     */
    public void setCurrentSysuser(Sysuser currentSysuser) {
        this.currentSysuser = currentSysuser;
    }

    /**
     * @return the newSysuser
     */
    public Sysuser getNewSysuser() {
        return newSysuser;
    }

    /**
     * @param newSysuser the newSysuser to set
     */
    public void setNewSysuser(Sysuser newSysuser) {
        this.newSysuser = newSysuser;
    }

    /**
     * @return the sysusers
     */
    public List<Sysuser> getSysusers() {
        return sysusers;
    }

    /**
     * @param sysusers the sysusers to set
     */
    public void setSysusers(List<Sysuser> sysusers) {
        this.sysusers = sysusers;
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

    @Override
    public void sendNotification(Sysuser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
