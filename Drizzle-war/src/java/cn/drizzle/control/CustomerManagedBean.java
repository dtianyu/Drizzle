/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.CustomerSessionBean;
import cn.drizzle.entity.Customer;
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
import javax.inject.Inject;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class CustomerManagedBean implements ManagedBeanDataOperation<Customer> {

    @EJB
    private CustomerSessionBean customerSessionBean;
    private Customer currentCustomer;
    private Customer newCustomer;
    private List<Customer> customers;
    @Inject
    UserManagedBean userManagedBean;

    /**
     * Creates a new instance of CustomerManagedBean
     */
    public CustomerManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destory() {
        if (getCustomers() != null) {
            getCustomers().clear();
            setCustomers(null);
        }
        setCurrentCustomer(null);
        setNewCustomer(null);
    }

    @Override
    public void init() {
        setCustomers(retrieve());
        if (!getCustomers().isEmpty()) {
            setCurrentCustomer(getCustomers().get(0));
        }
    }

    @Override
    public void create() {
        Customer entity = new Customer();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewCustomer(entity);
    }

    @Override
    public void delete(Customer entity) {
        if (entity != null) {
            try {
                customerSessionBean.delete(entity);
                if (getCustomers().contains(entity)) {
                    getCustomers().remove(entity);
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
    public void edit(Customer entity) {
        if (entity != null) {
            setCurrentCustomer(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewCustomer() != null) {
            try {
                customerSessionBean.update(getNewCustomer());
                if (!getCustomers().contains(getNewCustomer())) {
                    getCustomers().add(getNewCustomer());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Customer> retrieve() {
        return customerSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentCustomer() != null) {
            try {
                getCurrentCustomer().setStatus("M");
                getCurrentCustomer().setOptuser(userManagedBean.getUserid());
                getCurrentCustomer().setOptdate(userManagedBean.getDate());
                customerSessionBean.update(getCurrentCustomer());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Customer entity) {
        if (entity != null) {
            setCurrentCustomer(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentCustomer() != null) {
            try {
                getCurrentCustomer().setStatus("V");
                getCurrentCustomer().setCfmuser(userManagedBean.getUserid());
                getCurrentCustomer().setCfmdate(userManagedBean.getDate());
                customerSessionBean.update(getCurrentCustomer());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentCustomer() != null) {
            try {
                getCurrentCustomer().setStatus("M");
                getCurrentCustomer().setOptuser(userManagedBean.getUserid());
                getCurrentCustomer().setOptdate(userManagedBean.getDate());
                getCurrentCustomer().setCfmuser(null);
                getCurrentCustomer().setCfmdate(null);
                customerSessionBean.update(getCurrentCustomer());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentCustomer
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * @param currentCustomer the currentCustomer to set
     */
    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    /**
     * @return the newCustomer
     */
    public Customer getNewCustomer() {
        return newCustomer;
    }

    /**
     * @param newCustomer the newCustomer to set
     */
    public void setNewCustomer(Customer newCustomer) {
        this.newCustomer = newCustomer;
    }

    /**
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public void sendNotification(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
