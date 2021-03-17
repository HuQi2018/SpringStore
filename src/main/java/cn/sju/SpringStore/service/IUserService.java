package cn.sju.SpringStore.service;

import cn.sju.SpringStore.entiy.User;

public interface IUserService {
	
	User login(String username, String password);
	
	void register(User u);
	
	Integer updatePwd(Integer id, String old_pwd, String new_pwd);
	
	User getUserData(Integer id);
	
	Integer updateUserData(Integer uid, User user);

	//IUserService.java
	Integer updateUserAvatar(Integer uid, User user);
	
}
