package org.usfirst.frc.team4611.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.LoggerType;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is where we declare what our constants are
 * It's where we set which PWM the Victors are plugged into and what ports are which joysticks etc.
 * This is where you're probably gonna end up when incorrect motors are running or we change ports for certain motors.
 */
public class RobotMap {
	public static DifferentialDrive driveTrain;
	
	public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;
	
	//Joystick ports
	public static int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static int rightJoyPort = 1; //But rather decided from the drivers station by the drivers
	
	public static ArrayList<LoggerType> loggerTypes = new ArrayList<LoggerType>();
	public static final long systemStartupTime = System.currentTimeMillis();
	
	public static final int teamID = 4611;
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static NetworkTableManager networkManager = new NetworkTableManager();

	
	
	public static void init () {
		//PWM Ports
		//PWM ports are physically on the rio and the number on the port should match with the int in code
		driveTrainFL = new Victor(3);
		driveTrainFR = new Victor(0);
		driveTrainBL = new Victor(2);
		driveTrainBR = new Victor(1);
		
		
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		
		//Objects
		SpeedControllerGroup leftSide = new SpeedControllerGroup(driveTrainFL, driveTrainBL);
		SpeedControllerGroup rightSide = new SpeedControllerGroup(driveTrainFR, driveTrainBR);
		
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		
		//Objects
		driveTrain =  new DifferentialDrive(leftSide, rightSide);
		//Constants
		
		Logger.init("Logs");
		RobotMap.log("Test", "Logging");
		RobotMap.updateValue("Test", "Value", 0);
	}
	
	/**
	 * Updates or adds a new value to the NetworkTable 
	 * in a subtable based on the LoggerType name
	 * @param key The identifer for the value
	 * @param value The value of the key
	 */
	public static void updateValue(String subtable, String key, Object value) {
		//Checks to see if this key has already been used
		if(!RobotMap.networkManager.updateValue(subtable, key, value)){	
			//If it's unsuccessful, it logs there was a problem
			System.out.println("Unable to update value with key: " + key + " on subtable NetworkTable");
			//TODO: Replace System.out.println with Logging functions once merged
		}
	}
	
	public static void log(String subTable, String message) {
		Logger.log(message, Logger.getLoggerType(subTable));
	}
	
	/**
	 * 
	 * @param subtable The subtable the value is on
	 * @param key the key the value is binded to
	 * @return the value connected to the key, or null otherwise
	 */
	public static Object getValue(String subtable, String key) {
		return RobotMap.networkManager.getValue(subtable, key);
	}
	
}
