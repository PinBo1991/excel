package com.dzpykj.excel.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {
	
	public static void main(String[] args) {
		createExcel("d:/1.xls","第一页");
	}
	
	public static void createExcel(String filepath,String sheetName){
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		OutputStream os = null;
		try {
			//创建Excel文件
			wb = new HSSFWorkbook();
			//创建sheet页
			sheet = wb.createSheet(sheetName);
			//创建行
			row = sheet.createRow(0);
			//根据列的位置创建单元格
			cell = row.createCell(0);
			//向单元格添加数据
			cell.setCellValue("测试");
			//输出文件到硬盘
			os = new FileOutputStream(filepath);
			wb.write(os);
		} catch (Exception e) {
			if(os != null){
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}		
	}
	
	public static void db2excel(String filepath,String sheetName,List<Map<String,Object>> list){
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		OutputStream os = null;
		int rowNum = 0;
		int cellNum = 0;
		try {
			//创建Excel文件
			wb = new HSSFWorkbook();
			//创建sheet页
			sheet = wb.createSheet(sheetName);
			for (Map<String,Object> map : list) {
				cellNum=0;
				//创建行
				row = sheet.createRow(rowNum);
				for (String key : map.keySet()) {
					//根据列的位置创建单元格
					cell = row.createCell(cellNum);
					//向单元格添加数据
					cell.setCellValue(map.get(key)+"");
					cellNum++;
				}
				rowNum++;
			}
			//输出文件到硬盘
			os = new FileOutputStream(filepath);
			wb.write(os);
		} catch (Exception e) {
			if(os != null){
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}		
	}
}
