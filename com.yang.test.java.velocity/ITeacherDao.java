package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Teacher;

public interface ITeacherDao extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
}