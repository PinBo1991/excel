package com.dzpykj.excel.excel.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzpykj.excel.excel.dao.IExcelDao;
import com.dzpykj.excel.excel.service.IExcelService;

@Service
public class ExcelServiceImpl implements IExcelService {
	
	@Autowired
	IExcelDao excelDao;

	@Override
	public Object queryForList() {
		return excelDao.queryForList();
	}

	@Override
	public Object queryForObject() {
		return excelDao.queryForObject();
	}

	@Override
	public void excel2db(String filepath, String sheetName) {
		excelDao.excel2db(filepath,sheetName);
	}

	@Override
	public void file2db(InputStream in) {
		excelDao.file2db(in);
	}
}
