package cn.sju.SpringStore.service;

import java.util.List;
import java.util.Map;

import cn.sju.SpringStore.entiy.Order;
import cn.sju.SpringStore.entiy.OrderItem;

public interface IOrderService {
	String createOrder(Integer id, String username, Integer aid, String product);
	
	Order getOrderByOrderNo(Integer id, String orderNo);

	Integer payOrder(Integer id, String username, String orderNo, Integer optionsRadios);

	List<Order> getOrder(Integer id, String username);

	List<OrderItem> getOrderItem(Integer id, String orderNo);

	void changeStatus(Integer uid, String orderNo, Integer status, String username);

	void close(Integer uid, String orderNo, List<OrderItem> orderItems, String username);
}
