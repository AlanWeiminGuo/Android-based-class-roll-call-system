package com.bs.course.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		try {
			return true;
		} catch (Exception e) {
			log.error("【权限处理异常】",e);
			return false;
		}
	}

	/**
	 * 权限不正确，退出
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private boolean handleExit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (request.getHeader("x-requested-with") != null && 
				request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			PrintWriter wirter =  response.getWriter();
			wirter.write("timeout");
			wirter.flush();
			return false;
		} else {
			// 普通http请求session超时的处理
			response.sendRedirect("/gongzheng");
			return false;
		}
	}
	
}
