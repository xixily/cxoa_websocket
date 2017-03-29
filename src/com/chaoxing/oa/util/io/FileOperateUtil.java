package com.chaoxing.oa.util.io;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;  
import org.springframework.util.FileCopyUtils;  
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chaoxing.oa.system.SysConfig; 

public class FileOperateUtil {
	 private static final String REALNAME = "realName";  
	    private static final String STORENAME = "storeName";  
	    private static final String SIZE = "size";  
	    private static final String SUFFIX = "suffix";  
	    private static final String CONTENTTYPE = "contentType";  
	    private static final String CREATETIME = "createTime";  
	    private final static int BUFFER = 1024;
	    private static final Logger logger = Logger.getLogger(FileUtil.class);
	    /** 
	     * 将上传的文件进行重命名 
	     *  
	     * @author dengxf 
	     * @param name 
	     * @return 
	     */  
	    private static String rename(String name) {  
	  
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")  
	                .format(new Date()));  
	        Long random = (long) (Math.random() * now);  
	        String fileName = now + "" + random;  
	  
	        if (name.indexOf(".") != -1) {  
	            fileName += name.substring(name.lastIndexOf("."));  
	        }  
	  
	        return fileName;  
	    }  
	  
	    /** 
	     * 压缩后的文件名 
	     *  
	     * @author dengxf 
	     * @param name 
	     * @return 
	     */  
	    private static String zipName(String name) {  
	        String prefix = "";  
	        if (name.indexOf(".") != -1) {  
	            prefix = name.substring(0, name.lastIndexOf("."));  
	        } else {  
	            prefix = name;  
	        }  
	        return prefix + ".zip";  
	    }  
	  
	    /** 
	     * 上传文件 
	     *  
	     * @author dengxf 
	     * @param request 
	     * @param params 
	     * @param values 
	     * @return 
	     * @throws Exception 
	     */  
	    public static List<Map<String, Object>> upload(HttpServletRequest request,  
	            String[] params, Map<String, Object[]> values) throws Exception {  
	  
	        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
	  
	        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;  
	        Map<String, MultipartFile> fileMap = mRequest.getFileMap();  
	  
	        String uploadDir = request.getSession().getServletContext()  
	                .getRealPath("/")  
	                + SysConfig.UPLOAD_DIR;  
	        File file = new File(uploadDir);  
	  
	        if (!file.exists()) {  
	            file.mkdir();  
	        }  
	  
	        String fileName = null;  
	        int i = 0;  
	        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()  
	                .iterator(); it.hasNext(); i++) {  
	  
	            Map.Entry<String, MultipartFile> entry = it.next();  
	            MultipartFile mFile = entry.getValue();  
	  
	            fileName = mFile.getOriginalFilename();  
	  
	            String storeName = rename(fileName);  
	  
	            String noZipName = uploadDir + storeName;  
	            String zipName = zipName(noZipName);  
	  
	            // 上传成为压缩文件  
	            ZipOutputStream outputStream = new ZipOutputStream(  
	                    new BufferedOutputStream(new FileOutputStream(zipName)));  
	            outputStream.putNextEntry(new ZipEntry(fileName));  
	            outputStream.setEncoding("GBK");  
	  
	            FileCopyUtils.copy(mFile.getInputStream(), outputStream);  
	  
	            Map<String, Object> map = new HashMap<String, Object>();  
	            // 固定参数值对  
	            map.put(FileOperateUtil.REALNAME, zipName(fileName));  
	            map.put(FileOperateUtil.STORENAME, zipName(storeName));  
	            map.put(FileOperateUtil.SIZE, new File(zipName).length());  
	            map.put(FileOperateUtil.SUFFIX, "zip");  
	            map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");  
	            map.put(FileOperateUtil.CREATETIME, new Date());  
	  
	            // 自定义参数值对  
	            for (String param : params) {  
	                map.put(param, values.get(param)[i]);  
	            }  
	  
	            result.add(map);  
	        }  
	        return result;  
	    }  
	  
	    /** 
	     * 下载 
	     *  
	     * @author dengxf 
	     * @param request 
	     * @param response 
	     * @param storeName 
	     * @param contentType 
	     * @param realName 
	     * @throws Exception 
	     */  
	    public static void download(HttpServletRequest request,  
	            HttpServletResponse response, String storeName, String contentType,  
	            String realName) throws Exception {  
	        response.setContentType("text/html;charset=UTF-8");  
	        request.setCharacterEncoding("UTF-8");  
	        BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null;  
	  
//	        String ctxPath = SysConfig.DOWNLOAD_EXCEL;  
//	        String ctxPath = request.getSession().getServletContext()  
//	        		.getRealPath("/")  
//	        		+ SysConfig.UPLOAD_DIR;  
//	        String downLoadPath = ctxPath + storeName;  
	  
	        long fileLength = new File(storeName).length();  
	  
	        response.setContentType(contentType);  
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
//	        response.setHeader("Content-disposition", "attachment; filename="  
//	        		+ new String(realName.getBytes("utf-8"), "utf-8"));  
	        response.setHeader("Content-Length", String.valueOf(fileLength));  
	  
	        bis = new BufferedInputStream(new FileInputStream(storeName));  
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }
	        bos.flush();
	        bis.close();  
	        bos.close();  
	    }  

		/**
		 * 功 能: 移动文件(只能移动文件) 参 数: strSourceFileName:指定的文件全路径名 strDestDir: 移动到指定的文件夹 返回值: 如果成功true;否则false
		 * 
		 * @param strSourceFileName
		 * @param strDestDir
		 * @return
		 */
		public static boolean copyTo(String strSourceFileName, String strDestDir) {
			File fileSource = new File(strSourceFileName);
			File fileDest = new File(strDestDir);

			// 如果源文件不存或源文件是文件夹
			if (!fileSource.exists() || !fileSource.isFile()) {
				logger.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
				return false;
			}

			// 如果目标文件夹不存在
			if (!fileDest.isDirectory() || !fileDest.exists()) {
				if (!fileDest.mkdirs()) {
					logger.debug("目录文件夹不存，在创建目标文件夹时失败!");
					return false;
				}
			}

			try {
				String strAbsFilename = strDestDir + File.separator + fileSource.getName();

				FileInputStream fileInput = new FileInputStream(strSourceFileName);
				FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

				logger.debug("开始拷贝文件");

				int count = -1;

				long nWriteSize = 0;
				long nFileSize = fileSource.length();

				byte[] data = new byte[BUFFER];

				while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

					fileOutput.write(data, 0, count);

					nWriteSize += count;

					long size = (nWriteSize * 100) / nFileSize;
					long t = nWriteSize;

					String msg = null;

					if (size <= 100 && size >= 0) {
						msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
						logger.debug(msg);
					} else if (size > 100) {
						msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
						logger.debug(msg);
					}

				}

				fileInput.close();
				fileOutput.close();

				logger.debug("拷贝文件成功!");
				return true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 功 能: 删除指定的文件 参 数: 指定绝对路径的文件名 strFileName 返回值: 如果删除成功true否则false;
		 * 
		 * @param strFileName
		 * @return
		 */
		public static boolean delete(String strFileName) {
			File fileDelete = new File(strFileName);

			if (!fileDelete.exists() || !fileDelete.isFile()) {
				logger.debug(strFileName + "不存在!");
				return false;
			}

			return fileDelete.delete();
		}

		/**
		 * 功 能: 移动文件(只能移动文件) 参 数: strSourceFileName: 是指定的文件全路径名 strDestDir: 移动到指定的文件夹中 返回值: 如果成功true; 否则false
		 * 
		 * @param strSourceFileName
		 * @param strDestDir
		 * @return
		 */
		public static boolean moveFile(String strSourceFileName, String strDestDir) {
			if (copyTo(strSourceFileName, strDestDir))
				return delete(strSourceFileName);
			else
				return false;
		}

		/**
		 * 功 能: 创建文件夹 参 数: strDir 要创建的文件夹名称 返回值: 如果成功true;否则false
		 * 
		 * @param strDir
		 * @return
		 */
		public static boolean makeDir(String strDir) {
			File fileNew = new File(strDir);

			if (!fileNew.exists()) {
				return fileNew.mkdirs();
			} else {
				return true;
			}
		}

		/**
		 * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
		 * 
		 * @param strDir
		 * @return
		 */
		public static boolean removeDir(String strDir) {
			File rmDir = new File(strDir);
			if (rmDir.isDirectory() && rmDir.exists()) {
				String[] fileList = rmDir.list();

				for (int i = 0; i < fileList.length; i++) {
					String subFile = strDir + File.separator + fileList[i];
					File tmp = new File(subFile);
					if (tmp.isFile())
						tmp.delete();
					else if (tmp.isDirectory())
						removeDir(subFile);
				}
				rmDir.delete();
			} else {
				return false;
			}
			return true;
		}
}
