package jing.fu;

import java.util.Properties;
import java.io.IOException;

public class PropertyManager {
	static Properties props = new Properties();
	static {
		try {
			props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config/TurtleSnake.properties"));
		}
		catch (IOException err) {
			err.printStackTrace();
		}		
		System.out.println(PropertyManager.class.getClassLoader().getResource("config/TurtleSnake.properties").getPath());
	}
	// private PropertyManager() {};
	public static String getProperty(String key) {
		return props.getProperty(key);
	}


	public static void main(String[] args){
		new PropertyManager();
	}	
}