package com.xk.hb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class T {

	public Integer id;
	public Integer value;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		value = value;
	}
}