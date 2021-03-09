package com.yang.test.java.springboot.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.java.springboot.endpoint.WebSocketServer;

@RestController
public class TestController {
	static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	WebSocketServer webSocketServer;

	/**
	 * 群发消息内容
	 *
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/ws/sendAll", method = RequestMethod.GET)
	public String sendAllMessage(@RequestParam(required = true) String message) throws IOException {
		webSocketServer.broadCastInfo(message);
		return "success";
	}

	/**
	 * 指定会话ID发消息
	 *
	 * @param message 消息内容
	 * @param userId  连接会话ID
	 * @return
	 */
	@RequestMapping(value = "/ws/sendOne", method = RequestMethod.GET)
	public String sendOneMessage(@RequestParam(required = true) String message, @RequestParam(required = true) String userId) throws IOException {
		webSocketServer.sendToUser(userId, message);
		return "success";
	}
}