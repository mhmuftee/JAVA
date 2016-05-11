package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.Spreadsheet.SpreadSheetManager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Configuration.loadFilePathsFromConfiguration();
			SpreadSheetManager spreadSheet = new SpreadSheetManager();
			spreadSheet.Make();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
