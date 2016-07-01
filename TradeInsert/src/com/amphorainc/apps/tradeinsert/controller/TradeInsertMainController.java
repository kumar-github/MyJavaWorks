package com.amphorainc.apps.tradeinsert.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.amphorainc.apps.tradeinsert.model.TradeInsertMainModel;
import com.amphorainc.apps.tradeinsert.view.TradeInsertMainView;

public class TradeInsertMainController
{
	private TradeInsertApplicationController tradeInsertApplicationController;
	private TradeInsertMainView tradeInsertMainView;
	private TradeInsertMainModel tradeInsertMainModel;

	private TradeInsertPreferenceController tradeInsertPreferenceController;

	public TradeInsertMainController()
	{
		this.tradeInsertMainView = new TradeInsertMainView();
		this.tradeInsertMainModel = new TradeInsertMainModel();

		addListeners();
	}

	public TradeInsertMainController(TradeInsertMainView tradeInsertMainView, TradeInsertMainModel tradeInsertMainModel)
	{
		this.tradeInsertMainView = tradeInsertMainView;
		this.tradeInsertMainModel = tradeInsertMainModel;

		addListeners();
	}

	public TradeInsertMainController(TradeInsertApplicationController tradeInsertApplicationController)
	{
		this.tradeInsertApplicationController = tradeInsertApplicationController;
		this.tradeInsertMainView = new TradeInsertMainView();
		this.tradeInsertMainModel = new TradeInsertMainModel();

		addListeners();
	}

	private void addListeners()
	{
		this.tradeInsertMainView.getInfoPanelMenuItem().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			}
		});

		this.tradeInsertMainView.getPreferencesMenuItem().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				/* logic to show preferences panel */
				tradeInsertPreferenceController = new TradeInsertPreferenceController(tradeInsertApplicationController);
				tradeInsertPreferenceController.showGUI();
			}
		});

		this.tradeInsertMainView.getExitMenuItem().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		this.tradeInsertMainView.getBrowseButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				selectExcelFile();
			}
		});

		this.tradeInsertMainView.getExitApplicationButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void selectExcelFile()
	{
		JFileChooser excelFileChooser = new JFileChooser();
		excelFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		excelFileChooser.setAcceptAllFileFilterUsed(false);

		excelFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("All Excel Files", "xls", "xlsx"));
		//excelFileChooser.setFileFilter(new FileNameExtensionFilter("Excel Workbook", "xls", "xlsx"));

		if (excelFileChooser.showOpenDialog(tradeInsertMainView) == JFileChooser.APPROVE_OPTION)
		{
			tradeInsertMainView.getFileNameTextField().setText(excelFileChooser.getSelectedFile().getAbsolutePath());
		}
	}

	private void loadTradesFromExcelFile()
	{
		try
		{
			File anExcelFile = new File(tradeInsertMainView.getFileNameTextField().getText());
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

	public void showGUI()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				tradeInsertMainView.showGUI();
			}
		});
	}
}