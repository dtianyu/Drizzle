/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.comm.Lib;
import cn.drizzle.comm.NotificationFactory;
import cn.drizzle.ejb.ComplaintClassSessionBean;
import cn.drizzle.ejb.ComplaintDetailSessionBean;
import cn.drizzle.ejb.ComplaintSessionBean;
import cn.drizzle.ejb.ServiceDetailSessionBean;
import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintCode;
import cn.drizzle.entity.ComplaintDetail;
import cn.drizzle.entity.Customer;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.mbi.ManagedBeanDataOperation;
import cn.drizzle.mbi.ManagedBeanDetailOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author C0160
 */
@Named
@SessionScoped
public class ComplaintManagedBean implements ManagedBeanDataOperation<Complaint>, ManagedBeanDetailOperation<ComplaintDetail> {

    @EJB
    private ServiceDetailSessionBean serviceDetailSessionBean;
    @EJB
    private ComplaintClassSessionBean complaintClassSessionBean;
    @EJB
    private ComplaintDetailSessionBean complaintDetailSessionBean;
    @EJB
    private ComplaintSessionBean complaintSessionBean;
    private Complaint currentComplaint;
    private Complaint newComplaint;
    private List<Complaint> complaints;
    private ComplaintDetail currentComplaintDetail;
    private ComplaintDetail newComplaintDetail;
    private List<ComplaintDetail> complaintDetails;
    private List<ComplaintDetail> deletedComplaintDetails;
    private List<ComplaintDetail> editedComplaintDetails;
    private List<ComplaintClass> complaintClassList;
    private List<ComplaintCode> complaintCodeList;
    private List<ServiceDetail> serviceDetailList;
    private Date dayBegin;
    private Date dayEnd;

    @Inject UserManagedBean userManagedBean;

    public ComplaintManagedBean() {
    }

