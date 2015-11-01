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
@Table(name = "serviceclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceClass.findAll", query = "SELECT s FROM ServiceClass s"),
    @NamedQuery(name = "ServiceClass.findByClassid", query = "SELECT s FROM ServiceClass s WHERE s.classid = :classid"),
    @NamedQuery(name = "ServiceClass.findByServiceclass", query = "SELECT s FROM ServiceClass s WHERE s.serviceclass = :serviceclass"),
    @NamedQuery(name = "ServiceClass.findByRemark", query = "SELECT s FROM ServiceClass s WHERE s.remark = :remark"),
    @NamedQuery(name = "ServiceClass.findByStatus", query = "SELECT s FROM ServiceClass s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceClass.findByCreator", query = "SELECT s FROM ServiceClass s WHERE s.creator = :creator"),
    @NamedQuery(name = "ServiceClass.findByCredate", query = "SELECT s FROM ServiceClass s WHERE s.credate = :credate"),
    @NamedQuery(name = "ServiceClass.findByOptuser", query = "SELECT s FROM ServiceClass s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "ServiceClass.findByOptdate", query = "SELECT s FROM ServiceClass s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "ServiceClass.findByCfmuser", query = "SELECT s FROM ServiceClass s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "ServiceClass.findByCfmdate", query = "SELECT s FROM ServiceClass s WHERE s.cfmdate = :cfmdate")})
public class ServiceClass implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceclass")
    private List<ServiceDetail> serviceDetailList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "classid")
    private String classid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "serviceclass")
    private String serviceclass;
    @Size(max = 300)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classid")
    private List<ServiceCode> serviceCodeList;

    public ServiceClass() {
    }

    public ServiceClass(String classid) {
        this.classid = classid;
    }

    public ServiceClass(String classid, String serviceclass, String status) {
        this.classid = classid;
        this.serviceclass = serviceclass;
        this.status = status;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getServiceclass() {
        return serviceclass;
    }

    public void setServiceclass(String serviceclass) {
        this.serviceclass = serviceclass;
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

    @XmlTransient
    public List<ServiceCode> getServiceCodeList() {
        return serviceCodeList;
    }

    public void setServiceCodeList(List<ServiceCode> serviceCodeList) {
        this.serviceCodeList = serviceCodeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classid != null ? classid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceClass)) {
            return false;
        }
        ServiceClass other = (ServiceClass) object;
        if ((this.classid == null && other.classid != null) || (this.classid != null && !this.classid.equals(other.classid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ServiceClass[ classid=" + classid + " ]";
    }

    @XmlTransient
    public List<ServiceDetail> getServiceDetailList() {
        return serviceDetailList;
    }

    public void setServiceDetailList(List<ServiceDetail> serviceDetailList) {
        this.serviceDetailList = serviceDetailList;
    }
}
