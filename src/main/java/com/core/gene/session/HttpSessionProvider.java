package com.core.gene.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 提供常用session操作接口
 */
public class HttpSessionProvider implements SessionProvider{

	/**
	 * 设置
	 */
	@Override
	public void setAttribute(HttpServletRequest request,
			HttpServletResponse response, String name, Serializable value) {
		HttpSession session=request.getSession();
		session.setAttribute(name, value);
	}

	/**
	 * 获取
	 */
	@Override
	public Serializable getAttribute(HttpServletRequest request,
			HttpServletResponse response, String name) {
		HttpSession session =request.getSession(false);//如果当前没有session，那么就不会去创建新的seesion
		if(session!=null){
			return (Serializable)session.getAttribute(name);
		}
		return null;
	}

	/**
	 * 退出登录
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session =request.getSession(false);//如果当前没有session，那么就不会去创建新的seesion
		if(session!=null){
			session.invalidate();
		}
	}

	/**
	 * 获取sessionid
	 */
	@Override
	public String getSessionId(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return request.getSession().getId();
	}

}
