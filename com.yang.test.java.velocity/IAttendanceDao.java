package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.Attendance;

public interface IAttendanceDao extends JpaRepository<Attendance, Integer>, JpaSpecificationExecutor<Attendance> {
}