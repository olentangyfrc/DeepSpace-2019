package org.usfirst.frc.team4611.robot.logging;

import java.io.OutputStream;
import java.util.Properties;

public class DefaultValues {

	private Properties prop;
	//private OutputStream steam = new OutputStream("");
	
	public DefaultValues() {
		
		
	}
	
	public void updateProperty(String key, String value) {
		prop.setProperty(key, value);
	}
	
	public Object getProperty(String key) {
		return prop.get(key);
	}
	
	public void saveProperties() {
		
	}
}
