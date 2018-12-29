package com.taobao.mapper;

import java.util.List;
import com.taobao.pojo.TbContentCategory;

/**
 * 内容分类
 * @author Administrator
 *
 */
public interface TbContentCategoryMapper {
   /**
    * 根据父Id查询内容分类
    * @param parentId
    * @return
    */
	List<TbContentCategory> selectCCByParentId(Long parentId);
	
	/**
	 * 插入数据并返回id
	 * @param TbContentCategory
	 */
	void insertContentCate(TbContentCategory TbContentCategory);
	/**
	 * 修改内容分类
	 * @param id
	 * @return
	 */
	boolean updateContentCateById(Long id);
}