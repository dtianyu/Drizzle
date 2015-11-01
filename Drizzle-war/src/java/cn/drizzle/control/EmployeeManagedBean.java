/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.EmployeeSessionBean;
import cn.drizzle.entity.Employee;
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
public class EmployeeManagedBean implements ManagedBeanDataOperation<Employee> {

    @EJB
    private EmployeeSessionBean employeeSessionBean;
    private Employee currentEmployee;
    private Employee newEmployee;
    private List<Employee> employees;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public EmployeeManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getEmployees() != null) {
            getEmployees().clear();
            setEmployees(null);
        }
        setCurrentEmployee(null);
        setNewEmployee(null);
    }

    @Override
    public void init() {
        setEmployees(retrieve());
        if (!getEmployees().isEmpty()) {
            setCurrentEmployee(getEmployees().get(0));
        }
    }

    @Override
    public void create() {
        Employee entity = new Employee();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewEmployee(entity);
    }

    @Override
    public void delete(Employee entity) {
        if (entity != null) {
            try {
                employeeSessionBean.delete(entity);
                if (getEmployees().contains(entity)) {
                    getEmployees().remove(entity);
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
    public void edit(Employee entity) {
        if (entity != null) {
            setCurrentEmployee(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewEmployee() != null) {
            try {
                employeeSessionBean.update(getNewEmployee());
                if (!getEmployees().contains(getNewEmployee())) {
                    getEmployees().add(getNewEmployee());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Employee> retrieve() {
        return employeeSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentEmployee() != null) {
            try {
                getCurrentEmployee().setStatus("M");
                getCurrentEmployee().setOptuser(userManagedBean.getUserid());
                getCurrentEmployee().setOptdate(userManagedBean.getDate());
                employeeSessionBean.update(getCurrentEmployee());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Employee entity) {
        if (entity != null) {
            setCurrentEmployee(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentEmployee() != null) {
            try {
                getCurrentEmployee().setStatus("V");
                getCurrentEmployee().setCfmuser(userManagedBean.getUserid());
                getCurrentEmployee().setCfmdate(userManagedBean.getDate());
                employeeSessionBean.update(getCurrentEmployee());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentEmployee() != null) {
            try {
                getCurrentEmployee().setStatus("M");
                getCurrentEmployee().setOptuser(userManagedBean.getUserid());
                getCurrentEmployee().setOptdate(userManagedBean.getDate());
                getCurrentEmployee().setCfmuser(null);
                getCurrentEmployee().setCfmdate(null);
                employeeSessionBean.update(getCurrentEmployee());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentEmployee
     */
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * @param currentEmployee the currentEmployee to set
     */
    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    /**
     * @return the newEmployee
     */
    public Employee getNewEmployee() {
        return newEmployee;
    }

    /**
     * @param newEmployee the newEmployee to set
     */
    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    /**
     * @return the employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
    public void sendNotification(Employee entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
