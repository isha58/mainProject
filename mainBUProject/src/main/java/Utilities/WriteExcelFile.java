package Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile {

	//writing in excel file
	public static void writePopularModels(ArrayList<String> arr) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		CellStyle style = workbook.createCellStyle(); 
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		XSSFRow row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("Popular Models");
		cell0.setCellStyle(style);
		
		for(int i=0; i<arr.size(); i++) {
			XSSFRow row = sheet.createRow(i+1);

			int colnum = 0;
			Cell cell = row.createCell(colnum);
			cell.setCellValue(arr.get(i));
			sheet.autoSizeColumn(colnum);
		}
		
		try {
			File file = new File("./Outputs/UsedCarsPopularModels.xlsx");
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void writeBikesDetails(ArrayList<String[]> bikesDetails) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		CellStyle style = workbook.createCellStyle(); 
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		XSSFRow row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		Cell cell1 = row0.createCell(1);
		Cell cell2 = row0.createCell(2);
		cell0.setCellValue("Bike Name");
		cell1.setCellValue("Price");
		cell2.setCellValue("Launch Date");
		cell0.setCellStyle(style);
		cell1.setCellStyle(style);
		cell2.setCellStyle(style);
		
		int rownum = 1;
		for(String[] details : bikesDetails) {
			XSSFRow row = sheet.createRow(rownum++);
			
			int colnum = 0;
			for(String detail : details) {
				sheet.autoSizeColumn(colnum);
				Cell cell = row.createCell(colnum++);
				cell.setCellValue(detail);
				
			}
		}
		
		try {
			File file = new File("./Outputs/UpcomingBikesDetails.xlsx");
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
