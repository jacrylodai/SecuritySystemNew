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

import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.constants.Constants;

/**
 * 登录验证类，如没有登录则跳转回login.jsp进行登录
 * 对*.do *.jsp 进行拦截
 * @author Administrator
 *
 */
public class LoginCheckFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String contextPath=req.getContextPath();
		String servletPath=req.getServletPath();
		User user = (User)req.getSession().getAttribute(User.USER_SESSION_ID);
		
		//项目默认主页,项目帮助可以访问
		if(servletPath.equals("/index.jsp") 
				|| servletPath.equals("/help.jsp")){
			chain.doFilter(request, response);
			return;
		}
		
		if(user==null){

			//登录可以访问
			if(servletPath.equals(Constants.LOGIN_FUNC)){
				chain.doFilter(request, response);
			}else{
				resp.sendRedirect(contextPath+Constants.LOGIN_FUNC
						+"?command=loginCheckPrepare");
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}


}
