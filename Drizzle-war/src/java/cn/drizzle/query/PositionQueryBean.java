/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.query;

import cn.drizzle.ejb.PositionSessionBean;
import cn.drizzle.entity.Position;
import cn.drizzle.mbi.ManagedBeanDataQuery;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name="positionQueryBean")
@RequestScoped
public class PositionQueryBean implements ManagedBeanDataQuery<Position> {

    @EJB
    private PositionSessionBean positionSessionBean;
    private List<Position> positions;

    /**
     * Creates a new instance of PositionQueryBean
     */
    public PositionQueryBean() {
    }

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
        if (getPositions() != null) {
            getPositions().clear();
            setPositions(null);
        }
    }

    @Override
    public void init() {
        setPositions(positionSessionBean.retrieve());
    }

    /**
     * @return the positions
     */
    public List<Position> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
