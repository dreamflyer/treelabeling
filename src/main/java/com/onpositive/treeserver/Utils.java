package com.onpositive.treeserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import com.onpositive.treeserver.data.Tree;

public class Utils {
	private Utils() {
		
	}
	
	public static void generateTree(Map<String, Tree> treeMap) {
		try {
			String path = Utils.class.getResource("").getFile();
			
			path = path.replace("target/classes/com/onpositive/treeserver/", "");
			
			FileInputStream fis = new FileInputStream(path + "/resources/wiki_cat.txt");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line = br.readLine();
			
			while(line != null) {
				if(line.contains("->")) {
					String[] sp = line.split("->");
					
					addToMap(treeMap, sp[0], sp[1]);
				}
				
				line = br.readLine();
			}
			
			System.out.println(line);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static void addToMap(Map<String, Tree> map, String key, String value) {
		if(!map.containsKey(key)) {
			Tree t = new Tree();
			
			t.setName(key);
			t.setChildren(new ArrayList<String>());
			t.setWeights(new ArrayList<Integer>());
			t.setLabels(new ArrayList<Integer>());
			
			map.put(key, t);
		}
		
		Tree node = map.get(key);
		
		node.getChildren().add(value);
		node.getWeights().add(value.hashCode());
		node.getLabels().add(0);
	}
}
