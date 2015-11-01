package cn.drizzle.entity;

import cn.drizzle.entity.Department;
import cn.drizzle.entity.Job;
import cn.drizzle.entity.Position;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Date> birthday;
    public static volatile SingularAttribute<Employee, Float> weight;
    public static volatile SingularAttribute<Employee, String> bankaccount;
    public static volatile SingularAttribute<Employee, String> bankid;
    public static volatile SingularAttribute<Employee, String> tel;
    public static volatile SingularAttribute<Employee, Date> cfmdate;
    public static volatile SingularAttribute<Employee, String> addcontact;
    public static volatile SingularAttribute<Employee, String> creator;
    public static volatile SingularAttribute<Employee, Float> height;
    public static volatile SingularAttribute<Employee, String> married;
    public static volatile SingularAttribute<Employee, Department> deptid;
    public static volatile SingularAttribute<Employee, String> politicestatus;
    public static volatile SingularAttribute<Employee, String> name;
    public static volatile SingularAttribute<Employee, String> gender;
    public static volatile SingularAttribute<Employee, String> employeeid;
    public static volatile SingularAttribute<Employee, String> status;
    public static volatile SingularAttribute<Employee, String> alias;
    public static volatile SingularAttribute<Employee, String> optuser;
    public static volatile SingularAttribute<Employee, String> zipcode;
    public static volatile SingularAttribute<Employee, String> idcard;
    public static volatile SingularAttribute<Employee, String> bloodtype;
    public static volatile SingularAttribute<Employee, String> health;
    public static volatile SingularAttribute<Employee, Job> jobid;
    public static volatile SingularAttribute<Employee, Date> credate;
    public static volatile SingularAttribute<Employee, Position> positionid;
    public static volatile SingularAttribute<Employee, String> degree;
    public static volatile SingularAttribute<Employee, String> nationality;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, String> birthplace;
    public static volatile SingularAttribute<Employee, String> cfmuser;
    public static volatile SingularAttribute<Employee, String> nativeplace;
    public static volatile SingularAttribute<Employee, Date> optdate;
    public static volatile SingularAttribute<Employee, String> mobile;

}