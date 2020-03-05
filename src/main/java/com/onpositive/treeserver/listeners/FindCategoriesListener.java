package com.onpositive.treeserver.listeners;

import java.util.List;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class FindCategoriesListener extends AbstractListener<String, List<String>> {
	public FindCategoriesListener() {
		super(String.class);
	}

	protected void handleRequest(String sessionId, String data, Consumer<List<String>> callback) {
		callback.accept(this.getServer().findIdsByPrefix(data, 10));
	}

	protected String eventName() {
		return "find_categories";
	}
}
