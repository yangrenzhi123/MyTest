package com.yang.test.jpa.dao;

import java.util.List;

import com.yang.test.jpa.domain.Task;

public interface TaskDao {
	List<Task> list();
}