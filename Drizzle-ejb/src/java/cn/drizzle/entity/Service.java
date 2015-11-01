/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author C0160
 */
@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceid", query = "SELECT s FROM Service s WHERE s.serviceid = :serviceid"),
    @NamedQuery(name = "Service.findByCompany", query = "SELECT s FROM Service s WHERE s.company = :company"),
    @NamedQuery(name = "Service.findByCompanyAndServiceDate", query = "SELECT s FROM Service s WHERE s.company = :company and s.servicedate >= :daybegin and s.servicedate <= :dayend"),
    @NamedQuery(name = "Service.findByServicedate", query = "SELECT s FROM Service s WHERE s.servicedate = :servicedate"),
    @NamedQuery(name = "Service.findByDeptid", query = "SELECT s FROM Service s WHERE s.deptid = :deptid"),
    @NamedQuery(name = "Service.findByAreaid", query = "SELECT s FROM Service s WHERE s.areaid = :areaid"),
    @NamedQuery(name = "Service.findByIsfree", query = "SELECT s FROM Service s WHERE s.isfree = :isfree"),
    @NamedQuery(name = "Service.findByProvinceleave", query = "SELECT s FROM Service s WHERE s.provinceleave = :provinceleave"),
    @NamedQuery(name = "Service.findByCityleave", query = "SELECT s FROM Service s WHERE s.cityleave = :cityleave"),
    @NamedQuery(name = "Service.findByProvincearrive", query = "SELECT s FROM Service s WHERE s.provincearrive = :provincearrive"),
    @NamedQuery(name = "Service.findByCityarrive", query = "SELECT s FROM Service s WHERE s.cityarrive = :cityarrive"),
    @NamedQuery(name = "Service.findBySourceid", query = "SELECT s FROM Service s WHERE s.sourceid = :sourceid"),
    @NamedQuery(name = "Service.findBySourcedate", query = "SELECT s FROM Service s WHERE s.sourcedate = :sourcedate"),
    @NamedQuery(name = "Service.findBySourcecustomerid", query = "SELECT s FROM Service s WHERE s.sourcecustomerid = :sourcecustomerid"),
    @NamedQuery(name = "Service.findByContacter", query = "SELECT s FROM Service s WHERE s.contacter = :contacter"),
    @NamedQuery(name = "Service.findByTel", query = "SELECT s FROM Service s WHERE s.tel = :tel"),
    @NamedQuery(name = "Service.findByAddress", query = "SELECT s FROM Service s WHERE s.address = :address"),
    @NamedQuery(name = "Service.findByFinallycust", query = "SELECT s FROM Service s WHERE s.finallycust = :finallycust"),
    @NamedQuery(name = "Service.findByRemark", query = "SELECT s FROM Service s WHERE s.remark = :remark"),
    @NamedQuery(name = "Service.findByStatus", query = "SELECT s FROM Service s WHERE s.status = :status"),
    @NamedQuery(name = "Service.findByCreator", query = "SELECT s FROM Service s WHERE s.creator = :creator"),
    @NamedQuery(name = "Service.findByCredate", query = "SELECT s FROM Service s WHERE s.credate = :credate"),
    @NamedQuery(name = "Service.findByOptuser", query = "SELECT s FROM Service s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "Service.findByOptdate", query = "SELECT s FROM Service s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "Service.findByCfmuser", query = "SELECT s FROM Service s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "Service.findByCfmdate", query = "SELECT s FROM Service s WHERE s.cfmdate = :cfmdate"),
    @NamedQuery(name = "Service.findByAuduser", query = "SELECT s FROM Service s WHERE s.auduser = :auduser"),
    @NamedQuery(name = "Service.findByAuddate", query = "SELECT s FROM Service s WHERE s.auddate = :auddate")})
