/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.comm.Lib;
import cn.drizzle.ejb.CompanySessionBean;
import cn.drizzle.ejb.SysuserSessionBean;
import cn.drizzle.entity.Company;
import cn.drizzle.entity.Sysuser;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author C0160
 */
@Named
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private CompanySessionBean companySessionBean;
    @EJB
    private SysuserSessionBean sysuserSessionBean;
    private Sysuser currentUser;
    private Company company;
    private String userid;
    private String pwd;
    private String companycode;
    private boolean status;

    public UserManagedBean() {
        status = false;
    }

    public boolean checkUser() {
        return true;
    }

    public String login() {
        if (getUserid().length() == 0 || getPwd().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(null, "用户名或密码错误"));
        }
        try {
            Sysuser u = sysuserSessionBean.getByIdAndPwd(getUserid(), Lib.securityMD5(getPwd()));
            if (u != null) {
                setCurrentUser(u);
                setCompany(companySessionBean.getByCode(getCompanycode()));
                setStatus(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userManagedBean", this);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "用户名或密码不正确！"));
            }
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", e.getMessage()));
            return "login";
        }
        return "index.xhtml";
    }

    public String logout() {
        if (status) {
            setCompanycode(null);
            setCurrentUser(null);
            setCompany(null);
            setStatus(false);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            return "login";
        } else {
            return "login";
        }
    }

    public void updatePwd() {
        try {
            currentUser.setPwd(Lib.securityMD5(pwd));
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", e.getMessage()));
        }
        try {
            sysuserSessionBean.update(currentUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "更新成功"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", e.getMessage()));
        }
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public void resetCompany() {
        setCompany(companySessionBean.getByCode(getCompanycode()));
    }

    /**
     * @return the currentUser
     */
    public Sysuser getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Sysuser currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the companycode
     */
    public String getCompanycode() {
        return companycode;
    }

    /**
     * @param companycode the companycode to set
     */
    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }
}
