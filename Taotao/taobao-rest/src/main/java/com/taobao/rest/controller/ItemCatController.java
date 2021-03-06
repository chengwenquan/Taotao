package com.taobao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.rest.pojo.ItemCatResult;
import com.taobao.rest.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
//	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String getItemCatList(String callback) {
//		ItemCatResult result = itemCatService.getItemCatList();
//		
//		//需要把result转换成字符串	
//		String json = JsonAndObjUtils.jsonObjToStr(result);
//		if (StringUtils.isBlank(callback)) {
//			//说明是普通掉用
//			return json;
//		}
//		//如果字符串不为空，需要支持jsonp调用
//		//需要把result转换成字符串
//		return callback + "(" + json + ");";
//	}
	
	//第二种方法
		@RequestMapping(value="/list")
		@ResponseBody
		public Object getItemCatList(String callback) {
			ItemCatResult result = itemCatService.getItemCatList();
			if (StringUtils.isBlank(callback)) {
				//需要把result转换成字符串
				return result;
			}
			//如果字符串不为空，需要支持jsonp调用
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	
}
