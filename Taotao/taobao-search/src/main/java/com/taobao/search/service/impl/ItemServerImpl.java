package com.taobao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.mapper.TbItemMapper;
import com.taobao.pojo.TbItem;
import com.taobao.search.mapper.ItemMapper;
import com.taobao.search.pojo.SearchItem;
import com.taobao.search.service.ItemServer;

@Service
public class ItemServerImpl implements ItemServer {

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TaobaoResult importItems() throws Exception{
		//查询数据库获得商品列表
		List<SearchItem> itemList = itemMapper.getItemList();
		//遍历列表
		for (SearchItem item : itemList) {
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//添加域
			document.addField("id", item.getId());
			document.addField("item_title", item.getTitle());
			document.addField("item_sell_point", item.getSell_point());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImage());
			document.addField("item_category_name", item.getCategory_name());
			document.addField("item_desc", item.getItem_desc());
			//写入索引库
			solrServer.add(document);
		}
		//提交
		solrServer.commit();
		return TaobaoResult.ok();
	}

}
