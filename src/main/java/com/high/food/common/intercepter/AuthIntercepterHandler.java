package com.high.food.common.intercepter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.high.food.common.annotation.HighAuth;




@Component
public class AuthIntercepterHandler extends WebContentInterceptor{
	
//	@Autowired
//	private UserRepository userRepository;
	//private AuthRepository authRepository;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
		//HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handler instanceof HandlerMethod) {
			HighAuth highAuth = ((HandlerMethod) handler).getMethodAnnotation(HighAuth.class);
			
	        if(highAuth == null) {
	        	return super.preHandle(request, response, handler);
	        }
	         else {
	    	/*	AuthValue value = new AuthValue();
	    		value.setTokenKey(request.getHeader("s-token"));
	    		value.setId(request.getHeader("s-Id"));
	 
	    		if(userRepository.findByIdAndTokenKey(value) < 1) return false;
	    		else*/ return super.preHandle(request, response, handler);
	        
	        }
		}
		else {
			return super.preHandle(request, response, handler);
		}
    }   
}