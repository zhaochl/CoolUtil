package com.zcl.regx;

import org.junit.Assert;
import org.junit.Test;

public class RegexUtilTest {

	/**
	 * 验证邮箱
	 */
	@Test
	public void testCheckEmail() {
		boolean result = RegexUtil.checkEmail("zha2_ngsan@sina.com.cn");
		Assert.assertTrue(result);
	}

	/**
	 * 验证身份证号码
	 */
	@Test
	public void testCheckIdCard() {
		boolean result = RegexUtil.checkIdCard("432403193902273273");
		Assert.assertTrue(result);
	}

	/**
	 * 验证手机号码
	 */
	@Test
	public void testCheckMobile() {
		boolean result = RegexUtil.checkMobile("+8613620285733");
		Assert.assertTrue(result);
	}

	/**
	 * 验证电话号码
	 */
	@Test
	public void testCheckPhone() {
		boolean result = RegexUtil.checkPhone("+860738-4630706");
		Assert.assertTrue(result);
	}

	/**
	 * 验证整数（正整数和负整数）
	 */
	@Test
	public void testCheckDigit() {
		boolean result = RegexUtil.checkDigit("123132");
		Assert.assertTrue(result);
	}

	/**
	 * 验证小数和整数（正负整数和正负小数）
	 */
	@Test
	public void testCheckDecimals() {
		boolean result = RegexUtil.checkDecimals("-33.2");
		Assert.assertTrue(result);
	}

	/**
	 * 验证空白字符
	 */
	@Test
	public void testCheckBlankSpace() {
		boolean result = RegexUtil.checkBlankSpace("           ");
		Assert.assertTrue(result);
	}

	/**
	 * 匹配中文
	 */
	@Test
	public void testCheckChinese() {
		boolean result = RegexUtil.checkChinese("中文");
		Assert.assertTrue(result);
	}

	/**
	 * 验证日期
	 */
	@Test
	public void testCheckBirthday() {
		boolean result = RegexUtil.checkBirthday("1992/09/03");
		Assert.assertTrue(result);
	}

	/**
	 * 验证中国邮政编码
	 */
	@Test
	public void testCheckPostcode() {
		boolean result = RegexUtil.checkPostcode("417100");
		Assert.assertTrue(result);
	}

	/**
	 * 验证URL地址
	 */
	@Test
	public void testCheckURL() {
		boolean result = RegexUtil
				.checkURL("http://blog.csdn.com:80/xyang81/article/details?name=&abc=中文");
		Assert.assertTrue(result);
	}

	/**
	 * 验证IP地址
	 */
	@Test
	public void testCheckIpAddress() {
		boolean result = RegexUtil.checkIpAddress("192.1.22.255");
		Assert.assertTrue(result);
	}
}
