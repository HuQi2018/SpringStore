package cn.sju.SpringStore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sju.SpringStore.entiy.District;
import cn.sju.SpringStore.mapper.DistrictMapper;
import cn.sju.SpringStore.service.IDistrictService;
import cn.sju.SpringStore.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests{

    @Autowired
    DistrictMapper mapper;

//    @Test
    public void findByParent(){
        String parent="86";
        List<District> list=mapper.findByParent(parent);
        for(District dist:list){
            System.err.println(dist);
        }
    }
    
    @Autowired
    IDistrictService service;

//    @Test
    public void listByParent() {
        try {
            List<District> list=service.listByParent("86");
            for(District dist:list) {
                System.err.println(dist);
            }
        }catch(ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void findByCode(){
        String code="110000";
        District dist=mapper.findByCode(code);
        System.err.println(dist);
    }
}