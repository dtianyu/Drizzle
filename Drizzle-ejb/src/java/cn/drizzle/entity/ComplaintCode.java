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
@Table(name = "complaintcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintCode.findAll", query = "SELECT c FROM ComplaintCode c"),
    @NamedQuery(name = "ComplaintCode.findByComplaintcode", query = "SELECT c FROM ComplaintCode c WHERE c.complaintcode = :complaintcode"),
    @NamedQuery(name = "ComplaintCode.findByComplaintname", query = "SELECT c FROM ComplaintCode c WHERE c.complaintname = :complaintname"),
    @NamedQuery(name = "ComplaintCode.findByRemark", query = "SELECT c FROM ComplaintCode c WHERE c.remark = :remark"),
    @NamedQuery(name = "ComplaintCode.findByStatus", query = "SELECT c FROM ComplaintCode c WHERE c.status = :status"),
    @NamedQuery(name = "ComplaintCode.findByCreator", query = "SELECT c FROM ComplaintCode c WHERE c.creator = :creator"),
    @NamedQuery(name = "ComplaintCode.findByCredate", query = "SELECT c FROM ComplaintCode c WHERE c.credate = :credate"),
    @NamedQuery(name = "ComplaintCode.findByOptuser", query = "SELECT c FROM ComplaintCode c WHERE c.optuser = :optuser"),
    @NamedQuery(name = "ComplaintCode.findByOptdate", query = "SELECT c FROM ComplaintCode c WHERE c.optdate = :optdate"),
    @NamedQuery(name = "ComplaintCode.findByCfmuser", query = "SELECT c FROM ComplaintCode c WHERE c.cfmuser = :cfmuser"),
    @NamedQuery(name = "ComplaintCode.findByCfmdate", query = "SELECT c FROM ComplaintCode c WHERE c.cfmdate = :cfmdate")})
public class ComplaintCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "complaintcode")
    private String complaintcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "complaintname")
    private String complaintname;
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
    @JoinColumn(name = "classid", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ComplaintClass classid;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "complaintcode")
    private List<ComplaintDetail> complaintDetailList;

    public ComplaintCode() {
    }

    public ComplaintCode(String complaintcode) {
        this.complaintcode = complaintcode;
    }

    public ComplaintCode(String complaintcode, String complaintname, String status) {
        this.complaintcode = complaintcode;
        this.complaintname = complaintname;
        this.status = status;
    }

    public String getComplaintcode() {
        return complaintcode;
    }

    public void setComplaintcode(String complaintcode) {
        this.complaintcode = complaintcode;
    }

    public String getComplaintname() {
        return complaintname;
    }

    public void setComplaintname(String complaintname) {
        this.complaintname = complaintname;
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
        hash += (complaintcode != null ? complaintcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplaintCode)) {
            return false;
        }
        ComplaintCode other = (ComplaintCode) object;
        if ((this.complaintcode == null && other.complaintcode != null) || (this.complaintcode != null && !this.complaintcode.equals(other.complaintcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ComplaintCode[ complaintcode=" + complaintcode + " ]";
    }

    public ComplaintClass getClassid() {
        return classid;
    }

    public void setClassid(ComplaintClass classid) {
        this.classid = classid;
    }

    /**
     * @return the complaintDetailList
     */
    @XmlTransient
    public List<ComplaintDetail> getComplaintDetailList() {
        return complaintDetailList;
    }

    /**
     * @param complaintDetailList the complaintDetailList to set
     */
    public void setComplaintDetailList(List<ComplaintDetail> complaintDetailList) {
        this.complaintDetailList = complaintDetailList;
    }

}
