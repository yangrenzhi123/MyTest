package com.yang.test.java.springboot.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @author fengtao.xue
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {
	static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static final AtomicInteger OnlineCount = new AtomicInteger(0);

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static ConcurrentHashMap<String, List<Session>> sessions = new ConcurrentHashMap<>();

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(@PathParam(value = "userId") String userId, Session webSocketsession, EndpointConfig config) {
		String lock = userId.intern();
		
		List<Session> l = sessions.get(userId);
		synchronized (lock) {
			if(l == null) {
				l = new ArrayList<Session>();
				sessions.put(userId, l);
			}
		}
		
		
		int cnt = OnlineCount.incrementAndGet();
		l.add(webSocketsession);
		logger.info("有客户端连接加入，当前连接数为：{}", cnt);
		sendMessage(webSocketsession, "您已连接成功");
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		logger.info("有连接关闭");
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("来自客户端的消息：{}", message);
		sendMessage(session, "收到消息，消息内容：" + message);
	}

	/**
	 *
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		logger.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
		error.printStackTrace();
	}

	/**
	 * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
	 * 
	 * @param message
	 */
	public void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
			// session.getBasicRemote().sendText(String.format("%s",message));
		} catch (IOException e) {
			logger.error("发送消息出错：{}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void sendToUser(String userId, String message) {
		List<Session> l = sessions.get(userId);
		if (l != null && l.size() > 0) {
			for (int i = l.size() - 1; i >= 0; i--) {
				Session s = l.get(i);
				if (s.isOpen()) {
					sendMessage(s, message);
				} else {
					l.remove(s);
					try {
						s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			logger.warn("当前用户不在线：{}", userId);
		}
	}
}