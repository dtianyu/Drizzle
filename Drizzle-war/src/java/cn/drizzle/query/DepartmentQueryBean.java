/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.control.UserManagedBean;
import cn.drizzle.ejb.DepartmentSessionBean;
import cn.drizzle.entity.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author C0160
 */
@Named
@RequestScoped
public class DepartmentQueryBean implements Serializable {

    @EJB
    private DepartmentSessionBean departmentSessionBean;
    private List<Department> departments;
    private List<Department> departmentList;
    private Department currentEntity;
    private String selectedId;

    @Inject
    UserManagedBean userManagedBean;

    public DepartmentQueryBean() {
    }

    @PostConstruct
    public void construct() {
        departmentList = new ArrayList<>();
        currentEntity = new Department();
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getDepartments() != null) {
            getDepartments().clear();
            setDepartments(null);
        }
    }

    public void init() {
        setDepartments(departmentSessionBean.retrieve(userManagedBean.getCompany().getId().toString()));
    }

    public List<Department> autoCompleteDepartments(String value) {
        getDepartmentList().clear();
        for (Department dept : departments) {
            if (dept.getDeptcode().startsWith(value) || dept.getDept().startsWith(value)) {
                getDepartmentList().add(dept);
            }
        }
        return departmentList;
    }

    public void submit(String update) {
        FacesContext fc = FacesContext.getCurrentInstance();
        setSelectedId(fc.getExternalContext().getRequestParameterMap().get(update + ":formQuery:tblQuery_selection"));
        setCurrentEntity(departmentSessionBean.getByCode(selectedId));
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("id", selectedId);
        context.addCallbackParam("entity", getCurrentEntity());
        context.addCallbackParam("update", update);
    }

    public void selectEntity(SelectEvent event) {
        setCurrentEntity((Department) event.getObject());
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("dept", currentEntity);
    }

    public void unselectEntity(UnselectEvent event) {

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
     * @return the departmentList
     */
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    /**
     * @param departmentList the departmentList to set
     */
    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    /**
     * @return the currentEntity
     */
    public Department getCurrentEntity() {
        return currentEntity;
    }

    /**
     * @param currentEntity the currentEntity to set
     */
    public void setCurrentEntity(Department currentEntity) {
        this.currentEntity = currentEntity;
    }

    /**
     * @return the selectedId
     */
    public String getSelectedId() {
        return selectedId;
    }

    /**
     * @param selectedId the selectedId to set
     */
    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }
}
