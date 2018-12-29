package com.taobao.mapper;

import java.util.List;
import com.taobao.pojo.TbItemParam;

public interface TbItemParamMapper {
	
	/**
	 * 获取规格参数列表
	 * @return
	 */
	List<TbItemParam> getCatAndName();
	
	/**
	 * 根据id查询类目是否存在
	 * @return
	 */
	TbItemParam selectParamById(Long cid);
	
	/**
	 * 保存模版
	 * @param tbItemParam
	 * @return
	 */
	boolean insertParam(TbItemParam tbItemParam);
	
	boolean deleteItemParam(Long id);
   
}