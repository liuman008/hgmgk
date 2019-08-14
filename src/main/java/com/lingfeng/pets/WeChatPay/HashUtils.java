package com.lingfeng.pets.WeChatPay;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HashUtils {
	public static byte[] hashMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(data);
        return bytes;
    }
	public static String hashMD5(String data) throws NoSuchAlgorithmException {
		return toHEX(hashMD5(data.getBytes()));
	}

    public static byte[] hashHMAC_SHA256(byte[] data, String signKey) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(data, signKey);
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        return mac.doFinal(data);
    }
    public static String hashHMAC_SHA256(String data, String signKey) throws NoSuchAlgorithmException, InvalidKeyException {
    	return toHEX(hashHMAC_SHA256(data.getBytes(), signKey));
    }

    public static String toHex(byte[] bytes) {
        char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);

        for(int i = 0; i < bytes.length; ++i) {
            ret.append(HEX_DIGITS[bytes[i] >> 4 & 15]);
            ret.append(HEX_DIGITS[bytes[i] & 15]);
        }

        return ret.toString();
    }
    
    public static String toHEX(byte[] bytes) {
        char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);

        for(int i = 0; i < bytes.length; ++i) {
            ret.append(HEX_DIGITS[bytes[i] >> 4 & 15]);
            ret.append(HEX_DIGITS[bytes[i] & 15]);
        }

        return ret.toString();
    }

}
