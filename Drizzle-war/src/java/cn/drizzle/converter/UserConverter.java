/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.SysuserSessionBean;
import cn.drizzle.entity.Sysuser;
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
@FacesConverter("userConverter")
public class UserConverter implements Converter {

    SysuserSessionBean sysuserSessionBean = lookupSysuserSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                Sysuser entity = sysuserSessionBean.getById(value);
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
            return ((Sysuser) value).getUserid().toString();
        }
    }

    private SysuserSessionBean lookupSysuserSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (SysuserSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/SysuserSessionBean!cn.drizzle.ejb.SysuserSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
