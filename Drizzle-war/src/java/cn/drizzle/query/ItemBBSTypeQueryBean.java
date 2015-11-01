/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.define.ItemBBSType;
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
public class ItemBBSTypeQueryBean {

    private List<ItemBBSType> itemBBSTypes;

    /**
     * Creates a new instance of ItemPropertyQueryBean
     */
    public ItemBBSTypeQueryBean() {
        itemBBSTypes = new ArrayList<ItemBBSType>();
        itemBBSTypes.add(new ItemBBSType("000", "不管理"));
        itemBBSTypes.add(new ItemBBSType("001", "管理序号"));
        itemBBSTypes.add(new ItemBBSType("010", "管理批号"));
        itemBBSTypes.add(new ItemBBSType("011", "管理批号+序号"));
        itemBBSTypes.add(new ItemBBSType("100", "管理品牌"));
        itemBBSTypes.add(new ItemBBSType("101", "管理品牌+序号"));
        itemBBSTypes.add(new ItemBBSType("110", "管理品牌+批号"));
        itemBBSTypes.add(new ItemBBSType("111", "管理品牌+批号+序号"));
    }

    /**
     * @return the itemBBSTypes
     */
    public List<ItemBBSType> getItemBBSTypes() {
        return itemBBSTypes;
    }

    /**
     * @param itemBBSTypes the itemBBSTypes to set
     */
    public void setItemBBSTypes(List<ItemBBSType> itemBBSTypes) {
        this.itemBBSTypes = itemBBSTypes;
    }
}
