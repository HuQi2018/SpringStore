package cn.sju.SpringStore.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sju.SpringStore.entiy.Order;
import cn.sju.SpringStore.entiy.OrderItem;

public interface OrderMapper {
	Integer createOrder(Order order);
	
	Integer createOrderItem(OrderItem orderItem);
	
	List<Integer> getNotPayOrder(Integer uid);
	
	Order getOrderByOrderNo(Integer uid, String orderNo);
	
	Integer payOrder(Order order);
	
	List<Order> getOrderByUserId(Integer uid);

	List<OrderItem> getOrderItem(String orderNo,Integer uid);
	
	//处理超时未支付
	Integer updateStatus(
		    @Param("orderNo") String orderNo,
		    @Param("status") Integer status,
		    @Param("modifiedUser") String modifiedUser,
		    @Param("modifiedTime") Date modifiedTime);

//	Order findById(Integer id);
}
