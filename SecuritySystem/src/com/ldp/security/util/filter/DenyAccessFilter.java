package com.ldp.security.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 禁止访问过滤器
 * @author Administrator
 *
 */
public class DenyAccessFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		throw new RuntimeException("你所查看的资源拒绝访问");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
