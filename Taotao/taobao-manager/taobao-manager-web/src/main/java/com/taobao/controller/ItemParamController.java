package com.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.service.ItemParamService;

@RequestMapping("/item/param")
@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 查询类目是否已经添加过模版如果添加过则将查到的数据放到TaobaoResult对象中
	 * 如果没有返回一个空对象
	 * @param cid
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaobaoResult selectItemParamById(@PathVariable Long cid){
		return itemParamService.selectItemParamById(cid);
	}
	
	/**
	 * 保存模版
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaobaoResult saveItemParam(@PathVariable Long cid,String paramData){
		return itemParamService.insertParam(cid, paramData);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaobaoResult deleteItemParam(Long ids){
		return itemParamService.deleteItemParam(ids);
	}
	
	
	
	
}
