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
@Table(name = "itemclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemClass.findAll", query = "SELECT i FROM ItemClass i"),
    @NamedQuery(name = "ItemClass.findByClassid", query = "SELECT i FROM ItemClass i WHERE i.classid = :classid"),
    @NamedQuery(name = "ItemClass.findByClasslevel", query = "SELECT i FROM ItemClass i WHERE i.classlevel = :classlevel"),
    @NamedQuery(name = "ItemClass.findByClassdesc", query = "SELECT i FROM ItemClass i WHERE i.classdesc = :classdesc"),
    @NamedQuery(name = "ItemClass.findByProperty", query = "SELECT i FROM ItemClass i WHERE i.property = :property"),
    @NamedQuery(name = "ItemClass.findByMaketype", query = "SELECT i FROM ItemClass i WHERE i.maketype = :maketype"),
    @NamedQuery(name = "ItemClass.findByPotype", query = "SELECT i FROM ItemClass i WHERE i.potype = :potype"),
    @NamedQuery(name = "ItemClass.findByUnittype", query = "SELECT i FROM ItemClass i WHERE i.unittype = :unittype"),
    @NamedQuery(name = "ItemClass.findByAutono", query = "SELECT i FROM ItemClass i WHERE i.autono = :autono"),
    @NamedQuery(name = "ItemClass.findByInvtype", query = "SELECT i FROM ItemClass i WHERE i.invtype = :invtype"),
    @NamedQuery(name = "ItemClass.findByBbstype", query = "SELECT i FROM ItemClass i WHERE i.bbstype = :bbstype"),
    @NamedQuery(name = "ItemClass.findByParent", query = "SELECT i FROM ItemClass i WHERE i.parent = :parent"),
    @NamedQuery(name = "ItemClass.findByStatus", query = "SELECT i FROM ItemClass i WHERE i.status = :status"),
    @NamedQuery(name = "ItemClass.findByCreator", query = "SELECT i FROM ItemClass i WHERE i.creator = :creator"),
    @NamedQuery(name = "ItemClass.findByCredate", query = "SELECT i FROM ItemClass i WHERE i.credate = :credate"),
    @NamedQuery(name = "ItemClass.findByOptuser", query = "SELECT i FROM ItemClass i WHERE i.optuser = :optuser"),
    @NamedQuery(name = "ItemClass.findByOptdate", query = "SELECT i FROM ItemClass i WHERE i.optdate = :optdate"),
    @NamedQuery(name = "ItemClass.findByCfmuser", query = "SELECT i FROM ItemClass i WHERE i.cfmuser = :cfmuser"),
    @NamedQuery(name = "ItemClass.findByCfmdate", query = "SELECT i FROM ItemClass i WHERE i.cfmdate = :cfmdate")})
public class ItemClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "classid")
    private String classid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "classlevel")
    private String classlevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "classdesc")
    private String classdesc;
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
    @Size(max = 20)
    @Column(name = "parent")
    private String parent;
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
    @Size(max = 20)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
    private List<ItemMaster> itemMasterList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class2")
    private List<ItemMaster> itemMasterList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class3")
    private List<ItemMaster> itemMasterList3;

    public ItemClass() {
    }

    public ItemClass(String classid) {
        this.classid = classid;
    }

    public ItemClass(String classid, String classlevel, String classdesc, String property, String maketype, String potype, String unittype, boolean autono, boolean invtype, String bbstype, String status) {
        this.classid = classid;
        this.classlevel = classlevel;
        this.classdesc = classdesc;
        this.property = property;
        this.maketype = maketype;
        this.potype = potype;
        this.unittype = unittype;
        this.autono = autono;
        this.invtype = invtype;
        this.bbstype = bbstype;
        this.status = status;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassdesc() {
        return classdesc;
    }

    public void setClassdesc(String classdesc) {
        this.classdesc = classdesc;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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
        hash += (classid != null ? classid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemClass)) {
            return false;
        }
        ItemClass other = (ItemClass) object;
        if ((this.classid == null && other.classid != null) || (this.classid != null && !this.classid.equals(other.classid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.drizzle.entity.ItemClass[ classid=" + classid + " ]";
    }

    /**
     * @return the itemMasterList1
     */
    @XmlTransient
    public List<ItemMaster> getItemMasterList1() {
        return itemMasterList1;
    }

    /**
     * @param itemMasterList1 the itemMasterList1 to set
     */
    public void setItemMasterList1(List<ItemMaster> itemMasterList1) {
        this.itemMasterList1 = itemMasterList1;
    }

    /**
     * @return the itemMasterList2
     */
    @XmlTransient
    public List<ItemMaster> getItemMasterList2() {
        return itemMasterList2;
    }

    /**
     * @param itemMasterList2 the itemMasterList2 to set
     */
    public void setItemMasterList2(List<ItemMaster> itemMasterList2) {
        this.itemMasterList2 = itemMasterList2;
    }

    /**
     * @return the itemMasterList3
     */
    @XmlTransient
    public List<ItemMaster> getItemMasterList3() {
        return itemMasterList3;
    }

    /**
     * @param itemMasterList3 the itemMasterList3 to set
     */
    public void setItemMasterList3(List<ItemMaster> itemMasterList3) {
        this.itemMasterList3 = itemMasterList3;
    }

    /**
     * @return the classlevel
     */
    public String getClasslevel() {
        return classlevel;
    }

    /**
     * @param classlevel the classlevel to set
     */
    public void setClasslevel(String classlevel) {
        this.classlevel = classlevel;
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
