package cn.sju.SpringStore.service;

import java.util.List;

import cn.sju.SpringStore.entiy.Address;
import cn.sju.SpringStore.service.ex.AddressCountLimitException;
import cn.sju.SpringStore.service.ex.InsertException;

public interface IAddressService {
	void createAddress(Integer uid,String username, Address address)throws AddressCountLimitException, InsertException;

	List<Address> getAddress(Integer id);
	
	Address getAddressByAid(Integer id);
	
	Integer delAddress(Integer uid, Integer id);
	
	Integer defAddress(Integer uid, Integer id);
}
