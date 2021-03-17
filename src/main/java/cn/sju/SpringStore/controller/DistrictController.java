package cn.sju.SpringStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sju.SpringStore.entiy.District;
import cn.sju.SpringStore.service.IDistrictService;
import cn.sju.SpringStore.util.ResponseResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    IDistrictService service;

    @GetMapping("/")
    public ResponseResult<List<District>> listByParent(String parent){
        List<District> list = service.listByParent(parent);
        return new ResponseResult<List<District>>(1,"获取成功！",list);
    }
}
