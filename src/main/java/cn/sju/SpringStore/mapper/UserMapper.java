package cn.sju.SpringStore.mapper;

import org.apache.ibatis.annotations.Param;

import cn.sju.SpringStore.entiy.User;


public interface UserMapper {
//	@Select("select * from t_user where username=#{username}")
	Integer addUser(User user);//注册
	
	User findByUsername(String username);//登录
	
	User findById(@Param("uid")Integer uid);
	
	User getUserInfoById(@Param("uid")Integer uid);
	
	Integer changePwd(@Param("uid")int uid,@Param("new_pwd")String new_pwd);//修改密码
	
	Integer updateUserData(User user);//修改个人资料
	
	//UserMapper.java
	Integer updateUserAvatar(User user);//修改个人头像
}
