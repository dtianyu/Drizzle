package cn.drizzle.entity;

import cn.drizzle.entity.Service;
import cn.drizzle.entity.Sysuser;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-08-03T19:26:28")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> simplecode;
    public static volatile SingularAttribute<Customer, String> bankaccount;
    public static volatile SingularAttribute<Customer, String> bankid;
    public static volatile SingularAttribute<Customer, String> tel;
    public static volatile SingularAttribute<Customer, Sysuser> salerid;
    public static volatile SingularAttribute<Customer, String> regaddress;
    public static volatile SingularAttribute<Customer, Date> cfmdate;
    public static volatile SingularAttribute<Customer, String> pricingtype;
    public static volatile SingularAttribute<Customer, String> customer;
    public static volatile SingularAttribute<Customer, String> tradetype;
    public static volatile SingularAttribute<Customer, String> shipadd;
    public static volatile SingularAttribute<Customer, String> creator;
    public static volatile SingularAttribute<Customer, String> city;
    public static volatile SingularAttribute<Customer, String> currency;
    public static volatile SingularAttribute<Customer, BigDecimal> taxrate;
    public static volatile SingularAttribute<Customer, String> tel2;
    public static volatile SingularAttribute<Customer, String> area;
    public static volatile SingularAttribute<Customer, String> deptid;
    public static volatile SingularAttribute<Customer, String> taxkind;
    public static volatile SingularAttribute<Customer, String> grade;
    public static volatile SingularAttribute<Customer, String> fax2;
    public static volatile SingularAttribute<Customer, String> industry;
    public static volatile SingularAttribute<Customer, String> taxtype;
    public static volatile SingularAttribute<Customer, String> fullname;
    public static volatile SingularAttribute<Customer, String> taxid;
    public static volatile SingularAttribute<Customer, String> fax;
    public static volatile SingularAttribute<Customer, String> status;
    public static volatile SingularAttribute<Customer, String> optuser;
    public static volatile SingularAttribute<Customer, String> zipcode;
    public static volatile SingularAttribute<Customer, String> contacter;
    public static volatile SingularAttribute<Customer, String> paymentid;
    public static volatile SingularAttribute<Customer, String> country;
    public static volatile SingularAttribute<Customer, Date> credate;
    public static volatile ListAttribute<Customer, Service> serviceList;
    public static volatile SingularAttribute<Customer, String> customerid;
    public static volatile SingularAttribute<Customer, String> cfmuser;
    public static volatile SingularAttribute<Customer, Date> optdate;
    public static volatile SingularAttribute<Customer, BigDecimal> regcapital;
    public static volatile SingularAttribute<Customer, String> contactadd;

}