package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tadmin")
public class Tadmin {
	protected static final long serialVersionUID = -1L;

	public Tadmin() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	/**
	 * 用户名
	 */
	@Column(name = "UNAME")
	private String uname;
	/**
	 * 密码
	 */
	@Column(name = "UPWD")
	private String upwd;
	/**
	 * 姓名
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 联系电话
	 */
	@Column(name = "TEL")
	private String tel;

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

	public String getUpwd() {
		return this.upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
