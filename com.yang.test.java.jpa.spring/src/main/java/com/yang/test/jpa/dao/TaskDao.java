package com.yang.test.jpa.dao;

import java.io.IOException;

import com.yang.test.java.velocity.ShuJuQianYiTable;

public interface TaskDao {

	void test(String tableName) throws IOException;
	ShuJuQianYiTable test2(String tableName) throws IOException;
}