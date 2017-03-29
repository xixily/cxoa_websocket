package com.chaoxing.oa.util.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.dom4j.Element;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtils {
	private static Gson GSON = null;
	
	private static JsonParser JSONPARSER;
	
	public static Integer LIMIT = 20;
	
	public static Element element;
	
	public static Gson getGson(){
		if(GSON==null){
			GsonBuilder gb=new GsonBuilder();
			gb.excludeFieldsWithoutExposeAnnotation();
			GSON = gb.setDateFormat("yyyy-MM-dd").create();
		}
		return GSON;
	}
	
	public static Gson getGson(boolean flag){
		if(GSON==null){
			GsonBuilder gb=new GsonBuilder();
			gb.excludeFieldsWithoutExposeAnnotation();
			GSON = gb.setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		}
		return GSON;
	}
	
	public static JsonParser getJsonParser(){
		if(JSONPARSER==null){
			JSONPARSER = new JsonParser();
		}
		return JSONPARSER;
	}
	
	public static String dateFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Date dateFormat(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final static String outSuccessJson = "{\"success\":true}";
	
	public final static String outErrorJson = "{\"success\":false}";
	
	public static String getOutDatagridJson(@SuppressWarnings("rawtypes") List lst,Integer total){
		String json = getGson().toJson(lst);
		JsonArray jsonArray = (JsonArray) JSONUtils.getJsonParser().parse(json);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", true);
		jsonObject.addProperty("total", total);
		jsonObject.add("rows", jsonArray);
		return jsonObject.toString();
	}
	
	public static Date addDay(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return calendar.getTime();
	}
	
	public static Date addMonth(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		return calendar.getTime();
	}
	
	public static Date addYear(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		return calendar.getTime();
	}
	
	public static String writeTextFile(String path,String text){
		String date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		date = sdf.format(new Date());
		try {
			File file = new File(path);
			if (!file.isDirectory() && !file.exists() ) {
				file.mkdirs();
			}
			path = path+date+".meter";
			file = new File(path);
			if ( !file.createNewFile() ) return null;
			OutputStream out = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(out);
			osw.write(text);
			osw.flush();
			osw.close();
			System.out.println(333);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String writeTextFileByList(String path,List<String> lst){
		String txt = "";
		String separator = System.getProperty("line.separator");
		for (String string : lst) {
			if(!"NULL".equals(string))txt += string+separator;
		}
		path = writeTextFile(path, txt);
		return path;
	}
	
	public static String getMD5Str(String str){
		MessageDigest messageDigest = null;
		try{
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++){
			if(Integer.toHexString(0xFF & byteArray[i]).length() == 1){
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			}else{
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}
	
	public static String trimValue(Object src)
	{
		String result = "";
		if (src == null) result = "";
		else{
			if (src instanceof String) result = ((String) src).trim();
			else result = String.valueOf(src);
		}
		return result;
	}
	
	public static String formatDate(Date dataSrc, String pattern)
	{
		String result = "";
		if (dataSrc == null) return result;
		if (pattern == null || pattern == "") pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		result = trimValue(sdf.format(dataSrc));
		return result;
	}
	
	public static String createFolder(String folderPath){
		String strFolderPath = "";
		if(null != folderPath && !"".equals(folderPath)){
			File filePath = new File(folderPath);
			if(!filePath.exists()){
				filePath.mkdir();
			}
			strFolderPath = filePath.getPath();
		}
		return strFolderPath;
	}
	
	public static String createFile(String filePathAndName, String fileContent){
		String strFilePath = "";
		try{
			if(null == filePathAndName || "".equals(filePathAndName)){
				return strFilePath;
			}
			File filePath = new File(filePathAndName);
			if(!filePath.exists()){
				filePath.createNewFile();
			}else{
				String strOldFilePath = filePath.getPath();
				String strNewFilePath = filePath.getParent()+"/"+filePath.getName()+"_"+formatDate(new Date(),"yyyyMMddHHmmss");
				renameFile(strOldFilePath,strNewFilePath);
				filePath.createNewFile();
			}
			strFilePath = filePath.getPath();
			if(null != fileContent && !"".equals(fileContent)){
				FileWriter writer = new FileWriter(filePath,true);
				PrintWriter pw = new PrintWriter(writer);
				pw.println(fileContent);
				writer.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return strFilePath;
	}
	
	public static void renameFile(String oldPathAndName,String newPathAndName){
		if(!oldPathAndName.equals(newPathAndName)){
           File oldfile=new File(oldPathAndName);
           File newfile=new File(newPathAndName);
           if(newfile.exists()){
           
           }else{
               oldfile.renameTo(newfile);
           }
		}
	}
}
