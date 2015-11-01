package cn.drizzle.entity;

import cn.drizzle.entity.ServiceCode;
import cn.drizzle.entity.ServiceDetail;
import cn.drizzle.entity.Sysuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ServiceClass.class)
public class ServiceClass_ { 

    public static volatile SingularAttribute<ServiceClass, Date> credate;
    public static volatile ListAttribute<ServiceClass, ServiceDetail> serviceDetailList;
    public static volatile SingularAttribute<ServiceClass, String> serviceclass;
    public static volatile SingularAttribute<ServiceClass, String> remark;
    public static volatile SingularAttribute<ServiceClass, String> status;
    public static volatile SingularAttribute<ServiceClass, Sysuser> cfmuser;
    public static volatile SingularAttribute<ServiceClass, Sysuser> optuser;
    public static volatile ListAttribute<ServiceClass, ServiceCode> serviceCodeList;
    public static volatile SingularAttribute<ServiceClass, Date> optdate;
    public static volatile SingularAttribute<ServiceClass, Date> cfmdate;
    public static volatile SingularAttribute<ServiceClass, String> classid;
    public static volatile SingularAttribute<ServiceClass, Sysuser> creator;

}