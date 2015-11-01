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
@Table(name = "complaint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complaint.findAll", query = "SELECT c FROM Complaint c"),
    @NamedQuery(name = "Complaint.findByComplaintid", query = "SELECT c FROM Complaint c WHERE c.complaintid = :complaintid"),
    @NamedQuery(name = "Complaint.findByCompany", query = "SELECT c FROM Complaint c WHERE c.company = :company"),
    @NamedQuery(name = "Complaint.findByCompanyAndComplaintDate", query = "SELECT c FROM Complaint c WHERE c.company = :company and c.complaintdate >= :daybegin and c.complaintdate <= :dayend"),
    @NamedQuery(name = "Complaint.findByComplaintdate", query = "SELECT c FROM Complaint c WHERE c.complaintdate = :complaintdate"),
    @NamedQuery(name = "Complaint.findByCustomerid", query = "SELECT c FROM Complaint c WHERE c.status='V' and c.customerid = :customerid"),
    @NamedQuery(name = "Complaint.findByDeptid", query = "SELECT c FROM Complaint c WHERE c.deptid = :deptid"),
    @NamedQuery(name = "Complaint.findByAreaid", query = "SELECT c FROM Complaint c WHERE c.areaid = :areaid"),
    @NamedQuery(name = "Complaint.findByContacter", query = "SELECT c FROM Complaint c WHERE c.contacter = :contacter"),
    @NamedQuery(name = "Complaint.findByTel", query = "SELECT c FROM Complaint c WHERE c.tel = :tel"),
    @NamedQuery(name = "Complaint.findByAddress", query = "SELECT c FROM Complaint c WHERE c.address = :address"),
    @NamedQuery(name = "Complaint.findByFinallycust", query = "SELECT c FROM Complaint c WHERE c.finallycust = :finallycust"),
    @NamedQuery(name = "Complaint.findByMark", query = "SELECT c FROM Complaint c WHERE c.mark = :mark"),
    @NamedQuery(name = "Complaint.findByStatus", query = "SELECT c FROM Complaint c WHERE c.status = :status"),
    @NamedQuery(name = "Complaint.findByCreator", query = "SELECT c FROM Complaint c WHERE c.creator = :creator"),
    @NamedQuery(name = "Complaint.findByCredate", query = "SELECT c FROM Complaint c WHERE c.credate = :credate"),
    @NamedQuery(name = "Complaint.findByOptuser", query = "SELECT c FROM Complaint c WHERE c.optuser = :optuser"),
    @NamedQuery(name = "Complaint.findByOptdate", query = "SELECT c FROM Complaint c WHERE c.optdate = :optdate"),
    @NamedQuery(name = "Complaint.findByCfmuser", query = "SELECT c FROM Complaint c WHERE c.cfmuser = :cfmuser"),
    @NamedQuery(name = "Complaint.findByCfmdate", query = "SELECT c FROM Complaint c WHERE c.cfmdate = :cfmdate"),
    @NamedQuery(name = "Complaint.findByAuduser", query = "SELECT c FROM Complaint c WHERE c.auduser = :auduser"),
    @NamedQuery(name = "Complaint.findByAuddate", query = "SELECT c FROM Complaint c WHERE c.auddate = :auddate")})
public class Complaint implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "deptid")
    private int deptid;
    @Size(max = 10)

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "complaintid")
    private String complaintid;
    @Column(name = "company")
    private Integer company;
    @Basic(optional = false)
    @NotNull
    @Column(name = "complaintdate")
    @Temporal(TemporalType.DATE)
    private Date complaintdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "areaid")
    private String areaid;
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
    @Column(name = "mark")
    private String mark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "creator",referencedColumnName = "userid")
    @ManyToOne
    private Sysuser creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @JoinColumn(name = "optuser",referencedColumnName = "userid")
    @ManyToOne
    private Sysuser optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @JoinColumn(name = "cfmuser",referencedColumnName = "userid")
    @ManyToOne
    private Sysuser cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;
    @JoinColumn(name = "auduser",referencedColumnName = "userid")
    @ManyToOne
    private Sysuser auduser;
    @Column(name = "auddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auddate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complaintid")
    private List<ComplaintDetail> complaintDetailList;
    @JoinColumn(name = "customerid", referencedColumnName = "customerid")
    @ManyToOne(optional = false)
    private Customer customerid;

    public Complaint() {
    }

    public Complaint(String complaintid) {
        this.complaintid = complaintid;
    }

    public String getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(String complaintid) {
        this.complaintid = complaintid;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Date getComplaintdate() {
        return complaintdate;
    }

    public void setComplaintdate(Date complaintdate) {
        this.complaintdate = complaintdate;
    }


    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    @XmlTransient
    public List<ComplaintDetail> getComplaintDetailList() {
        return complaintDetailList;
    }

    public void setComplaintDetailList(List<ComplaintDetail> complaintDetailList) {
        this.complaintDetailList = complaintDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complaintid != null ? complaintid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaint)) {
            return false;
        }
        Complaint other = (Complaint) object;
        if ((this.complaintid == null && other.complaintid != null) || (this.complaintid != null && !this.complaintid.equals(other.complaintid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Complaint[ complaintid=" + complaintid + " ]";
    }

    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }


}
