package org.usfirst.frc.team4611.robot.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileManager {

	private FileWriter masterWrite;
	private boolean hasFile = true;
	public FileManager(String folderName, String masterName) {
		try {
			File folder = new File("/media/sda1/Logs/" + masterName + "/");
 			folder.mkdirs();
 			System.out.println(folder.getFreeSpace());
			masterWrite = new FileWriter(new File("/media/sda1/Logs/" + masterName + "/" + "RoboRioLogs.txt"));
			System.out.println("Found usb");
		} catch (Exception e) {
			hasFile = false;
			e.printStackTrace();
		} 
	}
	
	/**
	 * Saves a message to a master log file, and a separate log file specific
	 * to the logName given in the parameters
	 * @param message The message to be saved to the log file
	 * @param logName The Identifier/Name used to group this message with a separate log file with other information
	 * on the same matter
	 */
	public void write(String message) {
		if(hasFile) {

			try {
				masterWrite.write(message + "\n");
				masterWrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Used to get the timestamp of a log. Compares the time from the constant systemStartup time (located in RobotMap)
	 * 	to the current calendar time, getting the difference between them
	 * @return Returns the time that a certain log was called from the start of the program
	 * @author Ben Hilger
	 */
	public String getTimeLogged() {
		
		String timestamp = "";
		double time = 12345678.8;

		DecimalFormat formatter = new DecimalFormat("00");

		//Converts the time (in milliseconds) to seconds (/1000), them minutes (/60), then hours (/60), or returns the hours of the day
		int hours = (int)time/60/60;
		time = time-(hours*60*60);
	
		//Converts the remaining time (in milliseconds) to seconds (/1000), then minutes (/60), or returns the minute of time
		int minutes = (int)time/60;
		time = time-(minutes*60);
		
		//Converts the remaining time (in milliseconds) to seconds (/1000), or returns the current seconds in real time
		int seconds = (int)time;
		time = time - seconds;
		
		//Takes the remaining seconds to a milliseconds variable, or returns the current milliseconds in real time
		double milliseconds = time;
		//Rounds all of the numbers to the hundredth place so it looks nice
		hours = (int)Math.round(((double)hours*100))/100;
		minutes = (int)Math.round((double)minutes*100)/100;
		seconds = (int)Math.round((double)seconds*100)/100;
		milliseconds = (int)Math.round((double)milliseconds*100)/100;
		
		//Gives timestamp the value of a formatted output string (format explained in log method documentation)
		timestamp = (hours != 0 ? formatter.format(hours) + ":" : "") + formatter.format(minutes) + ":" + formatter.format(seconds) + "." + formatter.format(milliseconds);
		return timestamp;
	}

	public void robotDisabled() {
		if(hasFile) {
			try {
				this.masterWrite.flush();
			}catch(Exception e) {
				System.out.println("Unable to write to Rio with exception: " + e.getLocalizedMessage());
			}
		}
		
	}
}
