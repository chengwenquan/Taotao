package com.taobao.service;

import com.taobao.common.pojo.EUDataGridResult;
import com.taobao.common.pojo.TaobaoResult;

/**
 * 规格参数
 * @author Administrator
 *
 */
public interface ItemParamService {
	
	/**
	 * 获取规格参数列表
	 * @return
	 */
	EUDataGridResult selectItemParam(int page, int rows);
	
	/**
	 * 根据cid查询类目模版是否存在
	 * @param cid
	 * @return
	 */
	TaobaoResult selectItemParamById(Long cid);
	
	/**
	 * 保存商品模版
	 * @param cid
	 * @param paramData
	 * @return
	 */
	TaobaoResult insertParam(Long cid,String paramData);
	
	/**
	 * 删除商品模版
	 * @param id
	 * @return
	 */
	TaobaoResult deleteItemParam(Long id);

}
