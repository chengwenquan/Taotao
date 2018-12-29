package com.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taobao.common.pojo.PictureResult;
import com.taobao.service.PictureService;

@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult uploadFile(MultipartFile uploadFile){
		PictureResult result = pictureService.uploadPic(uploadFile);	
		return result;
	}

}
