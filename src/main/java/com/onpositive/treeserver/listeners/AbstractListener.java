package com.onpositive.treeserver.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.onpositive.treeserver.Server;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public abstract class AbstractListener<RequestType, ResponseType> implements DataListener<RequestType> {
	private Class<RequestType> clazz;
	
	private Server server;
	
	public AbstractListener(Class<RequestType> clazz) {
		this.clazz = clazz;
	}
	
	public void register(Server server, SocketIOServer connection) {
		connection.addEventListener(this.eventName(), this.clazz, this);
		
		this.server = server;
	}
	
	public Server getServer() {
		return server;
	}
	
	public void onData(SocketIOClient client, RequestType data, AckRequest request) throws Exception {
		this.handleRequest(client.getSessionId().toString(), data, response -> request.sendAckData(response));
	}
	
	protected abstract void handleRequest(String sessionId, RequestType data, Consumer<ResponseType> callback);
	
	protected abstract String eventName();
}
