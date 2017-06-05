package allutils.other;


/**
 * 
 * 进制转换工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
	public class HexTo10To2 {
		//10进制转化为16进制
		public static String getToHexString(int str){
			
			 String hexStr = Integer.toHexString(str);
			
			return hexStr;
		}
		
		//10进制转化为2进制
		public static String getToBinaryString(int num){
			
			String str = Integer.toBinaryString(num);
			
			return str;
		}
		
		//10进制字符串转 16进制
		public static String getToHexString(String numStr){
			
			String hexStr = Integer.toHexString(Integer.parseInt(numStr));
			
			return hexStr;
		}
		
		//10进制字符串转 2进制
		public static String getToBinaryString(String numStr){
			
			String hexStr = Integer.toBinaryString(Integer.parseInt(numStr));
			
			return hexStr;
		}
		
		//16进制转化为10进制
		public static int getHexToShi(String str){
			
			int parseInt = Integer.parseInt(str,16);
			
			return parseInt;
		}
		
		//16进制转化为2进制
		public static String getHexToBinaryString(String str){
			
			String bStr = Integer.toBinaryString(Integer.parseInt(str,16));
			
			return bStr;
		}
	
		//字节数组转 16进制字符串
		 public static String getBinaryToHexString(byte[] bytes){
		         
		         String hexStr =  "0123456789ABCDEF";
		         String result = "";  
		            String hex = "";  
		            for(int i=0;i<bytes.length;i++){
		            	//字节低4位  
		                char charAt2 = hexStr.charAt(bytes[i]&0x0F);
		                //字节高4位  
		            	char charAt = hexStr.charAt((bytes[i]&0xF0)>>4);
		                
		            	hex = String.valueOf(charAt);  
		                
		                hex += String.valueOf(charAt2);  
		                result +=hex+" ";  //这里可以去掉空格，或者添加0x标识符。
		            }  
		            return result;  
		     }
		
		 //16进制字符串转 字节数组
		 public static byte[] HexStrToBytes(String hexStr){
	         //如果字符串长度不为偶数，则追加0
	         if(hexStr.length() % 2 !=0){
	        	 hexStr = "0"+hexStr;
	         }
	         
	         byte[] b = new byte[hexStr.length() / 2];
	         byte c1, c2;
	         for (int y = 0, x = 0; x < hexStr.length(); ++y, ++x){
	             c1 = (byte)hexStr.charAt(x);
	             if (c1 > 0x60) c1 -= 0x57;
	             else if (c1 > 0x40) c1 -= 0x37;
	             else c1 -= 0x30;
	             c2 = (byte)hexStr.charAt(++x);
	             if (c2 > 0x60) c2 -= 0x57;
	             else if (c2 > 0x40) c2 -= 0x37;
	             else c2 -= 0x30;
	             b[y] = (byte)((c1 << 4) + c2);
	         }
	         return b;
	     }
		 
		 
		 
		 public static void main(String[] args) {
			//调用java自带的api
			
	        //测试十进制转16进制
		    //System.out.println("十进制10转16进制为:"+Integer.toHexString(10));
		    System.out.println("十进制10转16进制为:"+getToHexString(10));
		    
		    //测试十进制转2进制
		    //System.out.println("十进制10转二进制为"+Integer.toBinaryString(10));
		    System.out.println("十进制10转二进制为:"+getToBinaryString(10));
		     
	        //十进制字符串转 16进制
	        //System.out.println("字符串10转16进制为:"+Integer.toHexString(Integer.parseInt("10")));
	        System.out.println("字符串10转16进制为:"+getToHexString("10"));
	      
	        //十进制字符串转 2进制 
	        //System.out.println("字符串10转二进制为:"+Integer.toBinaryString(Integer.parseInt("10")));
	        System.out.println("字符串10转二进制为:"+getToBinaryString("10"));
	        
	         
	        //十六进制转化为十进制
	        //System.out.println(Integer.parseInt("AA",16));
	        System.out.println("16进制AA转10进制为:"+getHexToShi("AA"));
	       
	       
	        //十六进制转化为二进制
	        //System.out.println("十进制16转2进制为:"+Integer.toBinaryString(Integer.parseInt("AA",16)));
	        System.out.println("16进制AA转2进制为:"+getHexToBinaryString("AA"));
	        
	        //字节数组转 16进制字符串
	        byte[] b=new byte[6];
	        for (int i = 0; i < b.length; i++) {
	        	b[i]=(byte) i;
			}
	        
	        byte[] c={6,7,8,9,10,127};
	        System.out.println("字节数组转 16进制字符串:"+getBinaryToHexString(b));
	        System.out.println("字节数组转 16进制字符串:"+getBinaryToHexString(c));
	        
	        
	        //16进制字符串转 字节数组
	        byte[] hexStrToBytes = HexStrToBytes("0A0B0C0D0E0Faa");
	        String ss="";
	        for (byte d : hexStrToBytes) {
				//System.out.println(d);
				ss+=d;
			}
	        System.out.println("16进制字符串转 字节数组:"+ss);
	        
		}        
	}
