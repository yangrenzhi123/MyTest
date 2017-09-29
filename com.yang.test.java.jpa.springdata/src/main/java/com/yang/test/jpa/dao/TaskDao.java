package com.yang.test.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yang.test.jpa.domain.T;

public interface TaskDao extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

	Long countById(Integer id);
}