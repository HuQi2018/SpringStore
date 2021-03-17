package cn.sju.SpringStore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sju.SpringStore.entiy.Address;
import cn.sju.SpringStore.mapper.AddressMapper;
import cn.sju.SpringStore.service.IAddressService;
import cn.sju.SpringStore.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {

    @Autowired
    AddressMapper mapper;

//    @Test
//    public void saveAddress() {
//        Address add=new Address();
//        add.setUid(7);
//        add.setName("小明同学2");;
//        Integer row=mapper.saveAddress(add);
//        System.err.println("row="+row);
//    }

//    @Test
//    public void countByUid() {
//        Integer count=mapper.countByUid(9);
//        System.err.println("count="+count);
//    } 
    @Autowired
    IAddressService service;
    
    @Test
    public void createAddress() {
        try {
        	List<Address> address=mapper.getAddress(9);
        	System.out.println(address);
//            Address add=new Address();
//            add.setName("小张同学");
//            add.setAddress("程序员之家");
//            service.createAddress(6, "管理员", add);
        }catch(ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
}