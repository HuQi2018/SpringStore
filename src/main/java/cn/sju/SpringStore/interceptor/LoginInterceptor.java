package cn.sju.SpringStore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
	    HttpSession session=request.getSession();

	    if(session.getAttribute("id")==null) {
	        // 重定向到登录页面
	        response.sendRedirect("/web/login.html");
	        return false;
	    }

	    // 放行
	    return true;
	}
}
