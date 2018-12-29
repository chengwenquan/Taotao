package com.taobao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.ItemPara;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.mapper.TbItemParamMapper;
import com.taobao.pojo.TbItemParam;
import com.taobao.service.ItemParamService;

import net.sf.json.JSONArray;

@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	
	/**
	 * 规格参数列表查询
	 * @param page  页数 
	 * @param rows  每页的行数
	 */
	@Override
	public EUDataGridResult selectItemParam(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.getCatAndName();
		//处理返回值
		EUDataGridResult eu = new EUDataGridResult();
		//取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		eu.setTotal(pageInfo.getTotal());
		//参数数据，格式为json格式    TODO
		for(TbItemParam param:list){
			String paraData = param.getParamData();
			String group = "";
			if(paraData==null||paraData.equals("")){
				param.setParamData(group);
				break;
			}
			JSONArray json = JSONArray.fromObject(paraData);
			List<ItemPara> persons = (List<ItemPara>)JSONArray.toCollection(json, ItemPara.class);
			
			for(ItemPara itp : persons){
				group = group+itp.getGroup()+",";
			}
			//去掉最后一个逗号
			if(!group.equals("")){
				group = group.substring(0,group.length()-1);
			}
			param.setParamData(group);	
		}
		
		eu.setRows(list);
		return eu;
	}


	@Override
	public TaobaoResult selectItemParamById(Long cid) {
		TbItemParam tbItemParam = tbItemParamMapper.selectParamById(cid);
		if(tbItemParam==null){
			return TaobaoResult.ok();
		}
		return TaobaoResult.ok(tbItemParam);
	}


	@Override
	public TaobaoResult insertParam(Long cid, String paramData) {
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		Date date = new Date();
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);	
		tbItemParamMapper.insertParam(tbItemParam);

		return TaobaoResult.ok();
	}


	@Override
	public TaobaoResult deleteItemParam(Long id) {
		tbItemParamMapper.deleteItemParam(id);
		return TaobaoResult.ok();
	}

}
