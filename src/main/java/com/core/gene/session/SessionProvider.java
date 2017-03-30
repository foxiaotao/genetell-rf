package com.core.gene.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提供常用session操作接口
 */
public interface SessionProvider {
	
	/**
	 * 设置
	 */
	public void setAttribute(HttpServletRequest request,HttpServletResponse response,String name,Serializable value);
	
	/**
	 * 获取
	 */
	public Serializable getAttribute(HttpServletRequest request,HttpServletResponse response,String name);
	
	/**
	 * 
	 */
	public void logout(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 获取Id
	 */
	public String getSessionId(HttpServletRequest request,HttpServletResponse response);
}
