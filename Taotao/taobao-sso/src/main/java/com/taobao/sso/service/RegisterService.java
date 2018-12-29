package com.taobao.sso.service;


import com.taobao.common.pojo.TaobaoResult;
import com.taobao.pojo.TbUser;

public interface RegisterService {
	
	TaobaoResult checkData(String param,int type);
	
	TaobaoResult register(TbUser tbUser);
	
}
