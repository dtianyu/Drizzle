package cn.drizzle.entity;

import cn.drizzle.entity.ComplaintDetail;
import cn.drizzle.entity.Customer;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Complaint.class)
public class Complaint_ { 

    public static volatile SingularAttribute<Complaint, String> status;
    public static volatile SingularAttribute<Complaint, Sysuser> optuser;
    public static volatile SingularAttribute<Complaint, String> contacter;
    public static volatile SingularAttribute<Complaint, String> tel;
    public static volatile SingularAttribute<Complaint, String> areaid;
    public static volatile SingularAttribute<Complaint, String> mark;
    public static volatile SingularAttribute<Complaint, Date> cfmdate;
    public static volatile SingularAttribute<Complaint, Date> auddate;
    public static volatile SingularAttribute<Complaint, String> finallycust;
    public static volatile SingularAttribute<Complaint, Sysuser> creator;
    public static volatile SingularAttribute<Complaint, Date> credate;
    public static volatile SingularAttribute<Complaint, String> complaintid;
    public static volatile SingularAttribute<Complaint, Integer> deptid;
    public static volatile SingularAttribute<Complaint, Customer> customerid;
    public static volatile SingularAttribute<Complaint, String> address;
    public static volatile SingularAttribute<Complaint, Sysuser> cfmuser;
    public static volatile SingularAttribute<Complaint, Integer> company;
    public static volatile SingularAttribute<Complaint, Date> optdate;
    public static volatile SingularAttribute<Complaint, Sysuser> auduser;
    public static volatile SingularAttribute<Complaint, Date> complaintdate;
    public static volatile ListAttribute<Complaint, ComplaintDetail> complaintDetailList;

}