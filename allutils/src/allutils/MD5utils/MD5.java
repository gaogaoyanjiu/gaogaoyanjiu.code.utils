package allutils.MD5utils;


import java.security.MessageDigest;

/**
 * 
 * MD5加密工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public final class MD5 {

	private static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	private static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String ToMD5(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			if(origin!=null){
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	public static void main(String[] args) {
		//System.out.println(MD5.ToMD5("123456"));
	}
}
