package com.taobao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.portal.pojo.SearchResult;
import com.taobao.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	@Override
	public SearchResult search(String keyword, int page, int rows) {
		Map<String,String> param = new HashMap<>();
		param.put("keyword",keyword);
		param.put("page",page+"");
		param.put("rows",rows+"");
		String jsonStr = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
		TaobaoResult taobaoResult = TaobaoResult.formatToPojo(jsonStr, SearchResult.class);
		SearchResult searchResult = (SearchResult) taobaoResult.getData();
		return searchResult;
	}

}
