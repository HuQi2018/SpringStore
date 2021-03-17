package cn.sju.SpringStore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sju.SpringStore.entiy.Product;

public interface ProductMapper {
	List<Product> findHotList();
	
	Product findById(Integer id);

	List<Product> findNewList();
	
	Integer updateNum(@Param("num") Integer num,@Param("id") Integer id);//库存操作
}
