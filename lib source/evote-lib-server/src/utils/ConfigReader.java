package utils;

import java.util.Properties;

public class ConfigReader {
	public static String read(String name) {
		Properties properties = Prop.loadProperties("config.properties");
		return properties.getProperty(name);
	}
	
}
