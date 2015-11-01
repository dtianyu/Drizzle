/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.ComplaintClassSessionBean;
import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintCode;
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
public class ComplaintClassQueryBean implements ManagedBeanDataQuery<ComplaintClass> {

    @EJB
    private ComplaintClassSessionBean complaintClassSessionBean;
    private List<ComplaintClass> complaintClassList;

    public ComplaintClassQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @Override
    public void init() {
        setComplaintClassList(complaintClassSessionBean.retrieve());
    }

    /**
     * @return the complaintClassList
     */
    public List<ComplaintClass> getComplaintClassList() {
        return complaintClassList;
    }

    /**
     * @param complaintClassList the complaintClassList to set
     */
    public void setComplaintClassList(List<ComplaintClass> complaintClassList) {
        this.complaintClassList = complaintClassList;
    }
}
