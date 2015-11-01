/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerid", query = "SELECT c FROM Customer c WHERE c.customerid = :customerid"),
    @NamedQuery(name = "Customer.findByCustomer", query = "SELECT c FROM Customer c WHERE c.customer = :customer"),
    @NamedQuery(name = "Customer.findByFullname", query = "SELECT c FROM Customer c WHERE c.fullname = :fullname"),
    @NamedQuery(name = "Customer.findBySimplecode", query = "SELECT c FROM Customer c WHERE c.simplecode = :simplecode"),
    @NamedQuery(name = "Customer.findByTel", query = "SELECT c FROM Customer c WHERE c.tel = :tel"),
    @NamedQuery(name = "Customer.findByFax", query = "SELECT c FROM Customer c WHERE c.fax = :fax"),
    @NamedQuery(name = "Customer.findByTel2", query = "SELECT c FROM Customer c WHERE c.tel2 = :tel2"),
    @NamedQuery(name = "Customer.findByFax2", query = "SELECT c FROM Customer c WHERE c.fax2 = :fax2"),
    @NamedQuery(name = "Customer.findByZipcode", query = "SELECT c FROM Customer c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "Customer.findByCountry", query = "SELECT c FROM Customer c WHERE c.country = :country"),
    @NamedQuery(name = "Customer.findByArea", query = "SELECT c FROM Customer c WHERE c.area = :area"),
    @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM Customer c WHERE c.city = :city"),
    @NamedQuery(name = "Customer.findBySalerid", query = "SELECT c FROM Customer c WHERE c.salerid = :salerid"),
    @NamedQuery(name = "Customer.findByDeptid", query = "SELECT c FROM Customer c WHERE c.deptid = :deptid"),
    @NamedQuery(name = "Customer.findByPricingtype", query = "SELECT c FROM Customer c WHERE c.pricingtype = :pricingtype"),
    @NamedQuery(name = "Customer.findByCurrency", query = "SELECT c FROM Customer c WHERE c.currency = :currency"),
    @NamedQuery(name = "Customer.findByTaxtype", query = "SELECT c FROM Customer c WHERE c.taxtype = :taxtype"),
    @NamedQuery(name = "Customer.findByTaxkind", query = "SELECT c FROM Customer c WHERE c.taxkind = :taxkind"),
    @NamedQuery(name = "Customer.findByTaxrate", query = "SELECT c FROM Customer c WHERE c.taxrate = :taxrate"),
    @NamedQuery(name = "Customer.findByTradetype", query = "SELECT c FROM Customer c WHERE c.tradetype = :tradetype"),
    @NamedQuery(name = "Customer.findByPaymentid", query = "SELECT c FROM Customer c WHERE c.paymentid = :paymentid"),
    @NamedQuery(name = "Customer.findByShipadd", query = "SELECT c FROM Customer c WHERE c.shipadd = :shipadd"),
    @NamedQuery(name = "Customer.findByGrade", query = "SELECT c FROM Customer c WHERE c.grade = :grade"),
    @NamedQuery(name = "Customer.findByIndustry", query = "SELECT c FROM Customer c WHERE c.industry = :industry"),
    @NamedQuery(name = "Customer.findByRegaddress", query = "SELECT c FROM Customer c WHERE c.regaddress = :regaddress"),
    @NamedQuery(name = "Customer.findByRegcapital", query = "SELECT c FROM Customer c WHERE c.regcapital = :regcapital"),
    @NamedQuery(name = "Customer.findByTaxid", query = "SELECT c FROM Customer c WHERE c.taxid = :taxid"),
    @NamedQuery(name = "Customer.findByBankid", query = "SELECT c FROM Customer c WHERE c.bankid = :bankid"),
    @NamedQuery(name = "Customer.findByBankaccount", query = "SELECT c FROM Customer c WHERE c.bankaccount = :bankaccount"),
    @NamedQuery(name = "Customer.findByStatus", query = "SELECT c FROM Customer c WHERE c.status = :status"),
    @NamedQuery(name = "Customer.findByCreator", query = "SELECT c FROM Customer c WHERE c.creator = :creator"),
    @NamedQuery(name = "Customer.findByCredate", query = "SELECT c FROM Customer c WHERE c.credate = :credate"),
    @NamedQuery(name = "Customer.findByOptuser", query = "SELECT c FROM Customer c WHERE c.optuser = :optuser"),
    @NamedQuery(name = "Customer.findByOptdate", query = "SELECT c FROM Customer c WHERE c.optdate = :optdate"),
    @NamedQuery(name = "Customer.findByCfmuser", query = "SELECT c FROM Customer c WHERE c.cfmuser = :cfmuser"),
    @NamedQuery(name = "Customer.findByCfmdate", query = "SELECT c FROM Customer c WHERE c.cfmdate = :cfmdate")})
