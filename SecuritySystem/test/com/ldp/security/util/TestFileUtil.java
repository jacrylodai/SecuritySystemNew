package com.ldp.security.util;

import java.io.File;

import junit.framework.TestCase;

import com.ldp.security.util.file.FileUtil;

public class TestFileUtil extends TestCase{

	public void testDeleteFile(){
		
		String filePath = "H:/test/tempFolder";
		File file = new File(filePath);
		FileUtil.deleteFolder(file);
	}
}
