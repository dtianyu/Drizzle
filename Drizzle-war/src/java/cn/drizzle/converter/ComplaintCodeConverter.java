/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.ComplaintCodeSessionBean;
import cn.drizzle.entity.ComplaintCode;
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

@FacesConverter("complaintCodeConverter")
public class ComplaintCodeConverter implements Converter {

    ComplaintCodeSessionBean complaintCodeSessionBean = lookupComplaintCodeSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ComplaintCode entity = complaintCodeSessionBean.getById(value);
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
            return ((ComplaintCode) value).getComplaintcode().toString();
        }
    }

    private ComplaintCodeSessionBean lookupComplaintCodeSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (ComplaintCodeSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/ComplaintCodeSessionBean!cn.drizzle.ejb.ComplaintCodeSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
