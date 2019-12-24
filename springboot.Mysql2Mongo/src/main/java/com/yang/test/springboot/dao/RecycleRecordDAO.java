package com.yang.test.springboot.dao;

import java.util.List;

import com.yang.test.springboot.mongo.entity.RecycleRecordDTO;

public interface RecycleRecordDAO {

	List<RecycleRecordDTO> limitRecycleRecord(Long start, Long maxid, int limit);
}