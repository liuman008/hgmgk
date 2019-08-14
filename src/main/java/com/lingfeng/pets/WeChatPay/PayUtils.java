package com.lingfeng.pets.WeChatPay;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;




public class PayUtils {

	public static String mapToXML(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<xml>\n");

		for (String key : map.keySet()) {
			String value = map.get(key);

			if (value != null && !value.isEmpty()) {
				sb.append("<").append(key).append(">");
				sb.append("<![CDATA[");
				sb.append(value);
				sb.append("]]>");
				sb.append("</").append(key).append(">\n");
			}
		}

		sb.append("</xml>");

		return sb.toString();
	}

	/**
	 * 生成报文签名
	 */
	public static String getSign(Map<String, String> map, String signKey)
			throws NoSuchAlgorithmException, InvalidKeyException {
		
		List<String> sortedKeys = new ArrayList<>();
		sortedKeys.addAll(map.keySet());
		sortedKeys.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		StringBuilder url = new StringBuilder();

		for (String key : sortedKeys) {
			String value = map.get(key);

			if (value != null && !value.isEmpty()) {
				if (url.length() > 0) {
					url.append("&");
				}

				url.append(key).append("=").append(value);
			}
		}

		if (url.length() > 0) {
			url.append("&");
		}
		url.append("key=").append(signKey);

		/*
		 * 对URL参数字符串进行加密； HMAC-SHA256(MD5("url").toUpperCase(),
		 * "signKey").toUpperCase()
		 */
		
		String md5 = HashUtils.hashMD5(url.toString());

		return md5;
	}
	
	
	/**
	 * 获取远程主机。
	 */
	public static String getHttpServletRequestRemoteHost(HttpServletRequest request) {
		String xRealIp = request.getHeader("X-Real-IP");
		
		if (xRealIp != null && !xRealIp.isEmpty()) {
			return xRealIp;
		}
		
		String xForwardFor = request.getHeader("X-Forwarded-For");
		if (xForwardFor != null && !xForwardFor.isEmpty()) {
			String[] xForwardForArray = xForwardFor.split(",");
			return xForwardForArray[0].trim();
		}
		
		return request.getRemoteAddr();
	}
	
	public static Map<String,String> readStringXmlOut(String xml) {  
        Map<String,String> map = new HashMap<String,String>();  
        Document doc = null;  
        try {  
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
            Element rootElt = doc.getRootElement(); // 获取根节点  
            List<Element> list = rootElt.elements();//获取根节点下所有节点  
            for (Element element : list) {  //遍历节点  
                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value  
            }  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return map;  
    }  


	
}
