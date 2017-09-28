package com.yang.test.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class T {

	private Integer id;

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}