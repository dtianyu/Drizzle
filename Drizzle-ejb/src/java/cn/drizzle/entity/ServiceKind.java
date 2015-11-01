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
@Table(name = "servicekind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceKind.findAll", query = "SELECT s FROM ServiceKind s"),
    @NamedQuery(name = "ServiceKind.findByKindid", query = "SELECT s FROM ServiceKind s WHERE s.kindid = :kindid"),
    @NamedQuery(name = "ServiceKind.findByServicekind", query = "SELECT s FROM ServiceKind s WHERE s.servicekind = :servicekind"),
    @NamedQuery(name = "ServiceKind.findByRemark", query = "SELECT s FROM ServiceKind s WHERE s.remark = :remark"),
    @NamedQuery(name = "ServiceKind.findByStatus", query = "SELECT s FROM ServiceKind s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceKind.findByCreator", query = "SELECT s FROM ServiceKind s WHERE s.creator = :creator"),
    @NamedQuery(name = "ServiceKind.findByCredate", query = "SELECT s FROM ServiceKind s WHERE s.credate = :credate"),
    @NamedQuery(name = "ServiceKind.findByOptuser", query = "SELECT s FROM ServiceKind s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "ServiceKind.findByOptdate", query = "SELECT s FROM ServiceKind s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "ServiceKind.findByCfmuser", query = "SELECT s FROM ServiceKind s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "ServiceKind.findByCfmdate", query = "SELECT s FROM ServiceKind s WHERE s.cfmdate = :cfmdate")})
public class ServiceKind implements Serializable {

    @OneToMany(mappedBy = "kindid")
    private List<Service> serviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kindid")
    private String kindid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "servicekind")
    private String servicekind;
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

    public ServiceKind() {
    }

    public ServiceKind(String kindid) {
        this.kindid = kindid;
    }

    public ServiceKind(String kindid, String servicekind, String status) {
        this.kindid = kindid;
        this.servicekind = servicekind;
        this.status = status;
    }

    public String getKindid() {
        return kindid;
    }

    public void setKindid(String kindid) {
        this.kindid = kindid;
    }

    public String getServicekind() {
        return servicekind;
    }

    public void setServicekind(String servicekind) {
        this.servicekind = servicekind;
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
        hash += (kindid != null ? kindid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceKind)) {
            return false;
        }
        ServiceKind other = (ServiceKind) object;
        if ((this.kindid == null && other.kindid != null) || (this.kindid != null && !this.kindid.equals(other.kindid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ServiceKind[ kindid=" + kindid + " ]";
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
