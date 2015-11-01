/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemUnitType;
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
public class ItemUnitTypeQueryBean {

    private List<ItemUnitType> itemUnitTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemUnitTypeQueryBean() {
        itemUnitTypes = new ArrayList<ItemUnitType>();
        itemUnitTypes.add(new ItemUnitType("1", "单单位"));
        itemUnitTypes.add(new ItemUnitType("2", "双单位"));
    }

    /**
     * @return the itemUnitTypes
     */
    public List<ItemUnitType> getItemUnitTypes() {
        return itemUnitTypes;
    }

    /**
     * @param itemUnitTypes the itemUnitTypes to set
     */
    public void setItemUnitTypes(List<ItemUnitType> itemUnitTypes) {
        this.itemUnitTypes = itemUnitTypes;
    }
}
