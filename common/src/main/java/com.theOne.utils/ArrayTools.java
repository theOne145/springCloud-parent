package com.peony.core.utils;

public class ArrayTools {
	
	public static String arrayToString(String[] strs) {
		if (strs == null || strs.length < 1)
			return "";
		StringBuilder buf = new StringBuilder();
		buf.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			buf.append(",");
			buf.append(strs[i]);
		}
		return buf.toString();
	}

	public static String arrayToString(String[] strs, String splitter) {
		if (strs == null || strs.length < 1)
			return "";
		StringBuilder buf = new StringBuilder();
		buf.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			buf.append(splitter);
			buf.append(strs[i]);
		}
		return buf.toString();
	}
	
	public static Object expand(Object[] array, int newSize) {
		if (array == null)
			return null;
		int len = array.length;
		if (len >= newSize)
			return array;
		else {
			Object newArray = new Object[newSize];
			System.arraycopy(array, 0, newArray, 0, len);
			return newArray;
		}
	}
	
	/**
	 * 删除数组中的某个元素
	 */
	public static String[] deleteArrayItem(String[] strArray, String delItem) {
		if (strArray == null || strArray.length <= 0)
			return strArray;
		for (int i = 0; i < strArray.length; i++) {
			if (delItem.equals(strArray[i])) {
				String[] newArray = new String[strArray.length - 1];
				System.arraycopy(strArray, 0, newArray, 0, i);
				System.arraycopy(strArray, i + 1, newArray, i, strArray.length
						- i - 1);
				return newArray;
			}
		}
		return strArray;
	}

}
