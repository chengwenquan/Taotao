package com.taobao.portal.pojo;

import java.util.List;

import com.taobao.pojo.TbOrder;
import com.taobao.pojo.TbOrderItem;
import com.taobao.pojo.TbOrderShipping;
/**
 * 订单信息实体类
 * <p>Title:OrderInfo.java</p>
 * @author 承文全
 * @date 2018年12月24日
 * @version 1.0
 */
public class OrderInfo extends TbOrder{

	private List<TbOrderItem> orderItems;//订单中商品信息
	private TbOrderShipping orderShipping;//物流信息
	
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
