package com.core.gene.controller.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.core.gene.annotation.Authority;
import com.core.gene.bean.SessionsUser;
import com.core.gene.session.SessionProvider;

public class AdminUserInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SessionProvider sessionProvider;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception)
			throws Exception {
		if(exception!=null){
			logger.error(exception.getMessage());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			Map obj = new HashMap(0);
			obj.put("success", false);
			obj.put("msg", exception.getMessage());
			PrintWriter out = response.getWriter();
			out.append(obj.toString());
			out.flush();
			out.close();
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		
		HandlerMethod method = (HandlerMethod) handler;
		Authority auth = method.getMethod().getAnnotation(Authority.class);
		if(requestURI.endsWith("/login.do")){
			return true;
		}else if (auth == null) {
			//不需要权限
			return true;
		}else if(auth.auth()){
			//通过session中的SessionUser 通过isAdmin 判断是不是管理员登陆
			SessionsUser user = (SessionsUser) sessionProvider.getAttribute(request, response, "SESSIONSUSER");
			if("1".equals(user.getIsAdmin())){
				//说明是管理员
				return true;
			}else{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				Map obj = new HashMap(1);
				obj.put("success", false);
				obj.put("msg", "您不具备该功能的权限");
				PrintWriter out = response.getWriter();
				out.append(obj.toString());
				out.flush();
				out.close();
				//没有权限
				return false;
			}
		}
		return false;
	}
	
	

}
