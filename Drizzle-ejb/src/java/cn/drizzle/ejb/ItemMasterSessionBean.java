/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.ejb;

import cn.drizzle.entity.ItemMaster;
import cn.drizzle.comm.SuperEJB;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class ItemMasterSessionBean extends SuperEJB<ItemMaster> {

    public ItemMasterSessionBean() {
        this.className = "ItemMaster";
    }


    @Override
    public List<ItemMaster> getByPId(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
