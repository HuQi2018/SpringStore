package cn.sju.SpringStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sju.SpringStore.entiy.Product;
import cn.sju.SpringStore.service.IProductService;
import cn.sju.SpringStore.util.ResponseResult;

@RestController
@RequestMapping("/products")
public class ProudctController extends BaseController{
	@Autowired 
    IProductService service;
	
	@GetMapping("hot")
    public ResponseResult<List<Product>> getHotList(){
		List<Product> data = service.getHotList();
		return new ResponseResult<List<Product>>(1, "获取成功！", data);
    }

	@GetMapping("new")
    public ResponseResult<List<Product>> getNewList(){
		List<Product> data = service.getNewList();
		return new ResponseResult<List<Product>>(1, "获取成功！", data);
    }
	
	@GetMapping("{id}/get")
	public ResponseResult<Product> getById(@PathVariable("id")Integer id){
	    Product product=service.getById(id);
	    return new ResponseResult<Product>(1, "获取成功！",product);
	}
}
