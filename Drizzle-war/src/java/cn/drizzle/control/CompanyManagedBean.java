/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.mbi.ManagedBeanDataOperation;
import cn.drizzle.ejb.CompanySessionBean;
import cn.drizzle.entity.Company;
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
 * @author CharlesTung
 */
@ManagedBean
@SessionScoped
public class CompanyManagedBean implements ManagedBeanDataOperation<Company> {

    @EJB
    private CompanySessionBean companySessionBean;
    private Company currentCompany;
    private Company newCompany;
    private List<Company> companies;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * Creates a new instance of CompanyManagedBean
     */
    public CompanyManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destory() {
        if (getCompanies() != null) {
            getCompanies().clear();
            setCompanies(null);
        }
        setCurrentCompany(null);
        setNewCompany(null);
    }

    @Override
    public void init() {
        setCompanies(retrieve());
        if (!getCompanies().isEmpty()) {
            setCurrentCompany(getCompanies().get(0));
        }
    }

    @Override
    public void create() {
        Company entity = new Company();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewCompany(entity);
    }

    @Override
    public void delete(Company entity) {
        if (entity != null) {
            try {
                companySessionBean.delete(entity);
                if (getCompanies().contains(entity)) {
                    getCompanies().remove(entity);
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
    public void edit(Company entity) {
        if (entity != null) {
            setCurrentCompany(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewCompany() != null) {
            try {
                companySessionBean.update(getNewCompany());
                if (!getCompanies().contains(getNewCompany())) {
                    getCompanies().add(getNewCompany());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Company> retrieve() {
        return companySessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentCompany() != null) {
            try {
                getCurrentCompany().setStatus("M");
                getCurrentCompany().setOptuser(userManagedBean.getUserid());
                getCurrentCompany().setOptdate(userManagedBean.getDate());
                companySessionBean.update(getCurrentCompany());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Company entity) {
        if (entity != null) {
            setCurrentCompany(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentCompany() != null) {
            try {
                getCurrentCompany().setStatus("V");
                getCurrentCompany().setCfmuser(userManagedBean.getUserid());
                getCurrentCompany().setCfmdate(userManagedBean.getDate());
                companySessionBean.update(getCurrentCompany());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentCompany() != null) {
            try {
                getCurrentCompany().setStatus("M");
                getCurrentCompany().setOptuser(userManagedBean.getUserid());
                getCurrentCompany().setOptdate(userManagedBean.getDate());
                getCurrentCompany().setCfmuser(null);
                getCurrentCompany().setCfmdate(null);
                companySessionBean.update(getCurrentCompany());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentCompany
     */
    public Company getCurrentCompany() {
        return currentCompany;
    }

    /**
     * @param currentCompany the currentCompany to set
     */
    public void setCurrentCompany(Company currentCompany) {
        this.currentCompany = currentCompany;
    }

    /**
     * @return the newCompany
     */
    public Company getNewCompany() {
        return newCompany;
    }

    /**
     * @param newCompany the newCompany to set
     */
    public void setNewCompany(Company newCompany) {
        this.newCompany = newCompany;
    }

    /**
     * @return the companies
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * @param companies the companies to set
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
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
    public void sendNotification(Company entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
