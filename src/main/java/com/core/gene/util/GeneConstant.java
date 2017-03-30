package com.core.gene.util;

import org.springframework.util.Assert;

public class GeneConstant {
	/**
	 * 获取session中手机号码的
	 * @param cellphone
	 * @return
	 */
	public static String getCellphoneSessionRegDateKey(String cellphone){
		Assert.notNull(cellphone,"session中手机号码不能为空");
		return cellphone+"_REGDATE";
	}
	public static String getCellphoneSessionContentKey(String cellphone){
		Assert.notNull(cellphone,"session中手机号码不能为空");
		return cellphone+"_CONTENT";
	}
	
	
	public static String PHONE_PATTERN_MATCHER = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,3,6,7,8])|(18[0,2,5-9])|(19[8]))\\d{8}$";
	public static String EMAIL_PATTERN_MATCHER = "\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b";
	
	
	public static String smsFreeSignName = "武汉白原科技";
	public static String smsTemplateCode = "SMS_58170004";
	
}
