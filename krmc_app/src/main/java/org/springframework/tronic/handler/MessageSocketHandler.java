package org.springframework.tronic.handler;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@EnableWebSocket 
public class MessageSocketHandler extends TextWebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(MessageSocketHandler.class);
	
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();	// 접속한 사용자들(다중처리 사용)
	
	public MessageSocketHandler() {
		super();
		logger.info("create SocketHandler instance!");
	}
	
	/**
	 * 클라이언트가 서버와 연결 종료
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		sessionSet.remove(session);					// 사용자 삭제
		session.close();							// 세션 종료
		logger.info("remove session!");
	}
	
	/**
	 * 클라이언트 접속
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		sessionSet.add(session);					// 사용자 추가
		logger.info("add session!");
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
		
		logger.error("web socket error!", exception);
	}
	
	@Override
	public boolean supportsPartialMessages() {
		logger.info("call method!");
		return super.supportsPartialMessages();
	}
	
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		/* 분기 처리 가능한 데이터 */
		//logger.info("receive Uri:" + session.getUri()); 
		
		logger.info("receive message:" + message.getPayload());
		
		session.sendMessage(new TextMessage(session.getId() + " ===> " + message.getPayload()));
		
		
	}
	
	
	
}
