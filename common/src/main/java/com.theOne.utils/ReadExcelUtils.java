package com.peony.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtils {
	/**
	 * 对外提供读取excel 的方法
	 * */
	public static List<List<Object>> readExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 读取 office 2003 excel // *
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file)
			throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (j == 0) {
				}
				if (cell == null) {
					value = "";
				} else { // String
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						if (j == 0) {
						}
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell
									.getNumericCellValue()));
						} else {
							value = cell.getNumericCellValue();
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				}
				if (value == null || "".equals(value)) {
					value = "";
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

	/**
	 * 读取Office 2007 excel
	 * */
	private static List<List<Object>> read2007Excel(File file)
			throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径

		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				
				if (cell == null) {
					value = "";
				} else {
					  //创建一个样式 
					XSSFCellStyle cellStyle = xwb.createCellStyle(); 
						//创建一个DataFormat对象 
					XSSFDataFormat format = xwb.createDataFormat(); 
					//这样才能真正的控制单元格格式，@就是指文本型，具体格式的定义还是参考上面的原文吧 
					cellStyle.setDataFormat(format.getFormat("@")); 
					//具体如何创建cell就省略了，最后设置单元格的格式这样写 
					cell.setCellStyle(cellStyle);
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
																// String
																// 字符
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell
									.getNumericCellValue()));
						} else {
							value = df.format(cell.getNumericCellValue())+"";
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				}
				if (value == null || "".equals(value)) {
					value = "";
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
}
