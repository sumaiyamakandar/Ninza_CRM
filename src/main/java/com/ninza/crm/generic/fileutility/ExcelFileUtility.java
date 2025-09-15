package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String toReadTheDataFromExcel(String Campaign,int rowNum,int CellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fs =new FileInputStream("./src/test/resources/Book1.xlsx");
		
		Workbook wb =WorkbookFactory.create(fs);
		
		String data =wb.getSheet(Campaign).getRow(rowNum).getCell(CellNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	
	
	
	public int toGetTheRowCount(String Campaign) throws EncryptedDocumentException, IOException {
		FileInputStream fs =new FileInputStream("./src/test/resources/Book1.xlsx");
	
		Workbook wb =WorkbookFactory.create(fs);

		int lastRowNum =wb.getSheet(Campaign).getLastRowNum();
		return lastRowNum;
	}

	public String ReadTheDataFromExcelLead(String lead, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
FileInputStream fs =new FileInputStream("./src/test/resources/testcase1.xlsx");
		
		Workbook wb =WorkbookFactory.create(fs);
		
		String data =wb.getSheet(lead).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	public String ReadTheDataFromExcelproduct(String product, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fs =new FileInputStream("./src/test/resources/Book2.xlsx");
				
				Workbook wb =WorkbookFactory.create(fs);
				
				String data =wb.getSheet(product).getRow(rowNum).getCell(cellNum).getStringCellValue();
				wb.close();
				return data;
			}

//	private void getLastRowNum() throws EncryptedDocumentException, IOException {
	//	FileInputStream fs =new FileInputStream("./resourcs/Book1.xlsx");
		
	//	Workbook wb =WorkbookFactory.create(fs);
		
	//	for(int i=1; i<=lastRowNum;i++) {

			
	//	}
		
	}
//}