    @PostConstruct
    public void construct() {
        setDayBegin(userManagedBean.getDate());
        setDayEnd(userManagedBean.getDate());
        setDeletedComplaintDetails(new ArrayList<ComplaintDetail>());
        setEditedComplaintDetails(new ArrayList<ComplaintDetail>());
        setServiceDetailList(new ArrayList<ServiceDetail>());
        create();
        createDetail();
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getComplaints() != null) {
            getComplaints().clear();
            setComplaints(null);
        }
        if (getComplaintDetails() != null) {
            getComplaintDetails().clear();
            setComplaintDetails(null);
        }
        if (getDeletedComplaintDetails() != null) {
            getDeletedComplaintDetails().clear();
            setDeletedComplaintDetails(null);
        }
        if (getEditedComplaintDetails() != null) {
            getEditedComplaintDetails().clear();
            setEditedComplaintDetails(null);
        }
        setCurrentComplaint(null);
        setNewComplaint(null);
        setCurrentComplaintDetail(null);
        setNewComplaintDetail(null);
    }

    @Override
    public void init() {
        setComplaints(retrieve());
        if (!getComplaints().isEmpty()) {
            setCurrentComplaint(getComplaints().get(0));
            setComplaintDetails(getCurrentComplaint().getComplaintDetailList());
            if (!getComplaintDetails().isEmpty()) {
                setCurrentComplaintDetail(getComplaintDetails().get(0));
            } else {
                setCurrentComplaintDetail(getNewComplaintDetail());
            }
        } else {
            setCurrentComplaint(getNewComplaint());
            setComplaintDetails(new ArrayList<ComplaintDetail>());
            setCurrentComplaintDetail(getNewComplaintDetail());
        }
        setComplaintClassList(complaintClassSessionBean.retrieve());
    }

    @Override
    public void create() {
        Complaint entity = new Complaint();
        entity.setCompany(userManagedBean.getCompany().getId());
        entity.setComplaintdate(userManagedBean.getDate());
        entity.setComplaintid(getMaxId(userManagedBean.getDate()));
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
//        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewComplaint(entity);
    }

    @Override
    public void delete(Complaint entity) {
        if (entity != null) {
            try {
                complaintSessionBean.delete(entity);
                if (getComplaints().contains(entity)) {
                    getComplaints().remove(entity);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getMessage());
            }
            init();
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("没有选中任何资料！"));
        }
    }

    @Override
    public void edit(Complaint entity) {
        if (entity != null) {
            setCurrentComplaint(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewComplaint() != null) {
            try {
                getNewComplaint().setComplaintid(getMaxId(getNewComplaint().getComplaintdate()));
                getNewComplaint().setCreator(userManagedBean.getCurrentUser());
                getNewComplaint().setCredate(userManagedBean.getDate());
//                getNewComplaint().setOptuser(userManagedBean.getCurrentUser());
                getNewComplaint().setOptdate(userManagedBean.getDate());
                complaintSessionBean.update(getNewComplaint());
                if (!getComplaints().contains(getNewComplaint())) {
                    getComplaints().add(getNewComplaint());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Complaint> retrieve() {
        return complaintSessionBean.retrieve(userManagedBean.getCompany().getId().toString(), dayBegin, dayEnd);
    }

    @Override
    public void save() {
        if (getCurrentComplaint() != null) {
            try {
                getCurrentComplaint().setStatus("M");
//                getCurrentComplaint().setOptuser(userManagedBean.getCurrentUser());
                getCurrentComplaint().setOptdate(userManagedBean.getDate());
                complaintSessionBean.update(getCurrentComplaint());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Complaint entity) {
        if (entity != null) {
            setCurrentComplaint(entity);
            setComplaintDetails(getCurrentComplaint().getComplaintDetailList());
            setServiceDetailList(serviceDetailSessionBean.getByPId(entity.getComplaintid() + "%"));
        }
    }

    @Override
    public void verify() {
        if (getCurrentComplaint() != null) {
            try {
                getCurrentComplaint().setStatus("V");
//                getCurrentComplaint().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentComplaint().setCfmdate(userManagedBean.getDate());
                complaintSessionBean.update(getCurrentComplaint());
                sendNotification(getCurrentComplaint());
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(Lib.getLocalOperateMessage("msg.verify")));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void unverify() {
        if (getCurrentComplaint() != null) {
            try {
                getCurrentComplaint().setStatus("M");
//                getCurrentComplaint().setOptuser(userManagedBean.getCurrentUser());
                getCurrentComplaint().setOptdate(userManagedBean.getDate());
                getCurrentComplaint().setCfmuser(null);
                getCurrentComplaint().setCfmdate(null);
                complaintSessionBean.update(getCurrentComplaint());
                sendNotification(getCurrentComplaint());
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(Lib.getLocalOperateMessage("msg.unverify")));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getLocalizedMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public int getSeq() {
        if (complaintDetails.isEmpty()) {
            return 1;
        }
        int seq = 0;
        for (ComplaintDetail complaintDetail : complaintDetails) {
            if (complaintDetail.getSeq() > seq) {
                seq = complaintDetail.getSeq();
            }
        }
        boolean b = true;
        boolean ret;
        for (int i = 1; i <= seq; i++) {
            ret = true;
            for (ComplaintDetail complaintDetail : complaintDetails) {
                if (complaintDetail.getSeq() == i) {
                    ret = ret && false;
                    break;
                }
            }
            if (ret) {
                return i;
            }
        }
        if (b) {
            return seq + 1;
        } else {
            return 0;
        }
    }

    @Override
    public void initDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDetail() {
        if (getNewComplaintDetail() != null) {
            getNewComplaintDetail().setComplaintid(getCurrentComplaint());
            getNewComplaintDetail().setSeq(getSeq());
            getNewComplaintDetail().setComplaintidd(getNewComplaintDetail().getComplaintid().getComplaintid() + String.format("%04d", getNewComplaintDetail().getSeq()));
            if (!getEditedComplaintDetails().contains(getNewComplaintDetail())) {
                getEditedComplaintDetails().add(getNewComplaintDetail());
            }
            if (!getComplaintDetails().contains(getNewComplaintDetail())) {
                getComplaintDetails().add(getNewComplaintDetail());
            }
        }
    }

    @Override
    public void createDetail() {
        ComplaintDetail entity = new ComplaintDetail();
        entity.setQty(BigDecimal.ONE);
        entity.setIsprotected(false);
        setNewComplaintDetail(entity);
    }

    @Override
    public void editDetail(ComplaintDetail entity) {
        if (entity != null) {
            setCurrentComplaintDetail(entity);
            setComplaintCodeList(getCurrentComplaintDetail().getClassid().getComplaintCodeList());
        }
    }

    @Override
    public void persistDetail() {
        try {
            if (!getDeletedComplaintDetails().isEmpty()) {
                for (ComplaintDetail complaintDetail : deletedComplaintDetails) {
                    complaintDetailSessionBean.delete(complaintDetail);
                }
                getDeletedComplaintDetails().clear();
            }
            if (!getEditedComplaintDetails().isEmpty()) {
                for (ComplaintDetail complaintDetail : editedComplaintDetails) {
                    complaintDetailSessionBean.update(complaintDetail);
                }
                getEditedComplaintDetails().clear();
            }
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(Lib.getLocalOperateMessage("msg.save")));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
            throw new Error(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeDetail(ComplaintDetail entity) {
        if (entity != null) {
            try {
                if (getComplaintDetails().contains(entity)) {
                    getComplaintDetails().remove(entity);
                    getDeletedComplaintDetails().add(entity);
                }
            } catch (Exception e) {
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void updateDetail() {
        try {
            if ((getCurrentComplaintDetail() != null) && (!getComplaintDetails().contains(getCurrentComplaintDetail()))) {
                if (!getEditedComplaintDetails().contains(getCurrentComplaintDetail())) {
                    getEditedComplaintDetails().add(getCurrentComplaintDetail());
                }
            } else {
                throw new Exception("重复");//i18n
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(ex.getMessage()));
            throw new Error(ex.getMessage());
        }
    }

    @Override
    public void viewDetail(ComplaintDetail entity) {
        if (entity != null) {
            setCurrentComplaintDetail(entity);
        }
    }

    public String getMaxId(Date formdate) {
        int companyid = userManagedBean.getCompany().getId();
        String companycode = userManagedBean.getCompany().getCompanycode();
        String formcode = userManagedBean.getCompany().getSyscs().getComplaintidcode();
        String dateformat = userManagedBean.getCompany().getSyscs().getComplaintidformat();
        int len = userManagedBean.getCompany().getSyscs().getComplaintidlen();
        return complaintSessionBean.getBizNo(companyid, formdate, companycode + formcode, dateformat, len);
    }

    public void handleNewCustomerSelected(SelectEvent event) {
        try {
            Customer c = (Customer) event.getObject();
            getNewComplaint().setContacter(c.getContacter());
            getNewComplaint().setTel(c.getTel());
            getNewComplaint().setAddress(c.getContactadd());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getLocalizedMessage()));
        }
    }

    public void handleCurrCustomerSelected(SelectEvent event) {
        try {
            Customer c = (Customer) event.getObject();
            getCurrentComplaint().setContacter(c.getContacter());
            getCurrentComplaint().setTel(c.getTel());
            getCurrentComplaint().setAddress(c.getContactadd());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getLocalizedMessage()));
        }
    }

    public void handleNewComplaintClassChanged() {
        if (getNewComplaintDetail().getClassid() != null) {
            getNewComplaintDetail().setComplaintcode(null);
            setComplaintCodeList(getNewComplaintDetail().getClassid().getComplaintCodeList());
        }
    }

    public void handleCurrComplaintClassChanged() {
        if (getCurrentComplaintDetail().getClassid() != null) {
            getCurrentComplaintDetail().setComplaintcode(null);
            setComplaintCodeList(getCurrentComplaintDetail().getClassid().getComplaintCodeList());
        }
    }

    @Override
    public void sendNotification(Complaint entity) {
        NotificationFactory.createNotification(this.getClass(), entity);
    }

    /**
     * @return the currentComplaint
     */
    public Complaint getCurrentComplaint() {
        return currentComplaint;
    }

    /**
     * @param currentComplaint the currentComplaint to set
     */
    public void setCurrentComplaint(Complaint currentComplaint) {
        this.currentComplaint = currentComplaint;
    }

    /**
     * @return the newComplaint
     */
    public Complaint getNewComplaint() {
        return newComplaint;
    }

    /**
     * @param newComplaint the newComplaint to set
     */
    public void setNewComplaint(Complaint newComplaint) {
        this.newComplaint = newComplaint;
    }

    /**
     * @return the complaints
     */
    public List<Complaint> getComplaints() {
        return complaints;
    }

    /**
     * @param complaints the complaints to set
     */
    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    /**
     * @return the currentComplaintDetail
     */
    public ComplaintDetail getCurrentComplaintDetail() {
        return currentComplaintDetail;
    }

    /**
     * @param currentComplaintDetail the currentComplaintDetail to set
     */
    public void setCurrentComplaintDetail(ComplaintDetail currentComplaintDetail) {
        this.currentComplaintDetail = currentComplaintDetail;
    }

    /**
     * @return the newComplaintDetail
     */
    public ComplaintDetail getNewComplaintDetail() {
        return newComplaintDetail;
    }

    /**
     * @param newComplaintDetail the newComplaintDetail to set
     */
    public void setNewComplaintDetail(ComplaintDetail newComplaintDetail) {
        this.newComplaintDetail = newComplaintDetail;
    }

    /**
     * @return the complaintDetails
     */
    public List<ComplaintDetail> getComplaintDetails() {
        return complaintDetails;
    }

    /**
     * @param complaintDetails the complaintDetails to set
     */
    public void setComplaintDetails(List<ComplaintDetail> complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    /**
     * @return the deletedComplaintDetails
     */
    public List<ComplaintDetail> getDeletedComplaintDetails() {
        return deletedComplaintDetails;
    }

    /**
     * @param deletedComplaintDetails the deletedComplaintDetails to set
     */
    public void setDeletedComplaintDetails(List<ComplaintDetail> deletedComplaintDetails) {
        this.deletedComplaintDetails = deletedComplaintDetails;
    }

    /**
     * @return the editedComplaintDetails
     */
    public List<ComplaintDetail> getEditedComplaintDetails() {
        return editedComplaintDetails;
    }

    /**
     * @param editedComplaintDetails the editedComplaintDetails to set
     */
    public void setEditedComplaintDetails(List<ComplaintDetail> editedComplaintDetails) {
        this.editedComplaintDetails = editedComplaintDetails;
    }

    /**
     * @return the dayBegin
     */
    public Date getDayBegin() {
        return dayBegin;
    }

    /**
     * @param dayBegin the dayBegin to set
     */
    public void setDayBegin(Date dayBegin) {
        this.dayBegin = dayBegin;
    }

    /**
     * @return the dayEnd
     */
    public Date getDayEnd() {
        return dayEnd;
    }

    /**
     * @param dayEnd the dayEnd to set
     */
    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
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

    /**
     * @return the complaintCodeList
     */
    public List<ComplaintCode> getComplaintCodeList() {
        return complaintCodeList;
    }

    /**
     * @param complaintCodeList the complaintCodeList to set
     */
    public void setComplaintCodeList(List<ComplaintCode> complaintCodeList) {
        this.complaintCodeList = complaintCodeList;
    }

    /**
     * @return the serviceDetailList
     */
    public List<ServiceDetail> getServiceDetailList() {
        return serviceDetailList;
    }

    /**
     * @param serviceDetailList the serviceDetailList to set
     */
    public void setServiceDetailList(List<ServiceDetail> serviceDetailList) {
        this.serviceDetailList = serviceDetailList;
    }
}
