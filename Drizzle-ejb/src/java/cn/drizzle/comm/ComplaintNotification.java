/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.drizzle.comm;

import cn.drizzle.entity.Complaint;
import cn.drizzle.entity.Sysuser;

/**
 *
 * @author C0160
 */
public class ComplaintNotification extends Notification {

    private Complaint complaint;

    public ComplaintNotification() {
    }

    public ComplaintNotification(Complaint c) {
        this.complaint = c;
        Sysuser user;
        user = complaint.getCustomerid().getSalerid();
        if (user != null) {
            getPersonList().add(user);
        }
        user = complaint.getCreator();
        if (user != null) {
            getPersonList().add(user);
        }
        setContent("您好！\n" + complaint.getCustomerid().getCustomer() + "提交了一张请求单，单号为：" + complaint.getComplaintid() + "状态为：" + Lib.getLocalOperateMessage("status." + complaint.getStatus()).toString());
        setSubject("新增请求单" + complaint.getComplaintid());
        NotifyFactory.createNotify(this);
        this.sendNotification();
        this.deleteObservers();
    }

    /**
     * @return the complaint
     */
    public Complaint getComplaint() {
        return complaint;
    }

    /**
     * @param complaint the complaint to set
     */
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
