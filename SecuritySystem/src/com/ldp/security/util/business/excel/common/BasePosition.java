package com.ldp.security.util.business.excel.common;

import java.util.Map;

public class BasePosition {

	private String name;
	
	private int row;
	
	private int height;
	
	private Map<String, SubPosition> subPositionMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<String, SubPosition> getSubPositionMap() {
		return subPositionMap;
	}

	public void setSubPositionMap(Map<String, SubPosition> subPositionMap) {
		this.subPositionMap = subPositionMap;
	}
	
	public ContentPosition getContPosiBySubPosiName(String subPositionName){
		
		SubPosition subPosition = subPositionMap.get(subPositionName);
		if(subPosition == null){
			throw new RuntimeException("没有找到对应名称的subPosition");
		}
		ContentPosition contentPosition = new ContentPosition();
		
		int subCol = subPosition.getCol();
		int subRow = row + subPosition.getRelativeRow();
		
		contentPosition.setCol(subCol);
		contentPosition.setRow(subRow);
		return contentPosition;
	}
	
}
