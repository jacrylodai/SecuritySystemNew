package com.ldp.security.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ldp.security.util.PageParamHolder;
import com.ldp.security.util.xml.XMLConfigReader;

/**
 * 分页参数过滤器，取得请求地址中携带的偏移量pageOffset
 * （指第几个数据，如当前显示第3页，每页显示数量为10条，则偏移量为(3-1)*10=20
 * ，用于数据库查询语句query.setFirstResult(offset);
 * 
 * 以及当前页面显示数量pageSize，用于query.setMaxResults(pageSize);
 * 
 * ，把这2个值保存到PageParamHolder里
 * 在数据库操作时使用
 * @author Administrator
 *
 */
public class PageParamFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		PageParamHolder.setPageOffset(getOffset(req));
		PageParamHolder.setPageSize(getPagesize(req));
		
		try{
			
			chain.doFilter(request,response);
		}finally{
			
			PageParamHolder.removePageOffset();
			PageParamHolder.removePageSize();
		}
		
	}
	
    protected int getOffset(HttpServletRequest request){  
    	
        int offset = 0;  
    	String offsetStr=request.getParameter("pager.offset");
		try{
			if(offsetStr!=null){
				offset=Integer.parseInt(offsetStr);
			}else{
				offset=0;//0 by default
			}
		}catch(Exception ex){
			
		}
        return offset;  
    }  
      
    protected int getPagesize(HttpServletRequest request){  
    	
        return XMLConfigReader.getInstance().getSystemConfig().getPageSize();  
    }  
    
	public void destroy() {
		
	}


}
