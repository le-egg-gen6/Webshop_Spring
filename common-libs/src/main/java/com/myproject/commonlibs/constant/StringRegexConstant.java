package com.myproject.commonlibs.constant;

/**
 * @author nguyenle
 * @since 10:07 AM Thu 7/24/2025
 */
public class StringRegexConstant {

	public static final String PHONE = "^0[0-9]{9}$";

	public static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	public static final String ALPHANUMERIC = "^[a-zA-Z0-9]+$";

	public static final String PASSWORD_STRONG = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

	public static final String NON_EMPTY = "^\\S+$";

	public static final String VALID_FILENAME_PATTERN = "^[a-zA-Z0-9._-]+\\.[a-zA-Z0-9]{1,10}$";
}
