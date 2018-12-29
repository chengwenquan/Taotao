package com.taobao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.search.pojo.SearchResult;
import com.taobao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("q")
	@ResponseBody
	public TaobaoResult search(@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		try {
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
			SearchResult result = searchService.search(keyword, page, rows);
			return TaobaoResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
}
