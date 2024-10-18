package com.Service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	String data = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	StringBuffer sb = new StringBuffer();
	public String authToken() {
		
		for(int i = 1 ; i<=20 ; i++) {
			
			int index = (int) (Math.random()*data.length());
			sb.append(data.charAt(index));
		}
				return sb.toString();
	}
}
