package com.peony.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.bstek.dorado.data.variant.Record;

public class StringTools {	
	
	/**
	 * 替换掉字符中所有的全角和半角空格/换行符/回车符
	 * @param str
	 * @return
	 */
	public static String trimAll(String str) {
		return str.replaceAll(" |　|\n|\r", "");
	}
	
	public static int lengthb(String str) {
		return lengthb("GBK");
	}
	
	public static int lengthb(String str, String encode) throws UnsupportedEncodingException{
		return str.getBytes(encode).length;
	}

	/**
	 * @param size 字节数
	 * @return 格式化的尺寸文本串
	 */
	public static String formatByte(long size) {
		// M
		if (size / (1024 * 1024) > 0) {
			double result = (double) size / (1024 * 1024);
			return new java.text.DecimalFormat("0.00").format(result) + " MB";
		}
		// K
		if (size / 1024 > 0) {
			double result = (double) size / 1024;
			return new java.text.DecimalFormat("0.00").format(result) + " KB";
		}
		// B
		return size + " B";
	}
	
	/**
	 * String->Object.
	 * 
	 * @param value 待转换的字符串.
	 * @param toType 转换目标类型.
	 */
	public static Object fromString(String value, Class<?> toType) {
		try {
			return ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * Object -> String.
	 */
	public static String toString(Object object) {
		try {
			return ConvertUtils.convert(object);
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
	}
	
	public static String format(Object object, int maxLength){
		try{
			String result = "";
			if(object instanceof String)
				result = (String)object;
			else if(object instanceof Number)
				result = ((Number)object).toString();
			else if(object instanceof Record){
				Record record = (Record)object;
				Set<String> keys = record.keySet();
				StringBuilder msb = new StringBuilder(100);
				for(String key : keys){
					Object value = record.get(key);
					msb.append(key).append(":　").append(value == null ? "" : 
						value).append("\n");					
				}
				result = msb.toString();
			}else if(object instanceof Map){
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String,Object>)object;
				Set<String> keys = map.keySet();
				StringBuilder msb = new StringBuilder(100);
				for(String key : keys){
					Object value = map.get(key);
					msb.append(key).append(":　").append(value == null ? "" : 
						value).append("\n");					
				}
				result = msb.toString();
			}else{
				result = ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE, false);
			}
			return result.getBytes().length > maxLength ? cutString(result, maxLength) : result;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	// 根据Unicode编码完美的判断中文汉字和符号
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
	
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断字符串是否包含中文
	 * @param strName
	 * @return
	 */
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}
		
	 /** 
     * 按照指定字节长度截取字符串，防止中文被截成一半的问题 
     * 截止按照数据库的GBK编码
     * @param str 源字符串 
     * @param length 截取的字节数 
     * @return 截取后的字符串 
     * @throws UnsupportedEncodingException  
     */  
	public static String cutString(String str, int length) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("Unicode");
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		/**
		 * 在JAVA声明周期中, 都是在内存中以UCS2进行编码
		 */
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i - 1;
			}
			// 该UCS2字符是字母或数字，则保留该字符
			else {
				i = i + 1;
			}
		}
		return new String(bytes, 0, i, "Unicode");
	}
    
	/**
	 * 用于组sql语句中的in字符串 
	 * 例如 in=a,b,c,d split='
	 * 返回'a','b','c'
	 * @param in
	 * @param split
	 * @return
	 */
	public static String transSqlInString(String in,String split){
		String[] inArr = in.split(",");
		StringBuilder sqlString  = new StringBuilder().append(split).append(inArr[0]).append(split);
		for(int i =1 ;i<inArr.length;i++){
			sqlString.append(",").append(split).append(inArr[i]).append(split);
		}
		return sqlString.toString();
	}

}
