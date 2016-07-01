package org.apache.poi.hssf.usermodel.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * File for XSSF testing/examples
 *
 * THIS IS NOT THE MAIN XSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 *
 * @see #main
 * @author Andrew Oliver (acoliver at apache dot org)
 */
public final class XSSFReadWrite {

	/**
	 * creates an {@link XSSFWorkbook} the specified OS filename.
	 */
	private static XSSFWorkbook readFile(String filename) throws IOException
	{
		return new XSSFWorkbook(new FileInputStream(filename));
	}

	/**
	 * given a filename this outputs a sample sheet with just a set of
	 * rows/cells.
	 */
	/*private static void testCreateSampleSheet(String outputFilename) throws IOException
	{
		int rownum;
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet s = wb.createSheet();
		XSSFCellStyle cs = wb.createCellStyle();
		XSSFCellStyle cs2 = wb.createCellStyle();
		XSSFCellStyle cs3 = wb.createCellStyle();
		XSSFFont f = wb.createFont();
		XSSFFont f2 = wb.createFont();

		f.setFontHeightInPoints((short) 12);
		f.setColor((short) 0xA);
		f.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		f2.setFontHeightInPoints((short) 10);
		f2.setColor((short) 0xf);
		f2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		//cs.setDataFormat(XSSFDataFormat.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setFillPattern((short) 1); // fill w fg
		cs2.setFillForegroundColor((short) 0xA);
		cs2.setFont(f2);
		wb.setSheetName(0, "XSSF Test");
		for (rownum = 0; rownum < 300; rownum++) {
			XSSFRow r = s.createRow(rownum);
			if ((rownum % 2) == 0) {
				r.setHeight((short) 0x249);
			}

			for (int cellnum = 0; cellnum < 50; cellnum += 2) {
				XSSFCell c = r.createCell(cellnum);
				c.setCellValue(rownum * 10000 + cellnum
						+ (((double) rownum / 1000) + ((double) cellnum / 10000)));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs);
				}
				c = r.createCell(cellnum + 1);
				c.setCellValue(new XSSFRichTextString("TEST"));
				// 50 characters divided by 1/20th of a point
				s.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs2);
				}
			}
		}

		// draw a thick black border on the row at the bottom using BLANKS
		rownum++;
		rownum++;
		XSSFRow r = s.createRow(rownum);
		cs3.setBorderBottom(XSSFCellStyle.BORDER_THICK);
		for (int cellnum = 0; cellnum < 50; cellnum++) {
			XSSFCell c = r.createCell(cellnum);
			c.setCellStyle(cs3);
		}
		s.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
		s.addMergedRegion(new CellRangeAddress(100, 110, 100, 110));

		// end draw thick black border
		// create a sheet, set its title then delete it
		s = wb.createSheet();
		wb.setSheetName(1, "DeletedSheet");
		wb.removeSheetAt(1);

		// end deleted sheet
		FileOutputStream out = new FileOutputStream(outputFilename);
		wb.write(out);
		out.close();
	}*/

	/**
     * Method main
     *
     * Given 1 argument takes that as the filename, inputs it and dumps the
     * cell values/types out to sys.out.<br/>
     *
     * given 2 arguments where the second argument is the word "write" and the
     * first is the filename - writes out a sample (test) spreadsheet
     * see {@link XSSFReadWrite#testCreateSampleSheet(String)}.<br/>
     *
     * given 2 arguments where the first is an input filename and the second
     * an output filename (not write), attempts to fully read in the
     * spreadsheet and fully write it out.<br/>
     *
     * given 3 arguments where the first is an input filename and the second an
     * output filename (not write) and the third is "modify1", attempts to read in the
     * spreadsheet, deletes rows 0-24, 74-99.  Changes cell at row 39, col 3 to
     * "MODIFIED CELL" then writes it out.  Hence this is "modify test 1".  If you
     * take the output from the write test, you'll have a valid scenario.
     */
	public static void main(String[] args) {

		File fileName = new File("d:\\book1.xlsx");
		try {
			if (args.length < 2) {

				//XSSFWorkbook wb = XSSFReadWrite.readFile(fileName);
				org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fileName);

				System.out.println("Data dump:\n");

				for (int k = 0; k < wb.getNumberOfSheets(); k++)
				{
					org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(k);
					int rows = sheet.getPhysicalNumberOfRows();
					System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows + " row(s).");
					for (int r = 0; r < rows; r++)
					{
						Row row = sheet.getRow(r);
						if (row == null)
						{
							continue;
						}

						int cells = row.getPhysicalNumberOfCells();
						System.out.println("\nROW " + row.getRowNum() + " has " + cells + " cell(s).");
						for (int c = 0; c < cells; c++)
						{
							Cell cell = row.getCell(c);
							String value = null;

							switch (cell.getCellType())
							{

								case XSSFCell.CELL_TYPE_FORMULA:
									value = "FORMULA value=" + cell.getCellFormula();
									break;

								case XSSFCell.CELL_TYPE_NUMERIC:
									value = "NUMERIC value=" + cell.getNumericCellValue();
									break;

								case XSSFCell.CELL_TYPE_STRING:
									value = "STRING value=" + cell.getStringCellValue();
									break;

								default:
							}
							System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
									+ value);
						}
					}
				}
			} 
			else if (args.length == 2)
			{
				/*if (args[1].toLowerCase().equals("write")) {
					System.out.println("Write mode");
					long time = System.currentTimeMillis();
					XSSFReadWrite.testCreateSampleSheet(fileName);

					System.out.println("" + (System.currentTimeMillis() - time)
							+ " ms generation time");
				} else {
					System.out.println("readwrite test");
					XSSFWorkbook wb = XSSFReadWrite.readFile(fileName);
					FileOutputStream stream = new FileOutputStream(args[1]);

					wb.write(stream);
					stream.close();
				}*/
			}
			else if (args.length == 3 && args[2].toLowerCase().equals("modify1"))
			{
				// delete row 0-24, row 74 - 99 && change cell 3 on row 39 to string "MODIFIED CELL!!"

				/*XSSFWorkbook wb = XSSFReadWrite.readFile(fileName);
				FileOutputStream stream = new FileOutputStream(args[1]);
				XSSFSheet sheet = wb.getSheetAt(0);

				for (int k = 0; k < 25; k++) {
					XSSFRow row = sheet.getRow(k);

					sheet.removeRow(row);
				}
				for (int k = 74; k < 100; k++) {
					XSSFRow row = sheet.getRow(k);

					sheet.removeRow(row);
				}
				XSSFRow row = sheet.getRow(39);
				XSSFCell cell = row.getCell(3);
				cell.setCellValue("MODIFIED CELL!!!!!");

				wb.write(stream);
				stream.close();*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}