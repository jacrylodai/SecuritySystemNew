package com.ldp.security.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldp.security.basedata.domain.Department;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.validate.ClientValidate;

/**
 * 用户权限访问过滤器
 * @author Administrator
 *
 */
public class AccessControlFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String contextPath=req.getContextPath();
		String servletPath=req.getServletPath();
		User user = (User)req.getSession().getAttribute(User.USER_SESSION_ID);
		if(user==null){

			//LoginCheckFilter已进行过滤
			chain.doFilter(request, response);
			return;
		}else{
			
			String command = request.getParameter("command");
			if(ClientValidate.isEmpty(command)){
				throw new RuntimeException("命令参数为空");
			}
			
			boolean isAccess = User.checkIsAccessResourceAllowed(servletPath, command, user);
			
			if(isAccess){
				chain.doFilter(request, response);
				return;
			}else{
				throw new RuntimeException("你没有访问当前资源:"+servletPath
						+" ，操作为："+command+" 的权限");
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
