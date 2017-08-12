package com.yang.test.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yang.test.jpa.domain.Task;

public interface TaskDao extends JpaRepository<Task, Long> {
	@Query("from Task")
	public List<Task> findTop10();
}