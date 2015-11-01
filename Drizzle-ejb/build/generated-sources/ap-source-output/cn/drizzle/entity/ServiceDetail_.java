package cn.drizzle.entity;

import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintCode;
import cn.drizzle.entity.ItemMaster;
import cn.drizzle.entity.Service;
import cn.drizzle.entity.ServiceClass;
import cn.drizzle.entity.ServiceCode;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ServiceDetail.class)
public class ServiceDetail_ { 

    public static volatile SingularAttribute<ServiceDetail, String> sn;
    public static volatile SingularAttribute<ServiceDetail, String> responsible;
    public static volatile SingularAttribute<ServiceDetail, BigDecimal> runtime;
    public static volatile SingularAttribute<ServiceDetail, BigDecimal> qty;
    public static volatile SingularAttribute<ServiceDetail, ComplaintCode> complaintcode;
    public static volatile SingularAttribute<ServiceDetail, String> serviceidd;
    public static volatile SingularAttribute<ServiceDetail, Service> serviceid;
    public static volatile SingularAttribute<ServiceDetail, String> sourceidd;
    public static volatile SingularAttribute<ServiceDetail, ServiceClass> serviceclass;
    public static volatile SingularAttribute<ServiceDetail, String> batch;
    public static volatile SingularAttribute<ServiceDetail, Integer> seq;
    public static volatile SingularAttribute<ServiceDetail, Boolean> isprotected;
    public static volatile SingularAttribute<ServiceDetail, String> brand;
    public static volatile SingularAttribute<ServiceDetail, ItemMaster> itemno;
    public static volatile SingularAttribute<ServiceDetail, ComplaintClass> complaintclass;
    public static volatile SingularAttribute<ServiceDetail, ServiceCode> servicecode;

}