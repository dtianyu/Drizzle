/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.CustomerSessionBean;
import cn.drizzle.entity.Customer;
import cn.drizzle.mbi.ManagedBeanDataQuery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class CustomerQueryBean implements ManagedBeanDataQuery<Customer> {

    @EJB
    private CustomerSessionBean customerSessionBean;
    private List<Customer> customers;
    private List<Customer> customerList;

    public CustomerQueryBean() {
    }

    @PostConstruct
    public void construct() {
        customerList = new ArrayList<>();
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getCustomers() != null) {
            getCustomers().clear();
            setCustomers(null);
        }
        if (getCustomerList() != null) {
            getCustomerList().clear();
            setCustomerList(null);
        }
    }

    @Override
    public void init() {
        setCustomers(customerSessionBean.retrieve());
    }

    public List<Customer> autoCompleteCustomers(String query) {
        for (Customer customer : customers) {
            if (customer.getCustomerid().startsWith(query) || customer.getSimplecode().startsWith(query) || customer.getCustomer().startsWith(query)) {
                getCustomerList().add(customer);
            }
        }
        return customerList;
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

    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
