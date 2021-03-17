package cn.sju.SpringStore.service;

import java.util.List;

import cn.sju.SpringStore.entiy.Product;

public interface IProductService {
	List<Product> getHotList();
	
	List<Product> getNewList();
	
	Product getById(Integer id);
	
	void reduceNum(Integer pid, Integer num);
	
	void addNum(Integer pid,Integer num);
}
