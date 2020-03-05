package com.onpositive.treeserver;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.onpositive.treeserver.data.Tree;
import com.onpositive.treeserver.listeners.AbstractListener;

public class Server implements TreeManager {
	private SocketIOServer connection;
	
	private HashMap<String, Tree> treeMap = new HashMap<String, Tree>();
	
	public Server() {
		Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9595);
        
        Utils.generateTree(treeMap);
		
		this.connection = new SocketIOServer(config);
	}
	
	public void addListener(AbstractListener<?, ?> listener) {
		listener.register(this, connection);
	}
	
	public void run() {
		connection.start();
        
        try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        connection.stop();
	}
	
	public Tree treeNodeById(String id) {
		return treeMap.get(id);
	}
	
	public void setNodeLabel(String parentId, String childId, int label) {
		Tree parent = treeMap.get(parentId);
		
		parent.getLabels().set(parent.getChildren().indexOf(childId), label);
	}
	
	public Integer getNodeLabel(String parentId, String childId) {
		Tree parent = treeMap.get(parentId);
		
		return parent.getLabels().get(parent.getChildren().indexOf(childId));
	}
	
	public List<String> findIdsByPrefix(String prefix, int maxSize) {
		return treeMap.keySet().stream().filter((item) -> item.toLowerCase().startsWith(prefix.toLowerCase())).sorted().limit(maxSize).collect(Collectors.toList());
	}
}
