package com.chaoxing.oa.util.data;

public class IdentityCardUtil {
	static int[] radix = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
	static char[] checkCode = {'1','0','X','9','8','7','6','5','4','3','2'};

	/**
	 * 校验身份证最后一位是否匹配
	 * @param idCard
	 * @return
	 */
	public static boolean validate(String idCard){
		if(null == idCard) return false;
		char[] ids = idCard.toCharArray();
		int sum = 0;
		int result = 0;
		if(ids.length == 18){
			for (int i = 0; i < (ids.length -1) ; i++) {
				result = getMultiply(ids[i], i);
				if(result == -1) return false;
				sum += result;
			}
			return ids[17] == checkCode[sum%11];
		}
		return false;
	}
	
	private static int getMultiply(char c, int i) {
		try{
			return Integer.valueOf(String.valueOf(c))* radix[i];
		}catch(Exception e){
			System.out.println(c + " false with e:" + e);
			return -1;
		}
	}
	
	public static void main(String[] args) {
		if(IdentityCardUtil.validate("410105196808122731")){
			System.out.println("校验成功！");
		}else{
			System.out.println("校验失败！");
		}
	}
}
