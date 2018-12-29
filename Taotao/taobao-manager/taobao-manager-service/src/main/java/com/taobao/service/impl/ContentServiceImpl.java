package com.taobao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.mapper.TbContentMapper;
import com.taobao.pojo.TbContent;
import com.taobao.pojo.TbItem;
import com.taobao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public EUDataGridResult getList(Long categoryId, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentMapper.getListById(categoryId);
		EUDataGridResult eu = new EUDataGridResult();
		eu.setRows(list);
		//取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		eu.setTotal(pageInfo.getTotal());
		return eu;
	}

	@Override
	public TaobaoResult insertContent(TbContent content) {
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		tbContentMapper.insertContent(content);
		return TaobaoResult.ok();
	}

	@Override
	public TaobaoResult deleteContent(List<Long> ids) {
		tbContentMapper.deleteContentById(ids);
		return TaobaoResult.ok();
	}
	
}
