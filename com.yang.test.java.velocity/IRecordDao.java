package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Record;

public interface IRecordDao extends JpaRepository<Record, Integer>, JpaSpecificationExecutor<Record> {
}