package com.taobao.service;

import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
	
	TaobaoResult createItem(TbItem tbitem,String desc ,String itemParams);
	
}
