/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.ServiceClassSessionBean;
import cn.drizzle.entity.ServiceClass;
import cn.drizzle.mbi.ManagedBeanDataQuery;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author CharlesTung
 */
@ManagedBean
@RequestScoped
public class ServiceClassQueryBean implements ManagedBeanDataQuery<ServiceClass> {

    @EJB
    private ServiceClassSessionBean serviceClassSessionBean;
    private List<ServiceClass> serviceClassList;

    public ServiceClassQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @Override
    public void init() {
        setServiceClassList(serviceClassSessionBean.retrieve());
    }

    /**
     * @return the serviceClassList
     */
    public List<ServiceClass> getServiceClassList() {
        return serviceClassList;
    }

    /**
     * @param serviceClassList the serviceClassList to set
     */
    public void setServiceClassList(List<ServiceClass> serviceClassList) {
        this.serviceClassList = serviceClassList;
    }
}
