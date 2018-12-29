package com.taobao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.search.service.ItemServer;

@Controller
public class ItemController {

	@Autowired
	private ItemServer itemServer;
	
	
	
	@RequestMapping("/import")
	@ResponseBody
	public TaobaoResult importAll(){
		try {
			TaobaoResult result = itemServer.importItems();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	
	
	
}
