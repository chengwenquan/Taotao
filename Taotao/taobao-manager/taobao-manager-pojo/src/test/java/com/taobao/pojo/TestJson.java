package com.taobao.pojo;

import java.util.Date;

import com.taobao.common.utils.JsonAndObjUtils;
import com.taobao.common.utils.JsonUtils;

public class TestJson {

	public static void main(String[] args){
		
		TbUser user = new TbUser();
		user.setId(1001l);
		user.setUsername("zhangsan");
		user.setPassword("1234567");
		user.setPhone("15225969681");
		user.setEmail("123@qq.com");
		user.setUpdated(new Date());
		user.setCreated(new Date());
		
		String user_json = JsonUtils.objectToJson(user);
		System.out.println(user_json);
		
		String user_json1 = JsonAndObjUtils.jsonObjToStr(user);
		System.out.println("***"+user_json1);
		
	}
}
