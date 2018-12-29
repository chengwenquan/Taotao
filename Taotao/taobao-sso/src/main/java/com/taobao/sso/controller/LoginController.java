package com.taobao.sso.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.ExceptionUtils;
import com.taobao.pojo.TbUser;
import com.taobao.sso.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 登录时判断用户名密码是否正确
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public TaobaoResult login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		try {
			TaobaoResult result = loginService.getUserInfo(username, password, request, response);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		try {
			TaobaoResult result = loginService.getUserByToken(token);
			//支持jsonp调用
			if (StringUtils.isNotBlank(callback)) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	@RequestMapping("/user/logout/{token}")
	@ResponseBody
	public Object deleteRedisDataBykey(@PathVariable String token, String callback) {
		try {
			TaobaoResult result = loginService.deleteRedisDataBykey(token);
			//支持jsonp调用
			if (StringUtils.isNotBlank(callback)) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
}
