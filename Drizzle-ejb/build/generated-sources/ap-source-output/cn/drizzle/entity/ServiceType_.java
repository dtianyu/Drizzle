package cn.drizzle.entity;

import cn.drizzle.entity.Service;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ServiceType.class)
public class ServiceType_ { 

    public static volatile SingularAttribute<ServiceType, Date> credate;
    public static volatile ListAttribute<ServiceType, Service> serviceList;
    public static volatile SingularAttribute<ServiceType, String> servicetype;
    public static volatile SingularAttribute<ServiceType, String> remark;
    public static volatile SingularAttribute<ServiceType, String> status;
    public static volatile SingularAttribute<ServiceType, Sysuser> cfmuser;
    public static volatile SingularAttribute<ServiceType, Sysuser> optuser;
    public static volatile SingularAttribute<ServiceType, String> typeid;
    public static volatile SingularAttribute<ServiceType, Date> optdate;
    public static volatile SingularAttribute<ServiceType, Date> cfmdate;
    public static volatile SingularAttribute<ServiceType, Sysuser> creator;

}