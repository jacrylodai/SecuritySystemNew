package com.ldp.security.util.business.excel.common;

/**
 * 位置信息类，保存相应内容在excel文件中的位置
 * @author Administrator
 *
 */
public class ContentPosition {

	//列index	
	private int col;
	
	//行index
	private int row;

	public ContentPosition() {
		super();
	}

	public ContentPosition(int col, int row) {
		super();
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}
