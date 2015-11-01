/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.JobSessionBean;
import cn.drizzle.entity.Job;
import cn.drizzle.mbi.ManagedBeanDataOperation;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class JobManagedBean implements ManagedBeanDataOperation<Job> {

    @EJB
    private JobSessionBean jobSessionBean;
    private Job currentJob;
    private Job newJob;
    private List<Job> jobs;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * Creates a new instance of JobManagedBean
     */
    public JobManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destroy() {
        if (getJobs() != null) {
            getJobs().clear();
            setJobs(null);
        }
        setCurrentJob(null);
        setNewJob(null);
    }

    @Override
    public void init() {
        setJobs(retrieve());
        if (!getJobs().isEmpty()) {
            setCurrentJob(getJobs().get(0));
        }
    }

    @Override
    public void create() {
        Job entity = new Job();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewJob(entity);
    }

    @Override
    public void delete(Job entity) {
        if (entity != null) {
            try {
                jobSessionBean.delete(entity);
                if (getJobs().contains(entity)) {
                    getJobs().remove(entity);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("没有选中任何资料！"));
        }
    }

    @Override
    public void edit(Job entity) {
        if (entity != null) {
            setCurrentJob(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewJob() != null) {
            try {
                jobSessionBean.update(getNewJob());
                if (!getJobs().contains(getNewJob())) {
                    getJobs().add(getNewJob());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Job> retrieve() {
        return jobSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentJob() != null) {
            try {
                getCurrentJob().setStatus("M");
                getCurrentJob().setOptuser(userManagedBean.getUserid());
                getCurrentJob().setOptdate(userManagedBean.getDate());
                jobSessionBean.update(getCurrentJob());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Job entity) {
        if (entity != null) {
            setCurrentJob(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentJob() != null) {
            try {
                getCurrentJob().setStatus("V");
                getCurrentJob().setCfmuser(userManagedBean.getUserid());
                getCurrentJob().setCfmdate(userManagedBean.getDate());
                jobSessionBean.update(getCurrentJob());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void unverify() {
        if (getCurrentJob() != null) {
            try {
                getCurrentJob().setStatus("M");
                getCurrentJob().setOptuser(userManagedBean.getUserid());
                getCurrentJob().setOptdate(userManagedBean.getDate());
                getCurrentJob().setCfmuser(null);
                getCurrentJob().setCfmdate(null);
                jobSessionBean.update(getCurrentJob());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * @return the currentJob
     */
    public Job getCurrentJob() {
        return currentJob;
    }

    /**
     * @param currentJob the currentJob to set
     */
    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    /**
     * @return the newJob
     */
    public Job getNewJob() {
        return newJob;
    }

    /**
     * @param newJob the newJob to set
     */
    public void setNewJob(Job newJob) {
        this.newJob = newJob;
    }

    /**
     * @return the jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * @param jobs the jobs to set
     */
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    @Override
    public void sendNotification(Job entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
