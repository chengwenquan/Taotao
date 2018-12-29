package com.taobao.rest.service;

import com.taobao.pojo.TbItem;
import com.taobao.pojo.TbItemDesc;
import com.taobao.pojo.TbItemParamItem;

/**
 * 对商品进行处理
 * @author Administrator
 *
 */
public interface ItemService {

	TbItem getItemById(Long id);
	
	TbItemDesc getItemDescById(Long id);
	
	TbItemParamItem getParamItemById(Long id);
}
