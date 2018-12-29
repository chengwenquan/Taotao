package com.taobao.rest.service;

import com.taobao.common.pojo.TaobaoResult;

public interface ContentService {

	/**
	 * 根据内容分类id查询列表
	 * @param cid
	 * @return
	 */
	TaobaoResult getContentList(Long cid);
	/**
	 * 同步Content数据
	 * @param cid
	 * @return
	 */
	TaobaoResult syncContent(Long cid);
	
	
}
