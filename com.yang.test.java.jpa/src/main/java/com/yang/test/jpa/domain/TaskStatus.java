package com.yang.test.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_STATUS")
public class TaskStatus extends BaseEntity {

	private String statusName;

	@Column(name = "STATUS_NAME")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
