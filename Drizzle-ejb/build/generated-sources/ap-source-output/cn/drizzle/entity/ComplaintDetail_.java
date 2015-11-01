package cn.drizzle.entity;

import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintCode;
import cn.drizzle.entity.ItemMaster;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ComplaintDetail.class)
public class ComplaintDetail_ { 

    public static volatile SingularAttribute<ComplaintDetail, String> sn;
    public static volatile SingularAttribute<ComplaintDetail, Complaint> complaintid;
    public static volatile SingularAttribute<ComplaintDetail, String> responsible;
    public static volatile SingularAttribute<ComplaintDetail, String> batch;
    public static volatile SingularAttribute<ComplaintDetail, Integer> seq;
    public static volatile SingularAttribute<ComplaintDetail, Boolean> isprotected;
    public static volatile SingularAttribute<ComplaintDetail, String> brand;
    public static volatile SingularAttribute<ComplaintDetail, ItemMaster> itemno;
    public static volatile SingularAttribute<ComplaintDetail, BigDecimal> runtime;
    public static volatile SingularAttribute<ComplaintDetail, BigDecimal> qty;
    public static volatile SingularAttribute<ComplaintDetail, String> complaintidd;
    public static volatile SingularAttribute<ComplaintDetail, ComplaintCode> complaintcode;
    public static volatile SingularAttribute<ComplaintDetail, ComplaintClass> classid;

}