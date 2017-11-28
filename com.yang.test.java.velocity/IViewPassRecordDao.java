package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.ViewPassRecord;

public interface IViewPassRecordDao extends JpaRepository<ViewPassRecord, Integer>, JpaSpecificationExecutor<ViewPassRecord> {
}