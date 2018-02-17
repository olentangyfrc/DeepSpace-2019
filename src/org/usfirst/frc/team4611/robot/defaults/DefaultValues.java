package org.usfirst.frc.team4611.robot.defaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.ResetDefault;
import org.usfirst.frc.team4611.robot.commands.SetDefault;

public class DefaultValues {

	private Properties prop = new Properties();
	private OutputStream usb;
	
	private boolean hasFile = false;
	public DefaultValues() {
		if(!this.loadPropertiesFromUSB()) {
			hasFile = this.createFileOnUSB();
		}else{
			try {
				File file = new File("/media/sda1/defaults");
				file.mkdirs();
				usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
				hasFile = true;
			}catch(Exception e) {
				hasFile = false;
				e.printStackTrace();
			}
		}

	}
	
	private boolean loadPropertiesFromUSB() {
		InputStream stream;
		try {
			stream = new FileInputStream(new File("/media/sda1/defaults/defaults.properties"));
			prop.load(stream);
			RobotMap.log(RobotMap.defaultsSubTable, "Loaded defaults from usb");
			this.usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to find properties file on usb, looking for local file");
			return false;
		}
	}
	
	private boolean createFileOnUSB() {
		try {
			File file = new File("/media/lvuser/defaults/");
			file.mkdirs();
			usb = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
			RobotMap.log(RobotMap.defaultsSubTable, "Created new file on usb, planning to save on both");
			return true;
		}catch (Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to create file on usb, everything will only be available locally");
			return false;
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
			//this.saveProperties();
			return old;
		}else{
			RobotMap.log(RobotMap.defaultsSubTable, "You tried to set value " + value + " with key " + key + ", but THERE IS NO FILE");
		}
		return null;
	}
	
	public Object getProperty(String name, String key, String defaultVal) {
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

	public void saveProperties() {
		if(hasFile) {
			try {
				prop.store(usb, null);
				RobotMap.log(RobotMap.defaultsSubTable, "Saved entries successfully to usb");
			} catch (Exception e) {
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
	
	public int getDefaultMotorType() {
		try {
			Properties prop = new Properties();
			File file = new File("/home/lvuser/Rio-specific.properties");
			FileInputStream stream = new FileInputStream(file);
			prop.load(stream);
			return Integer.parseInt(prop.getProperty("motortype"));
		}catch(Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "This rio doesn't have which motors it uses, setting victors as the default");
			this.updateMotorType(0);
			return 0;
		}
	}
	
	public void updateMotorType(int type) {
		try {
			Properties prop = new Properties();
			File file = new File("/home/lvuser/Rio-specific.properties");
			FileInputStream stream = new FileInputStream(file);
			prop.load(stream);
			
			FileOutputStream outstream = new FileOutputStream(file);
			prop.setProperty("motortype", "" + type);
			prop.store(outstream, null);
		}catch(Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "UNABLE TO SAVE WHICH MOTORS TO USE");
			File file = new File("/home/lvuser/");
			file.mkdirs();
			file = new File("/home/lvuser/Rio-specific.properties");
		}
	}

}
