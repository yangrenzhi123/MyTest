package com.yang.test.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.OptimisticLocking;

@Entity
@OptimisticLocking
public class T {

	private Integer id;
	private Integer value;

	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}