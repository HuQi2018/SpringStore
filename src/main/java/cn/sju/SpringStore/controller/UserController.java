package cn.sju.SpringStore.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.sju.SpringStore.entiy.User;
import cn.sju.SpringStore.service.IUserService;
import cn.sju.SpringStore.service.ex.FileEmptyException;
import cn.sju.SpringStore.service.ex.FileIOException;
import cn.sju.SpringStore.service.ex.FileSizeException;
import cn.sju.SpringStore.service.ex.FileStateException;
import cn.sju.SpringStore.service.ex.FileTypeException;
import cn.sju.SpringStore.service.impl.UserServiceImpl;
import cn.sju.SpringStore.util.ResponseResult;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("login")
    public ResponseResult<User> login(String username, String password,HttpSession session) {
    	
        System.out.println("登录用户名：" + username);
        System.out.println("登录密码：" + password);
        User user = userService.login(username, password);
		session.setAttribute("username", user.getUsername());
		session.setAttribute("id", user.getUid());
		session.setAttribute("avatar", user.getAvatar());
        ResponseResult<User> rs = new ResponseResult<User>();
        rs.setState(1);
        rs.setMessage("登陆成功！");
        rs.setData(user);
        return rs;

    }
    
    @RequestMapping("register")
    public ResponseResult<Void> register(User user) {
    	
        System.out.println("注册用户名：" + user.getUsername());
        System.out.println("注册密码：" + user.getPassword());
        userService.register(user);
        ResponseResult<Void> rs = new ResponseResult<Void>();
        rs.setState(1);
        rs.setMessage("注册成功！");
        return rs;
        
    }
    
    @RequestMapping("getInfo")
    public ResponseResult<User> getInfo(HttpSession session) {
    	
    	ResponseResult<User> rs = new ResponseResult<User>();
    	User user = new User();
		Integer id = (Integer) session.getAttribute("id");
		String username = (String) session.getAttribute("username");
		String avatar = (String) session.getAttribute("avatar");
		if (id != null) {
			user.setAvatar(avatar);
			user.setUid(id);
			user.setUsername(username);
			rs = new ResponseResult<User>(1,"获取成功！",user);
		}else {
			rs.setState(1);
			rs.setMessage("获取成功！");
			rs = new ResponseResult<User>(-1,"请先登录！");
		}
        return rs;
    }
    
    @RequestMapping("getUserData")
    public ResponseResult<User> getUserData(HttpSession session) {
    	
    	ResponseResult<User> rs = new ResponseResult<User>();
		Integer id = (Integer) session.getAttribute("id");
		String username = (String) session.getAttribute("username");
		//传用户名、电话号码、电子邮箱、性别、id
		if (id != null) {
			User user = userService.getUserData(id);
			rs = new ResponseResult<User>(1,"获取成功！",user);
		}else {
			rs = new ResponseResult<User>(-1,"请先登录！");
		}
		return rs;
		
    }
    
    @RequestMapping("updatePwd")
    public ResponseResult<Void> updatePwd(String old_pwd, String new_pwd,HttpSession session) {
    	
    	ResponseResult<Void> rs = new ResponseResult<Void>();
		Integer id = (Integer) session.getAttribute("id");
		
		if (id != null) {
			System.out.println("原密码：" + old_pwd);
	        System.out.println("新密码：" + new_pwd);
	        userService.updatePwd(id, old_pwd, new_pwd);
	        rs.setState(1);
	        rs.setMessage("密码修改成功！");
		}else {
	        rs.setState(-4);
	        rs.setMessage("请先登录！");
		}
        return rs;
        
    }

    @RequestMapping("updateUserData")
    public ResponseResult<Void> updateUserData(User user,HttpSession session) {
    	
    	ResponseResult<Void> rs = new ResponseResult<Void>();
		Integer id = (Integer) session.getAttribute("id");
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		if (id != null) {
	        userService.updateUserData(id, user);
	        rs.setState(1);
	        rs.setMessage("信息修改成功！");
		}else {
	        rs.setState(-4);
	        rs.setMessage("请先登录！");
		}
        return rs;
        
    }

    @RequestMapping("logout")
    public ResponseResult<Void> logout(HttpSession session) {
    	
		session.removeAttribute("username");
		session.removeAttribute("id");
        ResponseResult<Void> rs = new ResponseResult<Void>();
        rs.setState(1);
        rs.setMessage("注销成功！");
        return rs;

    }
    
    private static final long AVATAR_MAX_SIZE=600*1024;
    private static final List<String> AVATAR_TYPES=new ArrayList<String>();

    // 静态初始化器：用于初始化本类的静态成员
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
    }
    //UserController.java
    @RequestMapping("updateUserAvatar")
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file,
    		HttpSession session,HttpServletRequest request) throws IllegalStateException, IOException {
    	
        // 空文件验证
        if(file.isEmpty()) {
            throw new FileEmptyException("文件上传异常！文件不能为空");
        }
        // 文件大小验证
        long fileSize=file.getSize();
        if(fileSize>AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件上传异常！文件大小超过上限:"+(AVATAR_MAX_SIZE/1024)+"kb");
        }
        // 文件类型验证
        if(!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("文件上传异常！文件类型不正确，允许的类型有："+AVATAR_TYPES);
        }
        // 生成文件名
        String oFilename=file.getOriginalFilename();
        Integer index=oFilename.lastIndexOf(".");
        String suffix="";
        if(index!=-1) {
            suffix=oFilename.substring(index);
        }
        String filename=UUID.randomUUID().toString()+suffix;

        // 生成目标路径
        String filePath=request.getServletContext().getRealPath("images\\avatar");
        File parent=new File(filePath);
        if(!parent.exists()) {
            parent.mkdirs();//创建对应的目录
        }

        File dest=new File(parent,filename);
        // 将用户上传的头像保存到服务器上
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件上传异常！"+e.getMessage());
            // throw new FileStateException("文件上传异常！"+e.getMessage(),e);
        } catch (IOException e) {
            throw new FileIOException("文件上传异常！"+e.getMessage());
        }
        
        String path = "/images/avatar/"+filename;
    	ResponseResult<String> rs = new ResponseResult<String>();
		Integer id = (Integer) session.getAttribute("id");
		User user = new User();
		user.setUsername((String) session.getAttribute("username"));
		user.setAvatar(path);
		session.setAttribute("avatar", path);
		if (id != null) {
	        userService.updateUserAvatar(id, user);
	        rs = new ResponseResult<String>(1,"上传成功！",path);
		}else {
	        rs = new ResponseResult<String>(-4,"请先登录！");
		}
        return rs;

    }
}