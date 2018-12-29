package com.taobao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taobao.pojo.TbUser;

public interface TbUserMapper {
   
	/**
	 * 根据查询类型和内容查询数据
	 * @param param
	 * @param type
	 * @return
	 */
	List<TbUser> checkData(@Param("param")String param, @Param("type")int type);
	
	/**
	 * 保存用户信息
	 * @param tbUser
	 * @return
	 */
	boolean insertTbUser(TbUser tbUser);
	
	/**
	 * 根据用户名和密码查询数据库
	 * @param param
	 * @param password
	 * @return
	 */
	TbUser getUserInfo(@Param("username")String username,@Param("password")String password);
}