package com.taobao.mapper;

import org.apache.ibatis.annotations.Param;

import com.taobao.pojo.TbItemParamItem;

public interface TbItemParamItemMapper {
   
	/**
	 * 插入商品规格参数
	 * @param tbItemParamItem
	 * @return
	 */
	boolean insertParamItem(TbItemParamItem tbItemParamItem);
	/**
	 * 获取商品的规格参数
	 * @param id
	 * @return
	 */
	TbItemParamItem getParamItemById(@Param("id")Long id);
	
}