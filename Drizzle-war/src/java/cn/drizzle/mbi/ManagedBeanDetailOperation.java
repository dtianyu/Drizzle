/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.mbi;

import java.io.Serializable;

/**
 *
 * @author C0160
 */
public interface ManagedBeanDetailOperation<T> extends Serializable {

    public int getSeq();

    public void initDetail();

    public void addDetail();

    public void createDetail();

    public void editDetail(T entity);

    public void persistDetail();

    public void removeDetail(T entity);

    public void updateDetail();

    public void viewDetail(T entity);
}
