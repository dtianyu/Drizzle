/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "itemmaster")
@XmlRootElement
@NamedQueries({    
    @NamedQuery(name = "ItemMaster.findRowCount", query = "SELECT count(i) FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findAll", query = "SELECT i FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findByItemno", query = "SELECT i FROM ItemMaster i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemMaster.findByItemdesc", query = "SELECT i FROM ItemMaster i WHERE i.itemdesc = :itemdesc"),
    @NamedQuery(name = "ItemMaster.findByItemspec", query = "SELECT i FROM ItemMaster i WHERE i.itemspec = :itemspec"),
    @NamedQuery(name = "ItemMaster.findByItemcode", query = "SELECT i FROM ItemMaster i WHERE i.itemcode = :itemcode"),
    @NamedQuery(name = "ItemMaster.findByItemdesc2", query = "SELECT i FROM ItemMaster i WHERE i.itemdesc2 = :itemdesc2"),
    @NamedQuery(name = "ItemMaster.findByItemspec2", query = "SELECT i FROM ItemMaster i WHERE i.itemspec2 = :itemspec2"),
    @NamedQuery(name = "ItemMaster.findByProperty", query = "SELECT i FROM ItemMaster i WHERE i.property = :property"),
    @NamedQuery(name = "ItemMaster.findByMaketype", query = "SELECT i FROM ItemMaster i WHERE i.maketype = :maketype"),
    @NamedQuery(name = "ItemMaster.findByPotype", query = "SELECT i FROM ItemMaster i WHERE i.potype = :potype"),
    @NamedQuery(name = "ItemMaster.findByUnittype", query = "SELECT i FROM ItemMaster i WHERE i.unittype = :unittype"),
    @NamedQuery(name = "ItemMaster.findByUnit", query = "SELECT i FROM ItemMaster i WHERE i.unit = :unit"),
    @NamedQuery(name = "ItemMaster.findByUnit2", query = "SELECT i FROM ItemMaster i WHERE i.unit2 = :unit2"),
    @NamedQuery(name = "ItemMaster.findByUnitexch", query = "SELECT i FROM ItemMaster i WHERE i.unitexch = :unitexch"),
    @NamedQuery(name = "ItemMaster.findByUnitsales", query = "SELECT i FROM ItemMaster i WHERE i.unitsales = :unitsales"),
    @NamedQuery(name = "ItemMaster.findByUnitpurchase", query = "SELECT i FROM ItemMaster i WHERE i.unitpurchase = :unitpurchase"),
    @NamedQuery(name = "ItemMaster.findByUnitcode", query = "SELECT i FROM ItemMaster i WHERE i.unitcode = :unitcode"),
    @NamedQuery(name = "ItemMaster.findByAutono", query = "SELECT i FROM ItemMaster i WHERE i.autono = :autono"),
    @NamedQuery(name = "ItemMaster.findByInvtype", query = "SELECT i FROM ItemMaster i WHERE i.invtype = :invtype"),
    @NamedQuery(name = "ItemMaster.findByBbstype", query = "SELECT i FROM ItemMaster i WHERE i.bbstype = :bbstype"),
    @NamedQuery(name = "ItemMaster.findByStdprice", query = "SELECT i FROM ItemMaster i WHERE i.stdprice = :stdprice"),
    @NamedQuery(name = "ItemMaster.findByStdcost", query = "SELECT i FROM ItemMaster i WHERE i.stdcost = :stdcost"),
    @NamedQuery(name = "ItemMaster.findByAvgcost", query = "SELECT i FROM ItemMaster i WHERE i.avgcost = :avgcost"),
    @NamedQuery(name = "ItemMaster.findByPocost", query = "SELECT i FROM ItemMaster i WHERE i.pocost = :pocost"),
    @NamedQuery(name = "ItemMaster.findByPoqtymin", query = "SELECT i FROM ItemMaster i WHERE i.poqtymin = :poqtymin"),
    @NamedQuery(name = "ItemMaster.findByPoqtymax", query = "SELECT i FROM ItemMaster i WHERE i.poqtymax = :poqtymax"),
    @NamedQuery(name = "ItemMaster.findByInvqtymin", query = "SELECT i FROM ItemMaster i WHERE i.invqtymin = :invqtymin"),
    @NamedQuery(name = "ItemMaster.findByInvqtymax", query = "SELECT i FROM ItemMaster i WHERE i.invqtymax = :invqtymax"),
    @NamedQuery(name = "ItemMaster.findByBuyerid", query = "SELECT i FROM ItemMaster i WHERE i.buyerid = :buyerid"),
    @NamedQuery(name = "ItemMaster.findByBarcode", query = "SELECT i FROM ItemMaster i WHERE i.barcode = :barcode"),
    @NamedQuery(name = "ItemMaster.findByRemark", query = "SELECT i FROM ItemMaster i WHERE i.remark = :remark"),
    @NamedQuery(name = "ItemMaster.findByStatus", query = "SELECT i FROM ItemMaster i WHERE i.status = :status"),
    @NamedQuery(name = "ItemMaster.findByCreator", query = "SELECT i FROM ItemMaster i WHERE i.creator = :creator"),
    @NamedQuery(name = "ItemMaster.findByCredate", query = "SELECT i FROM ItemMaster i WHERE i.credate = :credate"),
    @NamedQuery(name = "ItemMaster.findByOptuser", query = "SELECT i FROM ItemMaster i WHERE i.optuser = :optuser"),
    @NamedQuery(name = "ItemMaster.findByOptdate", query = "SELECT i FROM ItemMaster i WHERE i.optdate = :optdate"),
    @NamedQuery(name = "ItemMaster.findByCfmuser", query = "SELECT i FROM ItemMaster i WHERE i.cfmuser = :cfmuser"),
    @NamedQuery(name = "ItemMaster.findByCfmdate", query = "SELECT i FROM ItemMaster i WHERE i.cfmdate = :cfmdate")})
