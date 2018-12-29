package com.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.pojo.TbItem;
import com.taobao.service.ItemParamService;
import com.taobao.service.ItemService;



@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows){
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaobaoResult createItem(TbItem item, String desc, String itemParams) {
		TaobaoResult result = itemService.createItem(item, desc ,itemParams);
		return result;
	}
	
	@RequestMapping("/param/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page,Integer rows){
		EUDataGridResult result = itemParamService.selectItemParam(page, rows);
		return result;
	}
	
}
