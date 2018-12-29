package com.taobao.rest.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.mapper.TbItemDescMapper;
import com.taobao.mapper.TbItemMapper;
import com.taobao.mapper.TbItemParamItemMapper;
import com.taobao.pojo.TbItem;
import com.taobao.pojo.TbItemDesc;
import com.taobao.pojo.TbItemParamItem;
import com.taobao.rest.component.JedisClient;
import com.taobao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${ITEM_BASE_INFO_KEY}")
	private String ITEM_BASE_INFO_KEY;
	@Value("${ITEM_DESC_KEY}")
	private String ITEM_DESC_KEY;
	@Value("${ITEM_PARAM_KEY}")
	private String ITEM_PARAM_KEY;
	
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	
	@Override
	public TbItem getItemById(Long id) {
		//查询缓存中的是否存在，如果存在直接返回
		try{
			String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+ITEM_BASE_INFO_KEY+":"+id);
			if(!StringUtils.isEmpty(jsonStr)){
				//不为空时将json转为对象返回
				TbItem result =	JsonAndObjUtils.jsonStrToObj(new TbItem(), jsonStr);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//如果不存在则查询数据库
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		try{
			//向缓存中添加数据(注意：添加缓存不能影响正常的业务逻辑)
			jedisClient.set(REDIS_ITEM_KEY+":"+ITEM_BASE_INFO_KEY+":"+id, JsonAndObjUtils.jsonObjToStr(item));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY+":"+ITEM_BASE_INFO_KEY+":"+id, ITEM_EXPIRE_SECOND);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return item;
	}
	
	@Override
	public TbItemDesc getItemDescById(Long id){
		//查询缓存中的是否存在，如果存在直接返回
		try{
			String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+ITEM_DESC_KEY+":"+id);
			if(!StringUtils.isEmpty(jsonStr)){
				//不为空时将json转为对象返回
				TbItemDesc result =	JsonAndObjUtils.jsonStrToObj(new TbItemDesc(), jsonStr);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//如果不存在则查询数据库
		TbItemDesc desc = tbItemDescMapper.getItemDescById(id);
		try{
			//向缓存中添加数据(注意：添加缓存不能影响正常的业务逻辑)
			jedisClient.set(REDIS_ITEM_KEY+":"+ITEM_DESC_KEY+":"+id, JsonAndObjUtils.jsonObjToStr(desc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY+":"+ITEM_DESC_KEY+":"+id, ITEM_EXPIRE_SECOND);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return desc;
	}

	@Override
	public TbItemParamItem getParamItemById(Long id) {
		//查询缓存中的是否存在，如果存在直接返回
		try{
			String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+ITEM_PARAM_KEY+":"+id);
			if(!StringUtils.isEmpty(jsonStr)){
				//不为空时将json转为对象返回
				TbItemParamItem result = JsonAndObjUtils.jsonStrToObj(new TbItemParamItem(), jsonStr);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//如果不存在则查询数据库
		TbItemParamItem tbItemParamItem = tbItemParamItemMapper.getParamItemById(id);
		try{
			//向缓存中添加数据(注意：添加缓存不能影响正常的业务逻辑)
			jedisClient.set(REDIS_ITEM_KEY+":"+ITEM_PARAM_KEY+":"+id, JsonAndObjUtils.jsonObjToStr(tbItemParamItem));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY+":"+ITEM_PARAM_KEY+":"+id, ITEM_EXPIRE_SECOND);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tbItemParamItem;
	}
	

}
