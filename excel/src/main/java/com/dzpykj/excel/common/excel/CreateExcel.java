package com.dzpykj.excel.common.excel;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CreateExcel {
	public static void main(String[] args) {
			Workbook wb = null;
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			try {
				wb = WorkbookFactory.create(new File("d:/2.xls"));
				sheet = wb.getSheetAt(0);
//				System.out.println(sheet.getLastRowNum());
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
//					System.out.println(row.getLastCellNum());
					for (int j = 0; j < row.getLastCellNum(); j++) {
						cell = row.getCell(j);
						System.out.print(getCellValue(cell)+" ");
					}
					System.out.println();
				}
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	
	@SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell){
		String value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue()+"";
			break;
			
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue()+"";
			break;
		default:
			break;
		}
		return value;
	}
	
}
