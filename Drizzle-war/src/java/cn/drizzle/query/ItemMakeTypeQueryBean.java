/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemMakeType;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class ItemMakeTypeQueryBean {

    private List<ItemMakeType> itemMakeTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemMakeTypeQueryBean() {
        itemMakeTypes = new ArrayList<>();
        itemMakeTypes.add(new ItemMakeType("F", "自制完成品"));
        itemMakeTypes.add(new ItemMakeType("M", "自制半成品"));
        itemMakeTypes.add(new ItemMakeType("P", "外购件"));
        itemMakeTypes.add(new ItemMakeType("S", "托外加工"));
        itemMakeTypes.add(new ItemMakeType("V", "虚拟件"));
    }

    /**
     * @return the itemMakeTypes
     */
    public List<ItemMakeType> getItemMakeTypes() {
        return itemMakeTypes;
    }

    /**
     * @param itemMakeTypes the itemMakeTypes to set
     */
    public void setItemMakeTypes(List<ItemMakeType> itemMakeTypes) {
        this.itemMakeTypes = itemMakeTypes;
    }
}
