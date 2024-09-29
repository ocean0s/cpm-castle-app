package uo.cpm.util;

import java.util.Calendar;
import java.util.regex.Pattern;

public abstract class FormatChecker {

	/**
	 * @param string
	 * @return true if the argument is not null nor blank
	 */
	public static boolean blankCheck(String s) {
		return (s != null && !s.isBlank());
	}
	
	/**
	 * @param email
	 * @return if the email follows the pattern of a usual email
	 */
	public static boolean isValidEmail(String s) {
		if (!blankCheck(s))
			return false;
		s = s.toLowerCase();
		return Pattern.matches("[a-z0-9._ñ]+@[a-z0-9ñ]+.[a-z]+", s);
	}

	/**
	 * @param date
	 * @return wheter a date in the format dd/mm/yyyy is later or equal to today.
	 */
	public static boolean isValidDate(String s) {
		if (!blankCheck(s))
			return false;
		
		String[] strparts = s.split("/");
		if (strparts.length != 3)
			return false;
		
		int[] parts = new int [3];
		for (int i = 0; i < parts.length; i++) {
			try {
				parts[i] = Integer.parseInt(strparts[i]);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1; // months start at 0 with Calendar
		
		if (year > parts[2])
			return false;
		if (year < parts[2])
			return true;
		if (month > parts[1])
			return false;
		if (month < parts[1])
			return true;
		if (day > parts[0])
			return false;
		return true;
	}
	
}
