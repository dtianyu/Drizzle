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
public interface EJBOperate<T> {

    public void delete(T entity);

    public T getById(String value);

    public List<T> getByPId(String value);

    public List<T> retrieve();

    public T update(T entity);
}
