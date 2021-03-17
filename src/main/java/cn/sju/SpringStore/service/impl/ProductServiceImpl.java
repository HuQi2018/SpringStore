package cn.sju.SpringStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sju.SpringStore.entiy.Product;
import cn.sju.SpringStore.mapper.ProductMapper;
import cn.sju.SpringStore.service.IProductService;
import cn.sju.SpringStore.service.ex.ProductNotFoundException;
import cn.sju.SpringStore.service.ex.ProductOutOfStockException;
import cn.sju.SpringStore.service.ex.UpdateException;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    ProductMapper mapper;
    
    private List<Product> findHotList(){
        return mapper.findHotList();
    }
    
    @Override
    public List<Product> getHotList() {
        return findHotList();
    }

    private List<Product> findNewList(){
        return mapper.findNewList();
    }
    
    @Override
    public List<Product> getNewList() {
        return findNewList();
    }
    
    private Product findById(Integer id) {
        return mapper.findById(id);
    }
    
    @Override
    public Product getById(Integer id) {
        // 使用id查商品数据
        Product product=findById(id);
        if(product==null) {
            throw new ProductNotFoundException();
        }

        // 将不需要给用户的数据设为null
        product.setPriority(null);
        product.setStatus(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }

    @Override
    public void reduceNum(Integer pid, Integer num) {
        // 根据pid查询商品数据
        Product product=findById(pid);
        // 判断结果是否为null
        if(product==null) {
            // 是：ProductNotFoundException
            throw new ProductNotFoundException("减少商品库存数量异常！商品数据不存在！");
        }

        // 从查询结果中获取当前库存量
        Integer oldNum=product.getNum();
        // 计算库存减少后的结果
        Integer newNum=oldNum-num;
        // 判断该结果是否小于0
        if(newNum<0) {
            // 是：ProductOutOfStockException
            throw new ProductOutOfStockException("减少商品库存数量异常！商品库存不足！");
        }

        // 执行更新操作
        updateNum(newNum, pid);
    }
	
	/**
	 *  更新一个商品的库存数量
	 * @param num 新的库存数量
	 * @param id 商品的id
	 * @return
	 */
	private void updateNum(Integer num,Integer id) throws UpdateException {
	    Integer row=mapper.updateNum(num, id);
	    if(row!=1) {
	        throw new UpdateException("更新商品库存数量失败！更新异常！");
	    }
	}
	
	@Override
	public void addNum(Integer pid, Integer num) {
	    // 根据pid查询商品数据
	    Product product=findById(pid);
	    // 判断结果是否为null
	    if(product==null) {
	        // 是：ProductNotFoundException
	        throw new ProductNotFoundException("减少商品库存数量异常！商品数据不存在！");
	    }

	    // 忽略商品下架

	    // 从查询结果中获取当前库存量
	    Integer oldNum=product.getNum();
	    // 计算库存减少后的结果
	    Integer newNum=oldNum+num;

	    // 执行更新操作
	    updateNum(newNum, pid);
	}
}
