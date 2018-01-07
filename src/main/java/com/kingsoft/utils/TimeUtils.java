package com.kingsoft.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by Administrator on 2017/4/1.
 */
public class TimeUtils {

    /**
     * 获得系统当前时间戳
     * @return
     */
    public static Long getNowTimestamp() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 指定日期+天数
     * @param startDate 起始日期
     * @param days 天数
     * @return
     */
    public static Date getPlusDaysWithBeginTime(Date startDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, days);
        Date dateResult = calendar.getTime();
        return dateResult;
    }

    public static Date parseStrToDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据指定格式将日期格式化成字符串
     * @param date 时间
     * @param format 日期格式
     * @return
     */
    public static String formatDateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date dateCalculation(String nowdate, int days, String formate)  {
        // 时间表示格式可以改变，yyyyMMdd需要写例如20160523这种形式的时间
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
        Date date = sdf.parse(nowdate, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, days);
        Date dateResult = calendar.getTime();
        //String out = sdf.format(dateResult);
        return dateResult;
    }
    public  static int compareDate(Date dt1,Date dt2){
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
    }

    /**
     * 获取连个日期相差的天数
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {
        DateTime d1 = new DateTime(fDate);
        DateTime d2 = new DateTime(oDate);
        LocalDate start = new LocalDate(d1.getYear(), d1.getMonthOfYear(), d1.getDayOfMonth());
        LocalDate end = new LocalDate(d2.getYear(), d2.getMonthOfYear(), d2.getDayOfMonth());
        return Days.daysBetween(end, start).getDays();
    }
    public static java.util.Date getDateFromStr(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = sdf.parse(strDate, new ParsePosition(0));
        return date;
    }

    /**
     *
     * @param date
     * @param beforeAfter 获得日期的偏移 按天数获得
     * @return
     */
    public static Date getDateShift(Date date, int beforeAfter) {
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(date);
        gc.add(5, beforeAfter);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return gc.getTime();
    }

}
