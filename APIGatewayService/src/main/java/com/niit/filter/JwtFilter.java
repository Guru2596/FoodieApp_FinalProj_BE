package com.niit.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request1=(HttpServletRequest)request;
		HttpServletResponse response1=(HttpServletResponse)response;
		//expects the token to come from the header
		String authenticationHeader=request1.getHeader("authorization");
		
		if(request1.getMethod().equals("OPTIONS"))
		{
			//if the method is options the request can pass through not validation of token is required
			response1.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request1, response1);
			//doFilter(ServletRequest request, ServletResponse response) Causes the next filter in the chain to be invoked, or if the calling filter is the last filter in the chain, causes the resource at the end of the chain to be invoked.
		}
		else
		{
			if(authenticationHeader==null||!authenticationHeader.startsWith("Bearer"))
				throw new ServletException("Missing or Invalid Authorization Header");
		}
		//extract token from the header
		final String token=authenticationHeader.substring(7);//Bearer => 6+1 = 7, since token begins with Bearer
		//token validation
		Claims claims=Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
		request1.setAttribute("claims", claims);

		//pass the claims in the request, anyone wanting to

		chain.doFilter(request1, response1);

	}

}
