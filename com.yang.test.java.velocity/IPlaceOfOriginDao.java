package com.xk.campushealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xk.campushealth.entity.PlaceOfOrigin;

public interface IPlaceOfOriginDao extends JpaRepository<PlaceOfOrigin, Integer>, JpaSpecificationExecutor<PlaceOfOrigin> {
}