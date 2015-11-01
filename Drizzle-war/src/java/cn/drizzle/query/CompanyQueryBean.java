/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.CompanySessionBean;
import cn.drizzle.entity.Company;
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
public class CompanyQueryBean implements ManagedBeanDataQuery<Company> {
    @EJB
    private CompanySessionBean companySessionBean;

    private List<Company> companies;
    /**
     * Creates a new instance of CompanyQueryBean
     */
    public CompanyQueryBean() {
    }
    
    @PostConstruct
    public void construct(){
        init();
    }
    
    @PreDestroy
    public void destory(){
        if(companies!=null){
            getCompanies().clear();
            setCompanies(null);
        }
    }

    @Override
    public void init() {
        setCompanies(companySessionBean.retrieve());
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
}
