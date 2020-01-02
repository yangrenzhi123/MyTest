package com.yang.test.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.dao.OrderMapper;
import com.yang.test.java.entity.Order;

@RestController
public class ShardingController {

	@Autowired
	private OrderMapper orderMapper;

	@GetMapping("/i")
	@ResponseBody
	public void index() {
		orderMapper.insert();
	}

	@GetMapping("/")
	@ResponseBody
	public void findAll() {
		List<Order> l = orderMapper.findAll();
		System.out.println(l);
	}
}