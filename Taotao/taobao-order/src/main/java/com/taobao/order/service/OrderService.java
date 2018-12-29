package com.taobao.order.service;

import com.taobao.common.pojo.TaobaoResult;
import com.taobao.order.pojo.OrderInfo;

/**
 * 处理订单
 * <p>Title:OrderService.java</p>
 * @author 承文全
 * @date 2018年12月24日
 * @version 1.0
 */
public interface OrderService {
	
	/**
	 * 创建订单
	 * @param orderInfo
	 * @return
	 */
	TaobaoResult createOrder(OrderInfo orderInfo);

}
