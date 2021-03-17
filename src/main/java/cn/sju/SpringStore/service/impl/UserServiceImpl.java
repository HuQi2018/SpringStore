package cn.sju.SpringStore.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.sju.SpringStore.entiy.User;
import cn.sju.SpringStore.mapper.UserMapper;
import cn.sju.SpringStore.service.IUserService;
import cn.sju.SpringStore.service.ex.InsertException;
import cn.sju.SpringStore.service.ex.OldPwdNotMatchException;
import cn.sju.SpringStore.service.ex.PasswordNotMatchException;
import cn.sju.SpringStore.service.ex.UpdateException;
import cn.sju.SpringStore.service.ex.UserIsAlreadyDeleteException;
import cn.sju.SpringStore.service.ex.UserNotFoundException;
import cn.sju.SpringStore.service.ex.UsernameAlreadyInuseException;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
    	
        User result = userMapper.findByUsername(username);
        
        if (result == null) {
            throw new UserNotFoundException();
        }else if (result.getIsDelete()==1) {
			throw new UserIsAlreadyDeleteException();
		} else {
        	password=getMd5Password(password, result.getSalt());
//        	System.out.println(password);
//        	System.out.println(result.getPassword());
//        	if (result.getPassword().equals(password.getBytes())) {
            if (result.getPassword().equals(password)) {
            	result.setPassword(null);
            	result.setSalt(null);
                return result;
            } else {
                throw new PasswordNotMatchException();
            }
        }
    }
    
    @Override
    public void register(User u) {
    	User result = userMapper.findByUsername(u.getUsername());
        if (result == null) {
        	User user = new User();

            String salt=UUID.randomUUID().toString();
            user.setSalt(salt);
            String md5Password=getMd5Password(u.getPassword(), salt);
            user.setPassword(md5Password);
        	user.setUsername(u.getUsername());
        	user.setIsDelete(0);
        	Date now = new Date();
        	user.setCreatedTime(now);
        	user.setModifiedTime(now);
        	user.setCreatedUser(u.getUsername());
        	user.setModifiedUser(u.getUsername());
        	user.setAvatar("/images/index/user.jpg");
            Integer rs = userMapper.addUser(user);
            if (rs!=1) {
				throw new InsertException();
			}
        } else {
            throw new UsernameAlreadyInuseException();
        }
    }

    @Override
    public Integer updatePwd(Integer id, String old_pwd, String new_pwd) {
    	User result = userMapper.findById(id);
        if (result != null) {
        	String pwd = result.getPassword();
//        	user.setPassword(password);
    		old_pwd = getMd5Password(old_pwd, result.getSalt());
    		new_pwd = getMd5Password(new_pwd, result.getSalt());
            
            if (pwd.equals(old_pwd)) {
            	Integer rs = userMapper.changePwd(id,new_pwd);
            	if (rs==1) {
            		return rs;
				} else {
					throw new UpdateException();
				}
			} else {
				throw new OldPwdNotMatchException();
			}
        } else {
            throw new UserNotFoundException();
        }
    }
    
    @Override
	public User getUserData(Integer id) {
		User result = userMapper.getUserInfoById(id);
		if (result.getIsDelete()==1) {
			throw new UserIsAlreadyDeleteException();
		}
		return result;
	}

    @Override
	public Integer updateUserData(Integer uid, User user) {

    	Date now = new Date();
    	user.setModifiedTime(now);
    	user.setModifiedUser(user.getUsername());
    	user.setUid(uid);
		Integer rs = userMapper.updateUserData(user);
		if (rs==1) {
    		return rs;
		} else {
			throw new UpdateException();
		}
	}

    //UserServiceImpl.java
    @Override
	public Integer updateUserAvatar(Integer uid, User user) {
    	Date now = new Date();
    	user.setModifiedTime(now);
    	user.setModifiedUser(user.getUsername());
    	user.setUid(uid);
		Integer rs = userMapper.updateUserAvatar(user);
		if (rs==1) {
    		return rs;
		} else {
			throw new UpdateException();
		}
	}

    private String getMd5Password(String password,String salt) {
        // salt+password+salt 进行3次加密
        String msg=salt+password+salt;
        for(int i=0;i<3;i++) {
            msg=DigestUtils.md5DigestAsHex(msg.getBytes());
        }
        return msg;
    }
}