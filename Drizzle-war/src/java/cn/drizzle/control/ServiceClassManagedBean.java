/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;



import cn.drizzle.ejb.ServiceClassSessionBean;
import cn.drizzle.entity.ServiceClass;
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
public class ServiceClassManagedBean implements ManagedBeanDataOperation<ServiceClass> {

    @EJB
    private ServiceClassSessionBean serviceClassSessionBean;
    private ServiceClass currentServiceClass;
    private ServiceClass newServiceClass;
    private List<ServiceClass> serviceClasses;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ServiceClassManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getServiceClasses() != null) {
            getServiceClasses().clear();
            setServiceClasses(null);
        }
        setCurrentServiceClass(null);
        setNewServiceClass(null);
    }

    @Override
    public void init() {
        setServiceClasses(retrieve());
        if (!getServiceClasses().isEmpty()) {
            setCurrentServiceClass(getServiceClasses().get(0));
        }
    }

    @Override
    public void create() {
        ServiceClass entity = new ServiceClass();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewServiceClass(entity);
    }

    @Override
    public void delete(ServiceClass entity) {
        if (entity != null) {
            try {
                serviceClassSessionBean.delete(entity);
                if (getServiceClasses().contains(entity)) {
                    getServiceClasses().remove(entity);
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
    public void edit(ServiceClass entity) {
        if (entity != null) {
            setCurrentServiceClass(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewServiceClass() != null) {
            try {
                serviceClassSessionBean.update(getNewServiceClass());
                if (!getServiceClasses().contains(getNewServiceClass())) {
                    getServiceClasses().add(getNewServiceClass());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<ServiceClass> retrieve() {
        return serviceClassSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentServiceClass() != null) {
            try {
                getCurrentServiceClass().setStatus("M");
                getCurrentServiceClass().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceClass().setOptdate(userManagedBean.getDate());
                serviceClassSessionBean.update(getCurrentServiceClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(ServiceClass entity) {
        if (entity != null) {
            setCurrentServiceClass(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentServiceClass() != null) {
            try {
                getCurrentServiceClass().setStatus("V");
                getCurrentServiceClass().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentServiceClass().setCfmdate(userManagedBean.getDate());
                serviceClassSessionBean.update(getCurrentServiceClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentServiceClass() != null) {
            try {
                getCurrentServiceClass().setStatus("M");
                getCurrentServiceClass().setOptuser(userManagedBean.getCurrentUser());
                getCurrentServiceClass().setOptdate(userManagedBean.getDate());
                getCurrentServiceClass().setCfmuser(null);
                getCurrentServiceClass().setCfmdate(null);
                serviceClassSessionBean.update(getCurrentServiceClass());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentServiceClass
     */
    public ServiceClass getCurrentServiceClass() {
        return currentServiceClass;
    }

    /**
     * @param currentServiceClass the currentServiceClass to set
     */
    public void setCurrentServiceClass(ServiceClass currentServiceClass) {
        this.currentServiceClass = currentServiceClass;
    }

    /**
     * @return the newServiceClass
     */
    public ServiceClass getNewServiceClass() {
        return newServiceClass;
    }

    /**
     * @param newServiceClass the newServiceClass to set
     */
    public void setNewServiceClass(ServiceClass newServiceClass) {
        this.newServiceClass = newServiceClass;
    }

    /**
     * @return the serviceClasses
     */
    public List<ServiceClass> getServiceClasses() {
        return serviceClasses;
    }

    /**
     * @param serviceClasses the serviceClasses to set
     */
    public void setServiceClasses(List<ServiceClass> serviceClasses) {
        this.serviceClasses = serviceClasses;
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
    public void sendNotification(ServiceClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
