/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.ItemClassSessionBean;
import cn.drizzle.entity.ItemClass;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author C0160
 */
@FacesConverter("itemClassConverter")
public class ItemClassConverter implements Converter {

    ItemClassSessionBean itemClassSessionBean = lookupItemClassSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ItemClass entity = itemClassSessionBean.getById(value);
                if (entity != null) {
                    return entity;
                } else {
                    return null;
                }
            } catch (ConverterException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entity"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return ((ItemClass) value).getClassid().toString();
        }
    }

    private ItemClassSessionBean lookupItemClassSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (ItemClassSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/ItemClassSessionBean!cn.drizzle.ejb.ItemClassSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
