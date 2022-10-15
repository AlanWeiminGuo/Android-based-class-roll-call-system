package com.bs.course.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LogUtil implements MethodInterceptor {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Gson gson = new GsonBuilder()
			.serializeNulls()		//null值属性也需要序列化
			.setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期转换
			.create();

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String className = methodInvocation.getMethod().getDeclaringClass().getSimpleName();
		String methodName = methodInvocation.getMethod().getName();
		Object[] arguments = methodInvocation.getArguments();
		List<Object> params = new ArrayList<Object>();
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] instanceof HttpServletRequest || arguments[i] instanceof HttpServletResponse
					|| arguments[i] instanceof Model) {
				continue;
			}
			params.add(arguments[i]);
		}
		long before = System.currentTimeMillis();
		Object ret = methodInvocation.proceed();
		long after = System.currentTimeMillis();
		log.info("{}.{} 被调用 - 参数:{} - 耗时:{}ms",className,methodName,gson.toJson(params),after-before);
		return ret;
	}

}
