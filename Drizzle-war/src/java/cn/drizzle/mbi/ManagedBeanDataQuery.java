/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.mbi;

import java.io.Serializable;

/**
 *
 * @author C0160
 * @param <T>
 */
public interface ManagedBeanDataQuery<T> extends Serializable{
       
    public void init();
}
