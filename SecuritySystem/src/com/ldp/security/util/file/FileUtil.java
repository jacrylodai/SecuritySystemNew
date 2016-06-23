package com.ldp.security.util.file;

import java.io.File;
import java.util.List;

public class FileUtil {

	/**
	 * 生产文件 如果文件所在路径不存在则生成路径
	 * @param fileName
	 * 
	 * 文件名 带路径
	 * 
	 * @param isDirectory 是否为文件目录
	 * 
	 * @return
	 * 
	 * @author yayagepei
	 * 
	 * @date 2008-8-27
	 * 
	 */

	public static void buildFolder(String fileName, boolean isDirectory) {

		File target = new File(fileName);

		if (isDirectory) {

			target.mkdirs();

		} else {

			if (!target.getParentFile().exists()) {

				target.getParentFile().mkdirs();

			}

		}

	}
	
	/**
	 * 递归得到指定的文件目录下的所有文件，包含子文件夹下的文件，放入目标文件列表
	 * @param destList 目标文件列表
	 * @param sourceFileFolder 指定的文件目录
	 */
	public static void getAllSubFileListToDestList(List<File> destList
			,File sourceFileFolder){
		
		if(!sourceFileFolder.isDirectory()){
			throw new RuntimeException("指定的文件必须是文件目录");
		}
		File[] subFileArr = sourceFileFolder.listFiles();
		for(File subFile:subFileArr){
			if(subFile.isDirectory()){
				getAllSubFileListToDestList(destList, subFile);
			}else{
				destList.add(subFile);
			}
		}
	}
	
	/**
	 * 递归删除指定的文件目录，先删除其子文件，最终删除其本身
	 * @param sourceFileFolder 指定的文件目录
	 */
	public static void deleteFolder(File sourceFileFolder){

		if(!sourceFileFolder.isDirectory()){
			throw new RuntimeException("指定的文件必须是文件目录");
		}
		if(sourceFileFolder.isDirectory()){
			File[] subFileArr = sourceFileFolder.listFiles();
			for(File subFile:subFileArr){
				if(subFile.isDirectory()){
					deleteFolder(subFile);
				}else{
					subFile.delete();
				}
			}
			sourceFileFolder.delete();
		}
	}
}
