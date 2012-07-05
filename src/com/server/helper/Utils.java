package com.server.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils
{
	/**
	 * Example: "2012-03-30 14:09:01"
	 * 
	 * @return string of date
	 * @param date
	 */
	public static final String getDateString(long date)
	{
		Date dateObject = new Date(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(dateObject);
	}
}
