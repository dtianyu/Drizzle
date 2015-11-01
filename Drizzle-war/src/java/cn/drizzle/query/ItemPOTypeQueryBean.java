/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemPOType;
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
public class ItemPOTypeQueryBean {

    private List<ItemPOType> itemPOTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemPOTypeQueryBean() {
        itemPOTypes = new ArrayList<ItemPOType>();
        itemPOTypes.add(new ItemPOType("F", "固定补足"));
        itemPOTypes.add(new ItemPOType("M", "MRP"));
        itemPOTypes.add(new ItemPOType("M&P", "MRP和采购"));
        itemPOTypes.add(new ItemPOType("P", "采购"));
    }

    /**
     * @return the itemPOTypes
     */
    public List<ItemPOType> getItemPOTypes() {
        return itemPOTypes;
    }

    /**
     * @param itemPOTypes the itemPOTypes to set
     */
    public void setItemPOTypes(List<ItemPOType> itemPOTypes) {
        this.itemPOTypes = itemPOTypes;
    }
}
