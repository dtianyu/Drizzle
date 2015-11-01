package cn.drizzle.entity;

import cn.drizzle.entity.ComplaintClass;
import cn.drizzle.entity.ComplaintDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ComplaintCode.class)
public class ComplaintCode_ { 

    public static volatile SingularAttribute<ComplaintCode, Date> credate;
    public static volatile SingularAttribute<ComplaintCode, String> complaintname;
    public static volatile SingularAttribute<ComplaintCode, String> cfmuser;
    public static volatile SingularAttribute<ComplaintCode, String> status;
    public static volatile SingularAttribute<ComplaintCode, String> remark;
    public static volatile SingularAttribute<ComplaintCode, String> optuser;
    public static volatile SingularAttribute<ComplaintCode, Date> optdate;
    public static volatile ListAttribute<ComplaintCode, ComplaintDetail> complaintDetailList;
    public static volatile SingularAttribute<ComplaintCode, Date> cfmdate;
    public static volatile SingularAttribute<ComplaintCode, String> complaintcode;
    public static volatile SingularAttribute<ComplaintCode, ComplaintClass> classid;
    public static volatile SingularAttribute<ComplaintCode, String> creator;

}