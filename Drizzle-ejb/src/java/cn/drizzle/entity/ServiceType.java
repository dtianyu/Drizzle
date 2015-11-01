/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
 * @author CharlesTung
 */
@Entity
@Table(name = "servicetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s"),
    @NamedQuery(name = "ServiceType.findByTypeid", query = "SELECT s FROM ServiceType s WHERE s.typeid = :typeid"),
    @NamedQuery(name = "ServiceType.findByServicetype", query = "SELECT s FROM ServiceType s WHERE s.servicetype = :servicetype"),
    @NamedQuery(name = "ServiceType.findByRemark", query = "SELECT s FROM ServiceType s WHERE s.remark = :remark"),
    @NamedQuery(name = "ServiceType.findByStatus", query = "SELECT s FROM ServiceType s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceType.findByCreator", query = "SELECT s FROM ServiceType s WHERE s.creator = :creator"),
    @NamedQuery(name = "ServiceType.findByCredate", query = "SELECT s FROM ServiceType s WHERE s.credate = :credate"),
    @NamedQuery(name = "ServiceType.findByOptuser", query = "SELECT s FROM ServiceType s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "ServiceType.findByOptdate", query = "SELECT s FROM ServiceType s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "ServiceType.findByCfmuser", query = "SELECT s FROM ServiceType s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "ServiceType.findByCfmdate", query = "SELECT s FROM ServiceType s WHERE s.cfmdate = :cfmdate")})
public class ServiceType implements Serializable {

    @OneToMany(mappedBy = "typeid")
    private List<Service> serviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "typeid")
    private String typeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "servicetype")
    private String servicetype;
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

    public ServiceType() {
    }

    public ServiceType(String typeid) {
        this.typeid = typeid;
    }

    public ServiceType(String typeid, String servicetype, String status) {
        this.typeid = typeid;
        this.servicetype = servicetype;
        this.status = status;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ServiceType[ typeid=" + typeid + " ]";
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
