package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
	
	Properties prop;
	
	public ConfigReader() {
		
		try {
			FileInputStream fis = new FileInputStream("resources/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public String getBrowser() {
		
		
		return prop.getProperty("browser");
		
	}
	
	public String getUrl() {
		return prop.getProperty("url");
		
	}
	
	

}
