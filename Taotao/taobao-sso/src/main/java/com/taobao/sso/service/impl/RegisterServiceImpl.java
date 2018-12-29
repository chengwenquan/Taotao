package com.taobao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.mapper.TbUserMapper;
import com.taobao.pojo.TbUser;
import com.taobao.sso.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private TbUserMapper tbUserMapper;
	/**
	 * 校验用户信息
	 * @param param 校验内容
	 * @param type 校验类型可选参数1、2、3分别代表username、phone、email
	 */
	@Override
	public TaobaoResult checkData(String param, int type) {
		List<TbUser> list = tbUserMapper.checkData(param, type);
		//判断查询结果是否为空
		if(list==null||list.isEmpty()){
			return TaobaoResult.ok(true);
		}
		return TaobaoResult.ok(false);
	}
	
	@Override
	public TaobaoResult register(TbUser tbUser) {
		// 校验数据
		//校验用户名、密码不能为空
		if (StringUtils.isBlank(tbUser.getUsername())
				|| StringUtils.isBlank(tbUser.getPassword())) {
			return TaobaoResult.build(400, "用户名或密码不能为空");
		}
		//校验数据是否重复
		//校验用户名
		TaobaoResult result = checkData(tbUser.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return TaobaoResult.build(400, "用户名重复");
		}
		//校验手机号
		if (tbUser.getPhone() != null) {
			result = checkData(tbUser.getPhone(), 2);
			if (!(boolean) result.getData()) {
				return TaobaoResult.build(400, "手机号重复");
			}
		}
		//校验手机号
		if (tbUser.getEmail() != null) {
			result = checkData(tbUser.getEmail(), 3);
			if (!(boolean) result.getData()) {
				return TaobaoResult.build(400, "邮箱重复");
			}
		}
		//插入数据
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//对密码进行加密 DigestUtils.md5DigestAsHex();是spring自带的加密算法
		tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
		tbUserMapper.insertTbUser(tbUser);
		return TaobaoResult.ok();
	}

	
}
