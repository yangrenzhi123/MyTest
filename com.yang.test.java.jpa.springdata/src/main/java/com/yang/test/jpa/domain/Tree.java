package com.yang.test.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TREE")
public class Tree extends BaseEntity {

	private Long oId;

	private Long parentId;

	public Tree() {
	}

	public Tree(Long oId, Long parentId) {
		this.oId = oId;
		this.parentId = parentId;
	}

	@Column(name = "O_ID")
	public Long getoId() {
		return oId;
	}

	public void setoId(Long oId) {
		this.oId = oId;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}