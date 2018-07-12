package com.yang.test.jpa.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"USER\"")
public class User {

	private Integer id;

	private String national;
	
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "\"NATIONAL\"", length = 16)
	public String getNational() {
		return this.national;
	}

	public void setNational(String national) {
		this.national = national;
	}
}