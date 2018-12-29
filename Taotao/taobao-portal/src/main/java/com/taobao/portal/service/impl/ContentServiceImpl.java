package com.taobao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.pojo.TbContent;
import com.taobao.portal.pojo.AdNode;
import com.taobao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;

	@Override
	public String getAd1List() {
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD1_CID);
		//把json转换成java对象
		TaobaoResult taotaoResult = TaobaoResult.formatToList(json, TbContent.class);
		//取data属性，内容列表
		List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
		//把内容列表转换成AdNode列表
		List<AdNode> resultList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());

			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());

			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());

			resultList.add(node);
		}
		//需要把resultList转换成json数据
		String resultJson = JsonAndObjUtils.jsonArrayToStr(resultList);
		return resultJson;
	}

}
