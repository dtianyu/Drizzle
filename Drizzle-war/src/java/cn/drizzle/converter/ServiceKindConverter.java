/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.ServiceKindSessionBean;
import cn.drizzle.entity.ServiceKind;
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
 * @author CharlesTung
 */
@FacesConverter("serviceKindConverter")
public class ServiceKindConverter implements Converter {

    ServiceKindSessionBean serviceKindSessionBean = lookupServiceKindSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
                if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ServiceKind entity = serviceKindSessionBean.getById(value);
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
            return ((ServiceKind) value).getKindid().toString();
        }
    }

    private ServiceKindSessionBean lookupServiceKindSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (ServiceKindSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/ServiceKindSessionBean!cn.drizzle.ejb.ServiceKindSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
