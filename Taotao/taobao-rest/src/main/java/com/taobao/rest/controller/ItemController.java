package com.taobao.rest.controller;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.pojo.TbItem;
import com.taobao.pojo.TbItemDesc;
import com.taobao.pojo.TbItemParamItem;
import com.taobao.rest.service.ItemService;

/**
 * 商品信息控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 获取商品信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/base/{itemId}")
	@ResponseBody
	public TaobaoResult getItemById(@PathVariable Long itemId){
		
		try{
			TbItem item = itemService.getItemById(itemId);
			return TaobaoResult.ok(item);
		}catch(Exception e){
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 获取商品描述信息
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaobaoResult getItemDescById(@PathVariable Long itemId){
		
		try{
			TbItemDesc desc = itemService.getItemDescById(itemId);
			return TaobaoResult.ok(desc);
		}catch(Exception e){
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 获取参数信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaobaoResult getParamItemById(@PathVariable Long itemId){
		
		try{
			TbItemParamItem desc = itemService.getParamItemById(itemId);
			return TaobaoResult.ok(desc);
		}catch(Exception e){
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
}
