package com.yang.test.jpa.springdata2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yang.test.jpa.domain.T;

public interface TDao extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

	void deleteById(Integer id);
}