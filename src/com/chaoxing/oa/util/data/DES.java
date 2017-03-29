package com.chaoxing.oa.util.data;

import java.security.SecureRandom;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

/**
DES加密介绍
DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
。
注意：DES加密和解密过程中，密钥长度都必须是8的倍数
*/
public class DES {
	
//	private static final Logger logger = LoggerFactory.getLogger(DES.class);
	
	private static final Logger logger = Logger.getLogger(DES.class);
	
	private static final String DES_PASSWORD = "jif*93js%";
	
	public DES() {}
	
	public static void main(String[] args) {
		String sn = "D36FFD4C7A65B8E7F45FC13BF8B67AC5DFA3C4A37054E488510EC13827E2FFA3AA3D197731FBCF874865C8FBC742B9AA52A5316D237A744B95C8552A1C1E503EA6DABFF63ABD9FC650BD69F085D1DFB1A923480FED5852687A8D29FF34204EE64888459FAE0192696F448B0A8CD384AE57605669D9AFCA3154F25135842858016626659F7D65741A85073BE6BFE3AA8B8E2D1E39AB5D8039FB2F42AFA65C04FD5352F1057AEB2D3FD45060C5D6C2932F9D1E01A7BBB61580E55675CBA20DF9C992E2D25DE78A4AC3EE99DFDD38900262477FF2EFD0F2212A88F1FFD9402571D456224291604000E3B3EDABC0FA5E162C521A21FC933D610F242C32A164EEAEF78FBBCF8FB795206E54E2158B3454F7C5E924FAF418C1E38848A977F97E936F6820491D4583E83C20D46F1F50D555601EB89B5E3FFCE104024CE29A26DC323497B1A12D2F31C5388255DA68AB2F87DB6882639887AC5A0274174506D4596B88DC83189B33651BC68B248F7BC632C7138C4EEEAD3524CA4F8A7BA2DE11D4ACE0DE5B75EF662C0FB54A823706A6BD33CB382AF6976BED87DD2201AD36D1F605D03FAC547C74794BD093069B8684DB1C5C7FF2D5F6875968D722BE48457A2D3AE53E6DD00E60C44C232FCBDC5EFE274A507233074805D627D90262D09EB8DFE6803468005399E3408B20B31AED105D228B83C8529F3576FFC47EC86801F7C0463B8C3247BC889560DB18077FC1ED0AD1362A2C88BD823B8237325F49AF1C033F30209C7CE19D7438F00D35178A2E319909F457A156679B08C475B7C24A1303D4153E5B30E6E69DEED72413F0A5464925B24B4ABEB65E1AAE77A361A3BE2898A4302C2B534C1A073B2D1A428EFAACBA99DB92B8E7371B9FB7CBA00D4BCEF8DC1C65B791FD4A33663D8595D0B6897DC36B51A7D256F64609D654AA84CD1C159D9CE8984A8DC8C0F5A55FC7266F61553D648E930221AB43B519EB4A24C9B107117BC603E90D1E1B60D82844E7B0B8A3BD48A4B9397FF015C648B155D473685EA417F1AE0C0D6C10F667D44A411CFD27EE7C912E1D734D0D26F585B63BAE9F61E2FEA5FFB04B363766C835423D9F00B2BD27E9419FBB48009AB0A353AA0A913A5682412F5F49AF1C033F3020382455F503D57658";
        String sn2 = "D36FFD4C7A65B8E7F45FC13BF8B67AC5DFA3C4A37054E488510EC13827E2FFA3AA3D197731FBCF874865C8FBC742B9AA52A5316D237A744B95C8552A1C1E503EA6DABFF63ABD9FC650BD69F085D1DFB1A923480FED5852687A8D29FF34204EE64888459FAE0192696F448B0A8CD384AE57605669D9AFCA3154F25135842858016626659F7D65741A85073BE6BFE3AA8B8E2D1E39AB5D8039FB2F42AFA65C04FD5352F1057AEB2D3FD45060C5D6C2932F9D1E01A7BBB61580E55675CBA20DF9C992E2D25DE78A4AC3EE99DFDD38900262477FF2EFD0F2212A88F1FFD9402571D456224291604000E3B3EDABC0FA5E162C521A21FC933D610F242C32A164EEAEF78FBBCF8FB795206E54E2158B3454F7C5E924FAF418C1E38848A977F97E936F6820491D4583E83C20D46F1F50D555601EB89B5E3FFCE104024CE29A26DC323497B1A12D2F31C5388255DA68AB2F87DB6882639887AC5A0274174506D4596B88DC83189B33651BC68B248F7BC632C7138C4EEEAD3524CA4F8A7BA2DE11D4ACE0DE5B75EF662C0FB54A823706A6BD33CB382AF6976BED87DD2201AD36D1F605D03FAC547C74794BD093069B8684DB1C5C7FF2D5F6875968D722BE48457A2D3AE53E6DD00E60C44C232FCBDC5EFE274A507233074805D627D90262D09EB8DFE6803468005399E3408B20B31AED105D228B83C8529F3576FFC47EC86801F7C0463B8C3247BC889560DB18077FC1ED0AD1362A2C88BD823B8237325F49AF1C033F30209C7CE19D7438F00D35178A2E319909F457A156679B08C475B7C24A1303D4153E5B30E6E69DEED72413F0A5464925B24B4ABEB65E1AAE77A361A3BE2898A4302C2B534C1A073B2D1A428EFAACBA99DB92B8E7371B9FB7CBA00D4BCEF8DC1C65B791FD4A33663D8595D0B6897DC36B51A7D256F64609D654AA84CD1C159D9CE8984A8DC8C0F5A55FC7266F61553D648E930221AB43B519EB4A24C9B107117BC603E90D1E1B60D82844E7B0B8A3BD48A4B9397FF015C648B155D473685EA417F1AE0C0D6C10F667D44A411CFD27EE7C912E1D734D0D26F585B63BAE9F61E2FEA5FFB04B363766C835423D9F00B2BD27E9419FBB48009AB0A353AA0A913A5682412F5F49AF1C033F3020382455F503D57658";
//        DES.getObject(sn2);
        DES.getObject(sn);
	}
	
