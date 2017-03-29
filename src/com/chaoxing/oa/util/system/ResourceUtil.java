package com.chaoxing.oa.util.system;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author dengxf
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	private void ResourceUtil() {
	}

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return bundle.getString("uploadFieldName");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return bundle.getString("uploadFileExts");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}
	
	/**
	 * 获得下载文件的目录(exceldir)
	 * @return
	 */
	public static final String getDownloadDirectory(){
		return bundle.getString("excelDir");
	}
	/**
	 * 获得下载文件的目录(checkword)
	 * @return
	 */
	public static final String getCheckWord(){
		return bundle.getString("checkword");
	}
	/**
	 * 获得下载文件的目录(url1)
	 * @return
	 */
	public static final String getUrl1(){
		return bundle.getString("url1");
	}
	/**
	 * 获得下载文件的目录(port1)
	 * @return
	 */
	public static final String getPort1(){
		return bundle.getString("port1");
	}
	
	/**
	 * 获得下载文件的目录(url2)
	 * @return
	 */
	public static final String getUrl2(){
		return bundle.getString("url2");
	}
	
	/**
	 * 获得下载文件的目录(port2)
	 * @return
	 */
	public static final String getPort2(){
		return bundle.getString("port2");
	}
	
	/**
	 * 获得下载文件的目录(port2)
	 * @return
	 */
	public static final String getWebcontent(){
		return bundle.getString("web_content");
	}

	public static final String getKey(String key) {
		return bundle.getString(key);
	}
	
}
