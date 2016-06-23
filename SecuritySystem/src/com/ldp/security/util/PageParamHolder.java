package com.ldp.security.util;

/**
 * 分页参数存储类，存放在ThreadLocal里，与当前线程绑定，这样多线程访问时才不会错误
 * @author Administrator
 *
 */
public class PageParamHolder {

	private static ThreadLocal pageOffsetLocal;
	
	private static ThreadLocal pageSizeLocal;
	
	static{
		pageOffsetLocal=new ThreadLocal();
		pageSizeLocal=new ThreadLocal();
	}
	
	public static void setPageOffset(int offset){
		pageOffsetLocal.set(offset);
	}
	
	/**
	 * 取得偏移量
	 * @return
	 */
	public static int getPageOffset(){
		Integer offsetObj=(Integer)pageOffsetLocal.get();
		int offset=0;
		if(offsetObj==null){
			throw new RuntimeException("no value is saved");
		}else{
			offset=offsetObj;
		}
		return offset;
	}
	
	public static void setPageSize(int pageSize){
		pageSizeLocal.set(pageSize);
	}
	
	/**
	 * 取得当前页面显示数量
	 * @return
	 */
	public static int getPageSize(){
		Integer pageSizeObj=(Integer)pageSizeLocal.get();
		int pageSize=0;
		if(pageSizeObj==null){
			throw new RuntimeException("no value is saved");
		}else{
			pageSize=pageSizeObj;
		}
		return pageSize;
	}
	
	public static void removePageOffset(){
		
		pageOffsetLocal.remove();
	}

	
	public static void removePageSize(){
		
		pageSizeLocal.remove();
	}
}