public class Customer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerid")
    private List<Service> serviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "customerid")
    private String customerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "customer")
    private String customer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "simplecode")
    private String simplecode;
    @Size(max = 20)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Size(max = 20)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 20)
    @Column(name = "fax2")
    private String fax2;
    @Size(max = 20)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 300)
    @Column(name = "contactadd")
    private String contactadd;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 10)
    @Column(name = "country")
    private String country;
    @Size(max = 10)
    @Column(name = "area")
    private String area;
    @Size(max = 10)
    @Column(name = "city")
    private String city;
    @JoinColumn(name = "salerid",referencedColumnName = "userid")
    @ManyToOne
    private Sysuser salerid;
    @Size(max = 10)
    @Column(name = "deptid")
    private String deptid;
    @Size(max = 10)
    @Column(name = "pricingtype")
    private String pricingtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "taxtype")
    private String taxtype;
    @Size(max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxrate")
    private BigDecimal taxrate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 10)
    @Column(name = "paymentid")
    private String paymentid;
    @Size(max = 10)
    @Column(name = "shipadd")
    private String shipadd;
    @Size(max = 10)
    @Column(name = "grade")
    private String grade;
    @Size(max = 10)
    @Column(name = "industry")
    private String industry;
    @Size(max = 300)
    @Column(name = "regaddress")
    private String regaddress;
    @Column(name = "regcapital")
    private BigDecimal regcapital;
    @Size(max = 30)
    @Column(name = "taxid")
    private String taxid;
    @Size(max = 60)
    @Column(name = "bankid")
    private String bankid;
    @Size(max = 60)
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

    public Customer() {
    }

    public Customer(String customerid) {
        this.customerid = customerid;
    }

    public Customer(String customerid, String customer, String fullname, String simplecode, String currency, String taxtype, BigDecimal taxrate, String tradetype, String status) {
        this.customerid = customerid;
        this.customer = customer;
        this.fullname = fullname;
        this.simplecode = simplecode;
        this.currency = currency;
        this.taxtype = taxtype;
        this.taxrate = taxrate;
        this.tradetype = tradetype;
        this.status = status;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Sysuser getSalerid() {
        return salerid;
    }

    public void setSalerid(Sysuser salerid) {
        this.salerid = salerid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getPricingtype() {
        return pricingtype;
    }

    public void setPricingtype(String pricingtype) {
        this.pricingtype = pricingtype;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxtype() {
        return taxtype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public String getTaxkind() {
        return taxkind;
    }

    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getShipadd() {
        return shipadd;
    }

    public void setShipadd(String shipadd) {
        this.shipadd = shipadd;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public BigDecimal getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(BigDecimal regcapital) {
        this.regcapital = regcapital;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.Customer[ customerid=" + customerid + " ]";
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    /**
     * @return the contacter
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * @param contacter the contacter to set
     */
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    /**
     * @return the contactadd
     */
    public String getContactadd() {
        return contactadd;
    }

    /**
     * @param contactadd the contactadd to set
     */
    public void setContactadd(String contactadd) {
        this.contactadd = contactadd;
    }
}
