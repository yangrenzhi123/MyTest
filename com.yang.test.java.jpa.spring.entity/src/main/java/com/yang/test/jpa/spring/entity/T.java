package com.yang.test.jpa.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class T extends Super {

	private Long id;
	private Integer value;

	public T() {
		super();
	}

	public T(Long id, Integer value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "g_gen")
	@TableGenerator(name = "g_gen", table = "ID_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE", pkColumnValue = "COMMON_PK", allocationSize = 1)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}