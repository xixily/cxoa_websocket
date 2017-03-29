package com.chaoxing.oa.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RefactName {

	public static void main(String[] args) {
		File directory = new File("");//设定为当前文件夹 
		String dir = "";
		try{
		    System.out.println(directory.getCanonicalPath());//获取标准的路径 
		    System.out.println(directory.getAbsolutePath());//获取绝对路径 
		    dir = directory.getCanonicalPath();
		    File root = new File(dir);
		    showAllFiles(root);
		}catch(Exception e){} 
	}
	 final static void showAllFiles(File dir) throws Exception{
		  File[] fs = dir.listFiles();
		  if(fs==null)return ;
		  List<File> fileList  = new ArrayList<File>();
		  for (File f : fs) {
		      fileList.add(f);
		  }
		  for (File file : fileList) {
			if(file.isDirectory()){
				showAllFiles(file);
			}else{
				String filename = file.getAbsolutePath();
				filename = filename.substring(0,filename.lastIndexOf("."));
				file.renameTo(new File(""));
			}
		}
		 }
}
