package com.example.demo.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 时间处理工具类<br>
 *
 * @author haiweizhu
 */
public class DateFormatUtils {
    private  static Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 归属年份
     */
    public static final String BELONG_YEAR = "yyyy";

    /**
     * 归属日
     */
    public static final String BELONG_DAY = "d";


    /**
     * 订单时间格式
     */
    public static final String CASE_DATE_PATTERN = "yyyyMMdd";

    /**
     * 默认时间格式 年-月
     */
    public static final String DEFAULT_DATE_YEAR_MONTH = "yyyy-MM";

    public static final String DEFAULT_MONTH = "MM 月";

    public static final String SEPARATE_MONTH = "M";
    /**
     * 当天时间，二十四小时制
     */
    public static final String DEFAULT_TODAY_PATTERN = "HH:mm";

    /**
     * 昨天格式
     */
    public static final String DEFUALT_YESTERDAY_PATTERN = "昨天 HH:mm";

    /**
     * 月日格式
     */
    public static final String DEFAULT_MONTH_AND_DAY = "M月d日 HH:mm";
    /**
     * 年月日格式
     */
    public static final String DEFAULT_YEAR_MONTH_DAY = "yyyy-MM-dd HH:mm";

    /**
     * 时分秒
     */
    public static final String DEFAULT_HOUR_MIN_SEC = "HH:mm:ss";

    /**
     * MONDAY
     */
    public static final String MONDAY = "星期一";
    /**
     * TUESDAY
     */
    public static final String TUESDAY = "星期二";
    /**
     * WEDNESDAY
     */
    public static final String WEDNESDAY = "星期三";
    /**
     * THURSDAY
     */
    public static final String THURSDAY = "星期四";
    /**
     * FRIDAY
     */
    public static final String FRIDAY = "星期五";
    /**
     * SATURDAY
     */
    public static final String SATURDAY = "星期六";
    /**
     * SUNDAY
     */
    public static final String SUNDAY = "星期日";

    /**
     * 12月份
     */
    public static final String JAN = "1";
    public static final String FEB = "2";
    public static final String MAR = "3";
    public static final String APR = "4";
    public static final String MAY = "5";
    public static final String JUN = "6";
    public static final String JUL = "7";
    public static final String AUG = "8";
    public static final String SEP = "9";
    public static final String OCT = "10";
    public static final String NOV = "11";
    public static final String DECE = "12";

    /**
     * 日期不能为空提示信息
     */
    private static final String DATE_NULL_MSG = "The date must not be null";

    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_PATTERN2 = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_TIME_PATTERN3 = "yyyy/MM/dd";
    public static final String DEFAULT_TIME_PATTERN4 = "yyyy/MM/dd HH:mm:ss.S";
    public static final String DEFAULT_TIME_PATTERN5 = "yyyy-MM-dd";

    /**
     * 私有化构造函数
     */
    private DateFormatUtils() {
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(date);
    }

