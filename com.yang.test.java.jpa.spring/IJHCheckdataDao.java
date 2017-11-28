package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.JHCheckdata;

public interface IJHCheckdataDao extends JpaRepository<JHCheckdata, Integer>, JpaSpecificationExecutor<JHCheckdata> {
}