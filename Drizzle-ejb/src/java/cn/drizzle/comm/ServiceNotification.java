/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.ejb.SysuserSessionBean;
import cn.drizzle.entity.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author C0160
 */
public class ServiceNotification extends Notification {

    SysuserSessionBean sysuserSessionBean = lookupSysuserSessionBeanBean();
    private Service service;

    public ServiceNotification() {
    }

    public ServiceNotification(Service s) {
        this.service = s;
        getPersonList().add(sysuserSessionBean.getById(service.getCustomerid().getCustomerid()));
        setContent("您好！\n" + "您提交的" + service.getSourceid() + "请求单我们已受理，受理单号为" + service.getServiceid() + "\n单据状态为:" + Lib.getLocalOperateMessage("status." + s.getStatus()).toString());
        setSubject(service.getSourceid() + "状态更新");
        NotifyFactory.createNotify(this);
        this.sendNotification();
        this.deleteObservers();
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
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
