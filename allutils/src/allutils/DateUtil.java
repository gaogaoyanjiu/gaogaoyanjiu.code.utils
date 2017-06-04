package allutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: DateUtil.java
 * @Package cn.tdtk.common.util
 * @Description: 日期工具类
 * @author leixinming
 * @date 2012-5-4 上午11:04:17
 * @version V1.0
 */
public class DateUtil {
	/**
	 * 英文简写（默认）如：2012-01-01
	 */
	public final static String FORMAT_SHORT = "yyyy-MM-dd";
	/**
	 * 英文全称 如：2012-01-01 23:59:59
	 */
	public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	
	public final static String FORMAT_CHUAN = "yyyyMMddHHmmss";
	public final static String FORMAT_SYS= "yyyy/MM/dd HH:mm:ss";
	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	/**
	 * 中文简写 如：2012年01月01日
	 */
	public final static String FORMAT_SHORT_CN = "yyyy年MM月dd日";
	/**
	 * 中文全称 如：2012年01月01日 23时59分59秒
	 */
	public final static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
	/**
	 * 精确到毫秒的完整中文时间
	 */
	public final static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * @Title: getNow
	 * @Description: 获取当前时间并且以yyyy-MM-dd HH:mm:ss输出
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getNow() {
		return dateToString(new Date(), FORMAT_LONG);
	}
	public static Date getNowDate(String format){
		  return parse(getNow(), format);
	}
	
	public static String getNowChuan() {
		return dateToString(new Date(), FORMAT_CHUAN);
	}
	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(java.util.Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	* @Title: getDiffSecond
	* @Description: TODO(描述方法的作用)
	* @param  @param startTime
	* @param  @param endTime
	* @param  @return  获取秒数
	* @return long   
	* @throws
	*/
	public static long getDiffSecond(Date startTime , Date endTime){
		long diff = 0;
		diff = startTime.getTime()-endTime.getTime();
		diff = diff /1000;
		return new Long(diff).intValue();
	}
	/**
	* @Title: getDiffHour
	* @Description: TODO(描述方法的作用)
	* @param  @param startTime
	* @param  @param endTime
	* @param  @return  获取小时
	* @return long   
	* @throws
	*/
	public static long getDiffHour(Date startTime , Date endTime){
		long diff = 0;
		diff = startTime.getTime()-endTime.getTime();
		diff = diff / (1000 * 60 * 60);
		return new Long(diff).intValue();
	}
	/**
	 * @Title: formatLongDate
	 * @Description: 格式化为长日期格式：英文格式
	 * @param @param strDate
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date formatLongDate(String strDate) {
		return stringtoDate(strDate, FORMAT_LONG);
	}

	/**
	 * @Title: formatShortDate
	 * @Description: 格式化为短日期格式：英文格式
	 * @param @param strDate
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date formatShortDate(String strDate) {
		return stringtoDate(strDate, FORMAT_SHORT);
	}

	/**
	 * 两个日期相减
	 * 
	 * @param firstTime
	 * @param secTime
	 * @return 相减得到的秒数
	 */
	public static long timeSub(String firstTime, String secTime) {
		long first = stringtoDate(firstTime, FORMAT_LONG).getTime();
		long second = stringtoDate(secTime, FORMAT_LONG).getTime();
		return (second - first) / 1000;
	}
	/**
	  * 获取小时/分钟/秒
	  * 
	  * @param second
	  *            秒
	  * @return 包含小时、分钟、秒的时间字符串，例如3小时23分钟13秒。
	  */
	 public static String getHourMinute(long second) {
	  long hour = second / 60 / 60;
	  long minute = (second - hour * 60 * 60) / 60;
	  long sec = (second - hour * 60 * 60) - minute * 60;
	  return hour + "小时" + minute + "分钟" + sec + "秒";
	 }
	/**
	 * 把符合日期格式的字符串转换为日期类型
	 * 
	 * @param dateStr
	 * @return
	 */

	public static java.util.Date stringtoDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			// log.error(e);
			d = null;
		}
		return d;
	}
	
	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
