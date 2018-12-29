package com.taobao.sso.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.CookieUtils;
import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.common.utils.JsonUtils;
import com.taobao.mapper.TbUserMapper;
import com.taobao.pojo.TbUser;
import com.taobao.sso.component.JedisClient;
import com.taobao.sso.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_TOOKEN_KEY}")
	private String REDIS_TOOKEN_KEY; 
	@Value("${REDIS_TOKEN_EXPIRE}")
	private Integer REDIS_TOKEN_EXPIRE;
	
	@Override
	public TaobaoResult getUserInfo(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		//校验用户名密码是否正确
		TbUser user = tbUserMapper.getUserInfo(username, password);
		if(user==null){
			return TaobaoResult.build(400, "用户名或密码错误！");
		}
		//登录成功
		//使用uuid生成token
		String token = UUID.randomUUID().toString();
		//将密码清空
		user.setPassword(null);
		String user_json = JsonUtils.objectToJson(user);
		//把用户信息写入redis
		jedisClient.set(REDIS_TOOKEN_KEY+":"+token, user_json);
		jedisClient.expire(REDIS_TOOKEN_KEY+":"+token, REDIS_TOKEN_EXPIRE);
		CookieUtils.setCookie(request, response, "TB_TOKEN", token);
		return TaobaoResult.ok(token);
	}

	@Override
	public TaobaoResult getUserByToken(String token) {
		// 根据token取用户信息
		String json = jedisClient.get(REDIS_TOOKEN_KEY+":"+token);
		//判断是否查询到结果
		if (StringUtils.isBlank(json)) {
			return TaobaoResult.build(400, "用户session已经过期");
		}
		//把json转换成java对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		//更新session的过期时间
		jedisClient.expire(token, REDIS_TOKEN_EXPIRE);
		return TaobaoResult.ok(user);
	}

	
	@Override
	public TaobaoResult deleteRedisDataBykey(String token) {
		//删除redis中的用户登录信息
		jedisClient.del(REDIS_TOOKEN_KEY+":"+token);
		return TaobaoResult.ok();
	}

}
