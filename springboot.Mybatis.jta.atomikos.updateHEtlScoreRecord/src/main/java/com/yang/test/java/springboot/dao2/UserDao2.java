package com.yang.test.java.springboot.dao2;

import java.util.List;

import com.yang.test.java.springboot.entity.HEtlScoreRecord;
import com.yang.test.java.springboot.entity.HScoreRecord;

public interface UserDao2 {

	List<HScoreRecord> sflszhInScorerecordid(List<HEtlScoreRecord> l);
}