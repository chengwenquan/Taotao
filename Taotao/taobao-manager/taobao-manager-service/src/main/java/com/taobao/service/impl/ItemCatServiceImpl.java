package com.taobao.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.EasyUITreeNode;
import com.taobao.mapper.TbItemCatMapper;
import com.taobao.pojo.TbItemCat;
import com.taobao.service.ItemCatService;

/**
 * 商品分类管理 
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		List<TbItemCat> list = tbItemCatMapper.selectByPrimaryParentKey(parentId);
		Function<TbItemCat,EasyUITreeNode> function = (TbItemCat cat)->{
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent() ? "closed" : "open");
			return node;
		};
		List<EasyUITreeNode> resultList = list.stream().map(function).collect(Collectors.toList());
		return resultList;
	}

}
