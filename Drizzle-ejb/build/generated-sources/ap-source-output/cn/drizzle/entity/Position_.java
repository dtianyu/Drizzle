package cn.drizzle.entity;

import cn.drizzle.entity.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Position.class)
public class Position_ { 

    public static volatile SingularAttribute<Position, String> position;
    public static volatile ListAttribute<Position, Employee> employeeList;
    public static volatile SingularAttribute<Position, String> remark;
    public static volatile SingularAttribute<Position, String> status;
    public static volatile SingularAttribute<Position, String> optuser;
    public static volatile SingularAttribute<Position, Date> cfmdate;
    public static volatile SingularAttribute<Position, String> positioncode;
    public static volatile SingularAttribute<Position, String> creator;
    public static volatile SingularAttribute<Position, Integer> id;
    public static volatile SingularAttribute<Position, Date> credate;
    public static volatile SingularAttribute<Position, String> cfmuser;
    public static volatile SingularAttribute<Position, Date> optdate;
    public static volatile SingularAttribute<Position, Integer> employees;

}