    public static Date parse(String date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 处理时间戳为格式化字符串<br>
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String format(long timestamp, String pattern) {
        Date date = new Date(timestamp);
        return format(date, pattern);
    }

    /**
     * 处理时间为格式化字符串<br>
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 解析时间字符串<br>
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date parse(String source, String pattern) {
        if (null == source) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断是否是周末
     *
     * @param StringDate
     * @return
     */
    public static boolean isWeekend(String StringDate) {
        boolean isWeekend = false;
        Date date = parse(StringDate, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int today = cal.get(Calendar.DAY_OF_WEEK);
        if (today == Calendar.SATURDAY || today == Calendar.SUNDAY) {
            isWeekend = true;
        }
        return isWeekend;
    }

    /**
     * 判断日期先后（firstDay是否大于secondDay）
     *
     * @param firstDay
     * @param secondDay
     * @return
     */
    public static boolean compareDate(String firstDay, String secondDay) {
        Date firstDate = parse(firstDay, DEFAULT_DATE_PATTERN);
        Date secondDate = parse(secondDay, DEFAULT_DATE_PATTERN);
        if (firstDate.getTime() > secondDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断时间先后（firstTime是否大于secondTime）
     * @return
     */
    public static boolean compareTime(String firstTime, String secondTime) {
        Date firTime = parse(firstTime, DEFAULT_HOUR_MIN_SEC);
        Date secTime = parse(secondTime, DEFAULT_HOUR_MIN_SEC);
        if (firTime.getTime() > secTime.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据日期获取当月第一天日期
     *
     * @param nowday
     * @return
     */
    public static String getFirstDayOfMonth(String nowday) {
        Date date = parse(nowday, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format(cal.getTime());
        return firstDay;
    }


    /**
     * 根据日期获取该周的周一日期
     *
     * @param StringDate 字符串"2018-04-20"
     * @return 字符串"2018-04-16"
     */
    public static String getMondayThisWeek(String StringDate) {
        Date date = parse(StringDate, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = format(cal.getTime());
        return monday;
    }

    /**
     * 根据日期获取上一周的周一日期
     *
     * @param StringDate 当前日期，字符串"2018-04-20"
     * @return 字符串"2018-04-16"
     */
    public static String getMondayLastWeek(String StringDate) {
        Date date = parse(StringDate, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, -7);
        String monday = format(cal.getTime());
        return monday;
    }

    /**
     * 根据年月查询该月工作日天数（工作日是指非周六周日）
     *
     * @param belongMonth 格式'2018-04'
     * @return
     */
    public static int getWorkDayOfMonth(String belongMonth) {
        Calendar cal = Calendar.getInstance();
        Date date = parse(belongMonth, DEFAULT_DATE_YEAR_MONTH);
        cal.setTime(date);//设置当前时间
        int workDays = 0;
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);//从每月1号开始
        for (int i = 0; i < days; i++) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                workDays++;
            }
            cal.add(Calendar.DATE, 1);
        }
        return workDays;
    }


    /**
     * 根据日期获取该周的周日日期
     *
     * @param StringDate 字符串"2018-04-20"
     * @return 字符串"2018-04-22"
     */
    public static String getSundayThisWeek(String StringDate) {
        Date date = parse(StringDate, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String monday = format(cal.getTime());
        return monday;
    }

    /**
     * 根据日期获取该周的周五日期
     *
     * @param StringDate 字符串"2018-04-20"
     * @return 字符串"2018-04-22"
     */
    public static String getFridayThisWeek(String StringDate) {
        Date date = parse(StringDate, DEFAULT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        String monday = format(cal.getTime());
        return monday;
    }

    /**
     * 获取昨天日期
     *
     * @param today
     * @return
     */
    public static String getYesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday = format(calendar.getTime());
        return yesterday;
    }

    /**
     * 获取昨天日期(若为本月1号，则返回当天日期)
     *
     * @param today
     * @return
     */
    public static String getYesterdayBelongMonth(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format(cal.getTime());
        if (firstDay.equals(format(today))){
            //当天为本月1日
            return firstDay;
        }else{
            return getYesterday(today);
        }
    }

    /**
     * 获取明天日期
     *
     * @param today
     * @return
     */
    public static String getTommorw(String today) {
        Date date = parse(today, DEFAULT_DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String tommorw = format(calendar.getTime());
        return tommorw;
    }


    /**
     * 是否同一天<br>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameday(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameday(cal1, cal2);
    }

    /**
     * 是否同一天<br>
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameday(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 是否昨天<br>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isYesterday(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isYesterday(cal1, cal2);
    }

    /**
     * 是否昨天<br>
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isYesterday(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }

        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && (cal1.get(Calendar.DAY_OF_YEAR) - 1 == cal2.get(Calendar.DAY_OF_YEAR) || cal1
                .get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) - 1);
    }

    /**
     * 是否本周<br>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameweek(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameweek(cal1, cal2);
    }

    /**
     * 是否本周<br>
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameweek(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }

        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 是否本年<br>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameyear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameyear(cal1, cal2);
    }

    /**
     * 是否本年<br>
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameyear(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(DATE_NULL_MSG);
        }

        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    /**
     * 取一天内的小时数，二十四小时制<br>
     *
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        return format(date, DEFAULT_TODAY_PATTERN);
    }

    /**
     * 取一天内的小时数，二十四小时制<br>
     *
     * @param date
     * @return
     */
    public static String toTodayTime(Date date) {
        return format(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * 昨天时间格式<br>
     *
     * @param date
     * @return
     */
    public static String toYesterday(Date date) {
        return format(date, DEFUALT_YESTERDAY_PATTERN);
    }

    /**
     * 取星期几<br>
     *
     * @param calendar
     * @return
     */
    public static String toDayOfWeek(Calendar calendar) {
        String when = null;
        if (Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = MONDAY;
        } else if (Calendar.TUESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = TUESDAY;
        } else if (Calendar.WEDNESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = WEDNESDAY;
        } else if (Calendar.THURSDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = THURSDAY;
        } else if (Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = FRIDAY;
        } else if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = SATURDAY;
        } else if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

            when = SUNDAY;
        }
        return when;
    }

    /**
     * 返回月日<br>
     *
     * @param date
     * @return
     */
    public static String toMonthAndDay(Date date) {
        return format(date, DEFAULT_MONTH_AND_DAY);
    }

    /**
     * 返回年月日<br>
     *
     * @param date
     * @return
     */
    public static String toYearMonthDay(Date date) {
        return format(date, DEFAULT_YEAR_MONTH_DAY);
    }

    /**
     * 格式化时间字符串<br>
     * <p>
     * 今天的：时间 24小时制
     * </p>
     * <p>
     * 昨天：昨天
     * </p>
     * <p>
     * 一周内：显示星期几
     * </p>
     * <p>
     * 大于一周小于一年：4-7
     * </p>
     * <p>
     * 大于一年：2012-4-7
     * </p>
     *
     * @param source
     * @return
     */
    public static String formatDate(String source) {
        if (StringUtils.isNotBlank(source)) {
            Date date = DateFormatUtils.parse(source, DateFormatUtils.DEFAULT_DATE_PATTERN);

            if (date != null) {
                Date now = new Date();

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(now);
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(date);

                String result = null;

                if (DateFormatUtils.isSameday(cal1, cal2)) {

                    result = DateFormatUtils.toToday(date);
                } else if (DateFormatUtils.isYesterday(cal1, cal2)) {

                    result = DateFormatUtils.toYesterday(date);
                } else if (DateFormatUtils.isSameweek(cal1, cal2)) {

                    String dayOfWeek = DateFormatUtils.toDayOfWeek(cal2);
                    result = DateFormatUtils.toToday(date);

                    if (StringUtils.isNotBlank(dayOfWeek)) {
                        result = dayOfWeek + " " + result;
                    }
                } else if (DateFormatUtils.isSameyear(cal1, cal2)) {

                    result = DateFormatUtils.toMonthAndDay(date);
                } else {

                    result = DateFormatUtils.toYearMonthDay(date);
                }

                return result;
            }
        }

        return "";
    }

    public static String getLongStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return sdf.format(date);
    }

    public static String getLongStr(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return sdf.format(time);
    }

    /**
     * 获取timeAdd天后的日期
     *
     * @param date
     * @param timeAdd
     * @return
     */
    public static Date getDayAddDate(String date, int timeAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateFormatUtils.parse(date, DEFAULT_DATE_PATTERN));
        cal.add(Calendar.DATE, timeAdd);
        return cal.getTime();
    }

    /**
     * 获取timeAdd天后的日期
     *
     * @param date
     * @param timeAdd
     * @return
     */
    public static Date getDayAddDate(Date date, int timeAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, timeAdd);
        return cal.getTime();
    }


    /**
     * 获取timeAdd月后的日期
     *
     * @param date
     * @param timeAdd
     * @return
     */
    public static Date getMonthAddDate(Date date, int timeAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, timeAdd);
        return cal.getTime();
    }

    /**
     * 获得指定日期的后日期
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, int timeAdd) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            logger.error("时间转换有误",e);
        }
        c.setTime(date);
        c.add(Calendar.DATE, timeAdd);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * @param day1
     * @param day2
     * @return boolean
     * @throws
     * @Title: 查看是否为同一天
     * @Description:
     */
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取指定月份的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return DateFormatUtils.format(cal.getTime());
    }

    /**
     * 判断传入的时间是否同一个月
     * @param nowDateStr
     * @param belongDateStr
     * @return
     */
    public static boolean judgeSameMonth(String nowDateStr,String belongDateStr){
        String nowMonth = nowDateStr.substring(0,7);
        String belongMonth = belongDateStr.substring(0,7);
        if (nowMonth.equals(belongMonth)){
            return true;
        }
        return false;
    }

    /**
     * 某一年某个月的每一天
     */
    public static List<String> getMonthFullDay(int year, int month) {
        List<String> fullDayList = new ArrayList<String>();
        int day = 1;
        Calendar cal = Calendar.getInstance();// 获得当前日期对象
        int nowMonth = cal.get(Calendar.MONTH) + 1;
        int nowDate = cal.get(Calendar.DATE);
        // 清除信息
        cal.clear();
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month - 1);
        // 设置为1号,当前日期既为本月第一天
        cal.set(Calendar.DAY_OF_MONTH, day);
        int count = 0;
        if (month == nowMonth) {
            count = nowDate;
        } else {
            count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        for (int j = 0; j <= (count - 1); ) {
            if (DateFormatUtils.format(cal.getTime()).equals(getLastDay(year, month))) {
                break;
            }
            cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
            j++;
            fullDayList.add(DateFormatUtils.format(cal.getTime()));
        }
        return fullDayList;
    }

    public static void main(String args[]) {

    }

    public static int getLastDayOfMonth(Date sDate1) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(sDate1);
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }

    /**
     * 计算两个日期之间相差的月数
     * @param start
     * @param end
     * @return
     */
    public static int getDiffMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 获取天
     * @param date
     * @return
     */
    public static int getDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //获取当前时间的天
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 校验时间格式
     * @param source
     * @param datePattern
     * @return
     */
    public static boolean  isValidDate(String source,String datePattern) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(source);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static List<String> getLastMonths(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_YEAR_MONTH);
        List<String> monthList = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -j);
            Date m = c.getTime();
            monthList.add(sdf.format(m));
        }
        return monthList;
    }

    /**
     * 判断是否大于今天
     * @param date
     * @param pattern
     * @return
     */
    public static boolean compareToday(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String nowDate = sdf.format(new Date());
        String newDate = sdf.format(date);
        try{
            long nowTime = sdf.parse(nowDate).getTime();
            long newTime = sdf.parse(newDate).getTime();
            if(newTime >= nowTime){
                return true;
            }
        }catch (ParseException e){
            logger.error("时间转换错误");
        }
        return false;
    }

    /**
     * 根据月份获取上个月
     * flag 为true获取上个月
     *      为false获取下个月
     * @param currentMonth
     * @param flag
     * @return
     */
    public static String getPreviousMonth(String currentMonth,boolean flag) {
        SimpleDateFormat sd = new SimpleDateFormat(DEFAULT_DATE_YEAR_MONTH);

        try {
            Date currdate = sd.parse(currentMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currdate);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            if(flag){
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            }
            return  sd.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前系统年份
     * @return
     */
    public static String getCurrentYear(){
        SimpleDateFormat sd = new SimpleDateFormat(BELONG_YEAR);
        Date date = new Date();
        return sd.format(date);
    }

    /**
     * 根据还款时间查询统计归属时间（非本月则返回该月最后一天，本月则返回当日）
     * @param repayTime
     * @return
     */
    public static String getBelongDate(String repayTime){
        String nowDate = DateFormatUtils.format(new Date());
        boolean sameMonth = judgeSameMonth(nowDate, repayTime);
        String belongDate;
        if (sameMonth){
            //本月
            belongDate = nowDate;
        }else{
            //非本月
            String repayMonth = repayTime.substring(0,7);
            int year = Integer.valueOf(repayMonth.split("-")[0]);
            int month = Integer.valueOf(repayMonth.split("-")[1]);
            belongDate = getLastDay(year,month);
        }
        return belongDate;
    }
}
