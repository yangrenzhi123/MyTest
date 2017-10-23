package com.yang.test.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class T {

	private Integer Id;

	@Id
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
}