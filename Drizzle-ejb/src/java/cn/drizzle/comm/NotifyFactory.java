/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

/**
 *
 * @author C0160
 */
public class NotifyFactory {

    public static void createNotify(Notification n) {
        new ShortMessageNotify(n);
        new MailNotify(n);
    }
}
