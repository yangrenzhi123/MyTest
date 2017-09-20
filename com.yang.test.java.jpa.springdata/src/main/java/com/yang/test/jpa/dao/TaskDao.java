package com.yang.test.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yang.test.jpa.domain.Tab;

public interface TaskDao extends JpaRepository<Tab, Long>, JpaSpecificationExecutor<Tab> {

	Tab findById(Integer id);
}