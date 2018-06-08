package com.yang.test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/push")
public class EchoEndpoint {

	private static List<Session> l = new ArrayList<Session>();
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("is opened");
		EchoEndpoint.l.add(session);
	}

	@OnMessage
	public void onMessage(String message) throws IOException {
		for(Session item : EchoEndpoint.l) {
			item.getBasicRemote().sendText("123456");
		}
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("is error");
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("is closed");
	}
}