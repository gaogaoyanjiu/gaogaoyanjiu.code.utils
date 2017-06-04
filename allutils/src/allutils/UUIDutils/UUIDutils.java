package allutils.UUIDutils;

import java.util.UUID;

import sun.security.util.BigInt;

/**
 * 
 * UUID随机数工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class UUIDutils {
/**
 * 获取UUID 32位长度
 * @return
 */
	public static String getUUID() {    
		
		String str = UUID.randomUUID().toString();   
	     // 去掉"-"符号    
	     //String uuidStr = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);    
	     String uuidStr = str.replace("-", "");
	     return uuidStr;    
	 }  

/**
 *  去掉"-"符号   
 * @param uuidStr
 * @return
 */
	public static String getUUID(String uuidStr) {    
	     uuidStr = uuidStr.replace("-", "");
	     return uuidStr;    
	 }  
	
	public static void main(String[] args) {
		
		//获取原生UUID
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID().toString().length());//获取长度
		//直接获取去掉"-"的UUID
		System.out.println(getUUID());
		System.out.println(getUUID().length());//获取长度
		//传入UUID,返回截取了"-"的UUID
		System.out.println(getUUID(UUID.randomUUID().toString()));
		System.out.println(getUUID(UUID.randomUUID().toString()).length());//获取长度
		
		//将16进制转为10进制
		Double random = Math.random();
		String str = random.toString();
		String replace="";
		if (str.length()>18) {
			replace = str.replace(".", "");
			
		}else{
			replace = str.replace(".", "0");
		}
			
		System.out.println(replace);
		System.out.println(replace.length());
		
		
		
	}
}



