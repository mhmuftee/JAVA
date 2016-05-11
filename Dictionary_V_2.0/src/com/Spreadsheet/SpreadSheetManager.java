package com.Spreadsheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import main.Configuration;
import main.Constants;
import main.StringUtils;

public class SpreadSheetManager {

	private HSSFWorkbook dictionaryXls;
	private File localDictionary;
	private ManageWordMapping mWm;
	private HSSFCellStyle myStyle;
	private ArrayList<Data> words;
	private HashMap<String, String> wordsMap;
	public SpreadSheetManager() {
		mWm = new ManageWordMapping();
		wordsMap = mWm.getWordsMap();
	}

	private void closeXLS(File xlsFile) throws IOException {
		FileOutputStream fout = new FileOutputStream(xlsFile);
		dictionaryXls.write(fout);
		fout.close();
	}

	private void getWordsFromTempFile() {
		// TODO Auto-generated method stub
		words = mWm.getDictionary();
	}

	void insertStringinCell(HSSFCell cell, String data) {
		myStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(myStyle);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(data);
	}

	private void loadWordsfromCSVFileToTempFile() throws IOException {
		// TODO Auto-generated method stub
		File csvFile = new File(Configuration.getProperty(Constants.csvFile));
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(Constants.TabDelimiter);
			if (values[Constants.ZERO].contains(Constants.EnglishLanguage)
					&& values[Constants.ONE]
							.equalsIgnoreCase(Constants.EnglishLanguage)) {
				String word = values[Constants.TWO];
				String meaning = values[Constants.THREE];
				word = StringUtils.makeProperWord(word);
				meaning = StringUtils.makeProperWord(meaning);
				if (!wordsMap.containsKey(word)) {
					System.out.println(word);
					wordsMap.put(word, meaning);
				}
			}
		}
		br.close();
	}

	private void loadWordsFromLocalDictionaryToTempFile()
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		dictionaryXls = new HSSFWorkbook(new FileInputStream(localDictionary));
		int numberOfSheets = dictionaryXls.getNumberOfSheets();
		// System.out.println(numberOfSheets);
		for (int sheetIndex = Constants.ZERO; sheetIndex < numberOfSheets; sheetIndex++) {
			HSSFSheet sheet = dictionaryXls.getSheetAt(sheetIndex);
			int numberOfRows = sheet.getPhysicalNumberOfRows();
			// System.out.println("In sheet " + sheet.getSheetName() +
			// " number of rows are : " + numberOfRows);
			for (int rowIndex = Constants.ONE; rowIndex < numberOfRows; rowIndex++) {
				HSSFRow row = sheet.getRow(rowIndex);
				// System.out.println("number of cells in row " + rowIndex +
				// " is : " +row.getPhysicalNumberOfCells());
				if (row.getPhysicalNumberOfCells() == Constants.validNumberOfCells) {
					String word = row.getCell(Constants.ZERO)
							.getStringCellValue();
					String meaning = row.getCell(Constants.ONE)
							.getStringCellValue();
					word = StringUtils.removeExtraSpaces(word);
					meaning = StringUtils.removeExtraSpaces(meaning);
					// System.out.println(word + " :: " + meaning);
					if (!word.isEmpty())
						wordsMap.put(word, meaning);
				}
			}
		}
		localDictionary.delete();
		readyDictionaryXLS();
	}

	private void loadWordsFromTempFileToLocalDictionary()
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		dictionaryXls = new HSSFWorkbook(new FileInputStream(localDictionary));
		for (Data data : words) {
			String word = data.getWord();
			String meaning = data.getMeaning();
			String sheetName = String.valueOf(word.charAt(Constants.ZERO));
			int sheetIndex = dictionaryXls.getSheetIndex(sheetName);
			HSSFSheet sheet = dictionaryXls.getSheetAt(sheetIndex);
			int newRowNumber = 1 + sheet.getLastRowNum();
			HSSFRow newRow = sheet.createRow(newRowNumber);
			HSSFCell wordCell = newRow.createCell(Constants.ZERO);
			myStyle = sheet.getWorkbook().createCellStyle();
			insertStringinCell(wordCell, word);
			HSSFCell meaningCell = newRow.createCell(Constants.ONE);
			insertStringinCell(meaningCell, meaning);
			sheet.autoSizeColumn(Constants.ZERO);
			sheet.autoSizeColumn(Constants.ONE, Constants.TRUE);
		}
		closeXLS(localDictionary);
	}

	public void Make() throws IOException {
		readyDictionaryXLS();
		loadWordsFromLocalDictionaryToTempFile();
		loadWordsfromCSVFileToTempFile();
		getWordsFromTempFile();
		loadWordsFromTempFileToLocalDictionary();
	}

	private void readyDictionaryXLS() throws IOException {
		localDictionary = new File(
				Configuration.getProperty(Constants.dictionaryFile));
		if (!localDictionary.exists()) {
			localDictionary.createNewFile();
			dictionaryXls = new HSSFWorkbook();
			myStyle = dictionaryXls.createCellStyle();
			for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
				String sheetName = String.valueOf(alphabet);
				HSSFSheet sheet = dictionaryXls.createSheet(sheetName);
				HSSFRow row = sheet.createRow(Constants.ZERO);
				HSSFCell headerCellForWord = row.createCell(Constants.ZERO);
				insertStringinCell(headerCellForWord,
						Constants.ColumnHearderForWord);
				HSSFCell headerCellForMeaning = row.createCell(Constants.ONE);
				insertStringinCell(headerCellForMeaning,
						Constants.ColumnHeaderForMeaning);
			}
			closeXLS(localDictionary);
		}
	}
}
