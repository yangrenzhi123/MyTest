package com.yang.test.java.springboot.dao2;

import java.util.List;

import entity.HEtlScoreRecord;
import entity.HScoreRecord;

public interface UserDao2 {

	List<HScoreRecord> sflszhInScorerecordid(List<HEtlScoreRecord> l);
}