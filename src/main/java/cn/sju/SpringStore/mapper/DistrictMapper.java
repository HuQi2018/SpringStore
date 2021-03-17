package cn.sju.SpringStore.mapper;

import java.util.List;

import cn.sju.SpringStore.entiy.District;

public interface DistrictMapper {
	List<District> findByParent(String parent);
	
	District findByCode(String code);
}
