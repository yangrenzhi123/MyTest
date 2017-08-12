package com.yang.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yang.test.jpa.dao.TaskDao;

@RequestMapping(value = "/task")
@Controller
public class TaskController {

	@Autowired
	private TaskDao taskDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String lotteryIndex(Model model) {
		return "/task/list";
	}
}