package com.ldp.security.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {

	/**
	 * 下载指定的文件
	 * @param response
	 * @param sourceFile 源文件
	 * @param fileName 输出的文件名
	 * @throws IOException
	 */
	public static void downloadFileFromServer(HttpServletResponse response
			,File sourceFile,String fileName)
		throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		// 得到下载文件的名字
		// String filename=request.getParameter("filename");

		// 设置response的编码方式
		response.setContentType("application/x-msdownload");

		// 写明要下载的文件的大小
		response.setContentLength((int) sourceFile.length());

		// 解决中文乱码
		String fileNameFormat = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename="+fileNameFormat);

		// 读出文件到i/o流
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(sourceFile));

		// 从response对象中得到输出流,准备下载
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		byte[] buff = new byte[4000];
		int length = 0;
		while( (length = in.read(buff)) !=-1){
			out.write(buff, 0, length);
		}
		out.flush();
		out.close();
		in.close();	
	}
	
}
