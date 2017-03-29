package com.chaoxing.oa.util.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 
 * @author dengxuefeng
 *
 */
public class HttpProvider {
	private static Logger log = Logger.getLogger(HttpProvider.class.getName());
	
	public static String sendPost(String url, byte[] data) throws IOException{
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			StringBuffer result = new StringBuffer("");
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(con
						.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				return result.toString();
			} else {

			}

		} catch (IOException e) {
			
			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}
			
			if (con != null) {
				try{
					con.getInputStream().close();
				} catch (Throwable e){
					
				}
				try{
					con.getOutputStream().close();
				} catch (Throwable e){
					
				}
				con.disconnect();
			}
		}
		return null;
	}
	public static String sendPostBy400Num(String url, byte[] data) throws Exception{
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			StringBuffer result = new StringBuffer("");
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(1000);
			con.setReadTimeout(2000);
			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(con
						.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				in.close();
				return result.toString();
			} else {

			}

		} catch (IOException e) {
			
			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}
			
			if (con != null) {
				try{
					con.getInputStream().close();
				} catch (Throwable e){
					
				}
				try{
					con.getOutputStream().close();
				} catch (Throwable e){
					
				}
				con.disconnect();
			}
		}
		return null;
	}
	public static String sendGet(String url) throws Exception {
		return sendGet(url, 10000);
	}
	public static String sendGet(String url, int timeout) throws Exception {
		StringBuffer result = new StringBuffer("");
		System.out.println("Call url: "+url);
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(timeout);
			int responseCode = con.getResponseCode();
			log.info("Get return ["+responseCode+"] from "+url);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(con
						.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				in.close();
				return result.toString();
			} else {
				
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(in != null)
				in.close();
			try{
				con.getInputStream().close();
			} catch (Throwable e){
				
			}
			try{
				con.getOutputStream().close();
			} catch (Throwable e){
				
			}
			con.disconnect();
		}
		return result.toString();
	}
	
	public static String sendFile(String url, String filename) throws Exception{
		File file = new File(filename);
		if(!file.exists())
			throw new Exception("File ["+filename+"] not found");
		HttpURLConnection con = null;
		BufferedReader in = null;
		BufferedOutputStream out = null;
		InputStream fileIn = null;
		String result = null;
		try{
			
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setReadTimeout(3600000);
			out = new BufferedOutputStream(con.getOutputStream());
			
			fileIn = new BufferedInputStream(new FileInputStream(file));
			byte[] data = new byte[1024];
			int count = -1;
			while((count = fileIn.read(data)) != -1){
				out.write(data, 0, count);
			}
			out.flush();
			out.close();
			fileIn.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(con
						.getInputStream()));
				String line;
				result = "";
				while ((line = in.readLine()) != null) {
					result += line;
				}
				in.close();
				return result;
			} else {
				throw new Exception("Send file failed");
			}
		} catch (Exception e){
			throw e;
		} finally {
			try{
				if(in != null)
					in.close();
			}catch (Exception e){}
			try{
				if(in != null)
					out.close();
			}catch (Exception e){}
			try{
				con.getInputStream().close();
			} catch (Throwable e){
				
			}
			try{
				con.getOutputStream().close();
			} catch (Throwable e){
				
			}
			con.disconnect();
		}
	}
	public static String sendFormFile(String url, String filePath,
			Map<String, String> params) throws Exception {
		String result = null;
		final String BOUNDARY = "---------------------------7da2137580612";
		final String endline = "--" + BOUNDARY + "--\r\n";

		URL u = new URL(url);
		int port = u.getPort() == -1 ? 80 : u.getPort();
		Socket socket = new Socket(InetAddress.getByName(u.getHost()), port);
		OutputStream outStream = socket.getOutputStream();
		StringBuilder send = new StringBuilder();
		send.append("POST " + u.getPath() + " HTTP/1.1\r\n");
		send.append("Accept: image/*, application/x-shockwave-flash, "
				+ "application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, "
				+ "application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n");
		send.append("Accept-Language: zh-CN\r\n");
		send.append("Connection: Keep-Alive\r\n");
		send.append("Host: " + u.getHost() + ":" + port + "\r\n");
		send.append("Content-Type: multipart/form-data; boundary=" + BOUNDARY
				+ "\r\n");

		StringBuilder textEntity = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			textEntity.append("--");
			textEntity.append(BOUNDARY);
			textEntity.append("\r\n");
			textEntity.append("Content-Disposition: form-data; name=\""
					+ entry.getKey() + "\""+"\r\n\r\n");
			textEntity.append(entry.getValue());
			textEntity.append("\r\n");
		}
		File file = new File(filePath);
		StringBuilder fileEntity = new StringBuilder();
		fileEntity.append("--");
		fileEntity.append(BOUNDARY);
		fileEntity.append("\r\n");
//		fileEntity.append("Content-Disposition: form-data;name=\""
//				+ "upload" + "\";filename=\"" + file.getAbsolutePath()
//				+ "/" + file.getName() + "\"\r\n");
		fileEntity.append("Content-Disposition: form-data;name=\""
				+ "upload" + "\";filename=\"" + file.getAbsolutePath()
				+ "\"\r\n");
		fileEntity
				.append("Content-Type: application/octet-stream" + "\r\n\r\n");

		String end = "\r\n" + endline;
		send.append("Content-Length: "
				+ (textEntity.toString().getBytes().length
						+ fileEntity.toString().getBytes().length
						+ file.length() + end.getBytes().length) + "\r\n");
		send.append("\r\n");
		outStream.write(send.toString().getBytes());
		outStream.write(textEntity.toString().getBytes());
		outStream.write(fileEntity.toString().getBytes());
		byte[] buffer = new byte[1024];
		int len = 0;
		FileInputStream stream = new FileInputStream(file);
		while ((len = stream.read(buffer, 0, 1024)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.write(end.getBytes());
		stream.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String line = "";
		outStream.flush();
		int resLen = 0;
		System.out.println(reader.toString());
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("Content-Length")) {
				resLen = Integer.valueOf(line.split(":")[1].trim());
			} else if (line.equals("")) {
				break;
			}
			System.out.println(line);
		}
		char[] c = new char[resLen];
		reader.read(c);
		outStream.close();
		reader.close();
		socket.close();
		result = new String(c);
		System.out.println(result);
		return result;
	}

}
