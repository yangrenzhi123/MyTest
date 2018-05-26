package com.yang.test.jpa.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



/*
CREATE TABLE  ID_GENERATOR (  
  GEN_NAME VARCHAR(255) NOT NULL,
  GEN_VALUE int NOT NULL
)
*/
@Entity
@Table(name = "g")
public class G {

	private Long id;
	private Long valueId;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "g_gen")
	@TableGenerator(name = "g_gen", table = "ID_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE", pkColumnValue = "LOG_PK", allocationSize = 1)
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