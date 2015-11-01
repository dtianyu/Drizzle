package cn.drizzle.entity;

import cn.drizzle.entity.Customer;
import cn.drizzle.entity.Department;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.entity.ServiceKind;
import cn.drizzle.entity.ServiceType;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile ListAttribute<Service, ServiceDetail> serviceDetailList;
    public static volatile SingularAttribute<Service, String> remark;
    public static volatile SingularAttribute<Service, String> tel;
    public static volatile SingularAttribute<Service, ServiceKind> kindid;
    public static volatile SingularAttribute<Service, Date> cfmdate;
    public static volatile SingularAttribute<Service, String> finallycust;
    public static volatile SingularAttribute<Service, String> serviceid;
    public static volatile SingularAttribute<Service, Sysuser> creator;
    public static volatile SingularAttribute<Service, Department> deptid;
    public static volatile SingularAttribute<Service, ServiceType> typeid;
    public static volatile SingularAttribute<Service, String> provincearrive;
    public static volatile SingularAttribute<Service, String> provinceleave;
    public static volatile SingularAttribute<Service, String> status;
    public static volatile SingularAttribute<Service, Date> servicedate;
    public static volatile SingularAttribute<Service, Sysuser> optuser;
    public static volatile SingularAttribute<Service, String> isfree;
    public static volatile SingularAttribute<Service, String> contacter;
    public static volatile SingularAttribute<Service, String> areaid;
    public static volatile SingularAttribute<Service, Date> auddate;
    public static volatile SingularAttribute<Service, String> cityarrive;
    public static volatile SingularAttribute<Service, Date> credate;
    public static volatile SingularAttribute<Service, Customer> customerid;
    public static volatile SingularAttribute<Service, Sysuser> cfmuser;
    public static volatile SingularAttribute<Service, String> address;
    public static volatile SingularAttribute<Service, Integer> company;
    public static volatile SingularAttribute<Service, String> sourceid;
    public static volatile SingularAttribute<Service, Date> optdate;
    public static volatile SingularAttribute<Service, String> sourcecustomerid;
    public static volatile SingularAttribute<Service, Sysuser> auduser;
    public static volatile SingularAttribute<Service, Date> sourcedate;
    public static volatile SingularAttribute<Service, String> cityleave;

}