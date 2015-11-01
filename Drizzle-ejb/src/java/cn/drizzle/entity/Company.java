/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByCompanycode", query = "SELECT c FROM Company c WHERE c.companycode = :companycode"),
    @NamedQuery(name = "Company.findByCompany", query = "SELECT c FROM Company c WHERE c.company = :company"),
    @NamedQuery(name = "Company.findByShortname", query = "SELECT c FROM Company c WHERE c.shortname = :shortname"),
    @NamedQuery(name = "Company.findByCompany2", query = "SELECT c FROM Company c WHERE c.company2 = :company2"),
    @NamedQuery(name = "Company.findByShortname2", query = "SELECT c FROM Company c WHERE c.shortname2 = :shortname2"),
    @NamedQuery(name = "Company.findBySimplecode", query = "SELECT c FROM Company c WHERE c.simplecode = :simplecode"),
    @NamedQuery(name = "Company.findByTel", query = "SELECT c FROM Company c WHERE c.tel = :tel"),
    @NamedQuery(name = "Company.findByFax", query = "SELECT c FROM Company c WHERE c.fax = :fax"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByZipcode", query = "SELECT c FROM Company c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "Company.findByCurrency", query = "SELECT c FROM Company c WHERE c.currency = :currency"),
    @NamedQuery(name = "Company.findByRegaddress", query = "SELECT c FROM Company c WHERE c.regaddress = :regaddress"),
    @NamedQuery(name = "Company.findByTaxid", query = "SELECT c FROM Company c WHERE c.taxid = :taxid"),
    @NamedQuery(name = "Company.findByRegcapital", query = "SELECT c FROM Company c WHERE c.regcapital = :regcapital"),
    @NamedQuery(name = "Company.findByBankid", query = "SELECT c FROM Company c WHERE c.bankid = :bankid"),
    @NamedQuery(name = "Company.findByBankaccount", query = "SELECT c FROM Company c WHERE c.bankaccount = :bankaccount"),
    @NamedQuery(name = "Company.findByStatus", query = "SELECT c FROM Company c WHERE c.status = :status"),
    @NamedQuery(name = "Company.findByCreator", query = "SELECT c FROM Company c WHERE c.creator = :creator"),
    @NamedQuery(name = "Company.findByCredate", query = "SELECT c FROM Company c WHERE c.credate = :credate"),
    @NamedQuery(name = "Company.findByOptuser", query = "SELECT c FROM Company c WHERE c.optuser = :optuser"),
    @NamedQuery(name = "Company.findByOptdate", query = "SELECT c FROM Company c WHERE c.optdate = :optdate"),
    @NamedQuery(name = "Company.findByCfmuser", query = "SELECT c FROM Company c WHERE c.cfmuser = :cfmuser"),
    @NamedQuery(name = "Company.findByCfmdate", query = "SELECT c FROM Company c WHERE c.cfmdate = :cfmdate")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "companycode")
    private String companycode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company")
    private String company;
    @Size(max = 20)
    @Column(name = "shortname")
    private String shortname;
    @Size(max = 45)
    @Column(name = "company2")
    private String company2;
    @Size(max = 20)
    @Column(name = "shortname2")
    private String shortname2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "simplecode")
    private String simplecode;
    @Size(max = 45)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "fax")
    private String fax;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 10)
    @Column(name = "currency")
    private String currency;
    @Size(max = 100)
    @Column(name = "regaddress")
    private String regaddress;
    @Size(max = 30)
    @Column(name = "taxid")
    private String taxid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "regcapital")
    private BigDecimal regcapital;
    @Size(max = 60)
    @Column(name = "bankid")
    private String bankid;
    @Size(max = 60)
    @Column(name = "bankaccount")
    private String bankaccount;
    @Size(max = 32)
    @Column(name = "domain")
    private String domain;
    @Size(max = 32)
    @Column(name = "domainadmin")
    private String domainadmin;
    @Size(max = 32)
    @Column(name = "domainpwd")
    private String domainpwd;
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
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "companyid", fetch = FetchType.EAGER)
    private Syscs syscs;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String companycode, String company, String simplecode, String status) {
        this.id = id;
        this.companycode = companycode;
        this.company = company;
        this.simplecode = simplecode;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getShortname2() {
        return shortname2;
    }

    public void setShortname2(String shortname2) {
        this.shortname2 = shortname2;
    }

    public String getSimplecode() {
        return simplecode;
    }

    public void setSimplecode(String simplecode) {
        this.simplecode = simplecode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public BigDecimal getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(BigDecimal regcapital) {
        this.regcapital = regcapital;
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

    public Syscs getSyscs() {
        return syscs;
    }

    public void setSyscs(Syscs syscs) {
        this.syscs = syscs;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("cn/drizzle/i18n").getString("CN.DRIZZLE.ENTITY.COMPANY[ ID={0} ]"), new Object[] {id});
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainadmin() {
        return domainadmin;
    }

    public void setDomainadmin(String domainadmin) {
        this.domainadmin = domainadmin;
    }

    public String getDomainpwd() {
        return domainpwd;
    }

    public void setDomainpwd(String domainpwd) {
        this.domainpwd = domainpwd;
    }
}
