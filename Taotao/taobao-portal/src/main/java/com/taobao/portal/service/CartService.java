package com.taobao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.portal.pojo.CartItem;

public interface CartService {

	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 * @param itemId
	 * @param num
	 * @return
	 */
	String addCart(HttpServletRequest request,HttpServletResponse response,Long itemId,Integer num);
	
	/**
	 * 从cookie中获取添加到购物车中的商品
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCart(HttpServletRequest request);
	
	/**
	 * 修改商品的数量
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaobaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 删除商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	TaobaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
