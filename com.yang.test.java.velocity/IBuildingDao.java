package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Building;

public interface IBuildingDao extends JpaRepository<Building, Integer>, JpaSpecificationExecutor<Building> {
}