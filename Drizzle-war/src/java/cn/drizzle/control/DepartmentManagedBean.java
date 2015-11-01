/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.DepartmentSessionBean;
import cn.drizzle.entity.Department;
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
public class DepartmentManagedBean implements ManagedBeanDataOperation<Department> {

    @EJB
    private DepartmentSessionBean departmentSessionBean;
    private Department currentDepartment;
    private Department newDepartment;
    private List<Department> departments;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public DepartmentManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destroy() {
        if (getDepartments() != null) {
            getDepartments().clear();
            setDepartments(null);
        }
        setCurrentDepartment(null);
        setNewDepartment(null);
    }

    @Override
    public void init() {
        setDepartments(retrieve());
        if (!getDepartments().isEmpty()) {
            setCurrentDepartment(getDepartments().get(0));
        }
    }

    @Override
    public void create() {
        Department entity = new Department();
        entity.setCompanyid(userManagedBean.getCompany().getId());
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewDepartment(entity);
    }

    @Override
    public void delete(Department entity) {
        if (entity != null) {
            try {
                departmentSessionBean.delete(entity);
                if (getDepartments().contains(entity)) {
                    getDepartments().remove(entity);
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
    public void edit(Department entity) {
        if (entity != null) {
            setCurrentDepartment(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewDepartment() != null) {
            try {
                departmentSessionBean.update(getNewDepartment());
                if (!getDepartments().contains(getNewDepartment())) {
                    getDepartments().add(getNewDepartment());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Department> retrieve() {
        return departmentSessionBean.retrieve(userManagedBean.getCompany().getId().toString());
    }

    @Override
    public void save() {
        if (getCurrentDepartment() != null) {
            try {
                getCurrentDepartment().setStatus("M");
                getCurrentDepartment().setOptuser(userManagedBean.getUserid());
                getCurrentDepartment().setOptdate(userManagedBean.getDate());
                departmentSessionBean.update(getCurrentDepartment());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());

            }
            init();
        }
    }

    @Override
    public void view(Department entity) {
        if (entity != null) {
            setCurrentDepartment(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentDepartment() != null) {
            try {
                getCurrentDepartment().setStatus("V");
                getCurrentDepartment().setCfmuser(userManagedBean.getUserid());
                getCurrentDepartment().setCfmdate(userManagedBean.getDate());
                departmentSessionBean.update(getCurrentDepartment());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentDepartment() != null) {
            try {
                getCurrentDepartment().setStatus("M");
                getCurrentDepartment().setOptuser(userManagedBean.getUserid());
                getCurrentDepartment().setOptdate(userManagedBean.getDate());
                getCurrentDepartment().setCfmuser(null);
                getCurrentDepartment().setCfmdate(null);
                departmentSessionBean.update(getCurrentDepartment());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentDepartment
     */
    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    /**
     * @param currentDepartment the currentDepartment to set
     */
    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    /**
     * @return the departments
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * @param departments the departments to set
     */
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    /**
     * @return the newDepartment
     */
    public Department getNewDepartment() {
        return newDepartment;
    }

    /**
     * @param newDepartment the newDepartment to set
     */
    public void setNewDepartment(Department newDepartment) {
        this.newDepartment = newDepartment;
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
    public void sendNotification(Department entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
