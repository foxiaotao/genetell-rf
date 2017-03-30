package com.core.gene.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

final public class TimeUtil {
	private static List<Calendar> holidayList;
	private static boolean holidayFlag;

	private static Logger logger = Logger.getLogger(TimeUtil.class);

	final static public SimpleDateFormat Formater_Time14 = new SimpleDateFormat("HHmmss");

	final static public SimpleDateFormat Formater_Date14 = new SimpleDateFormat("yyyyMMdd");

	final static public SimpleDateFormat Formater_Time19 = new SimpleDateFormat("HH:mm:ss");

	final static public SimpleDateFormat Formater_Date19 = new SimpleDateFormat("yyyy-MM-dd");

	final static private String[] DaysOfWeek = new String[] { "日", "一", "二", "三", "四", "五", "六" };

	final static public SimpleDateFormat Formater_DateTime14 = new SimpleDateFormat("yyyyMMddHHmmss");

	final static public SimpleDateFormat Formater_DateTime19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	final static private Hashtable<String, SimpleDateFormat> Formats = new Hashtable<String, SimpleDateFormat>();

	/**
	 * 计算指定时间处于每日5分钟的第几个区间(0~287)<br>
	 * 当前日期和时间,yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	static public int calcRange(String time) {
		Date datec = parseDateTime(time);
		return calcRange(datec);
	}

	/**
	 * 计算指定时间处于每日5分钟的第几个区间(0~287)<br>
	 * 当前日期和时间,Date
	 * 
	 * @return
	 */
	static public int calcRange(Date time) {
		Calendar date = Calendar.getInstance();
		date.setTime(time);
		int seconds = date.get(Calendar.HOUR_OF_DAY) * 3600;
		seconds += date.get(Calendar.MINUTE) * 60;
		seconds += date.get(Calendar.SECOND);
		int a = seconds / 300;
		a += seconds % 300 > 0 ? 1 : 0;
		return a;
	}

	/**
	 * 计算指定时间处于每日5分钟的第几个区间(0~287)<br>
	 * 当前基于2000年秒数
	 * 
	 * @param time
	 * @return
	 */
	static public int calcRange(int time2000) {
		return calcRange(TimeUtil.to2000Date(time2000));
	}

	/**
	 * 当前日期,yyyy-MM-dd
	 */
	static synchronized public String getDate() {
		return Formater_Date19.format(new Date());
	}

	static synchronized public String getDate(Date date) {
		return Formater_Date19.format(date);
	}

	/**
	 * 36进制当前日期
	 */
	// static public String curTime(){
	// Calendar calendar=Calendar.getInstance();
	// StringBuffer sb=new StringBuffer();
	// sb.append(MathUtil.convt.cvt_int(calendar.get(Calendar.YEAR)%100,36));
	// sb.append(MathUtil.convt.cvt_int(calendar.get(Calendar.MONTH)+1,36));
	// sb.append(MathUtil.convt.cvt_int(calendar.get(Calendar.DATE),36));
	// return sb.toString();
	// }

	/**
	 * 当前时间,HH:mm:ss
	 */
	static synchronized public String getTime() {
		return Formater_Time19.format(new Date());
	}

	/**
	 * 当前日期和时间,yyyy-MM-dd HH:mm:ss
	 */
	static synchronized public String getDateTime() {
		return Formater_DateTime19.format(new Date());
	}

	/**
	 * 当前日期,yyyyMMdd
	 */
	static synchronized public String getDateEx() {
		return Formater_Date14.format(new Date());
	}

	/**
	 * 指定日期,yyyyMMdd
	 */
	static public synchronized String getDateEx(Date date) {
		return Formater_Date14.format(date);
	}

