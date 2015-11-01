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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmployeeid", query = "SELECT e FROM Employee e WHERE e.employeeid = :employeeid"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findByAlias", query = "SELECT e FROM Employee e WHERE e.alias = :alias"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employee.findByBirthday", query = "SELECT e FROM Employee e WHERE e.birthday = :birthday"),
    @NamedQuery(name = "Employee.findByBirthplace", query = "SELECT e FROM Employee e WHERE e.birthplace = :birthplace"),
    @NamedQuery(name = "Employee.findByMarried", query = "SELECT e FROM Employee e WHERE e.married = :married"),
    @NamedQuery(name = "Employee.findByHealth", query = "SELECT e FROM Employee e WHERE e.health = :health"),
    @NamedQuery(name = "Employee.findByPoliticestatus", query = "SELECT e FROM Employee e WHERE e.politicestatus = :politicestatus"),
    @NamedQuery(name = "Employee.findByNationality", query = "SELECT e FROM Employee e WHERE e.nationality = :nationality"),
    @NamedQuery(name = "Employee.findByNativeplace", query = "SELECT e FROM Employee e WHERE e.nativeplace = :nativeplace"),
    @NamedQuery(name = "Employee.findByIdcard", query = "SELECT e FROM Employee e WHERE e.idcard = :idcard"),
    @NamedQuery(name = "Employee.findByHeight", query = "SELECT e FROM Employee e WHERE e.height = :height"),
    @NamedQuery(name = "Employee.findByWeight", query = "SELECT e FROM Employee e WHERE e.weight = :weight"),
    @NamedQuery(name = "Employee.findByBloodtype", query = "SELECT e FROM Employee e WHERE e.bloodtype = :bloodtype"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByAddcontact", query = "SELECT e FROM Employee e WHERE e.addcontact = :addcontact"),
    @NamedQuery(name = "Employee.findByZipcode", query = "SELECT e FROM Employee e WHERE e.zipcode = :zipcode"),
    @NamedQuery(name = "Employee.findByMobile", query = "SELECT e FROM Employee e WHERE e.mobile = :mobile"),
    @NamedQuery(name = "Employee.findByTel", query = "SELECT e FROM Employee e WHERE e.tel = :tel"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByDegree", query = "SELECT e FROM Employee e WHERE e.degree = :degree"),
    @NamedQuery(name = "Employee.findByBankid", query = "SELECT e FROM Employee e WHERE e.bankid = :bankid"),
    @NamedQuery(name = "Employee.findByBankaccount", query = "SELECT e FROM Employee e WHERE e.bankaccount = :bankaccount"),
    @NamedQuery(name = "Employee.findByStatus", query = "SELECT e FROM Employee e WHERE e.status = :status"),
    @NamedQuery(name = "Employee.findByCreator", query = "SELECT e FROM Employee e WHERE e.creator = :creator"),
    @NamedQuery(name = "Employee.findByCredate", query = "SELECT e FROM Employee e WHERE e.credate = :credate"),
    @NamedQuery(name = "Employee.findByOptuser", query = "SELECT e FROM Employee e WHERE e.optuser = :optuser"),
    @NamedQuery(name = "Employee.findByOptdate", query = "SELECT e FROM Employee e WHERE e.optdate = :optdate"),
    @NamedQuery(name = "Employee.findByCfmuser", query = "SELECT e FROM Employee e WHERE e.cfmuser = :cfmuser"),
    @NamedQuery(name = "Employee.findByCfmdate", query = "SELECT e FROM Employee e WHERE e.cfmdate = :cfmdate")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "employeeid")
    private String employeeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "alias")
    private String alias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 20)
    @Column(name = "birthplace")
    private String birthplace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "married")
    private String married;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "health")
    private String health;
    @Size(max = 10)
    @Column(name = "politicestatus")
    private String politicestatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nativeplace")
    private String nativeplace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "idcard")
    private String idcard;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "height")
    private Float height;
    @Column(name = "weight")
    private Float weight;
    @Size(max = 6)
    @Column(name = "bloodtype")
    private String bloodtype;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Size(max = 100)
    @Column(name = "addcontact")
    private String addcontact;
    @Size(max = 20)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="电子邮件无效")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 10)
    @Column(name = "degree")
    private String degree;
    @Size(max = 20)
    @Column(name = "bankid")
    private String bankid;
    @Size(max = 20)
    @Column(name = "bankaccount")
    private String bankaccount;
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
    @JoinColumn(name = "positionid", referencedColumnName = "id")
    @ManyToOne
    private Position positionid;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne
    private Department deptid;
    @JoinColumn(name = "jobid", referencedColumnName = "id")
    @ManyToOne
    private Job jobid;

    public Employee() {
    }

    public Employee(String employeeid) {
        this.employeeid = employeeid;
    }

    public Employee(String employeeid, String name, String gender, Date birthday, String married, String health, String nationality, String nativeplace, String idcard, String status) {
        this.employeeid = employeeid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.married = married;
        this.health = health;
        this.nationality = nationality;
        this.nativeplace = nativeplace;
        this.idcard = idcard;
        this.status = status;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPoliticestatus() {
        return politicestatus;
    }

    public void setPoliticestatus(String politicestatus) {
        this.politicestatus = politicestatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddcontact() {
        return addcontact;
    }

    public void setAddcontact(String addcontact) {
        this.addcontact = addcontact;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
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


    public Position getPositionid() {
        return positionid;
    }

    public void setPositionid(Position positionid) {
        this.positionid = positionid;
    }

    public Department getDeptid() {
        return deptid;
    }

    public void setDeptid(Department deptid) {
        this.deptid = deptid;
    }

    public Job getJobid() {
        return jobid;
    }

    public void setJobid(Job jobid) {
        this.jobid = jobid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Employee[ employeeid=" + employeeid + " ]";
    }
    
}
