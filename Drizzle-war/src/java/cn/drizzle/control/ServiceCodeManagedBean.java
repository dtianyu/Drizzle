/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;


import cn.drizzle.ejb.ServiceCodeSessionBean;
import cn.drizzle.entity.ServiceCode;
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
public class ServiceCodeManagedBean implements ManagedBeanDataOperation<ServiceCode> {

    @EJB
    private ServiceCodeSessionBean serviceCodeSessionBean;
    private ServiceCode currentServiceCode;
    private ServiceCode newServiceCode;
    private List<ServiceCode> serviceCodes;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ServiceCodeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getServiceCodes() != null) {
            getServiceCodes().clear();
            setServiceCodes(null);
        }
        setCurrentServiceCode(null);
        setNewServiceCode(null);
    }

    @Override
    public void init() {
        setServiceCodes(retrieve());
        if (!getServiceCodes().isEmpty()) {
            setCurrentServiceCode(getServiceCodes().get(0));
        }
    }

    @Override
    public void create() {
        ServiceCode entity = new ServiceCode();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewServiceCode(entity);
    }

    @Override
    public void delete(ServiceCode entity) {
        if (entity != null) {
            try {
                serviceCodeSessionBean.delete(entity);
                if (getServiceCodes().contains(entity)) {
                    getServiceCodes().remove(entity);
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
    public void edit(ServiceCode entity) {
        if (entity != null) {
            setCurrentServiceCode(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewServiceCode() != null) {
            try {
                serviceCodeSessionBean.update(getNewServiceCode());
                if (!getServiceCodes().contains(getNewServiceCode())) {
                    getServiceCodes().add(getNewServiceCode());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ServiceCode> retrieve() {
        return serviceCodeSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentServiceCode() != null) {
            try {
                getCurrentServiceCode().setStatus("M");
                getCurrentServiceCode().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceCode().setOptdate(userManagedBean.getDate());
                serviceCodeSessionBean.update(getCurrentServiceCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ServiceCode entity) {
        if (entity != null) {
            setCurrentServiceCode(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentServiceCode() != null) {
            try {
                getCurrentServiceCode().setStatus("V");
                getCurrentServiceCode().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentServiceCode().setCfmdate(userManagedBean.getDate());
                serviceCodeSessionBean.update(getCurrentServiceCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentServiceCode() != null) {
            try {
                getCurrentServiceCode().setStatus("M");
                getCurrentServiceCode().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceCode().setOptdate(userManagedBean.getDate());
                getCurrentServiceCode().setCfmuser(null);
                getCurrentServiceCode().setCfmdate(null);
                serviceCodeSessionBean.update(getCurrentServiceCode());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentServiceCode
     */
    public ServiceCode getCurrentServiceCode() {
        return currentServiceCode;
    }

    /**
     * @param currentServiceCode the currentServiceCode to set
     */
    public void setCurrentServiceCode(ServiceCode currentServiceCode) {
        this.currentServiceCode = currentServiceCode;
    }

    /**
     * @return the newServiceCode
     */
    public ServiceCode getNewServiceCode() {
        return newServiceCode;
    }

    /**
     * @param newServiceCode the newServiceCode to set
     */
    public void setNewServiceCode(ServiceCode newServiceCode) {
        this.newServiceCode = newServiceCode;
    }

    /**
     * @return the serviceCodes
     */
    public List<ServiceCode> getServiceCodes() {
        return serviceCodes;
    }

    /**
     * @param serviceCodes the serviceCodes to set
     */
    public void setServiceCodes(List<ServiceCode> serviceCodes) {
        this.serviceCodes = serviceCodes;
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
    public void sendNotification(ServiceCode entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
