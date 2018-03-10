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
			this.usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	private boolean createFileOnUSB() {
		try {
			File file = new File("/media/lvuser/defaults/");
			file.mkdirs();
			usb = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public Object updateProperty(String name, String key, String value) {
		if(hasFile) {
			Object old = prop.setProperty(name + "-" + key, value);
			if(old == null) {
				new SetDefault(name, key);
				new ResetDefault(name, key);
			}
			return old;
		}
		return null;
	}
	
	public Object getProperty(String name, String key, String defaultVal) {
		if(hasFile) {
			if(prop.get(name + "-" + key) == null) {
				this.updateProperty(name, key, defaultVal);
			}
		}
	
		return hasFile ? prop.get(name + "-" + key) : defaultVal;
	}

	public void saveProperties() {
		if(hasFile) {
			try {
				prop.store(usb, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public double getDoubleDefaultValue(String name, String key, double defaultVal) {
		Object value = getProperty(name, key, "" + defaultVal);
		try {
			double newvalue = Double.parseDouble((String)value);
			return newvalue;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}

}
