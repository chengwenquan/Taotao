package com.taobao.portal.service;

import com.taobao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	
	String getItemDescById(Long itemId);
	
	String getItemParamById(Long itemId);
}
