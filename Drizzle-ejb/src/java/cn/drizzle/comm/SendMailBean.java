/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class SendMailBean {

    @Resource(name = "mail/myJavaMail")
    private Session myJavaMail;
    private String username;
    private String password;
    private boolean auth;

    /**
     * Creates a new instance of SendMailBean
     */
    public SendMailBean() {
    }

    public SendMailBean(String username, String pwd, boolean auth) {
        this.username = username;
        this.password = pwd;
        this.auth = auth;
    }

    public void sendMail(String to, String subject, String body) throws NamingException, MessagingException {
        if (getAuth()) {
            Properties props = new Properties();
            props.put("mail.smtp.host", myJavaMail.getProperty("mail.host"));
            props.put("mail.smtp.port", 25);
            props.put("mail.smtp.auth", true);
            Authenticator authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
            myJavaMail = Session.getInstance(props, authenticator);
        }
        try {
            MimeMessage message = new MimeMessage(myJavaMail);
            message.setSubject(subject);
            InternetAddress[] mailadd = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, mailadd);
            message.setText(body);
            Transport.send(message);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the auth
     */
    public boolean getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
}
