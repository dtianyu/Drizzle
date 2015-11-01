package cn.drizzle.entity;

import cn.drizzle.entity.ComplaintCode;
import cn.drizzle.entity.ComplaintDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ComplaintClass.class)
public class ComplaintClass_ { 

    public static volatile SingularAttribute<ComplaintClass, Date> credate;
    public static volatile SingularAttribute<ComplaintClass, String> cfmuser;
    public static volatile SingularAttribute<ComplaintClass, String> status;
    public static volatile SingularAttribute<ComplaintClass, String> remark;
    public static volatile SingularAttribute<ComplaintClass, String> optuser;
    public static volatile ListAttribute<ComplaintClass, ComplaintCode> complaintCodeList;
    public static volatile SingularAttribute<ComplaintClass, Date> optdate;
    public static volatile SingularAttribute<ComplaintClass, String> complaintclass;
    public static volatile ListAttribute<ComplaintClass, ComplaintDetail> complaintDetailList;
    public static volatile SingularAttribute<ComplaintClass, Date> cfmdate;
    public static volatile SingularAttribute<ComplaintClass, String> classid;
    public static volatile SingularAttribute<ComplaintClass, String> creator;

}