package cn.sju.SpringStore.service;

import java.util.List;

import cn.sju.SpringStore.entiy.District;

public interface IDistrictService {
	List<District> listByParent(String parent);
	
	District getByCode(String code);
}
