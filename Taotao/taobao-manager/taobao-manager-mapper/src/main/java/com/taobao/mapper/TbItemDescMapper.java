package com.taobao.mapper;

import org.apache.ibatis.annotations.Param;

import com.taobao.pojo.TbItemDesc;

/**
 * 商品描述	
 * @author Administrator
 *
 */
public interface TbItemDescMapper {
	
	/**
	 * 添加商品描述
	 * @param tbItemDesc
	 */
	boolean insert(TbItemDesc tbItemDesc);
	
	/**
	 * 获取商品描述
	 * @param id
	 * @return
	 */
	TbItemDesc getItemDescById(@Param("id")Long id);
	
	
  
}