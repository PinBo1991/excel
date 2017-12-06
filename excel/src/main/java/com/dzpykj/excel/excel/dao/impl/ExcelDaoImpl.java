package com.dzpykj.excel.excel.dao.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dzpykj.excel.excel.dao.IExcelDao;
import com.dzpykj.excel.excel.entity.Excel;

@Repository
public class ExcelDaoImpl implements IExcelDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Object queryForList() {
		String sql = "select * from tb_test";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public Object queryForObject() {
		String sql = "select feiyong,xiangxi,jine from tb_test";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Excel.class));
	}

	@Override
	public void excel2db(String filepath, String sheetName) {
		String sql = "insert into tb_test_bak(feiyong,xiangxi,jine) values(?,?,?)";
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		try {
			wb = WorkbookFactory.create(new File(filepath));
			sheet = wb.getSheetAt(0);
//			System.out.println(sheet.getLastRowNum());
			List<Object[]> rowl = new ArrayList<Object[]>();
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
//				System.out.println(row.getLastCellNum());
				List<Object> celll = new ArrayList<Object>();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					celll.add(getCellValue(cell));
//					System.out.print(getCellValue(cell)+" ");
				}
				rowl.add(celll.toArray());
//				System.out.println();
			}
			jdbcTemplate.batchUpdate(sql, rowl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public Object getCellValue(Cell cell){
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

	@Override
	public void file2db(InputStream in) {
		String sql = "insert into tb_test_bak(feiyong,xiangxi,jine) values(?,?,?)";
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		try {
			wb =  new HSSFWorkbook(in);
			sheet = wb.getSheetAt(0);
//			System.out.println(sheet.getLastRowNum());
			List<Object[]> rowl = new ArrayList<Object[]>();
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
//				System.out.println(row.getLastCellNum());
				List<Object> celll = new ArrayList<Object>();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					celll.add(getCellValue(cell));
//					System.out.print(getCellValue(cell)+" ");
				}
				rowl.add(celll.toArray());
//				System.out.println();
			}
			jdbcTemplate.batchUpdate(sql, rowl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