public class Service implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceid")
    private List<ServiceDetail> serviceDetailList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "serviceid")
    private String serviceid;
    @Column(name = "company")
    private Integer company;
    @Basic(optional = false)
    @NotNull
    @Column(name = "servicedate")
    @Temporal(TemporalType.DATE)
    private Date servicedate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "areaid")
    private String areaid;
    @Basic(optional = false)
    @NotNull
    @Size(max = 20)
    @Column(name = "isfree")
    private String isfree;
    @Size(max = 20)
    @Column(name = "provinceleave")
    private String provinceleave;
    @Size(max = 20)
    @Column(name = "cityleave")
    private String cityleave;
    @Size(max = 20)
    @Column(name = "provincearrive")
    private String provincearrive;
    @Size(max = 20)
    @Column(name = "cityarrive")
    private String cityarrive;
    @Size(max = 20)
    @Column(name = "sourceid")
    private String sourceid;
    @Column(name = "sourcedate")
    @Temporal(TemporalType.DATE)
    private Date sourcedate;
    @Size(max = 10)
    @Column(name = "sourcecustomerid")
    private String sourcecustomerid;
    @Size(max = 20)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    @Size(max = 300)
    @Column(name = "address")
    private String address;
    @Size(max = 100)
    @Column(name = "finallycust")
    private String finallycust;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "creator", referencedColumnName = "userid")
    @ManyToOne(optional = true)
    private Sysuser creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @JoinColumn(name = "optuser", referencedColumnName = "userid")
    @ManyToOne(optional = true)
    private Sysuser optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @JoinColumn(name = "cfmuser", referencedColumnName = "userid")
    @ManyToOne(optional = true)
    private Sysuser cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;
    @JoinColumn(name = "auduser", referencedColumnName = "userid")
    @ManyToOne(optional = true)
    private Sysuser auduser;
    @Column(name = "auddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auddate;
    @JoinColumn(name = "customerid", referencedColumnName = "customerid")
    @ManyToOne(optional = false)
    private Customer customerid;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department deptid;
    @JoinColumn(name = "kindid", referencedColumnName = "kindid")
    @ManyToOne(optional = false)
    private ServiceKind kindid;
    @JoinColumn(name = "typeid", referencedColumnName = "typeid")
    @ManyToOne(optional = false)
    private ServiceType typeid;

    public Service() {
    }

    public Service(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Date getServicedate() {
        return servicedate;
    }

    public void setServicedate(Date servicedate) {
        this.servicedate = servicedate;
    }

    /**
     * @return the areaid
     */
    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getProvinceleave() {
        return provinceleave;
    }

    public void setProvinceleave(String provinceleave) {
        this.provinceleave = provinceleave;
    }

    public String getCityleave() {
        return cityleave;
    }

    public void setCityleave(String cityleave) {
        this.cityleave = cityleave;
    }

    public String getProvincearrive() {
        return provincearrive;
    }

    public void setProvincearrive(String provincearrive) {
        this.provincearrive = provincearrive;
    }

    public String getCityarrive() {
        return cityarrive;
    }

    public void setCityarrive(String cityarrive) {
        this.cityarrive = cityarrive;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public Date getSourcedate() {
        return sourcedate;
    }

    public void setSourcedate(Date sourcedate) {
        this.sourcedate = sourcedate;
    }

    public String getSourcecustomerid() {
        return sourcecustomerid;
    }

    public void setSourcecustomerid(String sourcecustomerid) {
        this.sourcecustomerid = sourcecustomerid;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFinallycust() {
        return finallycust;
    }

    public void setFinallycust(String finallycust) {
        this.finallycust = finallycust;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sysuser getCreator() {
        return creator;
    }

    public void setCreator(Sysuser creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Sysuser getOptuser() {
        return optuser;
    }

    public void setOptuser(Sysuser optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public Sysuser getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(Sysuser cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    public Sysuser getAuduser() {
        return auduser;
    }

    public void setAuduser(Sysuser auduser) {
        this.auduser = auduser;
    }

    public Date getAuddate() {
        return auddate;
    }

    public void setAuddate(Date auddate) {
        this.auddate = auddate;
    }

    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceid != null ? serviceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceid == null && other.serviceid != null) || (this.serviceid != null && !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Service[ serviceid=" + serviceid + " ]";
    }

    /**
     * @return the deptid
     */
    public Department getDeptid() {
        return deptid;
    }

    /**
     * @param deptid the deptid to set
     */
    public void setDeptid(Department deptid) {
        this.deptid = deptid;
    }

    public ServiceKind getKindid() {
        return kindid;
    }

    public void setKindid(ServiceKind kindid) {
        this.kindid = kindid;
    }

    public ServiceType getTypeid() {
        return typeid;
    }

    public void setTypeid(ServiceType typeid) {
        this.typeid = typeid;
    }

    @XmlTransient
    public List<ServiceDetail> getServiceDetailList() {
        return serviceDetailList;
    }

    public void setServiceDetailList(List<ServiceDetail> serviceDetailList) {
        this.serviceDetailList = serviceDetailList;
    }
}