public class ItemMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Size(max = 100)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 20)
    @Column(name = "itemcode")
    private String itemcode;
    @Size(max = 60)
    @Column(name = "itemdesc2")
    private String itemdesc2;
    @Size(max = 60)
    @Column(name = "itemspec2")
    private String itemspec2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "property")
    private String property;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "maketype")
    private String maketype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "potype")
    private String potype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unittype")
    private String unittype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    private String unit;
    @Size(max = 10)
    @Column(name = "unit2")
    private String unit2;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitexch")
    private BigDecimal unitexch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unitsales")
    private String unitsales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unitpurchase")
    private String unitpurchase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "unitcode")
    private String unitcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autono")
    private boolean autono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "invtype")
    private boolean invtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "bbstype")
    private String bbstype;
    @Column(name = "stdprice")
    private BigDecimal stdprice;
    @Column(name = "stdcost")
    private BigDecimal stdcost;
    @Column(name = "avgcost")
    private BigDecimal avgcost;
    @Column(name = "pocost")
    private BigDecimal pocost;
    @Column(name = "poqtymin")
    private BigDecimal poqtymin;
    @Column(name = "poqtymax")
    private BigDecimal poqtymax;
    @Column(name = "invqtymin")
    private BigDecimal invqtymin;
    @Column(name = "invqtymax")
    private BigDecimal invqtymax;
    @Size(max = 10)
    @Column(name = "buyerid")
    private String buyerid;
    @Size(max = 60)
    @Column(name = "barcode")
    private String barcode;
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
    @JoinColumn(name = "class1", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ItemClass class1;
    @JoinColumn(name = "class2", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ItemClass class2;
    @JoinColumn(name = "class3", referencedColumnName = "classid")
    @ManyToOne(optional = false)
    private ItemClass class3;

    public ItemMaster() {
    }

    public ItemMaster(String itemno) {
        this.itemno = itemno;
    }

    public ItemMaster(String itemno, String itemdesc, String property, String maketype, String potype, String unittype, String unit, String unitsales, String unitpurchase, String unitcode, boolean autono, boolean invtype, String bbstype, String status) {
        this.itemno = itemno;
        this.itemdesc = itemdesc;
        this.property = property;
        this.maketype = maketype;
        this.potype = potype;
        this.unittype = unittype;
        this.unit = unit;
        this.unitsales = unitsales;
        this.unitpurchase = unitpurchase;
        this.unitcode = unitcode;
        this.autono = autono;
        this.invtype = invtype;
        this.bbstype = bbstype;
        this.status = status;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdesc2() {
        return itemdesc2;
    }

    public void setItemdesc2(String itemdesc2) {
        this.itemdesc2 = itemdesc2;
    }

    public String getItemspec2() {
        return itemspec2;
    }

    public void setItemspec2(String itemspec2) {
        this.itemspec2 = itemspec2;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMaketype() {
        return maketype;
    }

    public void setMaketype(String maketype) {
        this.maketype = maketype;
    }

    public String getPotype() {
        return potype;
    }

    public void setPotype(String potype) {
        this.potype = potype;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public BigDecimal getUnitexch() {
        return unitexch;
    }

    public void setUnitexch(BigDecimal unitexch) {
        this.unitexch = unitexch;
    }

    public String getUnitsales() {
        return unitsales;
    }

    public void setUnitsales(String unitsales) {
        this.unitsales = unitsales;
    }

    public String getUnitpurchase() {
        return unitpurchase;
    }

    public void setUnitpurchase(String unitpurchase) {
        this.unitpurchase = unitpurchase;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public boolean getAutono() {
        return autono;
    }

    public void setAutono(boolean autono) {
        this.autono = autono;
    }

    public boolean getInvtype() {
        return invtype;
    }

    public void setInvtype(boolean invtype) {
        this.invtype = invtype;
    }

    public String getBbstype() {
        return bbstype;
    }

    public void setBbstype(String bbstype) {
        this.bbstype = bbstype;
    }

    public BigDecimal getStdprice() {
        return stdprice;
    }

    public void setStdprice(BigDecimal stdprice) {
        this.stdprice = stdprice;
    }

    public BigDecimal getStdcost() {
        return stdcost;
    }

    public void setStdcost(BigDecimal stdcost) {
        this.stdcost = stdcost;
    }

    public BigDecimal getAvgcost() {
        return avgcost;
    }

    public void setAvgcost(BigDecimal avgcost) {
        this.avgcost = avgcost;
    }

    public BigDecimal getPocost() {
        return pocost;
    }

    public void setPocost(BigDecimal pocost) {
        this.pocost = pocost;
    }

    public BigDecimal getPoqtymin() {
        return poqtymin;
    }

    public void setPoqtymin(BigDecimal poqtymin) {
        this.poqtymin = poqtymin;
    }

    public BigDecimal getPoqtymax() {
        return poqtymax;
    }

    public void setPoqtymax(BigDecimal poqtymax) {
        this.poqtymax = poqtymax;
    }

    public BigDecimal getInvqtymin() {
        return invqtymin;
    }

    public void setInvqtymin(BigDecimal invqtymin) {
        this.invqtymin = invqtymin;
    }

    public BigDecimal getInvqtymax() {
        return invqtymax;
    }

    public void setInvqtymax(BigDecimal invqtymax) {
        this.invqtymax = invqtymax;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public ItemClass getClass2() {
        return class2;
    }

    public void setClass2(ItemClass class2) {
        this.class2 = class2;
    }

    public ItemClass getClass1() {
        return class1;
    }

    public void setClass1(ItemClass class1) {
        this.class1 = class1;
    }

    public ItemClass getClass3() {
        return class3;
    }

    public void setClass3(ItemClass class3) {
        this.class3 = class3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemno != null ? itemno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.itemno == null && other.itemno != null) || (this.itemno != null && !this.itemno.equals(other.itemno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ItemMaster[ itemno=" + itemno + " ]";
    }

    /**
     * @return the unittype
     */
    public String getUnittype() {
        return unittype;
    }

    /**
     * @param unittype the unittype to set
     */
    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }
}
