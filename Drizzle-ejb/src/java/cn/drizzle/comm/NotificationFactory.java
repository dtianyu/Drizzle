/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.Service;

/**
 *
 * @author C0160
 */
public class NotificationFactory {

    public static Notification createNotification(Class arg0, Object arg1) {
        if (arg0 == null || arg1 == null) {
            return null;
        }
        Notification n = null;
        switch (arg0.getName()) {
            case "cn.drizzle.control.ComplaintManagedBean":
                n = new ComplaintNotification((Complaint) arg1);
                break;
            case "cn.drizzle.control.ServiceManagedBean":
                n = new ServiceNotification((Service) arg1);
                break;
            case "cn.hanbell.erp.control.SalesDetailManagedBean":
//                n = new SalesOrderNotification((Cdrdmas) arg1);
                break;
        }

        return n;
    }
}
