package com.taobao.rest.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.mapper.TbContentMapper;
import com.taobao.pojo.TbContent;
import com.taobao.rest.component.JedisClient;
import com.taobao.rest.service.ContentService;


@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public TaobaoResult getContentList(Long cid) {
		List<TbContent> list;
		//首先从redis中查询有没有缓存的数据，如果有直接返回没有从数据哭中查询然后放到redis中
		try {
			//将从数据库中获取的数据放入redis中
			String json_redis = jedisClient.hget(REDIS_CONTENT_KEY,cid+"");
			if(!StringUtils.isBlank(json_redis)){
				list = JsonAndObjUtils.jsonStrToArray(new TbContent(), json_redis);
				return TaobaoResult.ok(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			list = tbContentMapper.getListById(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		
		try {
			//将从数据库中获取的数据放入redis中
			jedisClient.hset(REDIS_CONTENT_KEY,cid+"", JsonAndObjUtils.jsonArrayToStr(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return TaobaoResult.ok(list);
	}

	@Override
	public TaobaoResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return TaobaoResult.ok();
	}

}
