/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.ServiceCodeSessionBean;
import cn.drizzle.entity.ServiceCode;
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

@FacesConverter("serviceCodeConverter")
public class ServiceCodeConverter implements Converter {

    ServiceCodeSessionBean serviceCodeSessionBean = lookupServiceCodeSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ServiceCode entity = serviceCodeSessionBean.getById(value);
                if (entity != null) {
                    return entity;
                } else {
                    return null;
                }
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entity"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return ((ServiceCode) value).getServicecode().toString();
        }
    }

    private ServiceCodeSessionBean lookupServiceCodeSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (ServiceCodeSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/ServiceCodeSessionBean!cn.drizzle.ejb.ServiceCodeSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
