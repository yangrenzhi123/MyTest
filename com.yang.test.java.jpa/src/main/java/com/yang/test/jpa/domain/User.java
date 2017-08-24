package com.yang.test.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"USER\"")
public class User extends BaseEntity {

	private String name;

	private String qq;

	public User() {
		super();
	}

	public User(String name, String qq) {
		super();
		this.name = name;
		this.qq = qq;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "QQ")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}