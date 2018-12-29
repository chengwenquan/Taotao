package com.taobao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.common.utils.JsonUtils;
import com.taobao.pojo.TbItem;
import com.taobao.pojo.TbItemDesc;
import com.taobao.pojo.TbItemParamItem;
import com.taobao.portal.pojo.PortalItem;
import com.taobao.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	@Value("${REST_ITEM_DESC}")
	private String REST_ITEM_DESC;
	@Value("${REST_ITEM_PARAM}")
	private String REST_ITEM_PARAM;
	
	
	@Override
	public TbItem getItemById(Long itemId) {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
		//转换成java对象
		TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json, PortalItem.class);
		//取商品对象
		TbItem item = (TbItem) taobaoResult.getData();
		return item;
	}

	@Override
	public String getItemDescById(Long itemId) {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC + itemId);
		//转换成java对象
		TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json, TbItemDesc.class);
		TbItemDesc tbItemDesc = (TbItemDesc)taobaoResult.getData();
		String desc = tbItemDesc.getItemDesc();
		return desc;
	}

	@Override
	public String getItemParamById(Long itemId) {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM + itemId);
		//转换成java对象
		TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json, TbItemParamItem.class);
		TbItemParamItem tbItemParamItem = (TbItemParamItem)taobaoResult.getData();
		//获取json数据
		String paramJson = tbItemParamItem.getParamData();
		//将json字符串转换成map
		List<Map> mapList = JsonUtils.jsonToList(paramJson,Map.class);
		
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
			sb.append("		</tr>\n");
			// 取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
				sb.append("			<td>" + map2.get("v") + "</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");

		return sb.toString();
	}

}
