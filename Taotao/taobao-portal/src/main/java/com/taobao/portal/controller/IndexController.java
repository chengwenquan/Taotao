package com.taobao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.taobao.portal.service.ContentService;

/**
 * 跳转到首页
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		String str = contentService.getAd1List();
		model.addAttribute("ad1", str);
		return "index";
	}

}
