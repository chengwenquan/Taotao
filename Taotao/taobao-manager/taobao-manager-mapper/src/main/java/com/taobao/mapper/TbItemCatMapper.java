package com.taobao.mapper;

import java.util.List;

import com.taobao.pojo.TbItemCat;

/**
 * 商品类目
 * @author Administrator
 *
 */
public interface TbItemCatMapper {
   
	/**
	 * 根据父级目录查询子级目录
	 * @param parent_id
	 * @return
	 */
	List<TbItemCat> selectByPrimaryParentKey(Long parent_id);
	
	/**
	 * 查询所有的目录
	 * @return
	 */
	List<TbItemCat> selectAllItemCat();
	
}