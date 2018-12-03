package com.ccuk.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ApplicationConstant {
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String ISO_TIME_FORMAT_DD = "dd/MM/yyyy'T'HH:mm:ss.SSS'Z'";

	public static final DateFormat DATE_FORMATER = new SimpleDateFormat(DATE_FORMAT);
	public static final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	public static final DateTimeFormatter TIME_FORMATER = DateTimeFormatter.ofPattern(TIME_FORMAT);
	public static final DateTimeFormatter ISO_DATE_TIME_FORMATER = DateTimeFormatter.ofPattern(ISO_DATE_TIME_FORMAT);
	public static final DateTimeFormatter ISO_DATE_TIME_FORMATER_DD = DateTimeFormatter.ofPattern(ISO_TIME_FORMAT_DD);
}
