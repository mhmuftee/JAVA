package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	private static String configurationFilePath = "D:\\Program_Files\\workspace\\Java\\Dictionary_V_2.0\\src\\resources\\configuration.properties";
	static Properties configuration;

	public static void loadFilePathsFromConfiguration() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		File configurationFile = new File(configurationFilePath);
		configuration = new Properties();
		configuration.load(new FileReader(configurationFile));
	}
	
	public static String getProperty(String key){
		return configuration.getProperty(key);
	}
}
