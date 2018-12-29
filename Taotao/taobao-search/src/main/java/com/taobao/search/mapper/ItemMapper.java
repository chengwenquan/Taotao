package com.taobao.search.mapper;

import java.util.List;
import com.taobao.search.pojo.SearchItem;

public interface ItemMapper {

	/**
	 * 获取所有商品信息
	 * @return
	 */
	List<SearchItem> getItemList();
	
}
