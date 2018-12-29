package com.taobao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.IDUtils;
import com.taobao.mapper.TbItemDescMapper;
import com.taobao.mapper.TbItemMapper;
import com.taobao.mapper.TbItemParamItemMapper;
import com.taobao.pojo.TbItem;
import com.taobao.pojo.TbItemDesc;
import com.taobao.pojo.TbItemParamItem;
import com.taobao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	/**
	 * 商品列表查询
	 * @param page  页数 
	 * @param rows  每页的行数
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectAllItem();
		//处理返回值
		EUDataGridResult eu = new EUDataGridResult();
		eu.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		eu.setTotal(pageInfo.getTotal());
		return eu;
	}

	/**
	 * 添加商品
	 */
	@Override
	public TaobaoResult createItem(TbItem tbitem, String desc ,String itemParams) {
		//1.补全商品信息
		// 1.1 生成商品id
		long itemId = IDUtils.genItemId();
		// 1.2 补全TbItem属性
		tbitem.setId(itemId);
		// 1.3 '商品状态，1-正常，2-下架，3-删除'
		tbitem.setStatus((byte) 1);
		// 1.4 创建时间和更新时间
		Date date = new Date();
		tbitem.setCreated(date);
		tbitem.setUpdated(date);
		//2.添加到商品表
		tbItemMapper.insert(tbitem);
		//3.添加到商品描述表
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		tbItemDescMapper.insert(itemDesc);
		//4.添加商品规格
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setUpdated(date);
		tbItemParamItemMapper.insertParamItem(tbItemParamItem);		
		return TaobaoResult.ok();
	}

}
