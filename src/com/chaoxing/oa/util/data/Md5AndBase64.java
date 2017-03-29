package com.chaoxing.oa.util.data;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import com.chaoxing.oa.util.system.ResourceUtil;

import sun.misc.BASE64Encoder;

public class Md5AndBase64 {

	public static String loadFile(String fileName) {
		InputStream fis;
		try {
			fis = new FileInputStream(fileName);
			byte[] bs = new byte[fis.available()];
			fis.read(bs);
			String res = new String(bs, "utf8");
			fis.close();
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String md5EncryptAndBase64(String str) {
		return encodeBase64(md5Encrypt(str));
	}

	private static byte[] md5Encrypt(String encryptStr) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(encryptStr.getBytes("utf8"));
			return md5.digest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String encodeBase64(byte[] b) {
		sun.misc.BASE64Encoder base64Encode = new BASE64Encoder();
		String str = base64Encode.encode(b);
		return str;
	}

	public static void main(String[] args) {
//		File directory = new File("");
		Md5AndBase64 u = new Md5AndBase64();
		String xml = loadFile(u.getClass().getResource("/").getFile().toString() + "a.xml");
		String checkword = ResourceUtil.getCheckWord();
//		String checkword = "j8DzkIFgmlomPt0aLuwU";
		System.out.println(md5EncryptAndBase64(xml + checkword));
		System.out.println(md5EncryptAndBase64("abc"));
//	     //取得根目录路径  
//	       String rootPath=u.getClass().getResource("/").getFile().toString();  
//	       //当前目录路径  
//	       String currentPath1=u.getClass().getResource(".").getFile().toString();  
//	       String currentPath2=u.getClass().getResource("").getFile().toString();  
//	       //当前目录的上级目录路径  
//	       String parentPath=u.getClass().getResource("../").getFile().toString();
//		String xml = loadFile(args[0]);
	}
}
