package com.taobao.service;

import java.util.List;

import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.pojo.TbContent;

public interface ContentService {

	/**
	 * 根据内容分类id查询列表，分页展示
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getList(Long categoryId,Integer page,Integer rows);

	/**
	 * 添加一天记录
	 * @param content
	 * @return
	 */
	TaobaoResult insertContent(TbContent content);
	
	/**
	 * 根据id删除内容,可批量删除
	 * @param ids
	 * @return
	 */
	TaobaoResult deleteContent(List<Long> ids);

}
