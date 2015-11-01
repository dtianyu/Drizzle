package cn.drizzle.entity;

import cn.drizzle.entity.ItemMaster;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(ItemClass.class)
public class ItemClass_ { 

    public static volatile SingularAttribute<ItemClass, String> potype;
    public static volatile SingularAttribute<ItemClass, String> status;
    public static volatile SingularAttribute<ItemClass, String> optuser;
    public static volatile SingularAttribute<ItemClass, String> classdesc;
    public static volatile SingularAttribute<ItemClass, String> parent;
    public static volatile SingularAttribute<ItemClass, String> property;
    public static volatile SingularAttribute<ItemClass, String> maketype;
    public static volatile SingularAttribute<ItemClass, Date> cfmdate;
    public static volatile ListAttribute<ItemClass, ItemMaster> itemMasterList2;
    public static volatile ListAttribute<ItemClass, ItemMaster> itemMasterList1;
    public static volatile ListAttribute<ItemClass, ItemMaster> itemMasterList3;
    public static volatile SingularAttribute<ItemClass, String> creator;
    public static volatile SingularAttribute<ItemClass, String> bbstype;
    public static volatile SingularAttribute<ItemClass, Date> credate;
    public static volatile SingularAttribute<ItemClass, Boolean> invtype;
    public static volatile SingularAttribute<ItemClass, String> cfmuser;
    public static volatile SingularAttribute<ItemClass, Date> optdate;
    public static volatile SingularAttribute<ItemClass, Boolean> autono;
    public static volatile SingularAttribute<ItemClass, String> unittype;
    public static volatile SingularAttribute<ItemClass, String> classlevel;
    public static volatile SingularAttribute<ItemClass, String> classid;

}