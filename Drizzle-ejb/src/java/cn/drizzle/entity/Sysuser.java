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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "sysuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sysuser.findAll", query = "SELECT s FROM Sysuser s"),
    @NamedQuery(name = "Sysuser.findByUserid", query = "SELECT s FROM Sysuser s WHERE s.userid = :userid"),
    @NamedQuery(name = "Sysuser.findByName", query = "SELECT s FROM Sysuser s WHERE s.name = :name"),
    @NamedQuery(name = "Sysuser.findByPwd", query = "SELECT s FROM Sysuser s WHERE s.pwd = :pwd"),
    @NamedQuery(name = "Sysuser.findByEmployeeid", query = "SELECT s FROM Sysuser s WHERE s.employeeid = :employeeid"),
    @NamedQuery(name = "Sysuser.findBySid", query = "SELECT s FROM Sysuser s WHERE s.sid = :sid"),
    @NamedQuery(name = "Sysuser.findBySidpwd", query = "SELECT s FROM Sysuser s WHERE s.sidpwd = :sidpwd"),
    @NamedQuery(name = "Sysuser.findBySsoid", query = "SELECT s FROM Sysuser s WHERE s.ssoid = :ssoid"),
    @NamedQuery(name = "Sysuser.findByStatus", query = "SELECT s FROM Sysuser s WHERE s.status = :status"),
    @NamedQuery(name = "Sysuser.findByIdAndPwd", query = "SELECT s FROM Sysuser s WHERE s.userid = :userid and s.pwd = :pwd")})
public class Sysuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "userid")
    private String userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pwd")
    private String pwd;
    @Size(max = 10)
    @Column(name = "employeeid")
    private String employeeid;
    @Size(max = 32)
    @Column(name = "sid")
    private String sid;
    @Size(max = 32)
    @Column(name = "sidpwd")
    private String sidpwd;
    @Size(max = 32)
    @Column(name = "ssoid")
    private String ssoid;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    @Size(max = 20)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 20)
    @Column(name = "mobile2")
    private String mobile2;
    @Size(max = 100)
    @Column(name = "mailadd")
    private String mailadd;
    @Size(max = 100)
    @Column(name = "mailadd2")
    private String mailadd2;
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

    public Sysuser() {
    }

    public Sysuser(String userid) {
        this.userid = userid;
    }

    public Sysuser(String userid, String name, String pwd, String status) {
        this.userid = userid;
        this.name = name;
        this.pwd = pwd;
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSidpwd() {
        return sidpwd;
    }

    public void setSidpwd(String sidpwd) {
        this.sidpwd = sidpwd;
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sysuser)) {
            return false;
        }
        Sysuser other = (Sysuser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Sysuser[ userid=" + userid + " ]";
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getMailadd() {
        return mailadd;
    }

    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

    public String getMailadd2() {
        return mailadd2;
    }

    public void setMailadd2(String mailadd2) {
        this.mailadd2 = mailadd2;
    }
}
