package allutils.randomUtils;

import org.apache.commons.lang.RandomStringUtils;  
import org.apache.commons.lang.math.RandomUtils;  
/** 
 * @author zhangliang 
 * @version RandomNumberUtil.java 2017-6-1
 */  
public class RandomNumberUtil {  
  
    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };  
  
    /** 
     * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位) 
     * @param bigNum 
     * 用户指定随机数据的位数 
     */  
    public static long randomLong(int bigNum) {  
       
    	if (bigNum >= 19 || bigNum <= 0){  
            throw new IllegalArgumentException("bigNum 必须在  1 和 18 之间(1<=bigNum<=18)");  
        }
        String s = RandomStringUtils.randomNumeric(bigNum - 1);  
      
        return Long.parseLong(getPrefix() + s);  
    }  
  
    /** 
     * 随机产生在指定位数之间的long型数据,位数包括两边的值,minNum<=maxNum 
     * @param minNum 
     * 用户指定随机数据的最小位数 minNum>=1 
     * @param maxNum 
     * 用户指定随机数据的最大位数 maxNum<=18 
     */  
    public static long randomLong(int minNum, int maxNum)  {  
        if (minNum > maxNum) {  
            throw new IllegalArgumentException("minNum > maxNum");  
        }  
        if (minNum <= 0 || maxNum >= 19) {  
            throw new IllegalArgumentException("minNum <=0 || maxNum>=19");  
        }  
        return randomLong(minNum + getNum(maxNum - minNum));  
    }  
  
    //取位数区间
    private static int getNum(int max) { 
    	
    	int nextInt = RandomUtils.nextInt(max + 1);
    	
        return nextInt;  
    }  
  
    /** 
     * 保证第一位不是零 
     * @return 
     */  
    private static String getPrefix() {  
    	//System.out.println(RandomUtils.nextInt(9));
        return prefix[RandomUtils.nextInt(9)] + "";  
    }  
    
    /**
     * 获取长度为18的随机数
     * @param args
     */
    private static String getRondom() {  
    	
    	Long nextLong = RandomUtils.nextLong();
    	
    	String substr = nextLong.toString().substring(0, 18);
    	
		return substr;
    } 
    
    public static void main(String[] args) {
    	//获取长度为18的随机字符串
    	System.out.println(getRondom());
    	System.out.println(getRondom().length());
    	//保证第一位不是零 
    	//System.out.println(RandomNumberUtil.getPrefix());
    	//取位数区间
    	//System.out.println(RandomNumberUtil.getNum(1000));
    	//随机产生最大为18位long型数据,最大输入18
    	System.out.println(RandomNumberUtil.randomLong(18));
    	//随机产生在指定位数之间的long型数据,位数包括两边的值,1~18之间,min不能大于max 
    	//System.out.println(RandomNumberUtil.randomLong(1, 2));
	}
}  