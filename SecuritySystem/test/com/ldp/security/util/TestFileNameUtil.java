package com.ldp.security.util;

import com.ldp.security.util.file.FileNameUtil;

import junit.framework.TestCase;

public class TestFileNameUtil extends TestCase{

	public void testGetSuffix(){
		
		String fileName = "testForm.xls";
		assertEquals("xls", FileNameUtil.getFileNameSuffix(fileName));
	}
	
}
