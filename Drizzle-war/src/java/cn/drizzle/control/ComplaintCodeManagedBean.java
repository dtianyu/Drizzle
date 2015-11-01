/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.ComplaintCodeSessionBean;
import cn.drizzle.entity.ComplaintCode;
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
public class ComplaintCodeManagedBean implements ManagedBeanDataOperation<ComplaintCode> {

    @EJB
    private ComplaintCodeSessionBean complaintCodeSessionBean;
    private ComplaintCode currentComplaintCode;
    private ComplaintCode newComplaintCode;
    private List<ComplaintCode> complaintCodes;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ComplaintCodeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getComplaintCodes() != null) {
            getComplaintCodes().clear();
            setComplaintCodes(null);
        }
        setCurrentComplaintCode(null);
        setNewComplaintCode(null);
    }

    @Override
    public void init() {
        setComplaintCodes(retrieve());
        if (!getComplaintCodes().isEmpty()) {
            setCurrentComplaintCode(getComplaintCodes().get(0));
        }
    }

    @Override
    public void create() {
        ComplaintCode entity = new ComplaintCode();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewComplaintCode(entity);
    }

    @Override
    public void delete(ComplaintCode entity) {
        if (entity != null) {
            try {
                complaintCodeSessionBean.delete(entity);
                if (getComplaintCodes().contains(entity)) {
                    getComplaintCodes().remove(entity);
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
    public void edit(ComplaintCode entity) {
        if (entity != null) {
            setCurrentComplaintCode(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewComplaintCode() != null) {
            try {
                complaintCodeSessionBean.update(getNewComplaintCode());
                if (!getComplaintCodes().contains(getNewComplaintCode())) {
                    getComplaintCodes().add(getNewComplaintCode());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ComplaintCode> retrieve() {
        return complaintCodeSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentComplaintCode() != null) {
            try {
                getCurrentComplaintCode().setStatus("M");
                getCurrentComplaintCode().setOptuser(userManagedBean.getUserid());
                getCurrentComplaintCode().setOptdate(userManagedBean.getDate());
                complaintCodeSessionBean.update(getCurrentComplaintCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ComplaintCode entity) {
        if (entity != null) {
            setCurrentComplaintCode(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentComplaintCode() != null) {
            try {
                getCurrentComplaintCode().setStatus("V");
                getCurrentComplaintCode().setCfmuser(userManagedBean.getUserid());
                getCurrentComplaintCode().setCfmdate(userManagedBean.getDate());
                complaintCodeSessionBean.update(getCurrentComplaintCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentComplaintCode() != null) {
            try {
                getCurrentComplaintCode().setStatus("M");
                getCurrentComplaintCode().setOptuser(userManagedBean.getUserid());
                getCurrentComplaintCode().setOptdate(userManagedBean.getDate());
                getCurrentComplaintCode().setCfmuser(null);
                getCurrentComplaintCode().setCfmdate(null);
                complaintCodeSessionBean.update(getCurrentComplaintCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentComplaintCode
     */
    public ComplaintCode getCurrentComplaintCode() {
        return currentComplaintCode;
    }

    /**
     * @param currentComplaintCode the currentComplaintCode to set
     */
    public void setCurrentComplaintCode(ComplaintCode currentComplaintCode) {
        this.currentComplaintCode = currentComplaintCode;
    }

    /**
     * @return the newComplaintCode
     */
    public ComplaintCode getNewComplaintCode() {
        return newComplaintCode;
    }

    /**
     * @param newComplaintCode the newComplaintCode to set
     */
    public void setNewComplaintCode(ComplaintCode newComplaintCode) {
        this.newComplaintCode = newComplaintCode;
    }

    /**
     * @return the complaintCodes
     */
    public List<ComplaintCode> getComplaintCodes() {
        return complaintCodes;
    }

    /**
     * @param complaintCodes the complaintCodes to set
     */
    public void setComplaintCodes(List<ComplaintCode> complaintCodes) {
        this.complaintCodes = complaintCodes;
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
    public void sendNotification(ComplaintCode entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
