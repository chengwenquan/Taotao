package com.taobao.sso.controller;

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
import com.taobao.sso.service.RegisterService;

@Controller
@RequestMapping("/user")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	/**
	 * 校验用户信息
	 * @param param 校验内容
	 * @param type 校验类型可选参数1、2、3分别代表username、phone、email
	 * @param callback jsonp方法名
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback){
		TaobaoResult taobaoResult = registerService.checkData(param, type);
		if(StringUtils.isBlank(callback)){
			return taobaoResult;
		}
		//如果字符串不为空，需要支持jsonp调用
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taobaoResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public TaobaoResult register(TbUser user) {
		try {
			TaobaoResult result = registerService.register(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaobaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}

}
