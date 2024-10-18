
package com.Filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Entity.CustomerEntity;
import com.Repository.CustomerRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class TokenFilter implements Filter {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String url = req.getRequestURI().toString();
		System.err.println(url);
		if(url.contains("public")) {
			chain.doFilter(request, response);
		}else if(url.contains("private")) {
			System.err.println("private");
		 String authToken =	req.getHeader("authToken");
		 Optional<CustomerEntity> op =	customerRepository.findByAuthToken(authToken);
			if(op.isPresent()) {
				chain.doFilter(request, response);
			}else {
				response.getWriter().print("Please Login");
			}
		}else {
			response.getWriter().print("Please Login");	
		}
	}
}