	public static JsonObject getObject(String sn){
		try{
			byte[] decryResult = DES.decrypt(hexDecode(sn), DES_PASSWORD);
			String json = new String(decryResult,"UTF-8");
			System.out.println(json);
			JsonObject jo = (JsonObject) JSONUtils.getJsonParser().parse(json);
			return jo;
		} catch (Exception e) {
			logger.info("【DES解密错误】"+e.getMessage());
			return null;
		}
	}
	
	public static String getMail(JsonObject jo){
		try {
			return jo.get("email").getAsString();
		} catch (Exception e) {
			logger.info("【获取信息失败】"+e.getMessage());
			return null;
		}
	}
	
	public static String getName(JsonObject jo){
		try {
			return jo.get("name").getAsString();
		} catch (Exception e) {
			logger.info("【获取信息失败】"+e.getMessage());
			return null;
		}
	}
	
	public static Integer getUid(JsonObject jo){
		try {
			return jo.get("uid").getAsInt();
		} catch (Exception e) {
			logger.info("【获取信息失败】"+e.getMessage());
			return null;
		}
	}
	
	public static String getPhone(JsonObject jo){
		try {
			return jo.get("phone").getAsString();
		} catch (Exception e) {
			logger.info("【获取信息失败】"+e.getMessage());
			return null;
		}
	}

	/**
	* 加密
	* @param datasource byte[]
	* @param password String
	* @return byte[]
	*/
	public static byte[] encrypt(byte[] datasource, String password) {
		try{
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			//创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密
			//正式执行加密操作
			return cipher.doFinal(datasource);
		}catch(Throwable e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* 解密
	* @param src byte[]
	* @param password String
	* @return byte[]
	* @throws Exception
	*/
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
	
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}
	
	public static char[] hexEncode(byte[] input){
		try	{
			return Hex.encodeHex(input);
		} catch (Exception e){
			throw new IllegalStateException("Hex encoder exception", e);
		}
		
	}
	
	/**
	 * 参数加密
	 * @param url
	 * @return
	 */
	public static String enParameter(String url){
		byte[] _a = encrypt(url.getBytes(),DES_PASSWORD);
		String b = new String(hexEncode(_a));
		return b;
	}
	
	/**
	 * 参数解密
	 * @param args
	 * @throws Exception
	 */
	public static String deParameter(String url) throws Exception{
		byte[] _b = decrypt(hexDecode(url),DES_PASSWORD);
		String c = new String(_b);
		return c;
	}
	
}