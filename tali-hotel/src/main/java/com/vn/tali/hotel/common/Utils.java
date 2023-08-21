package com.vn.tali.hotel.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Utils {


	static String source = "1029384756";
	static String target = "6058493721";

	private Utils() {
	}

	public static String getDateFormat(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat(format).format(date));
		}
	}

	public static String getDayHourMinuteStringFromMinute(float totalMinute) {
		int day = (int) (totalMinute / 1440);
		int hour = (int) ((totalMinute - (1440 * day)) / 60);
		int minute = (int) ((totalMinute - (1440 * day)) % 60);
		String resultString = "";
		if (day > 0) {
			resultString = String.format("%s%d ngày", resultString, day);
		}
		if (hour > 0) {
			if (day > 0) {
				resultString = resultString + " ";
			}
			resultString = String.format("%s%d giờ", resultString, hour);
		}
		if (minute > 0) {
			if (hour > 0) {
				resultString = resultString + " ";
			}
			resultString = String.format("%s%d phút", resultString, minute);
		}
		return resultString;
	}

	public static String getHourStringFromMinute(float totalMinute) {
		int hour = (int) (totalMinute / 60);
		int minute = (int) (totalMinute % 60);
		String resultString = "";
		if (hour > 0) {
			resultString = String.format("%s%d giờ", resultString, hour);
		}
		if (minute > 0) {
			if (hour > 0) {
				resultString = resultString + " ";
			}
			resultString = String.format("%s%d phút", resultString, minute);
		}
		return resultString;
	}

	public static Calendar set(Calendar calendar, int hour, int minute, int second) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar;
	}

	public static String getTime(int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return (new SimpleDateFormat("HH:00").format(c.getTime()));
	}

	public static boolean isBetween(Date from, Date time, Date to) {
		if (from.before(time) && to.after(time)) {
			return true;
		} else {
			return false;
		}
	}

	public static int getQuaterOfMonth(int month) {
		if (month == 1 || month == 2 | month == 3) {
			return 1;
		} else if (month == 4 || month == 5 || month == 6) {
			return 2;
		} else if (month == 7 || month == 8 || month == 9) {
			return 3;
		} else if (month == 10 || month == 11 || month == 12) {
			return 4;
		}
		return 1;

	}

	/**
	 * convert from float quantity to full string
	 * 
	 * @param quantity
	 * @param unitname
	 * @param unitValueName
	 * @param unitValue
	 * @return
	 */

	public static String convertFromQuantityToString(BigDecimal quantity, String unitname, String unitValueName,
			int unitValue) {
		String result = "0";

		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumIntegerDigits(3);
		format.setMaximumFractionDigits(0);

//		BigDecimal phanNguyen = mod(quantity, unitValue);
//		BigDecimal residual = (quantity.remainder(new BigDecimal(unitValue)));
//
//		if (phanNguyen.compareTo(BigDecimal.ZERO) != 0) {
//			result = String.format("%s%s", format.format(phanNguyen.doubleValue()), unitname);
//		}
//
//		if (residual.compareTo(BigDecimal.ZERO) != 0 && phanNguyen.compareTo(BigDecimal.ZERO) != 0) {
//			result = String.format("%s %s%s", result, format.format(residual.doubleValue()), unitValueName);
//		} else if (residual.compareTo(BigDecimal.ZERO) != 0 && phanNguyen.compareTo(BigDecimal.ZERO) == 0) {
//			result = String.format("%s%s", format.format(residual.doubleValue()), unitValueName);
//		}

		if (quantity.signum() == 0) {
			result = String.format("%s%s", format.format(quantity.doubleValue()), unitValueName);
		}

		return result;
	}

	/**
	 * chia lấy phần nguyên
	 * 
	 * @return
	 */
	public static BigDecimal mod(BigDecimal a, float b) {

		BigDecimal tmp = ((a.abs().subtract(a.abs().remainder(new BigDecimal(b)))).divide(new BigDecimal(b)));
		if (a.compareTo(BigDecimal.ZERO) < 0) {
			return tmp.multiply(new BigDecimal(-1));
		} else {
			return tmp;
		}

	}

	public static boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean isValidPhone(String phone) {
		Pattern pattern = Pattern.compile("(0[1-9])+([0-9]{8})");
		if (Strings.isEmpty(phone))
			return false;
		return pattern.matcher(phone).matches();
	}

	public static String correctVNPhoneNumber(String phone) {
		if (Strings.isEmpty(phone)) {
			return "";
		} else {
			return phone.replaceAll("\\s", "");
		}

	}

	public static String getDateFormatForChart(Date date) {
		if (date == null) {
			date = new Date();
			return (new SimpleDateFormat("yyyy-MM-dd").format(date));
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
	}

	public static String getDateFormatForMySql(Date date) {
		if (date == null) {

			date = new Date();
			return (new SimpleDateFormat("yyyy-MM-dd").format(date));
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
	}

	public static String getDateTimeFormatForMySql(Date date) {
		if (date == null) {
			date = new Date();
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
		}
	}

	public static String getTimeFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("HH:mm").format(date));
		}
	}

	public static String getDateHourFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:00").format(date));
		}
	}

	public static String getDateTimeFormatForKaizen(Date date) {
		if (date == null) {
			date = new Date();
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		}
	}

	/**
	 * compare 2 Date by year == year, month == month and date == date
	 * 
	 * @param date1
	 * @param date2
	 * @return true if 2 date is equal
	 */
	public static boolean isEqualNotIncludeHour(Date date1, Date date2) {

		if (date1 != null && date2 != null) {
			if (date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth()
					&& date1.getDate() == date2.getDate()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn"t return false
		return true;
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DATE, -7);
		}
		c.set(Calendar.DAY_OF_WEEK, 2);
		return c.getTime();
	}

	public static Date getBeginningOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getBeginningWokingOfficeOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 8);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date gotoNextDate(Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	public static Date gotoPreviousDate(Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static int getHour(String hourMinute) {
		try {
			Date date = new SimpleDateFormat("HH:mm").parse(hourMinute);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.HOUR_OF_DAY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Date gotoNextWeek(Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.DATE, 7);
		return c.getTime();
	}

	public static Date gotoNextMonth(Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

	public static Date gotoPreviousMonth(Date toDate, int totalDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.MONTH, -totalDate);
		return c.getTime();
	}

	public static Date gotoNextYear(Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}


	public static String getDateFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}

	public static String getMonthYearFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("MM/yyyy").format(date));
		}
	}

	public static String getDayFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd").format(date));
		}
	}

	public static String getMonthFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("MM").format(date));
		}
	}

	public static String getDateToCompare(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy/MM/dd").format(date));
		}
	}

	public static String getMonthToCompare(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy/MM").format(date));
		}
	}

	public static String getDateFormatVNForTopicFirebase(Date date) {
		if (date == null) {
			date = new Date();
			return (new SimpleDateFormat("ddMMyyyy").format(date));
		} else {
			return (new SimpleDateFormat("ddMMyyyy").format(date));
		}
	}

	public static String getDatetimeFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public static String getDateMonthFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM").format(date));
		}
	}

	public static String getFullDateTimeWithMilisecondFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));
		}
	}
	
	public static String getFullDateForUTC7(Date date) {
		if (date == null) {
			return "";
		} else {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);

	        // Cộng thêm 7 giờ vì khi qua bên note sẽ bị trừ đi 7h
	        calendar.add(Calendar.HOUR_OF_DAY, 7);

	        // Định dạng lại ngày tháng sau khi cộng thêm 7 giờ
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        return sdf.format(calendar.getTime());
		}
	}

	public static String getHourMinuteSecond(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("HH:mm:ss").format(date));
		}
	}

	public static String getFullDatetimeFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
		}

	}

	public static String getDatetimeString(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
		}
	}

	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
	}

	public static int getHourOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static Date addDate(Date date, int totalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, totalDate);
		return calendar.getTime();
	}

	public static int getDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * SUNDAY : 6 MONDAY : 0 TUESDAY : 1 WEDNESDAY : 2 THUSDAY : 3 FRIDAY : 4
	 * SATURDAY : 5
	 * 
	 */
	public static int getDateOfWeekToDateEnum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
	}

	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonthOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance(); // this takes current date
		if (date != null) {
			c.setTime(date);
		}
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance(); // this takes current date
		c.setTime(getFirstDateOfMonth(date));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static Date getFirstDateOfYear(Date date) {
		Calendar c = Calendar.getInstance(); // this takes current date
		if (date != null) {
			c.setTime(date);
		}

//		c.add(Calendar.YEAR, 1);
//		c.set(Calendar.DAY_OF_YEAR, 1);
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);

		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date gotoPreviousYear(Date date, int totalDate) {
		Calendar c = Calendar.getInstance(); // this takes current date
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.YEAR, -totalDate);
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date getLastDateOfYear(Date date) {
		Calendar c = Calendar.getInstance(); // this takes current date
		if (date != null) {
			c.setTime(date);
		}
		c.set(Calendar.YEAR, c.get(Calendar.YEAR));
		c.set(Calendar.MONTH, 11); // 11 = december
		c.set(Calendar.DAY_OF_MONTH, 31); // new years eve

		return c.getTime();
	}

	public static String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}

	public static Date convertStringToDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return new Date();
		} else {
			return sdf.parse(dateString);
		}
	}

	public static Date convertStringToDateTimeDBFormat(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return null;
		} else {
			return sdf.parse(dateString);
		}
	}

	public static String convertStringToDateTimeStringDBFormat(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return "";
		} else {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(sdf.parse(dateString));
		}
	}

	public static Date convertStringToDateResetBeerGift(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.YEAR, 1);

		if (dateString == null || Strings.isEmpty(dateString)) {
			return cal1.getTime();
		} else {
			int year = cal1.get(Calendar.YEAR);
			return sdf.parse(dateString + "/" + year);

		}
	}

	public static String convertStringFood(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return "";
		} else {
			Date dateFormat = sdf.parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd").format(dateFormat);
		}
	}

	public static String pathStringSql(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return "";
		} else {
			Date dateFormat = sdf.parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd").format(dateFormat);
		}
	}
	
	public static Date formatDatabaseStringToDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.YEAR, 1);

		if (dateString == null || Strings.isEmpty(dateString)) {
			return cal1.getTime();
		} else {
			return sdf.parse(dateString);

		}
	}


	public static String formatDateToStringDatabase(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateString == null || Strings.isEmpty(dateString)) {
			return "";
		} else {
			Date dateFormat = sdf.parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd").format(dateFormat);
		}
	}

	public static Date convertStringToDate(String dateString, String timeFormat) throws ParseException {

		if (!Strings.isEmpty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			return sdf.parse(dateString);
		} else {
			return new Date();
		}
	}

	public static boolean isSameDay(Date date1, Date date2) throws ParseException {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal1.setTime(date2);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isSameDayAndMonth(Date date1, Date date2) throws ParseException {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal1.setTime(date2);

		if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isSameMonth(Date date1, Date date2) throws ParseException {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isSameYear(Date date1, Date date2) throws ParseException {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
			return true;
		}

		return false;
	}

	public static boolean createFolder(String path) {
		File theDir = new File(path);
		if (!theDir.exists()) {
			boolean result = false;

			try {
				System.out.println("Begin make dir: " + path);
				theDir.setWritable(true, false);
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				result = false;
				se.printStackTrace();
			}
			return result;
		} else {
			return true;
		}
	}

	public static void zip(List<String> fileList, String output) {
		try {

			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(output);

			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : fileList) {

				File srcFile = new File(file);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the start of the entry
				// data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();

			}

			// close the ZipOutputStream
			zos.close();

		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
	}

	public static String readHtmlFromFile(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
			buf.close();
			return sb.toString();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
			return "";
		}
	}

	public static int calculateTotalHoursFromNow(Date time) {

		Date now = new Date();
		if (Utils.getDateToCompare(time).compareTo(Utils.getDateToCompare(now)) < 0) {
			return -1;
		} else {
			return (int) ((time.getTime() - now.getTime()) / (1000 * 60 * 60));
		}
	}

	public static int calculateTotalMinutesFromNow(Date time) {

		Date now = new Date();
		if (Utils.getDateToCompare(time).compareTo(Utils.getDateToCompare(now)) < 0) {
			return -1;
		} else {
			return (int) ((time.getTime() - now.getTime()) / (1000 * 60));
		}
	}

	public static boolean isValidBookingTime(Date time, int minTimeDiff) {
		return calculateTotalMinutesFromNow(time) > minTimeDiff;
	}

	public static String stringVietnameseMoneyFormatWithFloat(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
			return String.format("%sđ", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			String str_price = String.format("%sđ", resp);
			return str_price;
		} catch (Exception e) {
			return "0đ";
		}

	}

	public static String stringVietnameseMoneyFormatWithBigDecimal(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
			return String.format("%sđ", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			String str_price = String.format("%sđ", resp);
			return str_price;
		} catch (Exception e) {
			return "0đ";
		}

	}

	public static String stringMoneyFormatWithBigDecimal(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
			return String.format("%s", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			String str_price = String.format("%s", resp);
			return str_price;
		} catch (Exception e) {
			return "0";
		}

	}

	public static String createRandomString(int length) {
		String stringRanger = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String createRandomQRCode(int length) {
		String stringRanger = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String createRandomCode(int length) {
		String stringRanger = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String createRandomNumber(int length) {
		String stringRanger = "0123456789";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String encodeHmacSHA256(String secretKey, String value) {
		byte[] hmacSha256 = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(value.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha256", e);
		}
		return String.format("%032x", new BigInteger(1, hmacSha256));
	}

	public static String decodeShaPassword(String hashPassword) {
		return "";
	}

	public static String convertStringToSlug(String str) {
		String slug = "";
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
			slug = "";
		}
		return slug;
	}

	public static void decodeJbdcToken(String token) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(token.getBytes("UTF-8"));
			System.out.println(String.format("%032x", new BigInteger(1, bytes)));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}

	}

	public static String removeAllNoneNumberic(String s) {
		if (Strings.isEmpty(s)) {
			return "";
		} else {
			return s.replaceAll("[^\\d.]", "");
		}

	}

	public static String removeAllNumberic(String s) {
		if (Strings.isEmpty(s)) {
			return "";
		} else {
			String number = s.replaceAll("\\d", "");
			return number;
		}

	}

	public static String removeAllSpace(String s) {
		if (s == null) {
			return "";
		} else {
			return s.replaceAll("\\s", "");
		}

	}

	/**
	 * Chuyển mã truyền lên thành mã hợp lệ
	 * 
	 * @param s
	 * @return
	 */
	public static String convertToCodeFormat(String s) {
		if (s == null) {
			return "";
		} else {
//			s = removeVietnameseFromString(removeSpecialCharacter(s));
			s = removeVietnameseFromString(s);

			return s.replaceAll("\\s", "").toUpperCase();
		}

	}

	public static String saveUploadedFile(MultipartFile image, String resourcePath, String filePath) {
		String imageUrl = "";
		if (!image.isEmpty()) {
			try {
				createFolder(resourcePath + filePath);
				byte[] bytes = image.getBytes();
				filePath = filePath + Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
				String fPath = resourcePath + filePath;
				Path path = Paths.get(fPath);
				Files.write(path, bytes);
				imageUrl = filePath;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return imageUrl;
	}

	public static String encryptOrderId(long orderId) {
		String s = String.format("%012d", orderId);
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = source.indexOf(c);
			result[i] = target.charAt(index);
		}

		return new String(result);
	}

	public static long decryptOrderId(String s) {
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = target.indexOf(c);
			result[i] = source.charAt(index);
		}

		return Long.parseLong((new String(result)));
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static List<Integer> stringToIntegerList(String string) {
		List<Integer> integerList = new LinkedList<>();
		String[] stringList = string.split(",");
		for (String s : stringList)
			integerList.add(Integer.valueOf(s));
		return integerList;
	}

	public static List<Long> stringToLongList(String string, String... separateChar) {
		if (string == null) {
			return null;
		}
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		List<Long> integerList = new LinkedList<>();
		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Long.valueOf(s));
			}
		}
		return integerList;
	}

	public static List<Integer> stringToIntegerList(String string, String... separateChar) {
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		List<Integer> integerList = new LinkedList<>();

		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Integer.valueOf(s));
			}

		}
		return integerList;
	}

	public static Set<Long> stringToLongSet(String string, String... separateChar) {
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		Set<Long> integerList = new HashSet<>();

		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Long.valueOf(s));
			}

		}
		return integerList;
	}

	public static float correctWeightForShipping(float weight) {
		float decimalPart = (weight - (int) weight);
		if (decimalPart > 0.5) {
			decimalPart = 1;
		} else if (decimalPart < 0.5) {
			decimalPart = (float) 0.5;
		}
		return (int) weight + decimalPart;
	}

	public static List<String> stringSplitToList(String string, String regex) {
		List<String> result = new ArrayList<String>();
		if (string != null && !string.isEmpty()) {
			result = new ArrayList<String>(Arrays.asList(string.split(regex)));
		}
		return result;
	}

	public static List<Integer> stringSplitToListInteger(String string, String regex) {
		List<Integer> result = new ArrayList<Integer>();
		if (string != null && !string.isEmpty()) {
			Pattern pattern = Pattern.compile(regex);
			result = pattern.splitAsStream(string).map(Integer::valueOf).collect(Collectors.toList());
		}
		return result;
	}

	public static List<Long> stringSplitToListLong(String string, String regex) {
		List<Long> result = new ArrayList<Long>();
		if (string != null && !string.isEmpty()) {
			Pattern pattern = Pattern.compile(regex);
			result = pattern.splitAsStream(string).map(Long::valueOf).collect(Collectors.toList());
		}
		return result;
	}


	public static Map<String, String> getQueryStringMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	public static String getFirstLetterEachWord(String string) {
		String resutl = "";
		String[] stringArray = string.split("(?<=[\\S])[\\S]*\\s*");
		for (String s : stringArray) {
			resutl = String.format("%s%s", resutl, s);
		}
		return resutl;
	}

	public static String convertObjectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(object);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertObjectToJsonObject(Object object) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertListObjectToJsonArray(List<?> objects) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, objects);
			final byte[] data = out.toByteArray();
			return new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertListObjectStringFindInSet(List<?> objects) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, objects);
			final byte[] data = out.toByteArray();
			return new String(data).replace("[", "").replace("]", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> convertJsonStringToListObject(String jsonString, Class<T[]> objectclass)
			throws Exception {
		jsonString = Strings.isEmpty(jsonString) ? "[]" : jsonString;
		ObjectMapper mapper = new ObjectMapper();
		List<T> result = Arrays.asList(mapper.readValue(jsonString, objectclass));
		return result;
	}

	public static <T> T convertJsonStringToObject(String jsonString, Class<T> objectclass) throws Exception {
		if (Strings.isEmpty(jsonString)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		T result = mapper.readValue(jsonString, objectclass);
		return result;
	}

	public static <T> String convertObjectToJsonString(Object data) throws Exception {

		if (data == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(data);
		return result;
	}

	public static String getFileExtension(MultipartFile file) {
		String extension = "";

		try {
			if (!file.isEmpty()) {
				String name = file.getOriginalFilename();
				extension = name.substring(name.lastIndexOf("."));
			}
		} catch (Exception e) {
			extension = "";
		}

		return extension;
	}


	public static String getContentType(String fileExtension) {
		switch (fileExtension.toLowerCase()) {
		case "png":
			return "image/png";
		case "jpg":
			return "image/jpeg";
		case "gif":
			return "image/gif";
		default:
			return "image/jpeg";
		}
	}

	public static String getDatabaseTimeString(Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
	}

	public static float reCalculateRate(float oldRate, float newRate, int currentRateCount) {
		return (oldRate * currentRateCount + newRate) / (float) (currentRateCount + 1);
	}

	public static int getAge(Date birthDay) {
		Calendar ca = Calendar.getInstance();
		int nowYear = ca.get(Calendar.YEAR);
		ca.setTime(birthDay);
		int birthYear = ca.get(Calendar.YEAR);
		return nowYear - birthYear;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		if (date1 == null)
			date1 = new Date();
		if (date2 == null)
			date2 = new Date();

		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public static long getDayDiff(Date date1, Date date2) {
		if (date1 == null)
			date1 = new Date();
		if (date2 == null)
			date2 = new Date();
		
		long diffInMillies = date2.getTime() - date1.getTime();
		return diffInMillies/(24 * 60 * 60 * 1000);
	}

	public static String getTimeStringByMinute(int minute) {
		String result = "";

		int remainMinute = minute % 60;
		int hour = minute / 60;
		int remainHour = hour % 24;
		int day = hour / 24;

		result = String.format("%d phút", remainMinute);
		if (remainHour > 0) {
			result = String.format("%d giờ %s", remainHour, result);
		}
		if (day > 0) {
			result = String.format("%d ngày %s", day, result);
		}

		return result;
	}

	public static boolean isToday(Date date) {
		Calendar current = Calendar.getInstance();
		Calendar dateCheck = Calendar.getInstance();
		dateCheck.setTime(date);
		if (dateCheck.get(Calendar.DAY_OF_MONTH) == current.get(Calendar.DAY_OF_MONTH)
				&& dateCheck.get(Calendar.MONTH) == current.get(Calendar.MONTH)
				&& dateCheck.get(Calendar.YEAR) == current.get(Calendar.YEAR)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotSpecialTime(Date date) {
		Calendar dateCheck = Calendar.getInstance();
		dateCheck.setTime(date);
		if (dateCheck.get(Calendar.HOUR) > 0 && dateCheck.get(Calendar.MINUTE) > 0
				&& dateCheck.get(Calendar.MILLISECOND) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static float calculatePercent(BigDecimal no1, BigDecimal no2) {
		if (no1 == null || no2 == null) {
			return 0;
		} else {
			if (no2.signum() == 0) {
				return 0;
			} else {
				return (no1.divide(no2, 10, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).floatValue();
			}
		}
	}

	public static String removeSpecialCharacter(String str) {
		str = str.replaceAll("[^a-zA-Z0-9]", "");
		return str;
	}

	public static String removeVietnameseFromString(String str) {
		str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
		str = str.replaceAll("[ÀÁẠẢÃĂẰẮẶẲẴÂẦẤẬẨẪ]", "A");
		str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
		str = str.replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E");
		str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
		str = str.replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O");
		str = str.replaceAll("[ìíịỉĩ]", "i");
		str = str.replaceAll("[ÌÍỊỈĨ]", "I");
		str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
		str = str.replaceAll("[ƯỪỨỰỬỮÙÚỤỦŨ]", "U");
		str = str.replaceAll("[ỳýỵỷỹ]", "y");
		str = str.replaceAll("[ỲÝỴỶỸ]", "Y");
		str = str.replaceAll("[Đ]", "D");
		str = str.replaceAll("[đ]", "d");
		return str;
	}

	public static String replaceStringContentByKeyValue(Map<String, String> keys, String content) {
		for (Map.Entry<String, String> entry : keys.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			content = content.replace(key, value);
		}
		return content;
	}

	public static String formatNumberVN(double number, boolean isRound) {
		try {
			StringBuilder sb = new StringBuilder();
			@SuppressWarnings("resource")
			Formatter formatter = new Formatter(sb, Locale.GERMAN);
			formatter.format(isRound ? "%(,.0f" : "%(,.2f", number);

			return sb.toString();
		} catch (Exception ex) {
			return String.valueOf(number);
		}
	}

	public static Date getDateTimeDiffByHour(Date date, int hour) {
		Calendar current = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentHour = current.get(Calendar.HOUR_OF_DAY);
		int currentMinute = current.get(Calendar.MINUTE);
		if (Utils.isToday(c.getTime()) && (currentHour < hour || (currentHour == hour && currentMinute == 0))) {
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 00);
			c.add(Calendar.DATE, -1);
		}
		return c.getTime();
	}

	// format lại phần dư của số thực
	/**
	 * 
	 * @param floatNumber số cần format
	 * @param inputNumber fort số thập phân bao nhiêu
	 * @return
	 */
	public static float formatFloatWithInputNumberDecimalPlaces(float floatNumber, int inputNumber) {
		if (inputNumber <= 0) {
			inputNumber = 2;
		}

		String floatNumberResult = String.format("%.0" + inputNumber + "f", floatNumber, inputNumber);
		return Float.valueOf(floatNumberResult);
	}

	/**
	 * So sánh tháng và năm
	 * 
	 * @param date1 -> 01-??-???
	 * @param date2 -> 01-??-???
	 * @return date1.compareTo(date2); //date1 < date2, returns less than 0
	 *         date2.compareTo(date1); //date2 > date1, returns greater than 0
	 *         date1.compareTo(date3); //date1 = date3, so will print 0 to console
	 */

	public static int compareDateMonthOfYear(Date date1, Date date2) {
		return getFirstDateOfMonth(date1).compareTo(getFirstDateOfMonth(date2));
	}


}
