package com.kevinwong.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 * 
 * @author Eastascend <EastascendWang@gmail.com>
 */
@Component
public class KingExceptionHandler implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		K k = new K();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			
			if (ex instanceof KingException) {
				k.put("code", ((KingException) ex).getCode());
				k.put("msg", ((KingException) ex).getMessage());
			}else if(ex instanceof DuplicateKeyException){
				k = K.error("数据库中已存在该记录");
			}else{
				k = K.error();
			}
			
			//记录异常日志
			logger.error(ex.getMessage(), ex);
			
			String json = JSON.toJSONString(k);
			response.getWriter().print(json);
		} catch (Exception e) {
			logger.error("KingExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
