package com.myproject.commonlibs.utils;

import java.util.Locale;
import lombok.experimental.UtilityClass;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.MessageSource;

/**
 * @author nguyenle
 * @since 5:02 PM Thu 7/24/2025
 */
@UtilityClass
public class MessageUtils {

	public static String getMessage(MessageSource source, String errorCode, Object... var2) {
		return source.getMessage(
			errorCode,
			var2,
			errorCode,
			Locale.getDefault()
		);
	}

}
