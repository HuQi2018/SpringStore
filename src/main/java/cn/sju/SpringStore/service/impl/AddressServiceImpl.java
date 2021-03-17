package cn.sju.SpringStore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sju.SpringStore.entiy.Address;
import cn.sju.SpringStore.entiy.District;
import cn.sju.SpringStore.mapper.AddressMapper;
import cn.sju.SpringStore.service.IAddressService;
import cn.sju.SpringStore.service.IDistrictService;
import cn.sju.SpringStore.service.ex.AddressCountLimitException;
import cn.sju.SpringStore.service.ex.IllegalOperationException;
import cn.sju.SpringStore.service.ex.InsertException;

@Service
public class AddressServiceImpl implements IAddressService{
	@Autowired
	AddressMapper mapper;
	
	@Override
	public List<Address> getAddress(Integer uid) {
		List<Address> address=mapper.getAddress(uid);
		return address;
	}

	@Override
	public Integer delAddress(Integer uid, Integer id) {
		Integer aid=mapper.getDefAddress(uid);//获取当前的默认地址
		if (aid == null) {
			throw new IllegalOperationException();
		}
		Integer address=mapper.delAddress(uid, id);//删除指定地址
		if (aid==id) {
			Integer newDef=mapper.getOneAddress(uid);//当删除的是默认地址时，则随机获得一个地址
			if (newDef == null) {
				return 1;//当该用户不存在地址时
			}
			Integer newAdd=mapper.defAddress(uid, newDef);//设置新的默认地址
		}
		return address;
	}

	@Override
	public Integer defAddress(Integer uid, Integer id) {
		Integer aid=mapper.getDefAddress(uid);//获取当前的默认地址
		if (aid == null) {
			throw new IllegalOperationException();
		}
		mapper.cancelDefAddress(uid);//取消数据库中的默认地址
		Integer address=mapper.defAddress(uid, id);//设置默认地址
		return address;
	}
	
	@Override
	public void createAddress(Integer uid, String username, Address address)
	        throws AddressCountLimitException, InsertException {
	    /*根据uid查询收货地址条数，自己将此查询功能封装成一个方法countByUid，如下笔记所述*/
	    Integer count=countByUid(uid);  
	    // 条数是否达到上限 3
	    if(count >= 15) {
	        // 是：AddressCountLimitException
	        throw new AddressCountLimitException();
	    }

	    // 补全uid
	    address.setUid(uid);
	    // 补全isDefault，根据上面查询到的收货地址条数进行判断
	    int isDefault=count==0 ? 1 : 0;
	    address.setIsDefault(isDefault);
	 // 补全省市区数据：补充省市区名称
	    String provinceName=getNameByCode(address.getProvinceCode().toString());
	    String cityName=getNameByCode(address.getCityCode().toString());
	    String areaName=getNameByCode(address.getAreaCode().toString());
	    address.setProvinceName(provinceName);
	    address.setCityName(cityName);
	    address.setAreaName(areaName);

	    // 创建当前时间对象
	    Date now =new Date();
	    // 补全4项日志数据
	    address.setCreatedUser(username);
	    address.setCreatedTime(now);
	    address.setModifiedUser(username);
	    address.setModifiedTime(now);
	    // 执行添加操作
	    saveAddress(address);
	}
	
	private Integer countByUid(Integer uid){
	    // 对参数的合理性进行判断  
	    if(uid==null || uid<1){
	        throw new IllegalArgumentException();
	    }
	    return mapper.countByUid(uid);
	}

	private void saveAddress(Address address){
	    Integer row=mapper.saveAddress(address);
	    if(row!=1){
	        throw new InsertException("添加收货地址异常！请联系管理员");
	    }
	}
	
	@Autowired
	IDistrictService service;
	
	public String getNameByCode(String code){
	    District dist=service.getByCode(code);
	    return dist==null?"":dist.getName();
	}

	@Override
	public Address getAddressByAid(Integer id) {
		Address address=mapper.getAddressByAid(id);
		return address;
	}
}
