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
@Table(name = "complaintclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintClass.findAll", query = "SELECT c FROM ComplaintClass c"),
    @NamedQuery(name = "ComplaintClass.findByClassid", query = "SELECT c FROM ComplaintClass c WHERE c.classid = :classid"),
    @NamedQuery(name = "ComplaintClass.findByComplaintclass", query = "SELECT c FROM ComplaintClass c WHERE c.complaintclass = :complaintclass"),
    @NamedQuery(name = "ComplaintClass.findByRemark", query = "SELECT c FROM ComplaintClass c WHERE c.remark = :remark"),
    @NamedQuery(name = "ComplaintClass.findByStatus", query = "SELECT c FROM ComplaintClass c WHERE c.status = :status"),
    @NamedQuery(name = "ComplaintClass.findByCreator", query = "SELECT c FROM ComplaintClass c WHERE c.creator = :creator"),
    @NamedQuery(name = "ComplaintClass.findByCredate", query = "SELECT c FROM ComplaintClass c WHERE c.credate = :credate"),
    @NamedQuery(name = "ComplaintClass.findByOptuser", query = "SELECT c FROM ComplaintClass c WHERE c.optuser = :optuser"),
    @NamedQuery(name = "ComplaintClass.findByOptdate", query = "SELECT c FROM ComplaintClass c WHERE c.optdate = :optdate"),
    @NamedQuery(name = "ComplaintClass.findByCfmuser", query = "SELECT c FROM ComplaintClass c WHERE c.cfmuser = :cfmuser"),
    @NamedQuery(name = "ComplaintClass.findByCfmdate", query = "SELECT c FROM ComplaintClass c WHERE c.cfmdate = :cfmdate")})
public class ComplaintClass implements Serializable {

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
    @Column(name = "complaintclass")
    private String complaintclass;
    @Size(max = 300)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 10)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.DATE)
    private Date credate;
    @Size(max = 10)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.DATE)
    private Date optdate;
    @Size(max = 10)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.DATE)
    private Date cfmdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classid")
    private List<ComplaintCode> complaintCodeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classid")
    private List<ComplaintDetail> complaintDetailList;

    public ComplaintClass() {
    }

    public ComplaintClass(String classid) {
        this.classid = classid;
    }

    public ComplaintClass(String classid, String complaintclass, String status) {
        this.classid = classid;
        this.complaintclass = complaintclass;
        this.status = status;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getComplaintclass() {
        return complaintclass;
    }

    public void setComplaintclass(String complaintclass) {
        this.complaintclass = complaintclass;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
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
        hash += (classid != null ? classid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplaintClass)) {
            return false;
        }
        ComplaintClass other = (ComplaintClass) object;
        if ((this.classid == null && other.classid != null) || (this.classid != null && !this.classid.equals(other.classid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ComplaintClass[ classid=" + classid + " ]";
    }

    @XmlTransient
    public List<ComplaintCode> getComplaintCodeList() {
        return complaintCodeList;
    }

    public void setComplaintCodeList(List<ComplaintCode> complaintCodeList) {
        this.complaintCodeList = complaintCodeList;
    }

    @XmlTransient
    public List<ComplaintDetail> getComplaintDetailList() {
        return complaintDetailList;
    }

    public void setComplaintDetailList(List<ComplaintDetail> complaintDetailList) {
        this.complaintDetailList = complaintDetailList;
    }
}
