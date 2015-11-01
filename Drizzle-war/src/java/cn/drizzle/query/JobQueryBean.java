/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.JobSessionBean;
import cn.drizzle.entity.Job;
import cn.drizzle.mbi.ManagedBeanDataQuery;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class JobQueryBean implements ManagedBeanDataQuery<Job> {

    @EJB
    private JobSessionBean jobSessionBean;
    private List<Job> jobs;

    /**
     * Creates a new instance of JobQueryBean
     */
    public JobQueryBean() {

    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getJobs() != null) {
            getJobs().clear();
            setJobs(null);
        }
    }

    @Override
    public void init() {
        setJobs(jobSessionBean.retrieve());
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
}
