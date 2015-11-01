/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.ComplaintClassSessionBean;
import cn.drizzle.entity.ComplaintClass;
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
public class ComplaintClassManagedBean implements ManagedBeanDataOperation<ComplaintClass> {

    @EJB
    private ComplaintClassSessionBean complaintClassSessionBean;
    private ComplaintClass currentComplaintClass;
    private ComplaintClass newComplaintClass;
    private List<ComplaintClass> complaintClasses;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ComplaintClassManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getComplaintClasses() != null) {
            getComplaintClasses().clear();
            setComplaintClasses(null);
        }
        setCurrentComplaintClass(null);
        setNewComplaintClass(null);
    }

    @Override
    public void init() {
        setComplaintClasses(retrieve());
        if (!getComplaintClasses().isEmpty()) {
            setCurrentComplaintClass(getComplaintClasses().get(0));
        }
    }

    @Override
    public void create() {
        ComplaintClass entity = new ComplaintClass();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewComplaintClass(entity);
    }

    @Override
    public void delete(ComplaintClass entity) {
        if (entity != null) {
            try {
                complaintClassSessionBean.delete(entity);
                if (getComplaintClasses().contains(entity)) {
                    getComplaintClasses().remove(entity);
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
    public void edit(ComplaintClass entity) {
        if (entity != null) {
            setCurrentComplaintClass(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewComplaintClass() != null) {
            try {
                complaintClassSessionBean.update(getNewComplaintClass());
                if (!getComplaintClasses().contains(getNewComplaintClass())) {
                    getComplaintClasses().add(getNewComplaintClass());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ComplaintClass> retrieve() {
        return complaintClassSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentComplaintClass() != null) {
            try {
                getCurrentComplaintClass().setStatus("M");
                getCurrentComplaintClass().setOptuser(userManagedBean.getUserid());
                getCurrentComplaintClass().setOptdate(userManagedBean.getDate());
                complaintClassSessionBean.update(getCurrentComplaintClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ComplaintClass entity) {
        if (entity != null) {
            setCurrentComplaintClass(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentComplaintClass() != null) {
            try {
                getCurrentComplaintClass().setStatus("V");
                getCurrentComplaintClass().setCfmuser(userManagedBean.getUserid());
                getCurrentComplaintClass().setCfmdate(userManagedBean.getDate());
                complaintClassSessionBean.update(getCurrentComplaintClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentComplaintClass() != null) {
            try {
                getCurrentComplaintClass().setStatus("M");
                getCurrentComplaintClass().setOptuser(userManagedBean.getUserid());
                getCurrentComplaintClass().setOptdate(userManagedBean.getDate());
                getCurrentComplaintClass().setCfmuser(null);
                getCurrentComplaintClass().setCfmdate(null);
                complaintClassSessionBean.update(getCurrentComplaintClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentComplaintClass
     */
    public ComplaintClass getCurrentComplaintClass() {
        return currentComplaintClass;
    }

    /**
     * @param currentComplaintClass the currentComplaintClass to set
     */
    public void setCurrentComplaintClass(ComplaintClass currentComplaintClass) {
        this.currentComplaintClass = currentComplaintClass;
    }

    /**
     * @return the newComplaintClass
     */
    public ComplaintClass getNewComplaintClass() {
        return newComplaintClass;
    }

    /**
     * @param newComplaintClass the newComplaintClass to set
     */
    public void setNewComplaintClass(ComplaintClass newComplaintClass) {
        this.newComplaintClass = newComplaintClass;
    }

    /**
     * @return the complaintClasses
     */
    public List<ComplaintClass> getComplaintClasses() {
        return complaintClasses;
    }

    /**
     * @param complaintClasses the complaintClasses to set
     */
    public void setComplaintClasses(List<ComplaintClass> complaintClasses) {
        this.complaintClasses = complaintClasses;
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
    public void sendNotification(ComplaintClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
