package com.high.food.libraries;

import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuthCode {
	
	public int RandomCode(){
		Random random = new Random();
        
		int result = random.nextInt(10000)+1000;
		 
		if(result>10000){
		    result = result - 1000;
		}
			return result;	
	}

	public String SecurityCode(){		
		Random random = new Random();
        
		int result = random.nextInt(100000)+10000;
		 
		if(result>100000){
		    result = result - 10000;
		}
		
		return String.valueOf(result);
	}
}
