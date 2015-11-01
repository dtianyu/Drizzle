/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.web;

import cn.drizzle.control.UserManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author CharlesTung
 */
public class LoginCheck implements PhaseListener {

//    @EJB(name = "userManagedBean")
//    private UserManagedBean userManagedBean;
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        try {
            boolean isLoginPage = fc.getViewRoot().getViewId().lastIndexOf("login.xhtml") > -1;
            if (!isLoginPage && !isLogined(fc)) {
                fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");
            }
        } catch (Exception e) {
            fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private boolean isLogined(FacesContext fc) {
        UserManagedBean user =(UserManagedBean) fc.getExternalContext().getSessionMap().get("userManagedBean");
        if (user == null) {
            return false;
        } else {
            return user.isStatus();
        }
    }
}
