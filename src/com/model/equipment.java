package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "equipment")
public class equipment {
    protected static final long serialVersionUID = -1L;

    public equipment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "设备id")
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "设备名称")
    private String uname;
    /**
     * 密码
     */
    @Column(name = "设备状态")
    private String state;
    /**
     * 姓名
     */
    @Column(name = "预定情况")
    private String situation;
    /**
     * 联系电话
     */

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSituation() {
        return this.situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

}
