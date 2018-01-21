package org.usfirst.frc.team4611.robot.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.ResetDefault;
import org.usfirst.frc.team4611.robot.commands.SetDefault;

public class DefaultValues {

	private Properties prop;
	private OutputStream stream;
	private boolean hasFile = true;
	public DefaultValues() {
		try {
			stream = new FileOutputStream("/home/lvuser/defaults/defaults.properties");
			RobotMap.log(RobotMap.defaultsSubTable, "Connected to defaults file");
		}catch(IOException e) {
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to find file, creating a new one");
			File sfile = new File("/home/lvuser/defaults");
			sfile.mkdirs();
			File file = new File("/home/lvuser/defaults/defaults.properties");
			try {
				stream = new FileOutputStream(file);
				RobotMap.log(RobotMap.defaultsSubTable, "Created and connected file.");
			}catch(Exception e2) {
				RobotMap.log(RobotMap.defaultsSubTable, "Unable to create file, only returning programmed defaults");
				RobotMap.log("Errors-" + RobotMap.defaultsSubTable, e.getLocalizedMessage());
				hasFile = false;
			}
		}
		
	}
	
	public Object updateProperty(String name, String key, String value) {
		if(hasFile) {
			Object old = prop.setProperty(name + "-" + key, value);
			if(old == null) {
				new SetDefault(name, key);
				new ResetDefault(name, key);
				RobotMap.log(RobotMap.defaultsSubTable, "Created a new default value and connected it to reset and set commands");
			}else {
				RobotMap.log(RobotMap.defaultsSubTable, "Updated with new value " + value + ", replacing " + (String)old);
			}
			this.saveProperties();
			return old;
		}else{
			RobotMap.log(RobotMap.defaultsSubTable, "You tried to set value " + value + " with key " + key + ", but THERE IS NO FILE");
		}
		return null;
	}
	
	private Object getProperty(String name, String key, String defaultVal) {
		if(hasFile) {
			if(prop.get(name + "-" + key) == null) {
				RobotMap.log(RobotMap.defaultsSubTable, "The key " + key + " doesn't exists, creating it now with the default value");
				this.updateProperty(name, key, defaultVal);
			}
		}else {
			RobotMap.log(RobotMap.defaultsSubTable, "Please stop trying to setup and get these values to a file that DOESN'T EXIST");
		}
	
		return hasFile ? prop.get(name + "-" + key) : null;
	}
	
	private void saveProperties() {
		if(hasFile) {
			try {
				prop.store(stream, null);
				RobotMap.log(RobotMap.defaultsSubTable, "Saved entries successfully");
			} catch(Exception e) {
				RobotMap.log("Errors-" + RobotMap.defaultsSubTable, e.getLocalizedMessage());
			}
		}
	}
	
	
	public double getDoubleDefaultValue(String name, String key, double defaultVal) {
		Object value = getProperty(name, key, "" + defaultVal);
		try {
			double newvalue = Double.parseDouble((String)value);
			return newvalue;
		}catch(Exception e) {
			RobotMap.log(name + "-default", "Tried to get value " + (String)value + " with key " + key + " as an double, which didn't work");
		}
		return defaultVal;
	}
	
}
