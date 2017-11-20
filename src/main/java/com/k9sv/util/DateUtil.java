package com.k9sv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final String DATEFORMAT = "yyyy-MM-dd";
	public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年

	/**
	 * 
	 * 取得指定分隔符分割的年月日的日期对象.
	 * 
	 * @param argsDate
	 *            格式为"yyyy-MM-dd"
	 * @param split时间格式的间隔符
	 *            ，例如“-”，“/”，要和时间一致起来。
	 * @return 一个java.util.Date()类型的对象
	 */
	public static Date getDateObj(String argsDate, String split) {
		try {
			String[] temp = argsDate.split(split);
			int year = new Integer(temp[0]).intValue();
			int month = new Integer(temp[1]).intValue();
			int day = new Integer(temp[2]).intValue();
			return getDateObj(year, month, day);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parse(String dateString, String format) {
		try {
			if (format == null) {
				format = DATEFORMAT;
			} else if ("0".equals(format)) {
				format = DATETIMEFORMAT;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateString);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * 取得指定年月日的日期对象.
	 * 
	 * @param year年
	 * @param month月注意是从1到12
	 * @param day
	 *            日
	 * @return 一个java.util.Date()类型的对象
	 */
	public static Date getDateObj(int year, int month, int day) {
		Calendar c = new GregorianCalendar();
		c.set(year, month - 1, day);
		return c.getTime();
	}

	/**
	 * 取几年前或几年后
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date getDateObj(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		Date end = calendar.getTime();
		return end;
	}

	/**
	 * 几天后的日期
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static String getFormatDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String getFormatDateTime(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}

	}

	public static Date getNow() {
		Date now = new Date();
		return now;
	}

	public static String getSubTimeString(Date date) {
		StringBuffer str = new StringBuffer("");
		Long _now = System.currentTimeMillis();
		Long d = date.getTime();

		long l = _now - d;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		if (day == 0) {
			if (hour > 0) {
				str.append(hour + "小时前");
			} else {
				if (min > 0) {
					str.append(min + "分钟前");
				} else {
					str.append(s + "秒前");
				}
			}
		} else if (day == 1) {
			str.append("昨天 ");
			str.append(DateUtil.getFormatDateTime(date, "HH:mm"));
		} else if (day == 2) {
			str.append("前天 ");
			str.append(DateUtil.getFormatDateTime(date, "HH:mm"));
		} else if (day > 2) {
			str.append(DateUtil.getFormatDateTime(date, "yyyy-MM-dd HH:mm"));
		}
		return str.toString();

	}

	/**
	 * <pre>
	 * 从生日计算年龄：
	 * yearOfAge: 年龄超过1年的，计算满几年；
	 * monthOfAge: 年龄不满1年的，计算满几个月；
	 * dayOfAge: 年龄不满1月的，计算满几天；
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public static String calAge(Date birthday) throws Exception {

		// Date birthday = parse(date, null);

		Calendar cal = Calendar.getInstance();

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		if (yearNow > yearBirth) {
			int r = yearNow - yearBirth;
			return r + "岁";
		}
		if (monthNow > monthBirth) {
			int r = monthNow - monthBirth;
			return r + "个月";
		}
		if (dayOfMonthNow > dayOfMonthBirth) {
			int r = dayOfMonthNow - dayOfMonthBirth;
			return r + "天";
		}
		return "刚出生";
	}

	public static String getHowOld(String date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = null;
		try {
			newdate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date curDate = new Date(System.currentTimeMillis());
		long diff = curDate.getTime() - newdate.getTime();

		return getTimeFormatText(diff, "howOld");
	}

	private static String getTimeFormatText(long diff, String string) {
		long r = 0;
		if (diff > year) {
			r = (diff / year);
			return r + "岁";
		}
		if (diff > month) {
			r = (diff / month);
			return r + "个月";
		}
		if (diff > day) {
			r = (diff / day);
			return r + "天";
		}
		return "刚出生";

	}
}
