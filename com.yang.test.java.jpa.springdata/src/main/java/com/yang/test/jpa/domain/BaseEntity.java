package com.yang.test.jpa.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
public class BaseEntity {

	private Long id;

	@GenericGenerator(name = "generator", strategy = "hilo", parameters = {
			@Parameter(name = "max_lo", value = "100"),
			@Parameter(name = "table", value = "HILO") })
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}