package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.BasicInfo;

public interface IBasicInfoDao extends JpaRepository<BasicInfo, Integer>, JpaSpecificationExecutor<BasicInfo> {
}