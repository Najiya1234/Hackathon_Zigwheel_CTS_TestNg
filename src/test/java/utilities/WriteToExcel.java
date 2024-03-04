package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {
	public static void writeToExcel(Map<String, String[]> bikeUnder4La) throws IOException {
	 	FileInputStream file = null;
	 	XSSFWorkbook workbook = null;

//	 	file = new FileInputStream(".\\Excel\\New_Bikes.xlsx");
	 	file = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\NewBikes_Excel.xlsx");
	 	 workbook = new XSSFWorkbook(file);
	    XSSFSheet sheet = null;
	    try {
	    		sheet = workbook.createSheet("bike");
	    }
	    catch(Exception e) {
	    	sheet = workbook.getSheet("bike");
	    }


	    XSSFRow headerRow = sheet.createRow(0);
	    String[] headers = {"Manufacturing", "Bike Name", "Bike Price", "Launch Date"};
	    for (int i = 0; i < headers.length; i++) {
	        XSSFCell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        sheet.autoSizeColumn(i);
	    }

	    int rowNum = 1; 
	    for (Map.Entry<String, String[]> entry : bikeUnder4La.entrySet()) {
	        XSSFRow row = sheet.createRow(rowNum++);

	        row.createCell(0).setCellValue("Honda"); // Assuming all bikes are Honda rowNum++
	        row.createCell(1).setCellValue(entry.getKey());
	        row.createCell(2).setCellValue(entry.getValue()[0]);
	        row.createCell(3).setCellValue(entry.getValue()[1]);

	    }
	    try {
	        FileOutputStream fileoutput = null;
	        fileoutput= new FileOutputStream(System.getProperty("user.dir")+"\\TestData\\NewBikes_Excel.xlsx");;
	        
	        workbook.write(fileoutput);
	        workbook.close();
	        file.close();
	        System.out.println("Excel file has been generated successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}


