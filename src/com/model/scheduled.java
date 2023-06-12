package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "scheduled")
public class scheduled {
    protected static final long serialVersionUID = -1L;

    public scheduled() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 预定设备
     */
    @Column(name = "申请id")
    private int id;
    /**
     * 预定设备
     */
    @Column(name = "预定设备")
    private String uname;
    /**
     * 设备id
     */
    @Column(name = "设备id")
    private String uid;
    /**
     * 预定人姓名
     */
    @Column(name = "预定人姓名")
    private String name;
    /**
     * 预定时间
     */
    @Column(name = "预定时间")
    private String time;
    /**
     * 预定时长
     */
    @Column(name = "预定时长")
    private String standing;
    /**
     * 预定原因
     */
    @Column(name = "预定原因")
    private String reason;
    /**
     * 申请状态
     */
    @Column(name = "申请状态")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
