package cn.drizzle.entity;

import cn.drizzle.entity.ServiceClass;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ServiceCode.class)
public class ServiceCode_ { 

    public static volatile SingularAttribute<ServiceCode, Date> credate;
    public static volatile ListAttribute<ServiceCode, ServiceDetail> serviceDetailList;
    public static volatile SingularAttribute<ServiceCode, Sysuser> cfmuser;
    public static volatile SingularAttribute<ServiceCode, String> status;
    public static volatile SingularAttribute<ServiceCode, String> remark;
    public static volatile SingularAttribute<ServiceCode, Sysuser> optuser;
    public static volatile SingularAttribute<ServiceCode, String> servicename;
    public static volatile SingularAttribute<ServiceCode, Date> optdate;
    public static volatile SingularAttribute<ServiceCode, Date> cfmdate;
    public static volatile SingularAttribute<ServiceCode, String> servicecode;
    public static volatile SingularAttribute<ServiceCode, ServiceClass> classid;
    public static volatile SingularAttribute<ServiceCode, Sysuser> creator;

}