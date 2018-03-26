package com.yang.test.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yang.test.jpa.domain.T;

public interface TaskDao2 extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

	List<T> findAll();
}