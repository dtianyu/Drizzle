/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@Entity
@Table(name = "servicedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceDetail.findAll", query = "SELECT s FROM ServiceDetail s"),
    @NamedQuery(name = "ServiceDetail.findByServiceidd", query = "SELECT s FROM ServiceDetail s WHERE s.serviceidd = :serviceidd"),
    @NamedQuery(name = "ServiceDetail.findByItemno", query = "SELECT s FROM ServiceDetail s WHERE s.itemno = :itemno"),
    @NamedQuery(name = "ServiceDetail.findByBrand", query = "SELECT s FROM ServiceDetail s WHERE s.brand = :brand"),
    @NamedQuery(name = "ServiceDetail.findByBatch", query = "SELECT s FROM ServiceDetail s WHERE s.batch = :batch"),
    @NamedQuery(name = "ServiceDetail.findBySn", query = "SELECT s FROM ServiceDetail s WHERE s.sn = :sn"),
    @NamedQuery(name = "ServiceDetail.findByComplaintclass", query = "SELECT s FROM ServiceDetail s WHERE s.complaintclass = :complaintclass"),
    @NamedQuery(name = "ServiceDetail.findByComplaintcode", query = "SELECT s FROM ServiceDetail s WHERE s.complaintcode = :complaintcode"),
    @NamedQuery(name = "ServiceDetail.findBySourceidd", query = "SELECT s FROM ServiceDetail s WHERE s.sourceidd LIKE :sourceidd")})
public class ServiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "serviceidd")
    private String serviceidd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;
    @Size(max = 50)
    @Column(name = "brand")
    private String brand;
    @Size(max = 50)
    @Column(name = "batch")
    private String batch;
    @Size(max = 50)
    @Column(name = "sn")
    private String sn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Column(name = "runtime")
    private BigDecimal runtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isprotected")
    private boolean isprotected;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "responsible")
    private String responsible;
    @Size(max = 20)
    @Column(name = "sourceidd")
    private String sourceidd;
    @JoinColumn(name = "serviceid", referencedColumnName = "serviceid")
    @ManyToOne(optional = false)
    private Service serviceid;
    @JoinColumn(name = "itemno", referencedColumnName = "itemno")
    @ManyToOne(optional = false)
    private ItemMaster itemno;
    @JoinColumn(name = "serviceclass", referencedColumnName = "classid")
    @ManyToOne(optional = true)
    private ServiceClass serviceclass;
    @JoinColumn(name = "servicecode", referencedColumnName = "servicecode")
    @ManyToOne(optional = true)
    private ServiceCode servicecode;
    @JoinColumn(name = "complaintclass", referencedColumnName = "classid")
    @ManyToOne(optional = true)
    private ComplaintClass complaintclass;
    @JoinColumn(name = "complaintcode", referencedColumnName = "complaintcode")
    @ManyToOne(optional = true)
    private ComplaintCode complaintcode;

    public ServiceDetail() {
    }

    public ServiceDetail(String serviceidd) {
        this.serviceidd = serviceidd;
    }

    public String getServiceidd() {
        return serviceidd;
    }

    public void setServiceidd(String serviceidd) {
        this.serviceidd = serviceidd;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public ItemMaster getItemno() {
        return itemno;
    }

    public void setItemno(ItemMaster itemno) {
        this.itemno = itemno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getRuntime() {
        return runtime;
    }

    public void setRuntime(BigDecimal runtime) {
        this.runtime = runtime;
    }

    public ComplaintClass getComplaintclass() {
        return complaintclass;
    }

    public void setComplaintclass(ComplaintClass complaintclass) {
        this.complaintclass = complaintclass;
    }

    public ComplaintCode getComplaintcode() {
        return complaintcode;
    }

    public void setComplaintcode(ComplaintCode complaintcode) {
        this.complaintcode = complaintcode;
    }

    public String getSourceidd() {
        return sourceidd;
    }

    public void setSourceidd(String sourceidd) {
        this.sourceidd = sourceidd;
    }

    public ServiceClass getServiceclass() {
        return serviceclass;
    }

    public void setServiceclass(ServiceClass serviceclass) {
        this.serviceclass = serviceclass;
    }

    public Service getServiceid() {
        return serviceid;
    }

    public void setServiceid(Service serviceid) {
        this.serviceid = serviceid;
    }

    public ServiceCode getServicecode() {
        return servicecode;
    }

    public void setServicecode(ServiceCode servicecode) {
        this.servicecode = servicecode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceidd != null ? serviceidd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceDetail)) {
            return false;
        }
        ServiceDetail other = (ServiceDetail) object;
        if ((this.serviceidd == null && other.serviceidd != null) || (this.serviceidd != null && !this.serviceidd.equals(other.serviceidd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ServiceDetail[ serviceidd=" + serviceidd + " ]";
    }

    /**
     * @return the isprotected
     */
    public boolean getIsprotected() {
        return isprotected;
    }

    /**
     * @param isprotected the isprotected to set
     */
    public void setIsprotected(boolean isprotected) {
        this.isprotected = isprotected;
    }

    /**
     * @return the responsible
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * @param responsible the responsible to set
     */
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
