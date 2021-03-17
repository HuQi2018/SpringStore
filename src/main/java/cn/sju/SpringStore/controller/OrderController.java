package cn.sju.SpringStore.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import cn.sju.SpringStore.entiy.District;
import cn.sju.SpringStore.entiy.Order;
import cn.sju.SpringStore.entiy.OrderItem;
import cn.sju.SpringStore.service.IOrderService;
import cn.sju.SpringStore.util.ResponseResult;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{
    @Autowired
    IOrderService service;

    @RequestMapping("/create")
    public ResponseResult<String> createOrder(Integer aid,String products,HttpSession session){
    	Integer id = (Integer) session.getAttribute("id");
    	String username = (String) session.getAttribute("username");
		String orderNo = service.createOrder(id, username, aid, products);
//		System.out.println(orderNo);
    	//创建订单：订单id、订单号、用户id、运费-0、订单状态-10、创建时间、修改时间   子订单
    	return new ResponseResult<String>(1,"订单创建成功！",orderNo.toString());
    	
    }

    @RequestMapping("/getOrderPriceByOrderNo")
    public ResponseResult<Order> getOrderPriceByOrderNo(String orderNo,HttpSession session){
    	Integer id = (Integer) session.getAttribute("id");
    	Order rs = service.getOrderByOrderNo(id, orderNo);
    	Order order = new Order();
    	order.setOrderNo(rs.getOrderNo());
    	order.setPayment(rs.getPayment());
    	return new ResponseResult<Order>(1,"订单获取成功！",order);
    }

    @RequestMapping("/payOrder")
    public ResponseResult<Void> payOrder(String orderNo,HttpSession session,Integer payType){
    	Integer id = (Integer) session.getAttribute("id");
    	String username = (String) session.getAttribute("username");
    	Integer rs = service.payOrder(id, username, orderNo, payType);
    	return new ResponseResult<Void>(1,"付款成功！");
    }

    @RequestMapping("/getOrder")
    public ResponseResult<List<Order>> getOrder(HttpSession session){
    	Integer id = (Integer) session.getAttribute("id");
    	String username = (String) session.getAttribute("username");
    	List<Order> rs = service.getOrder(id, username);
    	return new ResponseResult<List<Order>>(1,"订单获取成功！",rs);
    }

    @RequestMapping("/getOrderItem")
    public ResponseResult<List<OrderItem>> getOrderItem(String orderNo,HttpSession session){
    	Integer id = (Integer) session.getAttribute("id");
    	String username = (String) session.getAttribute("username");
    	List<OrderItem> rs = service.getOrderItem(id, orderNo);
    	return new ResponseResult<List<OrderItem>>(1,"订单获取成功！",rs);
    }
}
