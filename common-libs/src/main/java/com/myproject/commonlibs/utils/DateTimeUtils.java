package com.myproject.commonlibs.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

/**
 * @author nguyenle
 * @since 4:29 PM Wed 7/23/2025
 */
@UtilityClass
public class DateTimeUtils {

	private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

	public static String formatDateTime(LocalDateTime dateTime) {
		return dateTime.format(DATE_FORMATTER);
	}

}
