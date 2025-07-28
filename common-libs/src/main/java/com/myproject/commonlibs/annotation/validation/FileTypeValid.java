package com.myproject.commonlibs.annotation.validation;

import com.myproject.commonlibs.annotation.validation.validator.FileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nguyenle
 * @since 9:49 AM Thu 7/24/2025
 */
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FileTypeValid {

	String message() default "Invalid file type";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] allowedTypes() default {};

}
