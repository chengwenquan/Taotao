package com.taobao.service;

import java.util.List;
import com.taobao.common.pojo.EasyUITreeNode;
import com.taobao.common.pojo.TaobaoResult;

public interface ContentCatService {
	
	List<EasyUITreeNode> getContentCatList(Long parentId);
	
	TaobaoResult insertContentCate(Long parentId,String name);

}
