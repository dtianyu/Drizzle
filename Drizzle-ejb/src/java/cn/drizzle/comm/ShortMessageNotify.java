/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.entity.Sysuser;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author C0160
 */
public class ShortMessageNotify implements Observer, Notify {

    private Observable observable;
    private String mobile;
    private String content;

    public ShortMessageNotify() {
    }

    public ShortMessageNotify(Observable o) {
        this.observable = o;
        observable.addObserver(this);
    }

    @Override
    public void sendInfo(String value) {
        System.out.println("Send a mobile message to o " + value + this.content);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Notification) {
            Notification n = (Notification) o;
            for (Sysuser person : n.getPersonList()) {
                this.mobile = person.getMobile();
                this.content = person.getName() + n.getContent();
                if (this.mobile != null && this.content != null && !this.mobile.equals("") && !this.content.equals("")) {
                    sendInfo(this.mobile);
                }
            }
        }
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
