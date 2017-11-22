package com.yang.test.jpa.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLocking;

@Entity
@OptimisticLocking
public class T implements Serializable{

	private static final long serialVersionUID = 4420487777877600773L;
	private Integer Id;
	private Integer value;
	
	@Version
	private Integer v;

	@Id
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getV() {
		return v;
	}
	public void setV(Integer v) {
		this.v = v;
	}
}