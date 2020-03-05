package com.onpositive.treeserver;

import com.onpositive.treeserver.listeners.ChangeLabelListener;
import com.onpositive.treeserver.listeners.FindCategoriesListener;
import com.onpositive.treeserver.listeners.GetNodeByIdListener;

public class Main {
	public static void main(String[] args) {
		Server server = new Server();
        
		server.addListener(new FindCategoriesListener());
		server.addListener(new GetNodeByIdListener());
		server.addListener(new ChangeLabelListener());
		
        server.run();
	}
}
