package com.yang.test.java.dao;

import java.util.List;

import com.yang.test.java.entity.Order;

public interface OrderMapper {

	void insert();
	List<Order> findAll();
}