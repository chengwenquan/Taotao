package com.taobao.search.service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.pojo.TbItem;

public interface ItemServer {

	/**
	 * 将商品信息导入索引库
	 * @return
	 */
	TaobaoResult importItems() throws Exception;
	
}
