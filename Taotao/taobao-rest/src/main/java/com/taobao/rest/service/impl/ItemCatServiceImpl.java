package com.taobao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.mapper.TbItemCatMapper;
import com.taobao.pojo.TbItemCat;
import com.taobao.rest.pojo.CatNode;
import com.taobao.rest.pojo.ItemCatResult;
import com.taobao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public ItemCatResult getItemCatList() {
		//调用递归方法查询商品分类列表
		List catList = getItemCatList(0l);
		//返回结果
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		return result;
	}

	private List getItemCatList(Long parentId) {
		//根据parentId查询列表
		List<TbItemCat> list = tbItemCatMapper.selectByPrimaryParentKey(parentId);
		List resultList = new ArrayList<>();
		int n = 0;
		for (TbItemCat tbItemCat : list) {
			n++;
			if(n>=15) 
				break;
			//如果是父节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/products/"+tbItemCat.getId()+".html");
				//如果当前节点为第一级节点
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				//把node添加到列表
				resultList.add(node);
			} else {
				//如果是叶子节点
				String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		return resultList;
	}
}
