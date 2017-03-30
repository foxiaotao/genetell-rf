package com.core.gene.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenaratorUtil {

	/**
	 * 正则验证手机号码
	 * @param telephone
	 * @return
	 */
	public static Matcher patternTelePhone(String telephone) {
		Pattern p = Pattern.compile(GeneConstant.PHONE_PATTERN_MATCHER);  
    	Matcher m = p.matcher(telephone); 
    	return m;
	}
	/**
	 * 正则验证邮箱
	 * @param telephone
	 * @return
	 */
	public static Matcher patternEmail(String email) {
		Pattern p = Pattern.compile(GeneConstant.EMAIL_PATTERN_MATCHER);
		Matcher m = p.matcher(email); 
		return m;
	}
}
