/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.lazy;

import cn.drizzle.ejb.ItemMasterSessionBean;
import cn.drizzle.entity.ItemMaster;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author C0160
 */
public class ItemMasterModel extends LazyDataModel<ItemMaster> {

    private ItemMasterSessionBean sessionBean;
    protected List<ItemMaster> dataList;

    public ItemMasterModel() {

    }

    public ItemMasterModel(ItemMasterSessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public ItemMaster getRowData(String rowKey) {
        for (ItemMaster entity : dataList) {
            if (entity.getItemno().equals(rowKey)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(ItemMaster entity) {
        return entity.getItemno();
    }

    @Override
    public List<ItemMaster> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        setDataList(sessionBean.findAll(first, pageSize));
        setRowCount(sessionBean.getRowCount());
        return this.dataList;
    }
    

    /**
     * @return the dataList
     */
    public List<ItemMaster> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<ItemMaster> dataList) {
        this.dataList = dataList;
    }
    
    
    
}
