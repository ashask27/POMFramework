package com.qa.opencart.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static String TEST_DATA_SHEET = ".\\src\\test\\resources\\testdata\\registrationData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		
		System.out.println("reading data from sheet " + sheetName);
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];		
			for (int i=0; i<sheet.getLastRowNum(); i++) {
				for (int j=0; j< sheet.getRow(0).getLastCellNum() ; j++ ) {
					// skip first row as it has column names
					System.out.println(" i ==>  " + i + " j ===> " + j + " cells => " + sheet.getRow(0).getLastCellNum());
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();	
					System.out.println(" ==>  " + data[i][j]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
