package tamploautomation.tamploautomationproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.ityss.tamplo.helper.resources.ResourceHelper;

public class WriteDataIntoExcel 
{

	@Test(enabled = false)
	public void wirtedata() throws IOException
	{
		 String path = ResourceHelper.getResourcePath("src/main/resources/propertiesfile/FF854100.xlsx");
		 
		 String[] dataToWrite = {"nitin","tajane","resr"};
		 FileInputStream fis = new FileInputStream(path);
		 
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 
		 XSSFSheet sheet = workbook.getSheet("Sheet1");
		 
		 
		 int gettotalbusyrow = sheet.getLastRowNum();
		 
		 int totalbusycolums = sheet.getRow(0).getLastCellNum();
		 
		
		 
		 for (int i = 0; i <= gettotalbusyrow; i++) 
		 
		 {
		    
			 Row row = sheet.getRow(i); // data already present means rows already created not need to create 
			 // if you create new row then allready present data will erase  
			 
			 Cell cell = row.createCell(totalbusycolums);
				
			 cell.setCellType(cell.CELL_TYPE_STRING);
			 
			 cell.setCellValue(dataToWrite[i]);
		 }
		
		 
		 FileOutputStream fos = new FileOutputStream(path);
		 
		 workbook.write(fos);
		 
		 fos.close();
		 
		 System.out.println("END OF WRITING DATA IN EXCEL");
	}
}
