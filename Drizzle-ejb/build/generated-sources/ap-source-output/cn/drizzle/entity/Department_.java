package cn.drizzle.entity;

import cn.drizzle.entity.Department;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile ListAttribute<Department, Department> departmentList;
    public static volatile SingularAttribute<Department, String> remark;
    public static volatile SingularAttribute<Department, String> status;
    public static volatile SingularAttribute<Department, String> optuser;
    public static volatile SingularAttribute<Department, Department> pid;
    public static volatile SingularAttribute<Department, Date> cfmdate;
    public static volatile SingularAttribute<Department, String> creator;
    public static volatile SingularAttribute<Department, Integer> id;
    public static volatile SingularAttribute<Department, Date> credate;
    public static volatile SingularAttribute<Department, Integer> companyid;
    public static volatile SingularAttribute<Department, String> cfmuser;
    public static volatile SingularAttribute<Department, String> deptcode;
    public static volatile SingularAttribute<Department, String> leader;
    public static volatile SingularAttribute<Department, Date> optdate;
    public static volatile SingularAttribute<Department, String> dept;

}