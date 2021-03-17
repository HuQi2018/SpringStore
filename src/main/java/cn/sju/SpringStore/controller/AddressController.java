package cn.sju.SpringStore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sju.SpringStore.entiy.Address;
import cn.sju.SpringStore.service.IAddressService;
import cn.sju.SpringStore.util.ResponseResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
	 @Autowired
	 IAddressService service;
	 
	 @RequestMapping("get_address")
	 public ResponseResult<List<Address>> getAddress(HttpSession session){
		 
		 ResponseResult<List<Address>> rs = new ResponseResult<List<Address>>();
		 Integer id = (Integer) session.getAttribute("id");
		 if (id != null) {
			 List<Address> address = service.getAddress(id);
			 rs.setState(1);
		     rs.setMessage("获取成功！");
		     rs.setData(address);
		}else {
		     rs.setState(-4);
		     rs.setMessage("请先登录！");
		}
	    return rs;
	    
	 }

	 @RequestMapping("delete_address")
	 public ResponseResult<Void> delAddress(Integer id, HttpSession session){
		 
		 ResponseResult<Void> rs = new ResponseResult<Void>();
		 Integer uid = (Integer) session.getAttribute("id");
		 if (id != null) {
			 Integer address = service.delAddress(uid,id);
			 rs.setState(1);
		     rs.setMessage("删除成功！");
		}else {
		     rs.setState(-4);
		     rs.setMessage("请先登录！");
		}
	    return rs;
	    
	 }

	 @RequestMapping("default_address")
	 public ResponseResult<Void> defAddress(Integer id, HttpSession session){
		 
		 ResponseResult<Void> rs = new ResponseResult<Void>();
		 Integer uid = (Integer) session.getAttribute("id");
		 if (id != null) {
			 Integer address = service.defAddress(uid,id);
			 rs.setState(1);
		     rs.setMessage("设置成功！");
		}else {
		     rs.setState(-4);
		     rs.setMessage("请先登录！");
		}
	    return rs;
	    
	 }
	 
	 @PostMapping("create_address")
	 public ResponseResult<Void> createAddress(Address address,HttpSession session){
		 
		 ResponseResult<Void> rs = new ResponseResult<Void>();
		 Integer id = (Integer) session.getAttribute("id");
		 String username = (String) session.getAttribute("username");
		 if (id != null) {
			 service.createAddress(id, username, address);
			 rs.setState(1);
		     rs.setMessage("地址添加成功！");
		}else {
		     rs.setState(-4);
		     rs.setMessage("请先登录！");
		}
	    return rs;
	    
	 }
}
