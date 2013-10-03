package com.foodrun.excelparser;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.foodrun.excelparser.dao.Restaurant2Dao;
import com.foodrun.excelparser.export.RestaurantXmlExporter;
import com.foodrun.excelparser.parser.ExcelParser;
import com.foodrun.excelparser.vo.RestaurantVo;

public class App 
{
    public static void main( String[] args ) throws Exception
    {       
    	Restaurant2Dao restaurant2Dao = new Restaurant2Dao();
    	
    	String restaurantTest = restaurant2Dao.saveRestaurant2("ANTHONYS TEST RESTAURANT");
    }
    
    public RestaurantVo generateRestaurantVo(String path) throws Exception {
    	ExcelParser parser = new ExcelParser(new File(path));
    	
    	List<Map<Integer, String>> rows = parser.getSheetRows();
        RestaurantVo restaurant = parser.generateVos(rows);
        
        return restaurant;
    }
    
    public void printExcelTables() throws Exception {
    	String path = "C:\\Projects\\ExcelMySqlParser\\Importer.xls";
        ExcelParser parser = new ExcelParser(new File(path));
        
        try {
        	Map<Integer, String> fieldNames = parser.getFieldNames();
            for (Map.Entry<Integer, String> fieldName: fieldNames.entrySet()) {
            	System.out.print(fieldName.getValue() + ", ");
            }
            
            List<Map<Integer, String>> rows = parser.getSheetRows();
            for (Map<Integer, String> row: rows) {
              for (Map.Entry<Integer, String> entry: row.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
              }
              
              System.out.println("");             
            }
        } catch (Exception ex) {
        	System.out.println("Exception occured: " + ex.getMessage());
        }
    }
    
    public void exportXml(String path) {
    	try {      
            RestaurantVo restaurant = generateRestaurantVo(path);
            
            RestaurantXmlExporter restaurantXmlExporter = new RestaurantXmlExporter(restaurant);
            restaurantXmlExporter.generateXml();
    	} catch (Exception ex) {
    		System.out.println("Exception occured: " + ex.getMessage());
    	}   	
    }
}
