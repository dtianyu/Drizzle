/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.mbi;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author C0160
 */
public interface ManagedBeanDataOperation<T> extends Serializable {

    public void init();

    public void create();

    public void delete(T entity);

    public void edit(T entity);

    public void persist();

    public List<T> retrieve();

    public void save();
    
    public void sendNotification(T entity);

    public void view(T entity);

    public void verify();

    public void unverify();
}
