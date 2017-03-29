package com.chaoxing.oa.util.data;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptBase64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
       
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBase64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }
    
    public static void main(String[] args) {
		try {
			System.out.println(encryptBase64("1321231230".getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
