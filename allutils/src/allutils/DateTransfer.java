package allutils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * DateTransfer 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class DateTransfer {

	public static String yearMonthDate(int year, int month) {
		String yearmongth = "";
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			yearmongth = year + "-" + month + "-31";
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			yearmongth = year + "-" + month + "-30";
		} else if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				yearmongth = year + "-" + month + "-29";
			} else {
				yearmongth = year + "-" + month + "-28";
			}
		}
		return yearmongth;
	}

	public static String compareNow(Date date) {
		Date now = new Date();
		if (null == date || "".equals(date)) {
			return getStrDate(now);
		} else {
			if (date.getTime() > now.getTime()) {
				return getStrDate(now);
			} else {
				return getStrDate(date);
			}
		}
	}

	public static Date Str2Date(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// yyyy-MM-dd
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getStrDate(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// yyyy-MM-dd
																		// HH:mm:ss
		String dateString = formatter.format(d);
		return dateString;
	}

	public static String age2Birthday(int age) {
		Date year = new Date();
		SimpleDateFormat yearformatter = new SimpleDateFormat("yyyy");// yyyy-MM-dd
																		// HH:mm:ss
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");// yyyy-MM-dd
																	// HH:mm:ss
		String yearString = yearformatter.format(year);
		String dateString = formatter.format(year);
		int birthyYear = Integer.valueOf(yearString);

		return (birthyYear - age) + "-" + dateString;
	}

	public static String year2Date(int year) {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");// yyyy-MM-dd
																	// HH:mm:ss
		String dateString = formatter.format(today);
		return year + "-" + dateString;
	}

	public static int getYear(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String year = formatter.format(date);

		return Integer.valueOf(year);
	}

	public static Date strToDate(String str) throws ParseException {
		System.out.println(str);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
		DateFormat df = DateFormat.getDateInstance();
		// Date date = null;
		Date d = df.parse(str);
		return d;
	}

	public static Date strToDate(Long str) throws ParseException {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(str);
		return date;
	}

	public static Date getNow() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sdf.parse(sdf.format(new Date()));
	}

	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sdf.format(new Date());
	}

	public static String getSeriesNumber(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String series = formatter.format(date);
		return series;
	}

	/**
	 * 
	 * 产生30位随机主键
	 * 
	 * @param date
	 * @return
	 */
	public static String getSeriesId(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String series = formatter.format(date);
		series = series + "000000000000" + (int) (Math.random() * 9000 + 1000);
		return series;
	}

	public static String getTimeMillion() {
		StringBuffer temp = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		temp.append(sdf.format(new Date()));
		temp.append((int) (Math.random() * 1000000.0));
		return temp.toString();

	}

	public static long date2long(Date date1, Date date2) {
		return ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)) + 1;
	}

	public static Double StringToDouble(String cost) {
		Double costValue = 0d;
		try {
			if (cost.contains(",")) {
				costValue = Double.parseDouble(cost.replace(",", ""));
			} else {
				costValue = Double.parseDouble(cost);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return costValue;
	}

	public static String DoubleToString(Double cost) {
		String costValue = "";
		try {
			DecimalFormat format = new DecimalFormat("#,##0.00");
			costValue = format.format(cost);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return costValue;

	}

	public static String LongToString(Long cost) {
		String costValue = "";
		try {
			DecimalFormat format = new DecimalFormat("#,###");
			costValue = format.format(cost);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return costValue;

	}

	public static Date yesteday(int deadline) {
		int i = 0;
		Calendar cal = Calendar.getInstance();
		// 日期的DATE减去10 就是往后推10 天 同理 +10 就是往后推十天
		switch (deadline) {
		case 1:
			i = 0;
			break;
		case 2:
			i = 15;
			break;
		case 3:
			i = 30;
			break;
		case 4:
			i = 40;
			break;
		case 5:
			i = 90;
			break;
		default:
			i = 0;
		}
		cal.add(Calendar.DATE, i);
		return cal.getTime();
	}

	/*
	 * @param 时间推后一下
	 */
	public static Date daydelayone(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static Date yesaddteday(int deadline) {

		Calendar cal = Calendar.getInstance();
		// 日期的DATE减去10 就是往后推10 天 同理 +10 就是往后推十天

		cal.add(Calendar.DATE, deadline);
		return cal.getTime();
	}

	public static int datetoyearnum(Date date1, Date date2) {
		int yearnum = 1;
		Long yearday = DateTransfer.date2long(date2, date1);
		if (yearday <= 365) {
			yearnum = 1;
		} else if (yearday > 365 && yearday <= 1095) {
			yearnum = 2;
		} else if (yearday > 1095 && yearday <= 1825) {
			yearnum = 3;
		} else if (yearday > 1825 && yearday <= 3650) {
			yearnum = 4;
		} else if (yearday > 3650) {
			yearnum = 5;
		}
		return yearnum;
	}

	public static String dateMinusOneday(String stdate) {
		String minusOne = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(stdate);
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			minusOne = formatter.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minusOne;

	}

	@SuppressWarnings("deprecation")
	public static int hours(Date sdate, Date edate) {
		int hos = sdate.getHours() - edate.getHours();
		return hos;
	}

	public static String filename(String id) {
		StringBuffer temp = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		temp.append(sdf.format(new Date()));
		temp.append((int) (Math.random() * 10000.0));
		return temp.toString() + "-" + id;
	}

	/**
	 * 获取两个日期间隔天数
	 * 
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static long getDaysBetweenDate(Date sDate, Date eDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(sDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(eDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (toCalendar.getTime().getTime() - fromCalendar.getTime()
				.getTime()) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 计算两个日期的时间间隔(秒)
	 * 
	 * @param stime
	 * @return
	 * @throws ParseException
	 */
	public static long dateSeconds(Date stime, Date etime)
			throws ParseException {
		return (etime.getTime() - stime.getTime()) / 1000;
	}
	/**
	 * 计算两个日期的时间间隔(分钟)
	 * @param parse
	 * @param date
	 * @return
	 */
	public static double getIntervalOfMinute(Date stime, Date etime) {
		return (etime.getTime() - stime.getTime()) / (1000*60);
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(DateTransfer.getStrDate("Wed Sep 16 00:00:00 CST 2009"));
		// System.out.println(strToDate("2010-01-01"));
		System.out.println(Str2Date("2010-01-01"));
		// System.out.println(DateTransfer.getNow());

		// System.out.println(age2Birthday(20));
		// System.out.println(year2Date(2008));
		// System.out.println(date2long(new Date(),strToDate("2010-10-10")));
		// System.out.println(StringToDouble("999999993,500,00.00"));
		// System.out.println(StringToDouble("1231223.568"));
		// System.out.println(DoubleToString(StringToDouble("1231223.568")));
		/*
		 * System.out.println(DateTransfer.hours(new
		 * Date(),strToDate("2011-3-7")));
		 * 
		 * System.out.println((31449600000l/(24*60*60*1000))/365);
		 * 
		 * System.out.println(365*3); System.out.println(365*5);
		 * System.out.println(365*10);
		 */

		// System.out.println(DateTransfer.yesteday(2));
		// System.out.println(DateTransfer.Str2Date("1200-01-01").before(DateTransfer.Str2Date("1900-01-01")));
		// System.out.println(DateTransfer.getStrDate(DateTransfer.yesaddteday(-2)));
		// System.out.println(DateTransfer.getSeriesNumber(new Date()));
		// System.out.println(getTimeMillion());
		// System.out.println(getNowTime());
		// System.out.println(DateTransfer.yearMonthDate(2011,2));
	}

	
	/**
	 * 获取与当前时间的时间间隔(分钟)
	 * 
	 * @param time
	 * @return
	 */
	public static long getIntervalOfMinute(Date time) {
		if (time == null) {
			return 100;
		}
		return (long) ((new Date().getTime() - time.getTime()) / (1000 * 60));
	}
	
}
