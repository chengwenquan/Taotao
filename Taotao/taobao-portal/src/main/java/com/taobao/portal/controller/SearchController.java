package com.taobao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taobao.portal.pojo.SearchResult;
import com.taobao.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q")String keyword,
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="60")Integer rows,Model model){
		try {
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			keyword="";
		}
		 SearchResult result = searchService.search(keyword, page, rows);
		 model.addAttribute("query", keyword);
		 model.addAttribute("totalPages", result.getPageCount());
		 model.addAttribute("itemList", result.getItemList());
		 model.addAttribute("page", result.getCurPage());
		return "search";
	}
}
