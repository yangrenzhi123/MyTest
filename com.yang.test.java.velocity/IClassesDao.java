package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Classes;

public interface IClassesDao extends JpaRepository<Classes, Integer>, JpaSpecificationExecutor<Classes> {
}