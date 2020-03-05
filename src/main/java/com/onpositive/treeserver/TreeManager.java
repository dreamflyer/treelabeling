package com.onpositive.treeserver;

import java.util.List;

import com.onpositive.treeserver.data.Tree;

public interface TreeManager {
	public Tree treeNodeById(String id);
	
	public void setNodeLabel(String parentId, String childId, int label);
	
	public Integer getNodeLabel(String parentId, String childId);
	
	public List<String> findIdsByPrefix(String prefix, int maxSize);
}
