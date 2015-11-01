/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.sbi;

import java.util.Date;
import java.util.List;

/**
 *
 * @author C0160
 */
public interface EJBOperateWithDetail<T> {

    public String getBizNo(int company, Date day, String code, String format, int len);

    public List<T> retrieve(String company);

    public List<T> retrieve(String company, Date daybegin, Date dayend);
}
