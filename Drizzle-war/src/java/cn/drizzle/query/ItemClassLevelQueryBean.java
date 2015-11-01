/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemClassLevel;
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
public class ItemClassLevelQueryBean {

    private ItemClassLevel currentItemClassLevel;
    private List<ItemClassLevel> itemClassLevels;
    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemClassLevelQueryBean() {
       itemClassLevels = new ArrayList<ItemClassLevel>() ;
       itemClassLevels.add(new ItemClassLevel("1","大类")); 
       itemClassLevels.add(new ItemClassLevel("2","中类")); 
       itemClassLevels.add(new ItemClassLevel("3","小类")); 
    }
    
    /**
     * @return the currentItemClassLevel
     */
    public ItemClassLevel getCurrentItemClassLevel() {
        return currentItemClassLevel;
    }

    /**
     * @param currentItemClassLevel the currentItemClassLevel to set
     */
    public void setCurrentItemClassLevel(ItemClassLevel currentItemClassLevel) {
        this.currentItemClassLevel = currentItemClassLevel;
    }
    
    /**
     * @return the itemClassLevels
     */
    public List<ItemClassLevel> getItemClassLevels() {
        return itemClassLevels;
    }

    /**
     * @param itemClassLevels the itemClassLevels to set
     */
    public void setItemClassLevels(List<ItemClassLevel> itemClassLevels) {
        this.itemClassLevels = itemClassLevels;
    }



}
