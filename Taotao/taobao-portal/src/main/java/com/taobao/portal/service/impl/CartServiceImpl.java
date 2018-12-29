package com.taobao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.common.utils.CookieUtils;
import com.taobao.common.utils.JsonUtils;
import com.taobao.pojo.TbItem;
import com.taobao.portal.pojo.CartItem;
import com.taobao.portal.service.CartService;
import com.taobao.portal.service.ItemService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;
	
	@Override
	public String addCart(HttpServletRequest request, HttpServletResponse response, Long itemId, Integer num) {
		
		try {
			//1.从cookie中获取已经添加的商品
			 List<CartItem> list = getCartItemList(request);
			//2.如果cookie中存在本次要添加的上商品则商品数量加num,如果没有则新增商品
			 boolean bool=true;
			 for(CartItem cartItem:list){
				 //注意包装类型是对象不能使用==比较，应该把包装类型转成基本数据类型(转任意一个即可)然后进行==比较，
				 if(cartItem.getId().longValue()==itemId){
					 cartItem.setNum( cartItem.getNum()+num);
					 bool=false;
					 break;
				 }
			 }
			 if(bool){
				 TbItem tbitem = itemService.getItemById(itemId);
				 CartItem item = new CartItem();
				 item.setId(itemId);
				 item.setNum(num);
				 item.setPrice(tbitem.getPrice());
				 item.setTitle(tbitem.getTitle());
				 String imageJson = tbitem.getImage();
				 if(StringUtils.isNotBlank(imageJson)){
					 String imges[] = imageJson.split(",");
					 item.setImage(imges[0]);
				 }
				 list.add(item);
			 }
			//3.将所有商品再次放到cookie中
			 CookieUtils.setCookie(request, response, "TB_CART", JsonUtils.objectToJson(list), COOKIE_EXPIRE, true);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		 //4.所有都没问题就返回true否则返回false
		return "true";
	}

	/**
	 * 取购物车商品列表
	 * <p>Title: getCartItemList</p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		try {
			//从cookie中取商品列表
			String json = CookieUtils.getCookieValue(request, "TB_CART", true);
			if(StringUtils.isNotBlank(json)){
				//把json转换成java对象
				List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
				return list==null?new ArrayList<CartItem>():list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public List<CartItem> getCart(HttpServletRequest request) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}

	@Override
	public TaobaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//根据商品id查询商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				//更新数量
				cartItem.setNum(num);
				break;
			}
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "TB_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		return TaobaoResult.ok();
	}

	@Override
	public TaobaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、找到对应id的商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				// 4、删除商品。
				itemList.remove(cartItem);
				break;
			}
		}
		// 5、再重新把商品列表写入cookie。
		CookieUtils.setCookie(request, response, "TB_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		// 6、返回成功
		return TaobaoResult.ok();
	}
}
