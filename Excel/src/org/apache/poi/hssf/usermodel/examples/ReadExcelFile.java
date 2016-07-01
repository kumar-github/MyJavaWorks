package org.apache.poi.hssf.usermodel.examples;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelFile
{
	public static void main(String[] args)
	{
		//File anExcelFile = new File("d:\\Book1.xls");
		File anExcelFile = new File("d:\\tradeinsertswaps.xlsx");
		
		try
		{
			Workbook workBook = WorkbookFactory.create(anExcelFile);
			
			int totalSheets = workBook.getNumberOfSheets();
			System.out.println("totalSheets : " + totalSheets);
			
			Sheet sheet1 = workBook.getSheetAt(0);
			
			int rows = sheet1.getPhysicalNumberOfRows();
			System.out.println("Total rows : " + rows);
			
			for(int i=0;i<rows;i++)
			{
				Row row = sheet1.getRow(i);
				
				if(row == null)
					continue;
				int cells = row.getPhysicalNumberOfCells();
				System.out.println("Total cells at row#" + i + " : " + cells );
				
				for(int j=0; j<cells; j++)
				{
					Cell cell = row.getCell(j);
					String value = null;
					
					switch (cell.getCellType())
					{
					case Cell.CELL_TYPE_FORMULA:
						value = "FORMULA value=" + cell.getCellFormula();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						value = "NUMERIC value=" + cell.getNumericCellValue();
						break;
					case Cell.CELL_TYPE_STRING:
						value = "STRING value=" + cell.getStringCellValue();
						break;
					default:
					}
					System.out.println(cell.getColumnIndex() + " -- " + value);
				}
			}
			
		}
		catch (EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}