/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.EmployeeSessionBean;
import cn.drizzle.entity.Employee;
import cn.drizzle.mbi.ManagedBeanDataQuery;
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
public class EmployeeQueryBean implements ManagedBeanDataQuery<Employee> {

    @EJB
    private EmployeeSessionBean employeeSessionBean;
    private List<Employee> employeeList;

    public EmployeeQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
    }

    @Override
    public void init() {
        setEmployeeList(employeeSessionBean.retrieve());
    }

    /**
     * @return the employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
