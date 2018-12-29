package com.taobao.service;

import java.util.List;

import com.taobao.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	/**
	 * 查询目录
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getItemCatList(long parentId);
}
