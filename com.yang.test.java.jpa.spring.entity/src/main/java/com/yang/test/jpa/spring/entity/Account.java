package com.yang.test.jpa.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "S_ACCESS_ACCOUNT")
public class Account {

	private String accessAccountId;

	@Id
	public String getAccessAccountId() {
		return accessAccountId;
	}
	public void setAccessAccountId(String accessAccountId) {
		this.accessAccountId = accessAccountId;
	}
}