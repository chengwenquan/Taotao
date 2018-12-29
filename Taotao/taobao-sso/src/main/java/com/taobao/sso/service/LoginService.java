package com.taobao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.common.pojo.TaobaoResult;

public interface LoginService {
	
	TaobaoResult getUserInfo(String username,String password,HttpServletRequest request,HttpServletResponse response);

	TaobaoResult getUserByToken(String token);
	
	TaobaoResult deleteRedisDataBykey(String token);
}
