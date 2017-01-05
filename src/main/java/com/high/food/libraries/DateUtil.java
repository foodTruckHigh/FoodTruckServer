package com.high.food.libraries;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	public boolean passwordDateCheck(Timestamp date){
		
		return (Timestamp.valueOf(LocalDateTime.now()).getTime() - date.getTime()) / (24 * 60 * 60 * 1000) > 90 ? true : false;
	}

}
