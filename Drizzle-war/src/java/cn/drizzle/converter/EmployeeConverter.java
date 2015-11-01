/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.EmployeeSessionBean;
import cn.drizzle.entity.Employee;
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
@FacesConverter("employeeConverter")
public class EmployeeConverter implements Converter {

    EmployeeSessionBean employeeSessionBean = lookupEmployeeSessionBeanBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                Employee e = employeeSessionBean.getById(value);
                if (e != null) {
                    return e;
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
            return ((Employee) value).getEmployeeid().toString();
        }
    }

    private EmployeeSessionBean lookupEmployeeSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (EmployeeSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/EmployeeSessionBean!cn.drizzle.ejb.EmployeeSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
