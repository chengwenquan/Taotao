package com.taobao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.media.jfxmedia.Media;
import com.taobao.pojo.TbItem;
import com.taobao.portal.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据商品的id获取商品的基本信息
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItemBasePage(@PathVariable Long itemId,Model model){
		TbItem item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	/**
	 * 根据商品的id获取商品的描述内容
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		String result = itemService.getItemDescById(itemId);
		return result;
	}
	
	/**
	 * 获取商品的规格参数html片段
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String result = itemService.getItemParamById(itemId);
		return result;
	}
	

}
