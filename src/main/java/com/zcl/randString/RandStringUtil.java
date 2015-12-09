package com.zcl.randString;


import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class RandStringUtil {
	
	public static void main(String[] args) {
		
		String str ="abc";
		//base64
		byte[] b = Base64.encodeBase64(str.getBytes(), true);
		System.out.println(new String(b));
		
		//md5
		System.out.println(DigestUtils.md2Hex(str));
		
		//SHA1
		System.out.println(DigestUtils.sha1Hex(str));
		
		//random string
		String s = RandomStringUtils.randomAlphabetic(255);
		System.out.println(s);
		
	}

}
