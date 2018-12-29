package com.taobao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.pojo.TbUser;

public interface UserService {
	
	TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
