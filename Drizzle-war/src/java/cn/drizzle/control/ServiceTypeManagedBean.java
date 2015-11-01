/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.ServiceTypeSessionBean;
import cn.drizzle.entity.ServiceType;
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
public class ServiceTypeManagedBean implements ManagedBeanDataOperation<ServiceType> {

    @EJB
    private ServiceTypeSessionBean serviceTypeSessionBean;
    private ServiceType currentServiceType;
    private ServiceType newServiceType;
    private List<ServiceType> serviceTypes;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ServiceTypeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getServiceTypes() != null) {
            getServiceTypes().clear();
            setServiceTypes(null);
        }
        setCurrentServiceType(null);
        setNewServiceType(null);
    }

    @Override
    public void init() {
        setServiceTypes(retrieve());
        if (!getServiceTypes().isEmpty()) {
            setCurrentServiceType(getServiceTypes().get(0));
        }
    }

    @Override
    public void create() {
        ServiceType entity = new ServiceType();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewServiceType(entity);
    }

    @Override
    public void delete(ServiceType entity) {
        if (entity != null) {
            try {
                serviceTypeSessionBean.delete(entity);
                if (getServiceTypes().contains(entity)) {
                    getServiceTypes().remove(entity);
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
    public void edit(ServiceType entity) {
        if (entity != null) {
            setCurrentServiceType(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewServiceType() != null) {
            try {
                serviceTypeSessionBean.update(getNewServiceType());
                if (!getServiceTypes().contains(getNewServiceType())) {
                    getServiceTypes().add(getNewServiceType());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ServiceType> retrieve() {
        return serviceTypeSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentServiceType() != null) {
            try {
                getCurrentServiceType().setStatus("M");
                getCurrentServiceType().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceType().setOptdate(userManagedBean.getDate());
                serviceTypeSessionBean.update(getCurrentServiceType());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ServiceType entity) {
        if (entity != null) {
            setCurrentServiceType(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentServiceType() != null) {
            try {
                getCurrentServiceType().setStatus("V");
                getCurrentServiceType().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentServiceType().setCfmdate(userManagedBean.getDate());
                serviceTypeSessionBean.update(getCurrentServiceType());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentServiceType() != null) {
            try {
                getCurrentServiceType().setStatus("M");
                getCurrentServiceType().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceType().setOptdate(userManagedBean.getDate());
                getCurrentServiceType().setCfmuser(null);
                getCurrentServiceType().setCfmdate(null);
                serviceTypeSessionBean.update(getCurrentServiceType());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentServiceType
     */
    public ServiceType getCurrentServiceType() {
        return currentServiceType;
    }

    /**
     * @param currentServiceType the currentServiceType to set
     */
    public void setCurrentServiceType(ServiceType currentServiceType) {
        this.currentServiceType = currentServiceType;
    }

    /**
     * @return the newServiceType
     */
    public ServiceType getNewServiceType() {
        return newServiceType;
    }

    /**
     * @param newServiceType the newServiceType to set
     */
    public void setNewServiceType(ServiceType newServiceType) {
        this.newServiceType = newServiceType;
    }

    /**
     * @return the serviceTypes
     */
    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    /**
     * @param serviceTypes the serviceTypes to set
     */
    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
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
    public void sendNotification(ServiceType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
