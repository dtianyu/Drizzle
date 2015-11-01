/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@Entity
@Table(name = "syscs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Syscs.findAll", query = "SELECT s FROM Syscs s"),
    @NamedQuery(name = "Syscs.findById", query = "SELECT s FROM Syscs s WHERE s.id = :id"),
    @NamedQuery(name = "Syscs.findByContractidcode", query = "SELECT s FROM Syscs s WHERE s.contractidcode = :contractidcode"),
    @NamedQuery(name = "Syscs.findByContractidformat", query = "SELECT s FROM Syscs s WHERE s.contractidformat = :contractidformat"),
    @NamedQuery(name = "Syscs.findByContractidlen", query = "SELECT s FROM Syscs s WHERE s.contractidlen = :contractidlen"),
    @NamedQuery(name = "Syscs.findByComplaintidcode", query = "SELECT s FROM Syscs s WHERE s.complaintidcode = :complaintidcode"),
    @NamedQuery(name = "Syscs.findByComplaintidformat", query = "SELECT s FROM Syscs s WHERE s.complaintidformat = :complaintidformat"),
    @NamedQuery(name = "Syscs.findByComplaintidlen", query = "SELECT s FROM Syscs s WHERE s.complaintidlen = :complaintidlen"),
    @NamedQuery(name = "Syscs.findByServiceidcode", query = "SELECT s FROM Syscs s WHERE s.serviceidcode = :serviceidcode"),
    @NamedQuery(name = "Syscs.findByServiceidformat", query = "SELECT s FROM Syscs s WHERE s.serviceidformat = :serviceidformat"),
    @NamedQuery(name = "Syscs.findByServiceidlen", query = "SELECT s FROM Syscs s WHERE s.serviceidlen = :serviceidlen"),
    @NamedQuery(name = "Syscs.findByStatus", query = "SELECT s FROM Syscs s WHERE s.status = :status"),
    @NamedQuery(name = "Syscs.findByCreator", query = "SELECT s FROM Syscs s WHERE s.creator = :creator"),
    @NamedQuery(name = "Syscs.findByCredate", query = "SELECT s FROM Syscs s WHERE s.credate = :credate"),
    @NamedQuery(name = "Syscs.findByOptuser", query = "SELECT s FROM Syscs s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "Syscs.findByOptdate", query = "SELECT s FROM Syscs s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "Syscs.findByCfmuser", query = "SELECT s FROM Syscs s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "Syscs.findByCfmdate", query = "SELECT s FROM Syscs s WHERE s.cfmdate = :cfmdate")})
public class Syscs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "contractidcode")
    private String contractidcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "contractidformat")
    private String contractidformat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contractidlen")
    private int contractidlen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "complaintidcode")
    private String complaintidcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "complaintidformat")
    private String complaintidformat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "complaintidlen")
    private int complaintidlen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "serviceidcode")
    private String serviceidcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "serviceidformat")
    private String serviceidformat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "serviceidlen")
    private int serviceidlen;
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
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Company companyid;

    public Syscs() {
    }

    public Syscs(Integer id) {
        this.id = id;
    }

    public Syscs(Integer id, String contractidcode, String contractidformat, int contractidlen, String complaintidcode, String complaintidformat, int complaintidlen, String serviceidcode, String serviceidformat, int serviceidlen, String status) {
        this.id = id;
        this.contractidcode = contractidcode;
        this.contractidformat = contractidformat;
        this.contractidlen = contractidlen;
        this.complaintidcode = complaintidcode;
        this.complaintidformat = complaintidformat;
        this.complaintidlen = complaintidlen;
        this.serviceidcode = serviceidcode;
        this.serviceidformat = serviceidformat;
        this.serviceidlen = serviceidlen;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractidcode() {
        return contractidcode;
    }

    public void setContractidcode(String contractidcode) {
        this.contractidcode = contractidcode;
    }

    public String getContractidformat() {
        return contractidformat;
    }

    public void setContractidformat(String contractidformat) {
        this.contractidformat = contractidformat;
    }

    public int getContractidlen() {
        return contractidlen;
    }

    public void setContractidlen(int contractidlen) {
        this.contractidlen = contractidlen;
    }

    public String getComplaintidcode() {
        return complaintidcode;
    }

    public void setComplaintidcode(String complaintidcode) {
        this.complaintidcode = complaintidcode;
    }

    public String getComplaintidformat() {
        return complaintidformat;
    }

    public void setComplaintidformat(String complaintidformat) {
        this.complaintidformat = complaintidformat;
    }

    public int getComplaintidlen() {
        return complaintidlen;
    }

    public void setComplaintidlen(int complaintidlen) {
        this.complaintidlen = complaintidlen;
    }

    public String getServiceidcode() {
        return serviceidcode;
    }

    public void setServiceidcode(String serviceidcode) {
        this.serviceidcode = serviceidcode;
    }

    public String getServiceidformat() {
        return serviceidformat;
    }

    public void setServiceidformat(String serviceidformat) {
        this.serviceidformat = serviceidformat;
    }

    public int getServiceidlen() {
        return serviceidlen;
    }

    public void setServiceidlen(int serviceidlen) {
        this.serviceidlen = serviceidlen;
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

    public Company getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Syscs)) {
            return false;
        }
        Syscs other = (Syscs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Syscs[ id=" + id + " ]";
    }


    
}
