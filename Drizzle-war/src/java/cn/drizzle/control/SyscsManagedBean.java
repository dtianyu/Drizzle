/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.SyscsSessionBean;
import cn.drizzle.entity.Syscs;
import cn.drizzle.mbi.ManagedBeanDataOperation;
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
public class SyscsManagedBean implements ManagedBeanDataOperation<Syscs> {

    @EJB
    private SyscsSessionBean syscsSessionBean;
    private Syscs currentSyscs;
    private Syscs newSyscs;
    private List<Syscs> syscsList;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * Creates a new instance of SyscsManagedBean
     */
    public SyscsManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destroy() {
        if (getSyscsList() != null) {
            getSyscsList().clear();
            setSyscsList(null);
        }
        setCurrentSyscs(null);
        setNewSyscs(null);
    }

    @Override
    public void init() {
        try {
            setSyscsList(retrieve());
            if (!syscsList.isEmpty()) {
                setCurrentSyscs(getSyscsList().get(0));
            }
        } catch (Exception ex) {
            throw new Error(ex.getLocalizedMessage());
        }
    }

    @Override
    public void create() {
        Syscs entity = new Syscs();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        entity.setCompanyid(userManagedBean.getCompany());
        setNewSyscs(entity);
    }

    @Override
    public void delete(Syscs entity) {
        if (entity != null) {
            try {
                syscsSessionBean.delete(entity);
                if (getSyscsList().contains(entity)) {
                    getSyscsList().remove(entity);
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
    public void edit(Syscs entity) {
        if (entity != null) {
            setCurrentSyscs(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewSyscs() != null) {
            try {
                syscsSessionBean.update(getNewSyscs());
                if (!getSyscsList().contains(getNewSyscs())) {
                    getSyscsList().add(getNewSyscs());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Syscs> retrieve() {
        return syscsSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentSyscs() != null) {
            try {
                getCurrentSyscs().setStatus("M");
                getCurrentSyscs().setOptuser(userManagedBean.getUserid());
                getCurrentSyscs().setOptdate(userManagedBean.getDate());
                syscsSessionBean.update(getCurrentSyscs());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Syscs entity) {
        if (entity != null) {
            setCurrentSyscs(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentSyscs() != null) {
            try {
                getCurrentSyscs().setStatus("V");
                getCurrentSyscs().setCfmuser(userManagedBean.getUserid());
                getCurrentSyscs().setCfmdate(userManagedBean.getDate());
                syscsSessionBean.update(getCurrentSyscs());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentSyscs() != null) {
            try {
                getCurrentSyscs().setStatus("M");
                getCurrentSyscs().setOptuser(userManagedBean.getUserid());
                getCurrentSyscs().setOptdate(userManagedBean.getDate());
                getCurrentSyscs().setCfmuser(null);
                getCurrentSyscs().setCfmdate(null);
                syscsSessionBean.update(getCurrentSyscs());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentSyscs
     */
    public Syscs getCurrentSyscs() {
        return currentSyscs;
    }

    /**
     * @param currentSyscs the currentSyscs to set
     */
    public void setCurrentSyscs(Syscs currentSyscs) {
        this.currentSyscs = currentSyscs;
    }

    /**
     * @return the newSyscs
     */
    public Syscs getNewSyscs() {
        return newSyscs;
    }

    /**
     * @param newSyscs the newSyscs to set
     */
    public void setNewSyscs(Syscs newSyscs) {
        this.newSyscs = newSyscs;
    }

    /**
     * @return the syscsList
     */
    public List<Syscs> getSyscsList() {
        return syscsList;
    }

    /**
     * @param syscsList the syscsList to set
     */
    public void setSyscsList(List<Syscs> syscsList) {
        this.syscsList = syscsList;
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
    public void sendNotification(Syscs entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
