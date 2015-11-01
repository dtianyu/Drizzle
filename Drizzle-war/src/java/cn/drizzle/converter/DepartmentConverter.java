/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.converter;

import cn.drizzle.ejb.DepartmentSessionBean;
import cn.drizzle.entity.Department;
import java.util.List;
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
@FacesConverter("departmentConverter")
public class DepartmentConverter implements Converter {
    DepartmentSessionBean departmentSessionBean = lookupDepartmentSessionBeanBean();



    public DepartmentConverter() {
        //departments = departmentSessionBean.retrieveData();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                Department d = departmentSessionBean.getById(value);
                if (d != null) {
                    return d;
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
            return ((Department) value).getId().toString();
        }
    }

    private DepartmentSessionBean lookupDepartmentSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (DepartmentSessionBean) c.lookup("java:global/Drizzle/Drizzle-ejb/DepartmentSessionBean!cn.drizzle.ejb.DepartmentSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }




}
