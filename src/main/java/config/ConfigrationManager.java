package config;

import java.io.InputStream;
import java.util.Properties;


public class ConfigrationManager {
	private static Properties prop =new Properties();
	
	static {		
		try {
			InputStream in = ConfigrationManager.class
					.getClassLoader().getResourceAsStream("myconfigtest.txt");
			prop.load(in);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public static Integer getPropertyInteger(String key) {
		String myValue = getProperty(key);
		try {
			return Integer.valueOf(myValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static Boolean getPropertyBoolean(String key) {
		String myValue = getProperty(key);
		try {
			return Boolean.valueOf(myValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
