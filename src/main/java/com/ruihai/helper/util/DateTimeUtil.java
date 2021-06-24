package com.ruihai.helper.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
    private static int weeks = 0;

    /**
     * 时间格式:yyyy-MM-dd HH:mm:ss
     */
    private static final String yyyy_MM_dd_HH__mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式:yyyy-MM-dd
     */
    private static final String yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * 时间格式:yyyy-MM
     */
    private static final String yyyy_MM = "yyyy-MM";

    /**
     * 获得当前日期与本周一相差的天数
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 获得本周星期日的日期
     */
    public static String getCurrentWeekday() {
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得本周一的日期
     */
    public static String getMondayOFWeek() {
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得上周星期日的日期
     */
    public static String getPreviousWeekSunday() {
        weeks = 0;
        weeks--;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得上周星期一的日期
     */
    public static String getPreviousWeekday() {
        weeks = 0;
        weeks--;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得下周星期一的日期
     */
    public static String getNextMonday() {
        weeks++;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得下周星期日的日期
     */
    public static String getNextSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获取当天日期
     *
     * @param dateformat
     */
    public static String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 格式化时间格式
        String hehe = dateFormat.format(now);
        return hehe;
    }

    /**
     * 获得前天的日期
     *
     * @param dateformat
     */
    public static String getPreviousTimes(String dateformat) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -2);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获得昨天的日期
     *
     * @param dateformat
     */
    public static String getPreviousTime(String dateformat) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);// 把日期往前减少一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        String dateString = formatter.format(date);
        return dateString;
    }
    /**
     * 获得明天的日期
     *
     * @param dateformat
     */
    public static String getTomorrowTime(String dateformat) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往前减少一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获得当月的第一天(0时0分0秒)
     *
     * @return 类似(2014-07-31 00:00:00)
     */
    public static String getNowFirstMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));// 设置为1号,当前日期既为本月第一天
        String first = formatter.format(c.getTime());
        if(first != null && !first.equals("")){
            first += " 00:00:00";
        }
        return first;
    }

    /**
     * 获得当月的最后一天(23时59分59秒)
     *
     * @return 类似(2014-07-31 23:59:59)
     */
    public static String getNowLastMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = formatter.format(ca.getTime());
        if(last != null && !last.equals("")){
            last += " 23:59:59";
        }
        return last;
    }

    /**
     * 获得当月的第一天(0时0分0秒)
     *
     * @return 类似(2014-07-31 00:00:00)
     */
    public static String getNowFirstMonthDay(String dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));// 设置为1号,当前日期既为本月第一天
        String first = formatter.format(c.getTime());
        return first;
    }

    /**
     * 获得当月最后一天
     *
     * @param dateformat
     *            时间格式
     * @return
     */
    public static String getNowLastMonthDay(String dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = formatter.format(ca.getTime());
        return last;
    }

    /**
     * 获得指定Date类型日期月份的第一天
     *
     * @param date
     *            Date类型的时间
     * @param dateformat
     *            时间格式
     * @return
     */
    public static String getFirstMonthDay(Date date, String dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));// 设置为1号,当前日期既为本月第一天
        String first = formatter.format(c.getTime());
        return first;
    }

    /**
     * 获得指定Date类型日期月份的最后一天
     *
     * @param date
     *            Date类型的时间
     * @param dateformat
     *            时间格式
     * @return
     */
    public static String getLastMonthDay(Date date, String dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = formatter.format(c.getTime());
        return last;
    }

    public static int getCountDay(int year, int month) {
        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);// Java月份才0开始算 6代表上一个月7月
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取指定时间的月份(如果月份小于10,则返回01-09形式的时间)
     *
     * @param date
     *            Date格式的时间
     * @return 01-12
     */
    public static String getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM);
        String nowMonthTime = sdf.format(date);
        return nowMonthTime.substring(5, 7);
    }

    /**
     * 获取yyyy-MM格式的时间
     *
     * @param date
     *            Date格式的时间
     * @param format
     *            指定时间格式
     * @return 返回yyyy-MM格式的时间
     */
    public static String getTimeByFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将字符串时间转变为java.util.Date格式时间
     *
     * @param time
     *            字符串格式时间
     * @return java.util.Date
     */
    public static Date strTimeToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串时间转变为java.util.Date格式时间,格式自定
     *
     * @param time
     *            字符串格式时间
     * @param format
     * @return java.util.Date
     */
    public static Date strTimeToDate(String time,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取指定时间的上个月的yyyy-MM格式的时间
     *
     * @param date
     * @return
     */
    public static String getLastMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int months = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, (months - 1));
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date strDateTo = calendar.getTime();
        return sdf.format(strDateTo);
    }





    /**
     * 判断时间date1是否在时间date2之前<br/>
     * 如果第一个时间小于第二个时间返回true，反之返回false
     * (参数时间格式为yyyy-MM-dd)
     * @param date1 要比较的第一个时间
     * @param date2 要比较的第二个时间
     * @return date1<date2 返回true,date1>date2返回false,相等时等同于date1>date2
     */
    public static boolean isDateBeforeToBoolean(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
            return sdf.parse(date1).before(sdf.parse(date2));
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return false;
        }
    }

    /**
     * 判断时间date1是否在时间date2之后<br/>
     * 如果第一个时间大于于第二个时间返回true，反之返回false
     * (参数时间格式为yyyy-MM-dd)
     * @param date1 要比较的第一个时间
     * @param date2 要比较的第二个时间
     * @return date1>date2 返回true,date1<date2返回false,相等时等同于date1<date2
     */
    public static boolean isDateAfterToBoolean(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
            return sdf.parse(date1).after(sdf.parse(date2));
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return false;
        }
    }






    /**
     * (时间比较)获取两个yyy-MM-dd格式时间中较小的时间,如果时间相等，则返回第二个时间<br/>
     * (参数时间格式为yyyy-MM-dd)
     *
     * @param date1
     *            要比较的第一个时间
     * @param date2
     *            要比较的第二个时间
     * @return 返回小的时间
     */
    public static String getBeforeTime(String date1, String date2) {
        if(date1 != null && date1.length() > 0 && date2 != null && date2.length() > 0){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
            boolean falg = sdf.parse(date1).before(sdf.parse(date2));
            if (falg) {
                return date1;
            } else {
                return date2;
            }
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return null;
        }
    }

    /**
     * (时间比较)获取两个yyy-MM-dd格式时间中较大的时间,如果时间相等，则返回第二个时间<br/>
     * (参数时间格式为yyyy-MM-dd)
     *
     * @param date1
     *            要比较的第一个时间
     * @param date2
     *            要比较的第二个时间
     * @return 返回大的时间
     */
    public static String getAfterTime(String date1, String date2) {
        if(date1 != null && date1.length() > 0 && date2 != null && date2.length() > 0){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.yyyy_MM_dd);
            boolean falg = sdf.parse(date1).after(sdf.parse(date2));
            if (falg) {
                return date1;
            } else {
                return date2;
            }
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return null;
        }
    }

    /**
     * 将时间戳转换为时间
     * @param s     时间戳
     * @return
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }
}
