package com.myproject.commonlibs.annotation.validation.validator;

import com.myproject.commonlibs.annotation.validation.RegexMatchMode;
import com.myproject.commonlibs.annotation.validation.StringRegexValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author nguyenle
 * @since 10:00 AM Thu 7/24/2025
 */
public class StringRegexValidator implements ConstraintValidator<StringRegexValid, String> {

	private Pattern[] patterns;
	private RegexMatchMode mode;

	@Override
	public void initialize(StringRegexValid constraintAnnotation) {
		String[] regexes = constraintAnnotation.regexes();
		this.mode = constraintAnnotation.matchMode();
		this.patterns = new Pattern[regexes.length];
		for (int i = 0; i < regexes.length; i++) {
			this.patterns[i] = Pattern.compile(regexes[i]);
		}
	}

	@Override
	public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
		if (string == null) {
			return false;
		}
		long mathCount = Arrays.stream(patterns)
			.filter(p -> p.matcher(string).matches())
			.count();
		return switch (mode) {
			case ALL -> mathCount == patterns.length;
			case ANY -> mathCount > 0;
			case NONE -> mathCount == 0;
		};
	}

}
