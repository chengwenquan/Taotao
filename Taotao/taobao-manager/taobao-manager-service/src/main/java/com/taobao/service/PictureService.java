package com.taobao.service;

import org.springframework.web.multipart.MultipartFile;
import com.taobao.common.pojo.PictureResult;

public interface PictureService {
	
	PictureResult uploadPic(MultipartFile picFile);

}
