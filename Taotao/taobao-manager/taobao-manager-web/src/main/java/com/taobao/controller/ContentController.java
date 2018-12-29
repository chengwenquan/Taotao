package com.taobao.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.pojo.TbContent;
import com.taobao.service.ContentService;

@RequestMapping("/content")
@Controller
public class ContentController {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getItemList(Long categoryId,Integer page,Integer rows){
		EUDataGridResult result = contentService.getList(categoryId, page, rows);
		return result;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public TaobaoResult insertContent(TbContent content){
		TaobaoResult result = contentService.insertContent(content);
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaobaoResult deleteContent(@RequestParam(value = "ids") Long[] ids){
		 List<Long> list= Arrays.asList(ids); 
		return contentService.deleteContent(list);
	}
	
}
