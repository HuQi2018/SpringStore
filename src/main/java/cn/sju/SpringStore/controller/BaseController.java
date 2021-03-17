package cn.sju.SpringStore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sju.SpringStore.service.ex.AddressCountLimitException;
import cn.sju.SpringStore.service.ex.AddressNotFoundException;
import cn.sju.SpringStore.service.ex.CartNotFoundException;
import cn.sju.SpringStore.service.ex.IllegalOperationException;
import cn.sju.SpringStore.service.ex.InsertException;
import cn.sju.SpringStore.service.ex.NoUnPayOrderException;
import cn.sju.SpringStore.service.ex.OldPwdNotMatchException;
import cn.sju.SpringStore.service.ex.OrderNoIsNoFoundException;
import cn.sju.SpringStore.service.ex.OrderPayException;
import cn.sju.SpringStore.service.ex.PasswordNotMatchException;
import cn.sju.SpringStore.service.ex.ProductNotFoundException;
import cn.sju.SpringStore.service.ex.ServiceException;
import cn.sju.SpringStore.service.ex.UpdateException;
import cn.sju.SpringStore.service.ex.UserIsAlreadyDeleteException;
import cn.sju.SpringStore.service.ex.UserNotFoundException;
import cn.sju.SpringStore.service.ex.UsernameAlreadyInuseException;
import cn.sju.SpringStore.service.ex.CreateOrderException;
import cn.sju.SpringStore.util.ResponseResult;



public class BaseController {
	@ExceptionHandler
	@ResponseBody
	public ResponseResult<Void> handleException(ServiceException e, HttpSession session) {
		ResponseResult<Void> rs = new ResponseResult<Void>();
		if (e instanceof UserNotFoundException ) {
			rs.setState(-1);
			rs.setMessage("用户不存在！");
		} else if (e instanceof PasswordNotMatchException ) {
			rs.setState(-2);
			rs.setMessage("密码错误！");
		} else if (e instanceof UsernameAlreadyInuseException ) {
			rs.setState(-1);
			rs.setMessage("用户名已存在！");
		} else if (e instanceof UserIsAlreadyDeleteException ) {
			rs.setState(-1);
			rs.setMessage("该用户已被删除，请联系相关管理员！");
			session.removeAttribute("id");
			session.removeAttribute("username");
		} else if (e instanceof InsertException ) {
			rs.setState(-2);
			rs.setMessage("数据库插入失败，请联系管理员！");
		} else if (e instanceof OldPwdNotMatchException ) {
			rs.setState(-1);
			rs.setMessage("原密码错误！");
		} else if (e instanceof IllegalOperationException ) {
			rs.setState(-1);
			rs.setMessage("非法操作其他用户！");
		} else if (e instanceof UpdateException ) {
			rs.setState(-1);
			rs.setMessage("数据库更新失败！");
		} else if (e instanceof AddressCountLimitException ) {
			rs.setState(-1);
			rs.setMessage("新增收货地址异常！最大收货地址条数为15条");
		} else if (e instanceof CreateOrderException ) {
			rs.setState(-1);
			rs.setMessage("创建订单失败，请重试！");
		} else if (e instanceof OrderNoIsNoFoundException ) {
			rs.setState(-1);
			rs.setMessage("订单号不存在，请检查是否正确！");
		} else if (e instanceof OrderPayException ) {
			rs.setState(-1);
			rs.setMessage("订单支付异常，请联系客服！");
		} else if (e instanceof NoUnPayOrderException ) {
			rs.setState(-1);
			rs.setMessage("该订单已取消或已付款！");
		} else if (e instanceof CartNotFoundException ) {
			rs.setState(-1);
			rs.setMessage("创建订单记录异常！未找到相关购物车记录！");
		} else if (e instanceof AddressNotFoundException ) {
			rs.setState(-1);
			rs.setMessage("创建订单记录异常！未查到对应的收货地址！");
		}else if(e instanceof ProductNotFoundException) {
			rs.setState(36);
			rs.setMessage("显示商品详情异常！未找到商品数据");
		} else if (e instanceof RuntimeException ) {
			rs.setState(-3);
			if (e.getMessage() != null) {
				rs.setMessage(e.getMessage());
			}else {
				rs.setMessage("注册失败！");
			}
		}
		return rs;
	}
}
