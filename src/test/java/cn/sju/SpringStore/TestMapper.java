package cn.sju.SpringStore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sju.SpringStore.entiy.Product;
import cn.sju.SpringStore.entiy.User;
import cn.sju.SpringStore.mapper.ProductMapper;
import cn.sju.SpringStore.mapper.UserMapper;
import cn.sju.SpringStore.service.ex.InsertException;
import cn.sju.SpringStore.service.ex.ServiceException;
import cn.sju.SpringStore.service.ex.UsernameAlreadyInuseException;
import cn.sju.SpringStore.service.impl.ProductServiceImpl;
import cn.sju.SpringStore.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapper {
	
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
	UserServiceImpl serviceImpl;
	
//	@Test
	public void testaddUser() {
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		System.out.println("添加之前的uid："+user.getUid());
		int row = mapper.addUser(user);
		System.out.println(""+user.getUid());
		System.out.println(row);
	}
	
//	@Test
	public void testFindByName() {
		User user  = mapper.findByUsername("tom");
		System.out.println(user);
	}

//	@Test
	public void testReg() {
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		try {
			serviceImpl.register(user);
		} catch (UsernameAlreadyInuseException e) {
			System.out.println(e.getMessage());
		} catch (InsertException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Autowired
	ProductMapper mapper2;
	
//	@Test
	public void findHotList() {
	    List<Product> list=mapper2.findHotList();
	    for(Product p:list) {
	        System.err.println(p);
	    }
	}
	
//	@Test
	public void findById() {
	    System.err.println(mapper2.findById(10000001));
	}
	
	@Autowired
	ProductServiceImpl serviceImpl2;
	
//	@Test
	public void getHotList() {
	    List<Product> list=serviceImpl2.getHotList();
	    for(Product p:list) {
	        System.err.println(p);
	    }
	}
	@Test
	public void getById() {
	    try {
	        Product product=serviceImpl2.getById(100001);
	        System.err.println(product);
	    } catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
}
