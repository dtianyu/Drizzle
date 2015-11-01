/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.control;

import cn.drizzle.ejb.PositionSessionBean;
import cn.drizzle.entity.Position;
import cn.drizzle.mbi.ManagedBeanDataOperation;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class PositionManagedBean implements ManagedBeanDataOperation<Position> {

    @EJB
    private PositionSessionBean positionSessionBean;
    private Position currentPosition;
    private Position newPosition;
    private List<Position> positions;
    @ManagedProperty("#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * Creates a new instance of PositionManagedBean
     */
    public PositionManagedBean() {
    }

    @PostConstruct
    public void construct() {
        init();
        create();
    }

    @PreDestroy
    public void destroy() {
        if (getPositions() != null) {
            getPositions().clear();
            setPositions(null);
        }
        setCurrentPosition(null);
        setNewPosition(null);
    }

    @Override
    public void init() {
        setPositions(retrieve());
        if (!getPositions().isEmpty()) {
            setCurrentPosition(getPositions().get(0));
        }
    }

    @Override
    public void create() {
        Position entity = new Position();
        entity.setStatus("N");
        entity.setCreator(userManagedBean.getUserid());
        entity.setCredate(userManagedBean.getDate());
        entity.setOptuser(userManagedBean.getUserid());
        entity.setOptdate(userManagedBean.getDate());
        setNewPosition(entity);
    }

    @Override
    public void delete(Position entity) {
        if (entity != null) {
            try {
                positionSessionBean.delete(entity);
                if (getPositions().contains(entity)) {
                    getPositions().remove(entity);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("没有选中任何资料！"));
        }
    }

    @Override
    public void edit(Position entity) {
        if (entity != null) {
            setCurrentPosition(entity);
        }
    }

    @Override
    public void persist() {
        if (getNewPosition() != null) {
            try {
                positionSessionBean.update(getNewPosition());
                if (!getPositions().contains(getNewPosition())) {
                    getPositions().add(getNewPosition());
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public List<Position> retrieve() {
        return positionSessionBean.retrieve();
    }

    @Override
    public void save() {
        if (getCurrentPosition() != null) {
            try {
                getCurrentPosition().setStatus("M");
                getCurrentPosition().setOptuser(userManagedBean.getUserid());
                getCurrentPosition().setOptdate(userManagedBean.getDate());
                positionSessionBean.update(getCurrentPosition());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            init();
        }
    }

    @Override
    public void view(Position entity) {
        if (entity != null) {
            setCurrentPosition(entity);
        }
    }

    @Override
    public void verify() {
        if (getCurrentPosition() != null) {
            try {
                getCurrentPosition().setStatus("V");
                getCurrentPosition().setCfmuser(userManagedBean.getUserid());
                getCurrentPosition().setCfmdate(userManagedBean.getDate());
                positionSessionBean.update(getCurrentPosition());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            //init();
        }
    }

    @Override
    public void unverify() {
        if (getCurrentPosition() != null) {
            try {
                getCurrentPosition().setStatus("M");
                getCurrentPosition().setOptuser(userManagedBean.getUserid());
                getCurrentPosition().setOptdate(userManagedBean.getDate());
                getCurrentPosition().setCfmuser(null);
                getCurrentPosition().setCfmdate(null);
                positionSessionBean.update(getCurrentPosition());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(e.getMessage()));
                throw new Error(e.getLocalizedMessage());
            }
            //init();
        }
    }

    /**
     * @return the currentPosition
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param currentPosition the currentPosition to set
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * @return the newPosition
     */
    public Position getNewPosition() {
        return newPosition;
    }

    /**
     * @param newPosition the newPosition to set
     */
    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
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

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    @Override
    public void sendNotification(Position entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
