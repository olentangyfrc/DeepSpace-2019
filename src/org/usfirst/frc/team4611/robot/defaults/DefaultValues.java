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

	//What will updates and store the loaded propterties
	private Properties prop = new Properties();
	
	//The OutputSteam for the local file in the RoboRio
	private OutputStream stream;
	
	//The OutputStream for the usb file connected to the RoboRio
	private OutputStream usb;
	
	//Used to ensure that the file exists before writing to it
	private boolean hasFile = true;
	
	//Used to ensure that the file on the USB exists before writing to it
	private boolean hasUSBFile = true;
	public DefaultValues() {
		//Checks to see if can find a USB to load the properties from
		if(!this.loadPropertiesFromUSB()) {
			//If it can't find a USB, it checks for a local file
			if(!this.loadPropertiesFromLocal()) {
				//If it can't find a local file, it Tries to create a local file and a file on a connected usb
				hasFile = this.createFile();
				hasUSBFile = this.createFileOnUSB();
			}else {
				try{
					//If it can load from a local file, it creates a copy on a connected USB stick
					File file = new File("/media/sda1/defaults/");
					file.mkdirs();
					usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
					prop.store(usb, "Added from local file");
				}catch(Exception e) {
					//If it can't find the USB stick, it reports to the logger and ensures that it can't be written to
					RobotMap.log(RobotMap.defaultsSubTable, "Unable to find usb directory, only saving locally");
					hasUSBFile = false;
				}
			}
		}else{
			try {
				//If it can load from a USB file, it Tries to create a copy on the Rio file system
				File file = new File("/home/lvuser/defaults/");
				file.mkdirs();
				stream = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
				prop.store(stream, "All of these values are from an external usb");
			}catch(Exception e) {
				//If it can't load the local file, it reports that to the logger, and ensures that it can't be written to
				RobotMap.log(RobotMap.defaultsSubTable, "Unable to create local properties, only referencing usb files");
				hasFile = false;
			}
		}

	}
	
	/**
	 * Used to try and load properties from a USB stick
	 * @return If it was successful in loading the properties file
	 */
	private boolean loadPropertiesFromUSB() {
		//The inputstream to load the properties from
		InputStream stream;
		try {
			//Tries to setup the file storing the properties as an InputStream to load the properties from
			stream = new FileInputStream(new File("/media/sda1/defaults/defaults.properties"));
			//Tries to load the properties from the retrieved file
			prop.load(stream);
			//If successful, it reports that to the logger, and then creates a new file to have the properties saved to
			RobotMap.log(RobotMap.defaultsSubTable, "Loaded defaults from usb");
			this.usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			//If it's unable to do any of the above, it reports it to the logger
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to find properties file on usb, looking for local file");
			return false;
		}
	}
	
	/**
	 * Tries to load a properties file from a local file on the Rio
	 * @return if it was successful in loading the properties
	 */
	private boolean loadPropertiesFromLocal() {
		InputStream stream;
		try {
			//Tries to get the file as an inputstream to load the properties from
			stream = new FileInputStream(new File("/home/lvuser/defaults/defaults.properties"));
			
			//Tries to load the properties
			prop.load(stream);
			
			//If it's successful, it reports that to the logger and creates a new fileoutput stream to load new properties 
			RobotMap.log(RobotMap.defaultsSubTable, "Loaded defaults from local file");
			this.stream = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			//If unsuccessful, it reports it to the logger and returns false
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to find properties file locally, creating a new one");
			return false;
		}
	}
	
	/**
	 * Tries to create a new file on the local Rio
	 * @retun If it was sucessful
	 */
	private boolean createFile() {
		try {
			File file = new File("/home/lvuser/defaults/");
			file.mkdirs();
			stream = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
			RobotMap.log(RobotMap.defaultsSubTable, "Created new file, preparing usb for copying");
			return true;
		}catch (Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to create file, only returning programmed default values");
			return false;
		}
	}
	
	/**
	 * Tries to create a new file on the USB
	 * @return If it was successful
	 */
	private boolean createFileOnUSB() {
		try {
			File file = new File("/media/lvuser/defaults/");
			file.mkdirs();
			stream = new FileOutputStream(new File("/home/lvuser/defaults/defaults.properties"));
			RobotMap.log(RobotMap.defaultsSubTable, "Created new file on usb, planning to save on both");
			return true;
		}catch (Exception e) {
			RobotMap.log(RobotMap.defaultsSubTable, "Unable to create file on usb, everything will only be available locally");
			return false;
		}
	}
	
	/**
	 * Updates a saved property on the Rio and a connected USB stick 
	 * @param name The name to differentiate the value
	 * @param key The key to differentiate the value
	 * @param value The new value to be set
	 * @return The old object
	 */
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
				prop.store(stream, null);
				RobotMap.log(RobotMap.defaultsSubTable, "Saved entries successfully");
			} catch(Exception e) {
				RobotMap.log("Errors-" + RobotMap.defaultsSubTable, e.getLocalizedMessage());
			}
		}
		
		if(hasUSBFile) {
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
