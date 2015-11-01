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
@Table(name = "complaintdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintDetail.findAll", query = "SELECT c FROM ComplaintDetail c"),
    @NamedQuery(name = "ComplaintDetail.findByComplaintidd", query = "SELECT c FROM ComplaintDetail c WHERE c.complaintidd = :complaintidd"),
    @NamedQuery(name = "ComplaintDetail.findBySeq", query = "SELECT c FROM ComplaintDetail c WHERE c.seq = :seq"),
    @NamedQuery(name = "ComplaintDetail.findByBrand", query = "SELECT c FROM ComplaintDetail c WHERE c.brand = :brand"),
    @NamedQuery(name = "ComplaintDetail.findByBatch", query = "SELECT c FROM ComplaintDetail c WHERE c.batch = :batch"),
    @NamedQuery(name = "ComplaintDetail.findBySn", query = "SELECT c FROM ComplaintDetail c WHERE c.sn = :sn"),
    @NamedQuery(name = "ComplaintDetail.findByQty", query = "SELECT c FROM ComplaintDetail c WHERE c.qty = :qty"),
    @NamedQuery(name = "ComplaintDetail.findByRuntime", query = "SELECT c FROM ComplaintDetail c WHERE c.runtime = :runtime")})
public class ComplaintDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "complaintidd")
    private String complaintidd;
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
    @JoinColumn(name = "complaintid", referencedColumnName = "complaintid")
    @ManyToOne(optional = false)
    private Complaint complaintid;
    @JoinColumn(name = "classid", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ComplaintClass classid;
    @JoinColumn(name = "complaintcode", referencedColumnName = "complaintcode")
    @ManyToOne(optional = false)
    private ComplaintCode complaintcode;
    @JoinColumn(name = "itemno", referencedColumnName = "itemno")
    @ManyToOne(optional = false)
    private ItemMaster itemno;

    public ComplaintDetail() {
    }

    public ComplaintDetail(String complaintidd) {
        this.complaintidd = complaintidd;
    }

    public ComplaintDetail(String complaintidd, int seq, BigDecimal qty) {
        this.complaintidd = complaintidd;
        this.seq = seq;
        this.qty = qty;
    }

    public String getComplaintidd() {
        return complaintidd;
    }

    public void setComplaintidd(String complaintidd) {
        this.complaintidd = complaintidd;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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

    public Complaint getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(Complaint complaintid) {
        this.complaintid = complaintid;
    }

    public ComplaintCode getComplaintcode() {
        return complaintcode;
    }

    public void setComplaintcode(ComplaintCode complaintcode) {
        this.complaintcode = complaintcode;
    }

    public ComplaintClass getClassid() {
        return classid;
    }

    public void setClassid(ComplaintClass classid) {
        this.classid = classid;
    }

    public ItemMaster getItemno() {
        return itemno;
    }

    public void setItemno(ItemMaster itemno) {
        this.itemno = itemno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complaintidd != null ? complaintidd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplaintDetail)) {
            return false;
        }
        ComplaintDetail other = (ComplaintDetail) object;
        if ((this.complaintidd == null && other.complaintidd != null) || (this.complaintidd != null && other.complaintidd == null)) {
            return false;
        } else if (this.complaintidd.equals(other.complaintidd)) {
            //自己和自己不用比较
            return false;
        } else if (!(this.complaintid.equals(other.complaintid) && this.itemno.equals(other.itemno) && this.sn.equals(other.sn)
                && this.classid.equals(other.classid) && this.complaintcode.equals(other.complaintcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ComplaintDetail[ complaintidd=" + complaintidd + " ]";
    }

    /**
     * @return the isprotected
     */
    public boolean isIsprotected() {
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
