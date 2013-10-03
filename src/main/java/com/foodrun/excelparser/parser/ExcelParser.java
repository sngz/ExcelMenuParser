package com.foodrun.excelparser.parser;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.RecordFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.foodrun.excelparser.vo.MenuItemVo;
import com.foodrun.excelparser.vo.MenuPageVo;
import com.foodrun.excelparser.vo.RestaurantVo;
import com.foodrun.excelparser.vo.VariationVo;

public class ExcelParser extends FileParser {
	
	private Workbook workbook;
	Sheet sheet;
	
	public ExcelParser() {
		super();
	}

	public ExcelParser(File file) throws Exception {
		super(file);
		
		setFileStream(file);
		FileInputStream FileIS = getFileStream();
		
//		if( getExcelFileExtension() == ExcelFileExtension.XLSX) {
//		workbook = new XSSFWorkbook(FileIS);
//		}
//		else if (getExcelFileExtension() == ExcelFileExtension.XLS) {
			workbook = new HSSFWorkbook(FileIS);
//		}
//		else {
//			workbook = null;
//		}
//		
//		if( workbook == null) {
//			throw new Exception("Invalid file extension or format");
//		}
		sheet = workbook.getSheetAt(0);
	}
	
	
	
	/**
	 * Stores enum type for excel extension
	 *
	 */
	private enum ExcelFileExtension {
		XLS, XLSX;
	}	
	
	private ExcelFileExtension extension;
	
	public void setExcelFileExtension(ExcelFileExtension extension){
		this.extension = extension;
	}
	
	public ExcelFileExtension getExcelFileExtension() {
		return extension;
	} // End enum for Excel Extension
	

	@Override
	public Map<Integer, String> getFieldNames() throws Exception {				
		//Grab first row
		Row firstRow = sheet.getRow(sheet.getFirstRowNum());
		
		//Iterate each cell in first row for field names
		Map<Integer, String> columns = new HashMap<>();
		Iterator<Cell> cellIt = firstRow.cellIterator();
		while(cellIt.hasNext()) {
			Cell cell = cellIt.next();
			if (! cell.getStringCellValue().isEmpty() && cell.getStringCellValue() != null ) {
				columns.put(cell.getColumnIndex(), cell.getStringCellValue());
			}
		}
		
		return columns;
	}
	
	private List<String> getVariationTitles() throws RecordFormatException {
		List<String> variationTitlesList = new ArrayList<String>();
		
		//Grab second row
		Row secondRow = sheet.getRow(1);
		
		Iterator<Cell> cellIt = secondRow.cellIterator();
		while(cellIt.hasNext()) {
			Cell cell = cellIt.next();
			if (cell.getStringCellValue().isEmpty() || cell.getStringCellValue() == null ) {
				throw new RecordFormatException("Invalid Variations formatting");
			}
			else {
				variationTitlesList.add(cell.getStringCellValue());
			}
		}
		
		return variationTitlesList;
	}
	
	private List<VariationVo> generateVariationVos(List<String> variationTitles, Map<Integer, String> row) {
		int varTitleCounter = 0;				
		List<VariationVo> variationVoList = new ArrayList<VariationVo>();
		
		//start from the 4th index which is where the variations price starts
		for(int j=4; j <= row.size(); j++) {						
			//If Variation doesn't exist add a null value into array index to show 
			if( row.get(j) == null || row.get(j).isEmpty() ) {
				variationVoList.add(null);
				varTitleCounter++;
			}
			//Create new VariationVo to store variation title and price and add to VariationVo List
			else {
				//Change price from String to BigDecimal, remove dollar sign if exists.
				String ssPrice = row.get(j);
				String sPrice = ssPrice.replace("$", "");
				BigDecimal Price = new BigDecimal(sPrice);
				
				VariationVo variation = new VariationVo(variationTitles.get(varTitleCounter), Price);
				variationVoList.add(variation);
				varTitleCounter++;
			}
		}
		
		return variationVoList;
	}
	
	@Override
	public List<Map<Integer, String>> getSheetRows() throws Exception{
		List<Map<Integer, String>> rowCollection = new ArrayList<Map<Integer, String>>();
		
		Iterator<Row> rowIt = sheet.rowIterator();
		//skip first two rows since it's fieldnames / variations
		if (rowIt.hasNext()) {
			rowIt.next();
			if(rowIt.hasNext()) {
				rowIt.next();
			}
		}
		
		while (rowIt.hasNext()) {
			Row row = rowIt.next();
			Map<Integer, String> rowValue = new HashMap<>();
			
			Iterator<Cell> cellIt = row.cellIterator();
			while (cellIt.hasNext()) {
				Cell cell = cellIt.next();
				String value = (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
			            ? Double.toString(cell.getNumericCellValue())
			            : cell.getStringCellValue();
				rowValue.put(cell.getColumnIndex(), value);
			}
			rowCollection.add(rowValue);
		}
		return rowCollection;
	}
	
	public RestaurantVo generateVos(List<Map<Integer, String>> rowCollection) throws RecordFormatException {
		List<MenuPageVo> menuPagesList = new ArrayList<MenuPageVo>();
		List<MenuItemVo> menuItemsList = new ArrayList<MenuItemVo>();
		
		List<String> variationTitles = getVariationTitles();
		
		//Loop through rows
		for (int i = 0; i< rowCollection.size(); i++) {			
			Map<Integer, String> row = rowCollection.get(i);
			//Not empty = new menu page
			if (row.get(0) != null && !row.get(0).isEmpty() ) {
				MenuPageVo menuPage = new MenuPageVo(row.get(0), menuItemsList);
				menuPagesList.add(menuPage);
				menuPage.setMenuItems(menuItemsList);
				if (i != 0) {
					menuItemsList = new ArrayList<>();	
				}				
				
				//Go through cells for row and populate menu item info (excluding variations)
				MenuItemVo menuItem = new MenuItemVo(row.get(1), row.get(2), row.get(3), generateVariationVos(variationTitles, row));
				menuItemsList.add(menuItem);
			}
			else {
				MenuItemVo menuItem = new MenuItemVo(row.get(1), row.get(2), row.get(3), generateVariationVos(variationTitles, row));
				menuItemsList.add(menuItem);
			}
		}
		
		RestaurantVo restaurant = new RestaurantVo("Chipotle", "001", menuPagesList, variationTitles);
		return restaurant;
	}
	
}
