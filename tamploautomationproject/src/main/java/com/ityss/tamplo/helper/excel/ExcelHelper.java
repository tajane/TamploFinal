package com.ityss.tamplo.helper.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.ityss.tamplo.helper.resources.ResourceHelper;

public class ExcelHelper 
{
	static BufferedInputStream bufferedInputStream;
	static Workbook workbook;
	static Sheet sheet;
	static String filename = ResourceHelper.getResourcePath("src/main/resources/propertiesfile/tamplodata.xlsx");
	
	public static Object[][] getExcelData(String sheetname)  
	{
		try
		{
		bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
		} catch (IOException e)  {  e.printStackTrace();  }
		
		try
		{ 
		workbook = WorkbookFactory.create(bufferedInputStream); } catch (IOException e) { e.printStackTrace();   }  
		catch (InvalidFormatException e) {   e.printStackTrace(); }
		
		sheet = workbook.getSheet(sheetname);
		
		int getlastrownum = sheet.getLastRowNum();
		int getlastcellnum = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[getlastrownum][getlastcellnum];
		
		for (int i = 0; i < getlastrownum; i++) 
		{
			Row row = sheet.getRow(i+1);
			
			for (int j = 0; j < getlastcellnum; j++) 
			{
				Cell cell = row.getCell(j);
				String values = null;
				
				try 
				{
					values = cell.getStringCellValue().toString();
					
				} catch (Error e) 
				{
					values = ((XSSFCell)cell).getRawValue();
				}catch (NullPointerException e) 
				{
					
				}
				
				data[i][j] = values;
			}
		}
		return data;	
	}

}
