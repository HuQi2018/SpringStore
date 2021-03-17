package cn.sju.SpringStore.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sju.SpringStore.entiy.Address;
import cn.sju.SpringStore.entiy.Order;
import cn.sju.SpringStore.entiy.OrderItem;
import cn.sju.SpringStore.entiy.Product;
import cn.sju.SpringStore.mapper.OrderMapper;
import cn.sju.SpringStore.service.IAddressService;
import cn.sju.SpringStore.service.IOrderService;
import cn.sju.SpringStore.service.IProductService;
import cn.sju.SpringStore.service.ex.AddressNotFoundException;
import cn.sju.SpringStore.service.ex.CartNotFoundException;
import cn.sju.SpringStore.service.ex.CreateOrderException;
import cn.sju.SpringStore.service.ex.NoUnPayOrderException;
import cn.sju.SpringStore.service.ex.OrderNoIsNoFoundException;
import cn.sju.SpringStore.service.ex.OrderPayException;
import cn.sju.SpringStore.service.ex.UpdateException;

@Service
public class OrderServiceImpl  implements IOrderService{
	@Autowired
    private OrderMapper OrderMapper;

    @Autowired
    private IProductService productService;

    @Autowired
    private IAddressService addressService;
    
	@Override
	public String createOrder(Integer id, String username, Integer aid, String products) {
		
		Order order = new Order();

		Date now =new Date();
		String orderNo = generatorOrderNo(now);

		
		Address address = addressService.getAddressByAid(aid);
	    if(address==null) {
	        // 是：AddressNotFoundException
	        throw new AddressNotFoundException("创建订单记录异常！未查到对应的收货地址！");
	    }
	    
//		ArrayList<OrderItem> productList = new ArrayList<OrderItem>();
    	Long payment = (long) 0;
//    	System.out.println(products);
    	JSONObject jsonObject = new JSONObject(products);
        if(jsonObject.length()==0) {
            // 是：CartNotFoundException
            throw new CartNotFoundException("创建订单记录异常！未找到相关购物车记录");
        }
        
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        
    	Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			OrderItem orderItem = new OrderItem();
			Integer key = Integer.parseInt(keys.next().toString());//productId
			Integer value = Integer.parseInt(jsonObject.optString(key.toString()));//quantity
			orderItem.setUserId(id);
			orderItem.setProductId(key);
			orderItem.setQuantity(value);
			
			orderItem.setOrderNo(orderNo);
			Product product=productService.getById(key);
			orderItem.setProductName(product.getTitle());
			orderItem.setProductImage(product.getImage());
			orderItem.setCurrentUnitPrice(product.getPrice());
			payment = product.getPrice()*value;
			orderItem.setTotalPrice(product.getPrice()*value);
			orderItem.setCreatedTime(now);
			orderItem.setCreatedUser(username);
			orderItem.setModifiedTime(now);
			orderItem.setModifiedUser(username);
			
			orderItems.add(orderItem);
			
			OrderMapper.createOrderItem(orderItem);
//			productList.add(orderItem);
//			System.out.println(key+"："+value);
			
			// 销库存
			productService.reduceNum(key, value);
		}
		order.setName(address.getName());
		order.setPhone(address.getPhone());
		order.setAddress(address.getProvinceName()+address.getCityName()+address.getAreaName()+address.getAddress());
		order.setUserId(id);
		order.setOrderNo(orderNo);
		order.setPostage((long) 0);//默认无运费
		order.setPayment(payment);
		order.setPaymentType(0);//默认支付宝
		order.setStatus("10");//默认创建完订单就为，未支付状态
		order.setCreatedTime(now);
		order.setCreatedUser(username);
		order.setModifiedTime(now);
		order.setModifiedUser(username);
		Integer status = OrderMapper.createOrder(order);
		if (status!=1) {
			throw new CreateOrderException();
		}
		System.out.println(orderNo);
		
		