	/**
	 * 格式,yyyyMMddHHmmss
	 */
	static synchronized public Date parseDateEx(String date) {
		try {
			return Formater_Date14.parse(date);
		} catch (Exception ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 格式,yyyy-MM-dd HH:mm:ss
	 */
	static synchronized public Date parseDateEx19(String date) {
		try {
			return Formater_DateTime19.parse(date);
		} catch (Exception ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 格式，yyyy-MM-dd
	 */
	static synchronized public Date parseDate19(String date) {
		try {
			return Formater_Date19.parse(date);
		} catch (Exception ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 当前时间,HHmmss
	 */
	static synchronized public String getTimeEx() {
		return Formater_Time14.format(new Date());
	}

	/**
	 * 当前日期和时间,yyyyMMddHHmmss
	 */
	static synchronized public String getDateTimeEx() {
		return Formater_DateTime14.format(new Date());
	}

	/**
	 * 指定格式的当前日期和时间
	 */
	static public String getDateTime(String format) {
		try {
			synchronized (Formats) {
				if (!Formats.containsKey(format)) {
					Formats.put(format, new SimpleDateFormat(format));
				}
			}
			return Formats.get(format).format(new Date());
		} catch (Exception ex) {
			throw new RuntimeException("时间格式(" + format + ")错误", ex);
		}
	}

	/**
	 * 当前日期的相对days天
	 */
	static public Date nextDate(int days) {
		return nextDate(new Date(), days);
	}

	/**
	 * 指定日期的相对days天
	 */
	static public Date nextDate(Date date, int days) {
		long oned = 24 * 60 * 60 * 1000;
		long time = date.getTime();
		return new Date(time + days * oned);
	}

	/**
	 * 一天的毫秒(ms)数
	 */
	static public int oneday() {
		return 24 * 60 * 60 * 1000;
	}

	/***
	 * yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getMinSeconds() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 年中第几天
	 */
	static public int dayOfYear() {
		return dayOfYear(Calendar.getInstance());
	}

	static public int dayOfYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(date.getTime());
		return cl.get(Calendar.DAY_OF_YEAR);
	}

	static public int dayOfYear(Calendar date) {
		return date.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 月中第几天
	 */
	static public int dayOfMonth() {
		return dayOfMonth(Calendar.getInstance());
	}

	static public int dayOfMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(date.getTime());
		return cl.get(Calendar.DAY_OF_MONTH);
	}

	static public int dayOfMonth(Calendar date) {
		return date.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 周中第几天
	 */
	static public int dayOfWeek() {
		return dayOfWeek(Calendar.getInstance());
	}

	static public int dayOfWeek(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(date.getTime());
		return cl.get(Calendar.DAY_OF_WEEK);
	}

	static public int dayOfWeek(Calendar date) {
		return date.get(Calendar.DAY_OF_WEEK);
	}

	static public int year() {
		Calendar cl = Calendar.getInstance();
		return cl.get(Calendar.YEAR);
	}

	static public int month() {
		Calendar cl = Calendar.getInstance();
		return cl.get(Calendar.MONTH) + 1;
	}

	/**
	 * 星期几
	 */
	static public String weekOfDay() {
		return weekOfDay(Calendar.getInstance());
	}

	@SuppressWarnings("deprecation")
	static public String weekOfDay(Date date) {
		return DaysOfWeek[date.getDay()];
	}

	static public String weekOfDay(Calendar date) {
		return DaysOfWeek[date.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 日期和时间,yyyy-MM-dd HH:mm:ss
	 */
	static synchronized public String getDateTime(Date date) {
		return Formater_DateTime19.format(date);
	}

	/**
	 * 日期和时间,yyyy-MM-dd HH:mm:ss
	 */
	static synchronized public String getDateTime(Calendar date) {
		return Formater_DateTime19.format(date.getTime());
	}

	/**
	 * 日期和时间,yyyyMMddHHmmss
	 */
	static synchronized public String getDateTimeEx(Date date) {
		return Formater_DateTime14.format(date);
	}

	/**
	 * 日期和时间,yyyyMMddHHmmss
	 */
	static synchronized public String getDateTimeEx(Calendar date) {
		return Formater_DateTime14.format(date.getTime());
	}

	/**
	 * 日期和时间
	 */
	static public String getDateTime(Date date, String format) {
		try {
			synchronized (Formats) {
				if (!Formats.containsKey(format)) {
					Formats.put(format, new SimpleDateFormat(format));
				}
			}
			return Formats.get(format).format(date);
		} catch (Exception ex) {
			throw new RuntimeException("日期时间格式(" + format + ")错误", ex);
		}
	}

	static public String getDateTime(Calendar date, String format) {
		try {
			synchronized (Formats) {
				if (!Formats.containsKey(format)) {
					Formats.put(format, new SimpleDateFormat(format));
				}
			}
			SimpleDateFormat formater = Formats.get(format);
			return formater.format(date.getTime());
		} catch (Exception ex) {
			throw new RuntimeException("日期时间格式(" + format + ")错误", ex);
		}
	}

	/**
	 * 将字符串转换成日期,yyyy-MM-dd HH:mm:ss
	 */
	static synchronized public Date parseDateTime(String datetime) {
		try {
			return Formater_DateTime19.parse(datetime);
		} catch (ParseException ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 将字符串转换成日期,yyyyMMddHHmmss
	 */
	static synchronized public Date parseDateTimeEx(String datetime) {
		try {
			return Formater_DateTime14.parse(datetime);
		} catch (ParseException ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 将字符串转换成日期
	 */
	static synchronized public Date parseDateTime(String datetime, String format) {
		try {
			synchronized (Formats) {
				if (!Formats.containsKey(format)) {
					Formats.put(format, new SimpleDateFormat(format));
				}
			}
			return Formats.get(format).parse(datetime);
		} catch (ParseException ex) {
			throw new RuntimeException("解析时间错误", ex);
		}
	}

	/**
	 * 时间前推或后推分钟,其中addTime表示分钟.
	 * 
	 * @param format
	 *            : "yyyy-MM-dd HH:mm:ss"
	 * @param srcTime
	 *            : "2011-03-08 12:00:00"
	 * @param format
	 *            : 5
	 * @return
	 */
	static public final Date getPreTime(Date srcDate, int addTime) {
		Date destDate = null;
		try {
			destDate = new Date();
			long Time = (srcDate.getTime() / 1000) + addTime * 60;
			destDate.setTime(Time * 1000);
		} catch (Exception e) {
		}
		return destDate;
	}

	/**
	 * 1970.01.01 00.00.00基准时间(单位=秒)
	 */
	static public Date toDate(int time) {
		return new Date((long) (time) * 1000);
	}

	/**
	 * 1970.01.01 00.00.00基准时间 yyyy-MM-dd HH:mm:ss(单位=秒)
	 */
	static public String toDateString(int time) {
		return getDateTime(new Date((long) (time) * 1000));
	}

	static public String toDateString(long time) {
		return getDateTime(new Date((long) (time) * 1000));
	}

	/**
	 * 1970.01.01 00.00.00基准时间(单位=秒)
	 */
	static public int toTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 1970.01.01 00.00.00基准时间(单位=秒)
	 */
	static public int toTime(Date date) {
		return (int) (date.getTime() / 1000);
	}

	/**
	 * 2000年时间基数
	 */
	static final public int Time2000 = 946656000;

	/**
	 * 2000.01.01 00.00.00基准时间(单位=秒)
	 */
	static public Date to2000Date(int time) {
		return new Date((long) (time + Time2000) * 1000);
	}

	static public Date to2000Date(long time) {
		return new Date((long) (time + Time2000) * 1000);
	}

	static public long to2000MM() {
		return (((new Date()).getTime() / 1000) - 946656000) / 60;
	}

	/**
	 * 2000.01.01 00.00.00基准时间 yyyy-MM-dd HH:mm:ss(单位=秒)
	 */
	static public String to2000DateString(int time) {
		return getDateTime(new Date((long) (time + Time2000) * 1000));
	}

	/**
	 * 2000.01.01 00.00.00基准时间(单位=秒)
	 */
	static public int to2000Time() {
		return (int) (System.currentTimeMillis() / 1000) - Time2000;
	}

	/**
	 * 2000.01.01 00.00.00基准时间(单位=秒)
	 */
	static public long to2000Time(Date date) {
		long time = (date.getTime() / 1000) - Time2000;
		return time;
	}

	static public long to2000Time(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return 0;
		}
		Date date = TimeUtil.parseDateEx19(dateStr);
		long time = (date.getTime() / 1000) - Time2000;
		return time;
	}

	/**
	 * 格式转化
	 */
	static public String convert(String time, String format) {
		return getDateTime(parseDateTime(time), format);
	}

	/**
	 * 格式转化
	 */
	static public String convert(String time, String oformat, String nformat) {
		return getDateTime(parseDateTime(time, oformat), nformat);
	}

	static public long timeDiff(String time) {
		return parseDateTime(time).getTime() - new Date().getTime();
	}

	static public long timeDiff(String time, String format) {
		return parseDateTime(time, format).getTime() - new Date().getTime();
	}

	static public long timeDiff(String atime, String btime, String format) {
		return parseDateTime(atime, format).getTime() - parseDateTime(btime, format).getTime();
	}

	static public long timeDiff(String atime, String aformat, String btime, String bformat) {
		return parseDateTime(atime, aformat).getTime() - parseDateTime(btime, bformat).getTime();
	}

	static public long timeDiffFormatMinutes(String etime, String stime) {
		return timeDiff(etime, stime, "HH:mm") / 60000;
		// TimeUtil.timeDiff("00:05", "00:15","HH:mm")/60000

	}

	static public long timeDiffFormatDay(String etime, String stime) {
		return timeDiff(etime, stime, "yyyy-MM-dd") / (60000 * 60 * 24);
		// TimeUtil.timeDiff("00:05", "00:15","HH:mm")/60000

	}

	static public long timeDiff(Date startDate, Date endDate) {
		long t = (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
		return t;
	}
	static public long timeDiffSecond(Date startDate, Date endDate) {
		long t = (endDate.getTime() - startDate.getTime()) / 1000;
		return t;
	}

	static public int monthDiff(Date startDate, Date endDate) {
		int result = 0;
		try {
			// String d1 = "2012-01-12";
			// String d2 = "2011-11-11";
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(endDate);
			int year1 = c.get(Calendar.YEAR);
			int month1 = c.get(Calendar.MONTH);

			c.setTime(startDate);
			int year2 = c.get(Calendar.YEAR);
			int month2 = c.get(Calendar.MONTH);

			if (year1 == year2) {
				result = month1 - month2;
			} else {
				result = 12 * (year1 - year2) + month1 - month2;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public static long diffDays(Date date1, Date date2) {
		long quot = 0;
		try {
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return quot;
	}

	public int monthDiff(String startDate, String endDate) {
		int result = 0;

		try {
			// String d1 = "2012-01-12";
			// String d2 = "2011-11-11";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(endDate));
			int year1 = c.get(Calendar.YEAR);
			int month1 = c.get(Calendar.MONTH);

			c.setTime(sdf.parse(startDate));
			int year2 = c.get(Calendar.YEAR);
			int month2 = c.get(Calendar.MONTH);

			if (year1 == year2) {
				result = month1 - month2;
			} else {
				result = 12 * (year1 - year2) + month1 - month2;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 是否为周未
	 * 
	 * @param date
	 * @return
	 */
	static public boolean isWeekend(Date date) {
		boolean flag = false;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int w = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (w == 6 || w == 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 增加工作日
	 * 
	 * @param date
	 * @param adddays
	 * @return
	 */
	public static Date getWorkDate(Date date, int adddays, boolean isholiday) {
		Calendar src = Calendar.getInstance();
		src.setTime(date);
		holidayFlag = false;
		for (int i = 0; i < adddays; i++) {
			// 把源日期加一天
			src.add(Calendar.DAY_OF_MONTH, 1);
			holidayFlag = checkHoliday(src, isholiday);
			if (holidayFlag) {
				i--;
			}
		}
		return src.getTime();
	}

	/**
	 * 校验指定的日期是否在节日列表中 具体节日包含哪些,可以在HolidayMap中修改
	 * 
	 * @param src
	 *            要校验的日期(源)
	 */
	public static boolean checkHoliday(Calendar src, boolean checkHoliday) {
		boolean result = false;
		if (checkHoliday && holidayList == null) {
			initHolidayList();
		}
		// 先检查是否是周六周日(有些国家是周五周六)
		if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		if (checkHoliday) {
			for (Calendar c : holidayList) {
				if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH) && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 初始化节日List,如果需要加入新的节日,请在这里添加 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
	 * 注:年份可以随便写,因为比的时候只比月份和天
	 * 
	 */
	private static void initHolidayList() {
		holidayList = new ArrayList<Calendar>();

		// 五一劳动节
		Calendar may1 = Calendar.getInstance();
		may1.set(Calendar.MONTH, Calendar.MAY);
		may1.set(Calendar.DAY_OF_MONTH, 1);
		holidayList.add(may1);

		Calendar may2 = Calendar.getInstance();
		may2.set(Calendar.MONTH, Calendar.MAY);
		may2.set(Calendar.DAY_OF_MONTH, 2);
		holidayList.add(may2);

		Calendar may3 = Calendar.getInstance();
		may3.set(Calendar.MONTH, Calendar.MAY);
		may3.set(Calendar.DAY_OF_MONTH, 3);
		holidayList.add(may3);

		Calendar h3 = Calendar.getInstance();
		h3.set(2000, 1, 1);
		holidayList.add(h3);

		Calendar h4 = Calendar.getInstance();
		h4.set(2000, 12, 25);
		holidayList.add(h4);

		// 中国母亲节：五月的第二个星期日
		Calendar may5 = Calendar.getInstance();
		// 设置月份为5月
		may5.set(Calendar.MONTH, Calendar.MAY);
		// 设置星期:第2个星期
		may5.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
		// 星期日
		may5.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// System.out.println(may5.getTime());

		holidayList.add(may5);
	}

	/**
	 * 当前时间加减分钟
	 * 
	 * @param date
	 *            当前时间
	 * @param minus
	 *            加减分钟
	 * @return
	 */
	static public Date minMinus(Date date, int minus) {
		Date afterDate = new Date(date.getTime() + minus * 60000);
		return afterDate;
	}

	/**
	 * 
	 * 得到指定月的天数
	 * 
	 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, false);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}

	/**
	 * 
	 * @Title: getQBeginDate
	 * @Description: 计算出指定日期所在季度的第一天（01-01、04-01、07-01、10-01），
	 *               返回日期格式为"yyyy-MM-dd"
	 * @param date
	 *            调用方指定的日期
	 * @return 季度的第一天
	 */
	public static Date getQBeginDate(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);

		int currentMonth = currentDate.get(Calendar.MONTH) + 1;
		Date qBeginDate = null;

		// 第一季度
		if (currentMonth >= 1 && currentMonth <= 3) {
			currentDate.set(Calendar.MONTH, 0);
		}
		// 第二季度
		else if (currentMonth >= 4 && currentMonth <= 6) {
			currentDate.set(Calendar.MONTH, 3);
		}
		// 第三季度
		else if (currentMonth >= 7 && currentMonth <= 9) {
			currentDate.set(Calendar.MONTH, 6);
		}
		// 第四季度
		else if (currentMonth >= 10 && currentMonth <= 12) {
			currentDate.set(Calendar.MONTH, 9);
		}

		// 季度的第一天
		currentDate.set(Calendar.DATE, 1);

		// 格式化，日期只保留到年月日
		qBeginDate = TimeUtil.parseDate19(TimeUtil.getDate(currentDate.getTime()));

		return qBeginDate;
	}

	/**
	 * 
	 * @Title: getQEndDate
	 * @Description: 计算出指定日期所在季度的最后一天（03-31、06-30、09-30、12-31），
	 *               返回日期格式为"yyyy-MM-dd"
	 * @param date
	 *            调用方指定的日期
	 * @return 季度的最后一天
	 */
	public static Date getQEndDate(Date date) {

		Calendar currentDate = Calendar.getInstance();
		int currentMonth = currentDate.get(Calendar.MONTH) + 1;

		Date qEndDate = null;

		// 第一季度
		if (currentMonth >= 1 && currentMonth <= 3) {
			currentDate.set(Calendar.MONTH, 2);
			currentDate.set(Calendar.DATE, 31);
		}
		// 第二季度
		else if (currentMonth >= 4 && currentMonth <= 6) {
			currentDate.set(Calendar.MONTH, 5);
			currentDate.set(Calendar.DATE, 30);
		}
		// 第三季度
		else if (currentMonth >= 7 && currentMonth <= 9) {
			currentDate.set(Calendar.MONTH, 8);
			currentDate.set(Calendar.DATE, 30);
		}
		// 第四季度
		else if (currentMonth >= 10 && currentMonth <= 12) {
			currentDate.set(Calendar.MONTH, 11);
			currentDate.set(Calendar.DATE, 31);
		}

		// 格式化，日期只保留到年月日
		qEndDate = TimeUtil.parseDate19(TimeUtil.getDate(currentDate.getTime()));

		return qEndDate;
	}
	/**
	 * 判断当前日期是一年中的第几季
	 * @param date
	 * @return
	 */
	public static int getQuarter(String date) {
		int month = getMonth(date);
		int quarter;
		switch (month) {
		case 1:
		case 2:
		case 3:
			quarter = 1;
			break;
		case 4:
		case 5:
		case 6:
			quarter = 2;
			break;
		case 7:
		case 8:
		case 9:
			quarter = 3;
			break;
		case 10:
		case 11:
		case 12:
			quarter = 4;
			break;
		default:
			quarter = 0;
			break;
		}

		return quarter;
	}


	/**
	 * 得到一天是一年的第几个月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public static void main(String args[]) {
		// Date date = new Date();
		// int day =1 ;
		// Date dd = TimeUtil.getWorkDate(TimeUtil.parseDate19("2016-08-13"),
		// day-1,false);
		// System.out.println(TimeUtil.getDateEx(dd));
		String a = "a\\b\\s\\d";
		System.out.println(a.replaceAll("\\\\", "/"));
	}

}
