package com.taobao.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taobao.common.pojo.PictureResult;
import com.taobao.common.utils.FastDFSClient;
import com.taobao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	
	private String IMAGE_SERVER_BASE_URL = "http://192.168.124.102/";
	
	@Override
	public PictureResult uploadPic(MultipartFile picFile){
		PictureResult result = new PictureResult();
		
		if(picFile.isEmpty()){
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		try {
			//获取完整路径
			String originalFilename = picFile.getOriginalFilename();
			//根据路径获取扩展名
			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			FastDFSClient client = new FastDFSClient("classpath:resource/client.conf");
			String url = client.uploadFile(picFile.getBytes(), suffix);
			result.setError(0);
			result.setUrl(IMAGE_SERVER_BASE_URL+url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}

}
