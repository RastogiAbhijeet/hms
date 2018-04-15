package com.example.demo;



import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PassEncode{
	
	public BCryptPasswordEncoder passwordEncoder;
	
	public PassEncode() {
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public String encodePass(String password) {
		 
		
		return passwordEncoder.encode(password);
	}

	public boolean checkPass(CharSequence rawPassword, String encodedPassword) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
		
	}
	
}
