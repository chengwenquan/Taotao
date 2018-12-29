package com.taobao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.EasyUITreeNode;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.service.ContentCatService;


@Controller
@RequestMapping("/content/category")
public class ContentCatController {

	@Autowired
	private ContentCatService contentCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		return contentCatService.getContentCatList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaobaoResult insertContentCat(Long parentId,String name){
		return contentCatService.insertContentCate(parentId,name);
	}
	
}
