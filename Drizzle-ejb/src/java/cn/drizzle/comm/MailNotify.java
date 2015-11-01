/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.entity.Sysuser;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author C0160
 */
public class MailNotify implements Observer, Notify {

    SendMailBean sendMailBean = lookupSendMailBeanBean();
    private Observable observable;
    private String mailadd;
    private String content;
    private String subject;

    public MailNotify() {
    }

    public MailNotify(Observable o) {
        this.observable = o;
        observable.addObserver(this);
    }

    @Override
    public void sendInfo(String value) {
        try {
            sendMailBean.sendMail(this.mailadd, this.subject, this.content);
        } catch (NamingException | MessagingException ex) {
            Logger.getLogger(MailNotify.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(this.getClass().getSimpleName() + " Exception " + ex.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Notification) {
            Notification n = (Notification) o;
            this.subject = n.getSubject();
            for (Sysuser person : n.getPersonList()) {
                this.content = person.getName() + n.getContent();
                this.mailadd = person.getMailadd();
                if (this.mailadd != null && this.content != null && !this.mailadd.equals("") && !this.content.equals("")) {
                    sendInfo(this.getMailadd());
                }
            }
        }
    }

    /**
     * @return the mailadd
     */
    public String getMailadd() {
        return mailadd;
    }

    /**
     * @param mailadd the mailadd to set
     */
    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

    /**
     * @return the observable
     */
    public Observable getObservable() {
        return observable;
    }

    /**
     * @param observable the observable to set
     */
    public void setObservable(Observable observable) {
        this.observable = observable;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    private SendMailBean lookupSendMailBeanBean() {
        try {
            Context c = new InitialContext();
            return (SendMailBean) c.lookup("java:global/Drizzle/Drizzle-ejb/SendMailBean!cn.drizzle.comm.SendMailBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
