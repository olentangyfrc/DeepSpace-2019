package org.usfirst.frc.team4611.robot.logging;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

public class Logger {

	private static ArrayList<LoggerType> onlyShow = new ArrayList<LoggerType>();
	private static boolean OnlyViewSpecifics = false;
		
	private static FileManager man;
		
	/**
	 * Instantiates a basic logger, creating the LoggerTypes specific to the status of a logger,
	 * and logs if instantiated correctly
	 * @author Ben Hilger
	 */
	public static void init(String name){
		//Creates logger-specific LoggerTypes
		addNewLoggerType("Logger-Status");
		addNewLoggerType("Logger-ShowOnly");
		
		DecimalFormat format = new DecimalFormat("00");
		int date = Calendar.getInstance().get(Calendar.DATE);
		int month = Calendar.getInstance().get(Calendar.MONTH+1);
		
		int hour = Calendar.getInstance().get(Calendar.HOUR);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int ampm = Calendar.getInstance().get(Calendar.MILLISECOND);
		man = new FileManager(name, month + "-" + date + "-2018" + "-" + format.format(hour) + "-" + format.format(minute) + "-" + (ampm == Calendar.AM ? "AM" : "PM"));
				
		//Logs to the console that the logger is ready to go!!!
		log("Logger Enabled", Logger.getLoggerType("Logger-Status"));
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
	public static void log(String message, LoggerType type) {
		//Gets the time that  function was called (format determined by status variable)
		String timestamp = getTimeLogged();
		
		//Checks to see if only specific LoggerTypes should be printed to the console
		if(OnlyViewSpecifics) {		
			//Checks if the given LoggerType is within the onlyShaw arraylist
			for(int i = 0; i < onlyShow.size(); i++) {
				if(onlyShow.get(i).getName().equals(type.getName())) {
					//If it's within the onlyShow arraylist, it prints to the console in a set format (explained in documentation above  function)
					System.out.println("[" + timestamp + "]"  + "[" + getRealTimeCreated() + "]" + "[" + type + "]:" + message);
					man.write("[" + timestamp + "][" + type + "]:" + message);
				}
			}
			
		}else {
			//If the logger is allowed to print any LoggerType, it prints it in a set format (explained in documentation above  function)
			System.out.println("[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" + "[" + type + "]:" + message);
			man.write("[" + timestamp + "][" + type + "]:" + message);
		}
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
	 * Adds a new LoggerType, allowing for more specific and varying outputs depending on the
	 * application of  program. Allows easy access for more specific targeting of certain outputs
	 * with the onlyShowCertainLogTypes() method
	 * @param withName: "Identifier/Name" of the new logger type. Used when referencing  specific
	 * log type
	 * 
	 */
	public static void addNewLoggerType(String withName) {
		//Instantiates a new LoggerType with the given name
		LoggerType type = new LoggerType(withName);
		
		//Adds the new LoggerType to the main array for future reference by the Logger.getLoggerType() method
		RobotMap.loggerTypes.add(type);
	}
	
	/**
	 * Fills the onlyShow arraylist with the specific LoggerTypes in the parameter. It then will then
	 * only allow those LoggerTypes to be printed. Recommended use for specific debugging purposes.
	 * IMPORTANT: Automatically sets OnlyViewSpecifics to true, use the showAll() method to undo .
	 * @param types: The LoggerTypes that you only want displayed
	 * @author Ben Hilger
	 *
	 */
	public static void onlyShowCertainLogTypes(LoggerType[] types) {
		//Resets the onlyShow array so unwanted types are ensured to be removed
		onlyShow = new ArrayList<LoggerType>();
		
		//Goes through the given array, and adds each one to the onlyShow array
		for(int i = 0; i < types.length; i++) {
			onlyShow.add(types[i]);
		}
		
		//Ensures that the logger-specific types always get printed
		onlyShow.add(Logger.getLoggerType("Logger-ShowOnly"));
		onlyShow.add(Logger.getLoggerType("Logger-Status"));
		
		//Sets the OnlyViewSpecifics boolean to true so the log function knows to only show the ones added to the onShow arraylist
		OnlyViewSpecifics = true;
		
		//Logs to the console that it's only showing the specific LoggerTypes
		log("Only showing LoggerTypes with names: " + Arrays.toString(types), Logger.getLoggerType("Logger-ShowOnly"));
	}
	
	/**
	 * Changes the OnlyViewSpecifics value to false. Allows for any LoggerTypes to be printed
	 * @author Ben Hilger
	 */
	public void showAll() {
		OnlyViewSpecifics = false;
		
		//Logs to the console that it's showing all LoggerTypes
		log("Showing all LoggerTypes", Logger.getLoggerType("Logger-ShowOnly"));
	}
	
	
	
	/**
	 * @param withName The "Identifier/Name" that set in the addNewLoggerType(withName) parameter
	 * @return The LoggerType class that hold's the information ("Identifier/Name") necessary for output
	 */
	public static LoggerType getLoggerType(String withName) {
		for(int i = 0; i < RobotMap.loggerTypes.size(); i++) {
			if(RobotMap.loggerTypes.get(i).getName().equals(withName)) {
				return RobotMap.loggerTypes.get(i);
			}
		}
		Logger.addNewLoggerType(withName);
		return Logger.getLoggerType(withName);
	}
	
}


