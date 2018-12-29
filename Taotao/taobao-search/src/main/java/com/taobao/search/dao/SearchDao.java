package com.taobao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import com.taobao.search.pojo.SearchResult;

public interface SearchDao {

	/**
	 * 从solr里面查询数据
	 * @param solrQuery 查询条件
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(SolrQuery solrQuery) throws Exception;
}
