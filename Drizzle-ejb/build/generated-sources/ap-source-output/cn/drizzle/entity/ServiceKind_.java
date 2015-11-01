package cn.drizzle.entity;

import cn.drizzle.entity.Service;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ServiceKind.class)
public class ServiceKind_ { 

    public static volatile SingularAttribute<ServiceKind, Date> credate;
    public static volatile ListAttribute<ServiceKind, Service> serviceList;
    public static volatile SingularAttribute<ServiceKind, String> servicekind;
    public static volatile SingularAttribute<ServiceKind, String> remark;
    public static volatile SingularAttribute<ServiceKind, String> status;
    public static volatile SingularAttribute<ServiceKind, Sysuser> cfmuser;
    public static volatile SingularAttribute<ServiceKind, Sysuser> optuser;
    public static volatile SingularAttribute<ServiceKind, String> kindid;
    public static volatile SingularAttribute<ServiceKind, Date> optdate;
    public static volatile SingularAttribute<ServiceKind, Date> cfmdate;
    public static volatile SingularAttribute<ServiceKind, Sysuser> creator;

}