	    // 处理超时未支付      
	    // 启动子线程，休眠15分钟
	    // 在子线程醒来之后，执行关闭订单的操作
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            System.err.println("子线程准备休眠...");
	            try {
	                Thread.sleep(30*1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.err.println("子线程启动，执行关闭订单操作...");
	            // 调用关闭订单的方法
	            close(id, order.getOrderNo(), orderItems, username);
	        }
	    }).start();
		
		return orderNo;
	}

	@Override
	public Order getOrderByOrderNo(Integer id, String orderNo) {
		Order order = OrderMapper.getOrderByOrderNo(id,orderNo);
		if (order==null) {
			throw new OrderNoIsNoFoundException();
		}
		String status = order.getStatus();
		if (!status.equals("10")) {
			throw new NoUnPayOrderException();
		}
		return order;
	}
	
	private static String generatorOrderNo(Date now) {

//		String uuid = UUID.randomUUID().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateNowStr = sdf.format(now);
//		System.out.println(dateNowStr);//20200616175653
		Random random = new Random(Long.parseLong(dateNowStr));
		String ran = String.format("%04d", random.nextInt(9999));
		String orderNo = dateNowStr+ran;//订单号
		return orderNo;
		
	}

	@Override
	public Integer payOrder(Integer id, String username, String orderNo, Integer optionsRadios) {
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setUserId(id);
		order.setStatus("20");
		order.setPaymentType(optionsRadios);
		order.setModifiedUser(username);
		Date now = new Date();
		order.setPaymentTime(now);
		order.setModifiedTime(now);
		Integer rs = OrderMapper.payOrder(order);
		if (rs==null) {
			throw new OrderPayException();
		}
		return 1;
	}

	@Override
	public List<Order> getOrder(Integer id, String username) {
		List<Order> orders = OrderMapper.getOrderByUserId(id);
		System.out.println(orders);
		return orders;
	}
	
	@Override
	public List<OrderItem> getOrderItem(Integer id, String orderNo) {
		List<OrderItem> orderItems = OrderMapper.getOrderItem(orderNo,id);
		System.out.println(orderItems);
		return orderItems;
	}
	
	/**
	 * 更新订单状态
	 * @param orderNo 订单id
	 * @param status 状态 0-未支付 1-已支付 2-取消 3-关闭
	 * @param username 操作人姓名
	 * @param modifiedTime 最后修改时间
	 * @return
	 */
	private void updateStatus(
			String orderNo,Integer status,
	        String username,Date modifiedTime) throws UpdateException{
	    Integer row=OrderMapper.updateStatus(orderNo, status, username, modifiedTime);
	    if(row!=1) {
	        throw new UpdateException("更新订单状态异常！");
	    }
	}
	
	@Override
	public void changeStatus(Integer uid, String orderNo, Integer status, String username) {
	    // 使用oid查询订单数据
	    Order order=OrderMapper.getOrderByOrderNo(uid, orderNo);
	    // 判断结果是否为Null
	    if(order==null) {
	        // 是： OrderNotFoundException
	        throw new OrderNoIsNoFoundException("更新订单状态异常！订单数据不存在!");
	    }

	    // 更新订单状态
	    updateStatus(orderNo, status, username, new Date());
	}
	
	@Override
	public void close(Integer uid, String orderNo, List<OrderItem> orderItems, String username) {
	    // 使用oid查询订单数据
	    Order order=OrderMapper.getOrderByOrderNo(uid, orderNo);
	    // 判断结果是否为Null
	    if(order==null) {
	        // 是： OrderNotFoundException
	        throw new OrderNoIsNoFoundException("关闭订单异常！订单数据不存在!");
	    }

	    // 判断订单状态是否不为0
	    if(!order.getStatus().equals(0)) {
	        // 是：return;
	        return;
	    }

	    // 修改订单状态 status->3
	    changeStatus(uid, orderNo, 3, username);

	    // 归还库存
	    // 遍历orderItems
	    for(OrderItem item:orderItems) {
	        // -- 调用增加库存的方法 addNum(pid,num);
	        productService.addNum(item.getProductId(), item.getQuantity());
	    }
	};
	
}
