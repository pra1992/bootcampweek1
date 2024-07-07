package bootcamp3pom.pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {

	public static Object[][] readExcelData(String SheetName) throws IOException {
   
	XSSFWorkbook wBook = new XSSFWorkbook("C:\\Testleaf\\MavenProject\\testData\\DataSheet.xlsx");
  
   XSSFSheet sheet = wBook.getSheet(SheetName);
   System.out.println(sheet);
   
   int rowCount = sheet.getLastRowNum();
   int colCount = sheet.getRow(0).getLastCellNum();
   String[][] data = new String[rowCount][colCount];
  
   for(int i = 1; i<=rowCount ; i++) {
	   
	   try { 
	   XSSFRow row = sheet.getRow(i);
	   
	   for(int j=0; i< colCount; j++) {
		   
		   XSSFCell cell = row.getCell(j);
		   
		   String CellValue = cell.getStringCellValue();
		   data[i-1][j] = CellValue;
	   }}
   catch(NullPointerException e) {
	   break;
   }
   }
   
   wBook.close();
    return data;
	}
	
	
	
}