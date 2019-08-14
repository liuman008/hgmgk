package com.lingfeng.pets.WeChatPay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
	public static String readText(InputStream inputStream, String charset) throws IOException {
		return new String(readBytes(inputStream), charset);
	}
	
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024 * 4];
			int offset;
			
			while ((offset = inputStream.read(buffer, 0, buffer.length)) > 0) {
				baos.write(buffer, 0, offset);
			}
			
			return baos.toByteArray();
		}
	}
	
	public static void writeText(OutputStream outputStream, String text, String charset) throws IOException {
		writeBytes(outputStream, text.getBytes(charset));
	}
	
	public static void writeBytes(OutputStream outputStream, byte[] bytes) throws IOException {
		outputStream.write(bytes);
	}
	
	public static void writeBytes(OutputStream outputStream, InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024 * 4];
		int offset;
		
		while ((offset = inputStream.read(buffer, 0, buffer.length)) > 0) {
			outputStream.write(buffer, 0, offset);
		}
	}
}
