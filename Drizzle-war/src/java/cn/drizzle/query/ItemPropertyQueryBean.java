/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemProperty;
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
public class ItemPropertyQueryBean {

    private List<ItemProperty> itemProperties;
    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemPropertyQueryBean() {
       itemProperties = new ArrayList<ItemProperty>();
       itemProperties.add(new ItemProperty("1","成品")); 
       itemProperties.add(new ItemProperty("2","半成品")); 
       itemProperties.add(new ItemProperty("3","原料")); 
       itemProperties.add(new ItemProperty("4","物料")); 
       itemProperties.add(new ItemProperty("5","人工")); 
       itemProperties.add(new ItemProperty("7","包装物")); 
       itemProperties.add(new ItemProperty("8","低值易耗品")); 
       itemProperties.add(new ItemProperty("9","费用")); 
       itemProperties.add(new ItemProperty("A","资产")); 
       itemProperties.add(new ItemProperty("C","代理商品")); 
    }

    /**
     * @return the itemProperties
     */
    public List<ItemProperty> getItemProperties() {
        return itemProperties;
    }

    /**
     * @param itemProperties the itemProperties to set
     */
    public void setItemProperties(List<ItemProperty> itemProperties) {
        this.itemProperties = itemProperties;
    }
}
