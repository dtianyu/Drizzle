/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.comm.Lib;
import cn.drizzle.comm.MailNotify;
import cn.drizzle.comm.ShortMessageNotify;
import cn.drizzle.comm.Notification;
import cn.drizzle.comm.NotificationFactory;
import cn.drizzle.comm.ServiceNotification;
import cn.drizzle.ejb.ComplaintClassSessionBean;
import cn.drizzle.ejb.ComplaintSessionBean;
import cn.drizzle.ejb.ServiceClassSessionBean;
import cn.drizzle.ejb.ServiceDetailSessionBean;
import cn.drizzle.ejb.ServiceKindSessionBean;
import cn.drizzle.ejb.ServiceSessionBean;
import cn.drizzle.ejb.ServiceTypeSessionBean;
import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintCode;
import cn.drizzle.entity.ComplaintDetail;
import cn.drizzle.entity.Customer;
import cn.drizzle.entity.Service;
import cn.drizzle.entity.ServiceClass;
import cn.drizzle.entity.ServiceCode;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.entity.ServiceKind;
import cn.drizzle.entity.ServiceType;
import cn.drizzle.mbi.ManagedBeanDataOperation;
import cn.drizzle.mbi.ManagedBeanDetailOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class ServiceManagedBean implements ManagedBeanDataOperation<Service>, ManagedBeanDetailOperation<ServiceDetail> {

    @EJB
    private ComplaintClassSessionBean complaintClassSessionBean;
    @EJB
    private ServiceClassSessionBean serviceClassSessionBean;
    @EJB
    private ServiceDetailSessionBean serviceDetailSessionBean;
    @EJB
    private ComplaintSessionBean complaintSessionBean;
    @EJB
    private ServiceTypeSessionBean serviceTypeSessionBean;
    @EJB
    private ServiceKindSessionBean serviceKindSessionBean;
    @EJB
    private ServiceSessionBean serviceSessionBean;
    private Service currentService;
    private Service newService;
    private List<Service> services;
    private ServiceDetail newServiceDetail;
    private ServiceDetail currentServiceDetail;
    private List<ServiceDetail> serviceDetails;
    private List<ServiceDetail> deletedServiceDetails;
    private List<ServiceDetail> editedServiceDetails;
    private List<ServiceKind> serviceKindList;
    private List<ServiceType> serviceTypeList;
    private List<ServiceClass> serviceClassList;
    private List<ServiceCode> serviceCodeList;
    private Complaint currentComplaint;
    private List<Complaint> complaintList;
    private List<ComplaintDetail> complaintDetailList;
    private List<ComplaintClass> complaintClassList;
    private List<ComplaintCode> complaintCodeList;
    private Date dayBegin;
    private Date dayEnd;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    public ServiceManagedBean() {
    }

    @PostConstruct
    public void construct() {
        setDayBegin(userManagedBean.getDate());
        setDayEnd(userManagedBean.getDate());
        setDeletedServiceDetails(new ArrayList<ServiceDetail>());
        setEditedServiceDetails(new ArrayList<ServiceDetail>());
        create();
        createDetail();
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getServices() != null) {
            getServices().clear();
            setServices(null);
        }
        if (getServiceDetails() != null) {
            getServiceDetails().clear();
            setServiceDetails(null);
        }
        if (getDeletedServiceDetails() != null) {
            getDeletedServiceDetails().clear();
            setDeletedServiceDetails(null);
        }
        if (getEditedServiceDetails() != null) {
            getEditedServiceDetails().clear();
            setEditedServiceDetails(null);
        }
        setCurrentService(null);
        setNewService(null);
        setCurrentServiceDetail(null);
        setNewServiceDetail(null);
    }

    @Override
    public void init() {
        setServices(retrieve());
        if (!getServices().isEmpty()) {
            setCurrentService(getServices().get(0));
            setServiceDetails(getCurrentService().getServiceDetailList());
            if (!getServiceDetails().isEmpty()) {
                setCurrentServiceDetail(getServiceDetails().get(0));
            } else {
                setCurrentServiceDetail(getNewServiceDetail());
            }
        } else {
            setCurrentService(getNewService());
            setServiceDetails(new ArrayList<ServiceDetail>());
            setCurrentServiceDetail(getNewServiceDetail());
        }
        setServiceKindList(serviceKindSessionBean.retrieve());
        setServiceTypeList(serviceTypeSessionBean.retrieve());
        setServiceClassList(serviceClassSessionBean.retrieve());
        setComplaintClassList(complaintClassSessionBean.retrieve());
    }

    @Override
    public void create() {
        Service entity = new Service();
        entity.setCompany(userManagedBean.getCompany().getId());
        entity.setServicedate(userManagedBean.getDate());
        entity.setServiceid(getMaxId());
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getCurrentUser());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getCurrentUser());
        entity.setOptdate(userManagedBean.getDate());
        setNewService(entity);
        if (getServiceDetails() != null && !getServiceDetails().isEmpty()) {
            getServiceDetails().clear();
        }
    }

    @Override
    public void delete(Service entity) {
        if (entity != null) {
            try {
                serviceSessionBean.delete(entity);
                if (getServices().contains(entity)) {
                    getServices().remove(entity);
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
    public void edit(Service entity) {
        if (entity != null) {
            setCurrentService(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewService() != null) {
            try {
                getNewService().setServiceid(getMaxId());
                getNewService().setCreator(userManagedBean.getCurrentUser());
                getNewService().setCredate(userManagedBean.getDate());
                getNewService().setOptuser(userManagedBean.getCurrentUser());
                getNewService().setOptdate(userManagedBean.getDate());
                if (!editedServiceDetails.isEmpty()) {
                    for (ServiceDetail serviceDetail : editedServiceDetails) {
                        serviceDetail.setServiceidd(getNewService().getServiceid() + String.format("%04d", serviceDetail.getSeq()));
                    }
                    serviceSessionBean.update(getNewService(), getEditedServiceDetails());
                    editedServiceDetails.clear();
                } else {
                    serviceSessionBean.update(getNewService());
                }
                if (!getServices().contains(getNewService())) {
                    getServices().add(getNewService());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.toString()));
                throw new Error(e.getMessage());
            }
            init();
        }
    }

    @Override
    public List<Service> retrieve() {
        return serviceSessionBean.retrieve(userManagedBean.getCompany().getId().toString(), dayBegin, dayEnd);
    }

    @Override
    public void save() {
        if (getCurrentService() != null) {
            try {
                getCurrentService().setStatus("M");
                getCurrentService().setOptuser(userManagedBean.getCurrentUser());
                getCurrentService().setOptdate(userManagedBean.getDate());
                serviceSessionBean.update(getCurrentService());
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(Lib.getLocalOperateMessage("msg.save")));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Service entity) {
        if (entity != null) {
            setCurrentService(entity);
            setServiceDetails(getCurrentService().getServiceDetailList());
        }
    }

    @Override
    public void verify() {
        if (getCurrentService() != null) {
            try {
                getCurrentService().setStatus("V");
                getCurrentService().setCfmuser(userManagedBean.getCurrentUser());
                getCurrentService().setCfmdate(userManagedBean.getDate());
                serviceSessionBean.update(getCurrentService());
                sendNotification(getCurrentService());
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
        if (getCurrentService() != null) {
            try {
                getCurrentService().setStatus("M");
                getCurrentService().setOptuser(userManagedBean.getCurrentUser());
                getCurrentService().setOptdate(userManagedBean.getDate());
                getCurrentService().setCfmuser(null);
                getCurrentService().setCfmdate(null);
                serviceSessionBean.update(getCurrentService());
                sendNotification(getCurrentService());
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
        if (serviceDetails.isEmpty()) {
            return 1;
        }
        int seq = 0;
        for (ServiceDetail serviceDetail : serviceDetails) {
            if (serviceDetail.getSeq() > seq) {
                seq = serviceDetail.getSeq();
            }
        }
        boolean b = true;
        boolean ret;
        for (int i = 1; i <= seq; i++) {
            ret = true;
            for (ServiceDetail serviceDetail : serviceDetails) {
                if (serviceDetail.getSeq() == i) {
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
        if (getNewServiceDetail() != null) {
            getNewServiceDetail().setServiceid(getCurrentService());
            getNewServiceDetail().setSeq(getSeq());
            getNewServiceDetail().setServiceidd(getNewServiceDetail().getServiceid().getServiceid() + String.format("%04d", getNewServiceDetail().getSeq()));
            if (!getEditedServiceDetails().contains(getNewServiceDetail())) {
                getEditedServiceDetails().add(getNewServiceDetail());
            }
            if (!getServiceDetails().contains(getNewServiceDetail())) {
                getServiceDetails().add(getNewServiceDetail());
            }
        }
    }

    @Override
    public void createDetail() {
        ServiceDetail entity = new ServiceDetail();
        entity.setQty(BigDecimal.ONE);
        entity.setIsprotected(false);
        setNewServiceDetail(entity);
    }

    @Override
    public void editDetail(ServiceDetail entity) {
        if (entity != null) {
            setCurrentServiceDetail(entity);
            setComplaintCodeList(getCurrentServiceDetail().getComplaintclass().getComplaintCodeList());
            setServiceCodeList(getCurrentServiceDetail().getServiceclass().getServiceCodeList());
        }
    }

    @Override
    public void persistDetail() {
        try {
            if (!getDeletedServiceDetails().isEmpty()) {
                for (ServiceDetail serviceDetail : deletedServiceDetails) {
                    serviceDetailSessionBean.delete(serviceDetail);
                }
                getDeletedServiceDetails().clear();
            }
            if (!getEditedServiceDetails().isEmpty()) {
                for (ServiceDetail serviceDetail : editedServiceDetails) {
                    serviceDetailSessionBean.update(serviceDetail);
                }
                getEditedServiceDetails().clear();
            }
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("更新成功"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void removeDetail(ServiceDetail entity) {
        if (entity != null) {
            try {
                if (getServiceDetails().contains(entity)) {
                    getServiceDetails().remove(entity);
                    getDeletedServiceDetails().add(entity);
                }
            } catch (Exception e) {
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void updateDetail() {
        if (getCurrentServiceDetail() != null) {
            if (!getEditedServiceDetails().contains(getCurrentServiceDetail())) {
                getEditedServiceDetails().add(getCurrentServiceDetail());
            }
        }
    }

    @Override
    public void viewDetail(ServiceDetail entity) {
        if (entity != null) {
            setCurrentServiceDetail(entity);
        }
    }

    public String getMaxId() {
        int companyid = userManagedBean.getCompany().getId();
        String companycode = userManagedBean.getCompany().getCompanycode();
        String formcode = userManagedBean.getCompany().getSyscs().getServiceidcode();
        String dateformat = userManagedBean.getCompany().getSyscs().getServiceidformat();
        int len = userManagedBean.getCompany().getSyscs().getServiceidlen();
        return serviceSessionBean.getBizNo(companyid, userManagedBean.getDate(), companycode + formcode, dateformat, len);
    }

    public void handleNewServiceAddDetailFromComplaint() {
        if ((getNewService() != null) && (getCurrentComplaint() != null) && (!getComplaintDetailList().isEmpty())) {
            getNewService().setSourceid(getCurrentComplaint().getComplaintid());
            getNewService().setSourcedate(getCurrentComplaint().getComplaintdate());
            getNewService().setSourcecustomerid(getCurrentComplaint().getCustomerid().getCustomer());
            getNewService().setContacter(getCurrentComplaint().getContacter());
            getNewService().setTel(getCurrentComplaint().getTel());
            getNewService().setAddress(getCurrentComplaint().getAddress());
            getNewService().setFinallycust(getCurrentComplaint().getFinallycust());
            if (!editedServiceDetails.isEmpty()) {
                getEditedServiceDetails().clear();
            }
            for (ComplaintDetail complaintDetail : complaintDetailList) {
                ServiceDetail entity = new ServiceDetail();
                entity.setServiceid(getNewService());
                entity.setSeq(getSeq());
                entity.setServiceidd(entity.getServiceid() + String.format("%04d", entity.getSeq()));
                entity.setItemno(complaintDetail.getItemno());
                entity.setBrand(complaintDetail.getBrand());
                entity.setBatch(complaintDetail.getBatch());
                entity.setSn(complaintDetail.getSn());
                entity.setQty(complaintDetail.getQty());
                entity.setRuntime(complaintDetail.getRuntime());
                entity.setIsprotected(complaintDetail.isIsprotected());
                entity.setResponsible(complaintDetail.getResponsible());
                entity.setComplaintclass(complaintDetail.getClassid());
                entity.setComplaintcode(complaintDetail.getComplaintcode());
                entity.setSourceidd(complaintDetail.getComplaintidd());
                if (!getServiceDetails().contains(entity)) {
                    getServiceDetails().add(entity);
                }
                if (!getEditedServiceDetails().contains(entity)) {
                    getEditedServiceDetails().add(entity);
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(Lib.getLocalOperateMessage("nodetailrows")));
        }
    }

    public void initComplaint(Customer entity) {
        if (entity != null) {
            setComplaintList(complaintSessionBean.retrieve(entity));
            if (!getComplaintList().isEmpty()) {
                setCurrentComplaint(getComplaintList().get(0));
                setComplaintDetailList(getCurrentComplaint().getComplaintDetailList());
            }
        }
    }

    public void handleComplaintSelect(SelectEvent event) {
        try {
            setComplaintDetailList(((Complaint) event.getObject()).getComplaintDetailList());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getLocalizedMessage()));
        }
    }

    public void handleCurrComplaintClassChanged() {
        if (getCurrentServiceDetail().getComplaintclass() != null) {
            getCurrentServiceDetail().setComplaintcode(null);
            setComplaintCodeList(getCurrentServiceDetail().getComplaintclass().getComplaintCodeList());
        }
    }

    public void handleCurrServiceClassChanged() {
        if (getCurrentServiceDetail().getServiceclass() != null) {
            getCurrentServiceDetail().setServicecode(null);
            setServiceCodeList(getCurrentServiceDetail().getServiceclass().getServiceCodeList());
        }
    }

    @Override
    public void sendNotification(Service entity) {
        NotificationFactory.createNotification(this.getClass(), entity);
    }

    /**
     * @return the currentService
     */
    public Service getCurrentService() {
        return currentService;
    }

    /**
     * @param currentService the currentService to set
     */
    public void setCurrentService(Service currentService) {
        this.currentService = currentService;
    }

    /**
     * @return the newService
     */
    public Service getNewService() {
        return newService;
    }

    /**
     * @param newService the newService to set
     */
    public void setNewService(Service newService) {
        this.newService = newService;
    }

    /**
     * @return the services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<Service> services) {
        this.services = services;
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

    /**
     * @return the serviceKindList
     */
    public List<ServiceKind> getServiceKindList() {
        return serviceKindList;
    }

    /**
     * @param serviceKindList the serviceKindList to set
     */
    public void setServiceKindList(List<ServiceKind> serviceKindList) {
        this.serviceKindList = serviceKindList;
    }

    /**
     * @return the serviceTypeList
     */
    public List<ServiceType> getServiceTypeList() {
        return serviceTypeList;
    }

    /**
     * @param serviceTypeList the serviceTypeList to set
     */
    public void setServiceTypeList(List<ServiceType> serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
    }

    /**
     * @return the complaintDetailList
     */
    public List<ComplaintDetail> getComplaintDetailList() {
        return complaintDetailList;
    }

    /**
     * @param complaintDetailList the complaintDetailList to set
     */
    public void setComplaintDetailList(List<ComplaintDetail> complaintDetailList) {
        this.complaintDetailList = complaintDetailList;
    }

    /**
     * @return the complaintList
     */
    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    /**
     * @param complaintList the complaintList to set
     */
    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
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
     * @return the newServiceDetail
     */
    public ServiceDetail getNewServiceDetail() {
        return newServiceDetail;
    }

    /**
     * @param newServiceDetail the newServiceDetail to set
     */
    public void setNewServiceDetail(ServiceDetail newServiceDetail) {
        this.newServiceDetail = newServiceDetail;
    }

    /**
     * @return the currentServiceDetail
     */
    public ServiceDetail getCurrentServiceDetail() {
        return currentServiceDetail;
    }

    /**
     * @param currentServiceDetail the currentServiceDetail to set
     */
    public void setCurrentServiceDetail(ServiceDetail currentServiceDetail) {
        this.currentServiceDetail = currentServiceDetail;
    }

    /**
     * @return the serviceDetails
     */
    public List<ServiceDetail> getServiceDetails() {
        return serviceDetails;
    }

    /**
     * @param serviceDetails the serviceDetails to set
     */
    public void setServiceDetails(List<ServiceDetail> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    /**
     * @return the deletedServiceDetails
     */
    public List<ServiceDetail> getDeletedServiceDetails() {
        return deletedServiceDetails;
    }

    /**
     * @param deletedServiceDetails the deletedServiceDetails to set
     */
    public void setDeletedServiceDetails(List<ServiceDetail> deletedServiceDetails) {
        this.deletedServiceDetails = deletedServiceDetails;
    }

    /**
     * @return the editedServiceDetails
     */
    public List<ServiceDetail> getEditedServiceDetails() {
        return editedServiceDetails;
    }

    /**
     * @param editedServiceDetails the editedServiceDetails to set
     */
    public void setEditedServiceDetails(List<ServiceDetail> editedServiceDetails) {
        this.editedServiceDetails = editedServiceDetails;
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

    /**
     * @return the serviceCodeList
     */
    public List<ServiceCode> getServiceCodeList() {
        return serviceCodeList;
    }

    /**
     * @param serviceCodeList the serviceCodeList to set
     */
    public void setServiceCodeList(List<ServiceCode> serviceCodeList) {
        this.serviceCodeList = serviceCodeList;
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
}
