package com.yang.test.jpa.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task extends BaseEntity {

	private Date deadline;

	private Date createdOn;

	private String taskTitle;

	private String description;

	private Integer handleBy;

	private TaskStatus taskStatus;

	public Task() {
		super();
	}

	public Task(String taskTitle, String description, Integer handleBy) {
		super();
		this.taskTitle = taskTitle;
		this.description = description;
		this.handleBy = handleBy;
	}

	public Task(Date deadline, Date createdOn, String taskTitle,
			String description, Integer handleBy) {
		super();
		this.deadline = deadline;
		this.createdOn = createdOn;
		this.taskTitle = taskTitle;
		this.description = description;
		this.handleBy = handleBy;
	}

	@Column(name = "TASK_TITLE")
	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	@Column(name = "HANDLE_BY")
	public Integer getHandleBy() {
		return handleBy;
	}

	public void setHandleBy(Integer handleBy) {
		this.handleBy = handleBy;
	}

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "STATUS", insertable = true, updatable = true)
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "DEADLINE")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}