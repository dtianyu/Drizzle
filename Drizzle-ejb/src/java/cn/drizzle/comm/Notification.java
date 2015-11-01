/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.entity.Sysuser;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author C0160
 */
public abstract class Notification extends Observable {

    private List<Sysuser> personList;
    private String content;
    private String subject;

    public Notification() {
        personList=new ArrayList<>();
    }

    public Notification(Sysuser person, String content, String subject) {
        this();
        personList.add(person);
        this.content = content;
        this.subject = subject;
    }

    public void sendNotification() {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the content
     */
    protected String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    protected void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the subject
     */
    protected String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    protected void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the personList
     */
    protected List<Sysuser> getPersonList() {
        return personList;
    }

    /**
     * @param personList the personList to set
     */
    protected void setPersonList(List<Sysuser> personList) {
        this.personList = personList;
    }
}
