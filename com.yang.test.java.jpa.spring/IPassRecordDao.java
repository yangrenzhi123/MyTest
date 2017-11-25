package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.PassRecord;

public interface IPassRecordDao extends JpaRepository<PassRecord, Integer>, JpaSpecificationExecutor<PassRecord> {
}