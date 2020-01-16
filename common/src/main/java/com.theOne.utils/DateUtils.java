package com.peony.core.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.Assert;
 
/**
 * 
 *
 *
 * Version 1.0
 * 
 * Created Time ：2007-9-29
 *
 * Author: Dragon
 * 
 */

public class DateUtils {
	public static String SIMPLE_PATTERN = "yyyy-MM-dd";
	public static String YYYYMM_PATTERN = "yyyy-MM";
	public static String MMDDHHmm_PATTERN = "MM-dd HH:mm";
	public static String HHMM_PATTERN = "yyyy-MM-dd HH:mm";
	public static String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatCurrentDate(){
		SimpleDateFormat sf = new SimpleDateFormat(SIMPLE_PATTERN);
		return sf.format(new Date());
	}
	
	public static String formatCurrentDate(String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(new Date());
	}
	
	public static String formateDate(Date date, String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	public static Integer getCurrentYear(){
		return getYear(new Date());
	}
	
	public static Integer getCurrentMonth(){
		return getMonth(new Date());
	}
	
	public static Integer getCurrentDay(){
		return getDay(new Date());
	}
	
	public static Integer getCurrentQuarter(){
		return getQuarter(new Date());
	}
	
	public static Integer getYear(Date date){
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		return gcdate.get(Calendar.YEAR);
	}
	
	public static Integer getMonth(Date date){
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		return gcdate.get(Calendar.MONTH)+1;
	}
	
	public static Integer getDay(Date date){
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		return gcdate.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getQuarter(Date date){
		int month = getMonth(date);
		if(month >=1 && month <=3)
			return 1;
		else if(month >=4 && month <=6)
			return 2;
		else if(month >=7 && month <=9)
			return 3;
		else 
			return 4;
	}
	
	public static int getDayOfMonth(int month){
		if(month==1 || month==3 || month==5 || month==7 || month==8 || 
				month==10 || month==12)
			return 31;
		else if(month == 2)
			return 28;
		else 
			return 30;
	}
	
	public static Date parseDate(String dateStr) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat(SIMPLE_PATTERN);
		return sf.parse(dateStr);
	} 
	
	public static Date parseDate(String dateStr, String pattern) throws ParseException{
		Assert.hasText(pattern);
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.parse(dateStr);
	}
	
	public static Date addYear(Date date, int num) {
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		gcdate.add(Calendar.YEAR, num);
		return gcdate.getTime();
	}
	
	public static Date addMonth(Date date, int num) {
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		gcdate.add(Calendar.MONTH, num);
		return gcdate.getTime();
	}
	
	public static Date addDay(Date date, int num) {
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		gcdate.add(Calendar.DAY_OF_MONTH, num);
		return gcdate.getTime();
	}
	
	/**
	 * 去除date里面的时间，保留年月日
	 */
	public static Date cutTime(Date date){
		GregorianCalendar gcdate = new GregorianCalendar();
		gcdate.setTime(date);
		gcdate.set(Calendar.HOUR_OF_DAY,0); //not gcdate.clear(Calendar.HOUR);
		gcdate.clear(Calendar.MINUTE);
		gcdate.clear(Calendar.SECOND);
		gcdate.clear(Calendar.MILLISECOND);
		return gcdate.getTime();
	}
	
	/**
	 * 去除date里面的时间，保留年月日
	 */
	public static Date getFirstOfMonth(Date date) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String firstDay = simpleFormate.format(calendar.getTime());
		return parseDate(firstDay);
	}
	
	/**
	 * 去除date里面的时间，保留年月日
	 * GregorianCalendar : add/roll  差异是roll不改变最大的值, 改变日期而不改变月
	*/
	public static Date getEndOfMonth(Date date) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String endDay = simpleFormate.format(calendar.getTime());
		return parseDate(endDay);
	}
	
	/**
	 * 去除date里面的时间，保留年月日
	 */
	public static boolean isEqualDay(Date date1, Date date2){
		GregorianCalendar gcdate1 = new GregorianCalendar();
		gcdate1.setTime(date1);
		GregorianCalendar gcdate2 = new GregorianCalendar();
		gcdate2.setTime(date2);
		return gcdate1.get(Calendar.YEAR) == gcdate2.get(Calendar.YEAR) &&
				gcdate1.get(Calendar.MONTH) == gcdate2.get(Calendar.MONTH) &&
				gcdate1.get(Calendar.DAY_OF_MONTH) == gcdate2.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param time 秒数
	 * @return 格式化的文本时间串
	 * 
	 */
	public static String formatTime(long second){
		String[] timeUnits = {"秒","分钟","小时"};
		if(second <60)
			return second + timeUnits[0];
		else if(second/60 < 60)
			return ArithTools.div(second,60,2) + timeUnits[1];
		else
			return ArithTools.div(second,60*60,2) + timeUnits[2];
	}
	
	/**
	 * 根据出生日期,计算年龄,精确到天
	 * @param bornDate
	 * @return
	 */
	public static int computeAge(Date birthday){
		if(birthday == null) return -1;
		Calendar calendar = Calendar.getInstance();
        if (calendar.before(birthday))
            throw new IllegalArgumentException(
                "The birthday is incorrect!");
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH);
        int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(birthday);
        int yearBirth = calendar.get(Calendar.YEAR);
        int monthBirth = calendar.get(Calendar.MONTH);
        int dayOfMonthBirth = calendar.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            }else {
                //monthNow>monthBirth
                age--;
            }
        }else {
            //monthNow<monthBirth
            //donothing
        }
        return age;		
	}
	
	/**
	 * 取得两个日期相差的天数
	 */
	public static int diffDays(Date startDate, Date endDate){
		GregorianCalendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		GregorianCalendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);
		
		startCalendar.clear(Calendar.MILLISECOND);
		startCalendar.clear(Calendar.SECOND);
		startCalendar.clear(Calendar.MINUTE);
		startCalendar.clear(Calendar.HOUR_OF_DAY);
		
		endCalendar.clear(Calendar.MILLISECOND);
		endCalendar.clear(Calendar.SECOND);
		endCalendar.clear(Calendar.MINUTE);
		endCalendar.clear(Calendar.HOUR_OF_DAY);
		
		int elapsed = 0;
		while (startCalendar.before(endCalendar)) {
			startCalendar.add(Calendar.DATE, 1);
			elapsed ++;
		}
		return elapsed;
	}
	public static int diffSeconds(Date startDate, Date endDate){
		return (int) ((startDate.getTime()-endDate.getTime())/1000);
		
	}
}

