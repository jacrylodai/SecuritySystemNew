package com.ldp.security.util;

import java.util.List;

/**
 * 页面模型类
 * @author Administrator
 *
 * @param <T>
 */
public class PageModel<T> {

	//数据
	private List<T> data;

	//偏移量
	private int offset;

	//当前页面数量
	private int pageSize;

	//总数量
	private int totalCount;

	/**
	 * 取得总页数
	 * @return
	 */
	public int getPageCount(){
		int pageCount = 1;
		if(0 == pageSize){
			throw new RuntimeException("无法取得总页数，每页数量为空");
		}
		if(0 == totalCount){
			return 1;
		}
		pageCount = totalCount/pageSize;
		int temp=totalCount%pageSize;
		if(0 != temp){
			pageCount++;
		}
		return pageCount;
	}
	
	public int getCurrentPageNumber(){
		
		if(0 == pageSize){
			throw new RuntimeException("无法取得当前页数，每页数量为空");
		}
		int currentPageNumber = offset/pageSize +1;
		return currentPageNumber;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
