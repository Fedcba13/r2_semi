package com.r2.admin.controller.support;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.sun.corba.se.spi.ior.MakeImmutable;

@ServerEndpoint(value="/chat/helloWebSocket",
				configurator=HelloWebSocketConfigurator.class)
public class HelloWebSocket {

	//현재 접속자를 관리할 Map객체
	public static Map<String, Session> clients
		= new HashMap<>();
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		//접속Map객체에 사용자 등록
		String userId = (String)config.getUserProperties().get("userId");
		clients.put(userId, session);
		
		//userId값을 Session객체의 userProperties에 저장
		session.getUserProperties()
			   .put("userId", userId);
		
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		String userId = (String)session.getUserProperties()
							   		   .get("userId");
		
		//접속자목록에서 해당 사용자 세션을 제거
		clients.remove(userId);
		
	}
}









