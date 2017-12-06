package com.dzpykj.excel.excel.service;

import java.io.InputStream;

public interface IExcelService {
	
	/**
	 * 查询tb_test表中所有的数据,并保存到List-Map中
	 */
	public Object queryForList();
	
	/**
	 * 查询tb_test表中所有的数据,保存到List-Object中
	 */
	public Object queryForObject();

	/**
	 * 将excel文件的内容放入数据库
	 * @param filepath excel文件位置
	 * @param sheetName 页
	 */
	public void excel2db(String filepath, String sheetName);
	
	/**
	 * 将excel文件的内容放入数据库
	 * @param in 文件的输入流
	 */
	public void file2db(InputStream in);
	
}
