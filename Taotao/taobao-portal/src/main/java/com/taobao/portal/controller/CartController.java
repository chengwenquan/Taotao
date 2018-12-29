package com.taobao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.portal.pojo.CartItem;
import com.taobao.portal.service.CartService;

/**
 * 购物车控制器
 * <p>Title:CarController.java</p>
 * @author 承文全
 * @date 2018年12月21日
 * @version 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	@ResponseBody
	public  String addcart(@PathVariable Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response){
		return cartService.addCart(request, response, itemId, num);
	}
	
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,Model model){
		//从cookie中获取用户添加到购物车的商品
		List<CartItem> list =cartService.getCart(request);
		model.addAttribute("cartList", list);
		return "cart";
	}

	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public String updateCartItemNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletResponse response, HttpServletRequest request) {
		TaobaoResult result = cartService.updateCartItem(itemId, num, request, response);
		return "ok";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletResponse response, HttpServletRequest request) {
		TaobaoResult result = cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";// 跳转到cart/cart控制器
	}
}
