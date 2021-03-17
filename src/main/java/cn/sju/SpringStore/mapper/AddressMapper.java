package cn.sju.SpringStore.mapper;

import java.util.List;

import cn.sju.SpringStore.entiy.Address;

public interface AddressMapper {
	Integer saveAddress(Address address);

	Integer countByUid(Integer uid);
	
	List<Address> getAddress(Integer uid);
	
	Integer delAddress(Integer uid, Integer id);
	
	Integer defAddress(Integer uid, Integer id);
	
	Integer getDefAddress(Integer uid);
	
	Integer cancelDefAddress(Integer uid);
	
	Integer getOneAddress(Integer uid);

	Address getAddressByAid(Integer id);
}
