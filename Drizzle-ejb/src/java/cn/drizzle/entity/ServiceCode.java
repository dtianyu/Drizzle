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
@Table(name = "servicecode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceCode.findAll", query = "SELECT s FROM ServiceCode s"),
    @NamedQuery(name = "ServiceCode.findByServicecode", query = "SELECT s FROM ServiceCode s WHERE s.servicecode = :servicecode"),
    @NamedQuery(name = "ServiceCode.findByServicename", query = "SELECT s FROM ServiceCode s WHERE s.servicename = :servicename"),
    @NamedQuery(name = "ServiceCode.findByRemark", query = "SELECT s FROM ServiceCode s WHERE s.remark = :remark"),
    @NamedQuery(name = "ServiceCode.findByStatus", query = "SELECT s FROM ServiceCode s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceCode.findByCreator", query = "SELECT s FROM ServiceCode s WHERE s.creator = :creator"),
    @NamedQuery(name = "ServiceCode.findByCredate", query = "SELECT s FROM ServiceCode s WHERE s.credate = :credate"),
    @NamedQuery(name = "ServiceCode.findByOptuser", query = "SELECT s FROM ServiceCode s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "ServiceCode.findByOptdate", query = "SELECT s FROM ServiceCode s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "ServiceCode.findByCfmuser", query = "SELECT s FROM ServiceCode s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "ServiceCode.findByCfmdate", query = "SELECT s FROM ServiceCode s WHERE s.cfmdate = :cfmdate")})
public class ServiceCode implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicecode")
    private List<ServiceDetail> serviceDetailList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "servicecode")
    private String servicecode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "servicename")
    private String servicename;
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
    @JoinColumn(name = "classid", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ServiceClass classid;

    public ServiceCode() {
    }

    public ServiceCode(String servicecode) {
        this.servicecode = servicecode;
    }

    public ServiceCode(String servicecode, String servicename, String status) {
        this.servicecode = servicecode;
        this.servicename = servicename;
        this.status = status;
    }

    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
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

    public ServiceClass getClassid() {
        return classid;
    }

    public void setClassid(ServiceClass classid) {
        this.classid = classid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicecode != null ? servicecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCode)) {
            return false;
        }
        ServiceCode other = (ServiceCode) object;
        if ((this.servicecode == null && other.servicecode != null) || (this.servicecode != null && !this.servicecode.equals(other.servicecode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ServiceCode[ servicecode=" + servicecode + " ]";
    }

    @XmlTransient
    public List<ServiceDetail> getServiceDetailList() {
        return serviceDetailList;
    }

    public void setServiceDetailList(List<ServiceDetail> serviceDetailList) {
        this.serviceDetailList = serviceDetailList;
    }
}
