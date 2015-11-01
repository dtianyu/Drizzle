package cn.drizzle.entity;

import cn.drizzle.entity.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile SingularAttribute<Job, Integer> id;
    public static volatile SingularAttribute<Job, Date> credate;
    public static volatile ListAttribute<Job, Employee> employeeList;
    public static volatile SingularAttribute<Job, String> remark;
    public static volatile SingularAttribute<Job, String> status;
    public static volatile SingularAttribute<Job, String> cfmuser;
    public static volatile SingularAttribute<Job, String> optuser;
    public static volatile SingularAttribute<Job, String> job;
    public static volatile SingularAttribute<Job, Date> optdate;
    public static volatile SingularAttribute<Job, Date> cfmdate;
    public static volatile SingularAttribute<Job, String> jobcode;
    public static volatile SingularAttribute<Job, String> creator;

}