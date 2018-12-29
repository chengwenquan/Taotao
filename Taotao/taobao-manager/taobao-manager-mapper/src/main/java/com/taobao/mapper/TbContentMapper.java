package com.taobao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taobao.pojo.TbContent;

public interface TbContentMapper {
  /**
   * 根据分类id查询内容
   * @param id
   * @return
   */
	List<TbContent> getListById(Long id);
	
	boolean insertContent(TbContent content);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	boolean deleteContentById(@Param("ids")List<Long> ids);
}