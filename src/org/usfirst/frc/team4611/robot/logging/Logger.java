package org.usfirst.frc.team4611.robot.logging;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

public class Logger {
		
	private static FileManager fileManager;
		
	/**
	 * Instantiates a basic logger, creating the LoggerTypes specific to the status of a logger,
	 * and logs if instantiated correctly
	 * @author Ben Hilger
	 */
	public static void init(String name){
		
		DecimalFormat format = new DecimalFormat("00");
		int date = Calendar.getInstance().get(Calendar.DATE);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		
		int hour = Calendar.getInstance().get(Calendar.HOUR)+1;
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int ampm = Calendar.getInstance().get(Calendar.MILLISECOND);
		fileManager = new FileManager(name, month + "-"
						+ date + "-2018" + "-"
						+ format.format(hour)
						+ "-" + format.format(minute)
						+ "-" + (ampm == Calendar.AM ? "AM" : "PM"));
				
		//Logs to the console that the logger is ready to go!!!
		log("Logger Enabled", "console");
	}
	
	/** 
	 * Logs specified message (if allowed) to the console with a specific timestamp, and the type of message it is
	 * Structure:
	 * 	[time stamp][Name/Identifier]: message 
	 * IMPORTANT: Doesn't show hours if it equals 0
	 * Parameters:
	 * @param - message The message that is going to the printed to the console
	 * @param - type The type of log, used to decided if it's allowed to be displayed, and what tag it gets when printed 
	 * @author Ben Hilger
	 */
	public static void log(String message, String category) {
		//Gets the time that  function was called (format determined by status variable)
		String timestamp = getTimeLogged();
		

		try {
			// print to console if caller is lazy or specifies console or  
			if (category == null || category.equals("console") || fileManager == null) {
				System.out.println("[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" + "[" + category + "]:" + message);
			} else {
				fileManager.write("[" + timestamp + "][" + category + "]" + message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * convenience log() just forwards to more specific log(()
	 * should be used instead of System.out.print() variations
	 * 
	 */
	public static void log(String message) {
		Logger.log(message, "console");
	}

	
	/**
	 * Used to get the timestamp of a log. Compares the time from the constant systemStartup time (located in RobotMap)
	 * 	to the current calendar time, getting the difference between them
	 * @return Returns the time that a certain log was called from the start of the program
	 * @author Ben Hilger
	 */
	public static String getTimeLogged() {
		
		String timestamp = "";
		double time = Timer.getMatchTime();

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
	
	public static String getRealTimeCreated() {
		
		Calendar calendar = Calendar.getInstance();

		String timestamp = "";

		DecimalFormat formatter = new DecimalFormat("00");
		//Converts the time (in milliseconds) to seconds (/1000), them minutes (/60), then hours (/60), or returns the hours of the day
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
	
		//Converts the remaining time (in milliseconds) to seconds (/1000), then minutes (/60), or returns the minute of time
		long minutes = calendar.get(Calendar.MINUTE);
		
		//Converts the remaining time (in milliseconds) to seconds (/1000), or returns the current seconds in real time
		long seconds = calendar.get(Calendar.SECOND);
		
		//Takes the remaining seconds to a milliseconds variable, or returns the current milliseconds in real time
		long milliseconds = calendar.get(Calendar.MILLISECOND);
			
		//Rounds all of the numbers to the hundredth place so it looks nice
		
		//Gives timestamp the value of a formatted output string (format explained in log method documentation)
		timestamp = (hours != 0 ? formatter.format(hours) + ":" : "") + formatter.format(minutes) + ":" + formatter.format(seconds) + "." + formatter.format(milliseconds);
		return timestamp;
	}

	/**
	 * Called when the robot is disabled, writes everything that was logged to the Rio
	 */
	public static void robotDisabled() {
		fileManager.commit();
	}
	
}


