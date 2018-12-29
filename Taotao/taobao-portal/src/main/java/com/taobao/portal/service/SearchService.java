package com.taobao.portal.service;

import com.taobao.portal.pojo.SearchResult;

public interface SearchService {

	public SearchResult search(String keyword,int page,int rows);
	
}
