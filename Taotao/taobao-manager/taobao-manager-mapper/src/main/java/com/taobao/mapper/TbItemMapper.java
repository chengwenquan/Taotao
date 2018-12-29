package com.taobao.mapper;

import java.util.List;

import com.taobao.pojo.TbItem;

public interface TbItemMapper {
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
    TbItem selectByPrimaryKey(Long id);
    /**
     * 查询所欲商品
     * @return
     */
    List<TbItem> selectAllItem();
    
    /**
     * 添加商品
     * @param tbItem
     */
    void insert(TbItem tbItem);
    

    
}