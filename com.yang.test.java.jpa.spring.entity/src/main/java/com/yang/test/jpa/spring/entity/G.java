package com.yang.test.jpa.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class G {

	private Long id;
	private Long valueId;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "payablemoney_gen")
	@TableGenerator(name = "payablemoney_gen", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE", pkColumnValue = "PAYABLEMOENY_PK", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}
}