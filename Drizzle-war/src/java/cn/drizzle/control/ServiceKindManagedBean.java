/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.ServiceKindSessionBean;
import cn.drizzle.entity.ServiceKind;
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
public class ServiceKindManagedBean implements ManagedBeanDataOperation<ServiceKind> {

    @EJB
    private ServiceKindSessionBean serviceKindSessionBean;
    private ServiceKind currentServiceKind;
    private ServiceKind newServiceKind;
    private List<ServiceKind> serviceKinds;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ServiceKindManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getServiceKinds() != null) {
            getServiceKinds().clear();
            setServiceKinds(null);
        }
        setCurrentServiceKind(null);
        setNewServiceKind(null);
    }

    @Override
    public void init() {
        setServiceKinds(retrieve());
        if (!getServiceKinds().isEmpty()) {
            setCurrentServiceKind(getServiceKinds().get(0));
        }
    }

    @Override
    public void create() {
        ServiceKind entity = new ServiceKind();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewServiceKind(entity);
    }

    @Override
    public void delete(ServiceKind entity) {
        if (entity != null) {
            try {
                serviceKindSessionBean.delete(entity);
                if (getServiceKinds().contains(entity)) {
                    getServiceKinds().remove(entity);
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
    public void edit(ServiceKind entity) {
        if (entity != null) {
            setCurrentServiceKind(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewServiceKind() != null) {
            try {
                serviceKindSessionBean.update(getNewServiceKind());
                if (!getServiceKinds().contains(getNewServiceKind())) {
                    getServiceKinds().add(getNewServiceKind());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ServiceKind> retrieve() {
        return serviceKindSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentServiceKind() != null) {
            try {
                getCurrentServiceKind().setStatus("M");
                getCurrentServiceKind().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceKind().setOptdate(userManagedBean.getDate());
                serviceKindSessionBean.update(getCurrentServiceKind());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ServiceKind entity) {
        if (entity != null) {
            setCurrentServiceKind(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentServiceKind() != null) {
            try {
                getCurrentServiceKind().setStatus("V");
                getCurrentServiceKind().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentServiceKind().setCfmdate(userManagedBean.getDate());
                serviceKindSessionBean.update(getCurrentServiceKind());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentServiceKind() != null) {
            try {
                getCurrentServiceKind().setStatus("M");
                getCurrentServiceKind().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceKind().setOptdate(userManagedBean.getDate());
                getCurrentServiceKind().setCfmuser(null);
                getCurrentServiceKind().setCfmdate(null);
                serviceKindSessionBean.update(getCurrentServiceKind());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentServiceKind
     */
    public ServiceKind getCurrentServiceKind() {
        return currentServiceKind;
    }

    /**
     * @param currentServiceKind the currentServiceKind to set
     */
    public void setCurrentServiceKind(ServiceKind currentServiceKind) {
        this.currentServiceKind = currentServiceKind;
    }

    /**
     * @return the newServiceKind
     */
    public ServiceKind getNewServiceKind() {
        return newServiceKind;
    }

    /**
     * @param newServiceKind the newServiceKind to set
     */
    public void setNewServiceKind(ServiceKind newServiceKind) {
        this.newServiceKind = newServiceKind;
    }

    /**
     * @return the serviceKinds
     */
    public List<ServiceKind> getServiceKinds() {
        return serviceKinds;
    }

    /**
     * @param serviceKinds the serviceKinds to set
     */
    public void setServiceKinds(List<ServiceKind> serviceKinds) {
        this.serviceKinds = serviceKinds;
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
    public void sendNotification(ServiceKind entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
