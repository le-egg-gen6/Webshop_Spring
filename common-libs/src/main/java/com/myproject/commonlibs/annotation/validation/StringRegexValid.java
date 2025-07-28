package com.myproject.commonlibs.annotation.validation;

import com.myproject.commonlibs.annotation.validation.validator.StringRegexValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nguyenle
 * @since 9:57 AM Thu 7/24/2025
 */

@Constraint(validatedBy = StringRegexValidator.class)
@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringRegexValid {
	String message() default "Invalid string";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] regexes() default {};

	RegexMatchMode matchMode() default RegexMatchMode.ANY;
}
