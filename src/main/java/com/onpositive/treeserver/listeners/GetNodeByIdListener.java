package com.onpositive.treeserver.listeners;

import com.onpositive.treeserver.data.Tree;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class GetNodeByIdListener extends AbstractListener<String, Tree> {
	public GetNodeByIdListener() {
		super(String.class);
	}
	
	protected void handleRequest(String sessionId, String data, Consumer<Tree> callback) {
		callback.accept(this.getServer().treeNodeById(data));
	}

	protected String eventName() {
		return "get_node";
	}
}
