package com.taobao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.EasyUITreeNode;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.mapper.TbContentCategoryMapper;
import com.taobao.pojo.TbContentCategory;
import com.taobao.service.ContentCatService;

@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		List<TbContentCategory> list = tbContentCategoryMapper.selectCCByParentId(parentId);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode treenode = new EasyUITreeNode();
			treenode.setId(tbContentCategory.getId());
			treenode.setText(tbContentCategory.getName());
			treenode.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(treenode);
		}
		return resultList;
	}

	@Override
	public TaobaoResult insertContentCate(Long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		Date date = new Date();
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		tbContentCategoryMapper.insertContentCate(tbContentCategory);
		//修改父节点的目录
		tbContentCategoryMapper.updateContentCateById(parentId);
		return TaobaoResult.ok(tbContentCategory.getId());
	}

}
