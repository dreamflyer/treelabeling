package com.onpositive.treeserver.listeners;

import com.onpositive.treeserver.data.NodeLabelRequest;
import com.onpositive.treeserver.data.Tree;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class ChangeLabelListener extends AbstractListener<NodeLabelRequest, Tree> {
	public ChangeLabelListener() {
		super(NodeLabelRequest.class);
	}
	
	protected void handleRequest(String sessionId, NodeLabelRequest data, Consumer<Tree> callback) {
		int value = this.getServer().getNodeLabel(data.getParentId(), data.getChildId());
		
		value = value + 1;
		
		if(value > 1) {
			value = -1;
		} else if(value < -1) {
			value = 1;
		}
		
		this.getServer().setNodeLabel(data.getParentId(), data.getChildId(), value);
		
		callback.accept(this.getServer().treeNodeById(data.getParentId()));
	}
	
	protected String eventName() {
		return "switch_label";
	}
}
