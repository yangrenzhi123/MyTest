package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Student;

public interface IStudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
}