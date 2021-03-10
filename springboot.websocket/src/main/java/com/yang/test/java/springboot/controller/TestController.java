package com.yang.test.java.springboot.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(value = "/")
	public void all() throws IOException {

		Iterator<Map.Entry<String, List<Session>>> iter = WebSocketServer.sessions.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, List<Session>> entry = (Map.Entry<String, List<Session>>) iter.next();
			String key = (String)entry.getKey();
			List<Session> l = (List<Session>) entry.getValue();
			
			System.out.println("userId：" + key);
			for(Session s : l) {
				System.out.println(s);
			}
		}
	}
}