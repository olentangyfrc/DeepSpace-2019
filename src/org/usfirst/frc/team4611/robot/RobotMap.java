package org.usfirst.frc.team4611.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.defaults.DefaultValues;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.LoggerType;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is where we declare what our constants are
 * It's where we set which PWM the Victors are plugged into and what ports are which joysticks etc.
 * This is where you're probably gonna end up when incorrect motors are running or we change ports for certain motors.
 */
public class RobotMap {
	public static MecanumDrive driveTrain;
	
	/*public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;*/
	public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;
	
	public static Spark motor;

	//Joystick ports
	public static int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static int rightJoyPort = 1; //But rather decided from the drivers station by the drivers
	public static int joyButtonPort = 3;
	
	public static final int teamID = 4611;
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static NetworkTableManager networkManager = new NetworkTableManager();
	
	public static ArrayList<LoggerType> loggerTypes = new ArrayList<LoggerType>();
	public static final long systemStartupTime = System.currentTimeMillis();
	
	public static DoubleSolenoid sol;
	public static int openPort = 1;
	public static int closePort = 0;
	
	public static double motorSpeed = 1;
	
	public static String joyStickSubTable = "Joysticks";
	public static String mecanumSubTable = "Mecanum";
	public static String defaultsSubTable = "Defaults";
	public static String switcherSubTable = "Switchable";
	
	public static String leftJoyXID = "leftJoyX";
	public static String leftJoyYID = "leftJoyY";
	public static String leftJoyZID = "leftJoyZ";
	public static String rightJoyXID = "rightJoyX";
	public static String rightJoyYID = "rightJoyY";
	public static String rightJoyZID = "rightJoyZ";
	public static String motorPowerID = "motorPower";
	public static String strafePowerID = "strafePower";
	public static String deadZoneID = "deadZone";
	public static String deadZoneYID = "deadZoneY";
	public static String switcherTalonID = "talon-enabled";
	public static String switcherVictorID = "victor-enabled";
	
	
	public static DefaultValues defaults;
	
	public static void init() {
		//PWM Ports
		//PWM ports are physically on the rio and the number on the port should match with the int in code
		driveTrainFL = new Victor(1);
		driveTrainFR = new Victor(0);
		driveTrainBL = new Victor(2);
		driveTrainBR = new Victor(3);

		/*driveTrainFL = new WPI_TalonSRX(0);
		driveTrainFR = new WPI_TalonSRX(1);
		driveTrainBL = new WPI_TalonSRX(2);
		driveTrainBR = new WPI_TalonSRX(3);*/
		
		//
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		
		//motor = new Spark(4);
		
		//Objects
		//driveTrain =  new MecanumDrive(driveTrainFL, driveTrainBL, driveTrainFR, driveTrainBR);
		driveTrain = new MecanumDrive(driveTrainFL, driveTrainFR, driveTrainBL, driveTrainBR);
		//Constants
		sol = new DoubleSolenoid(RobotMap.openPort, RobotMap.closePort);
		Logger.init("Logs");
		defaults = new DefaultValues();

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
	
	/**
	 * 
	 * @param subtable The subtable the value is on
	 * @param key the key the value is binded to
	 * @return the value connected to the key, or null otherwise
	 */
	public static Object getValue(String subtable, String key) {
		return RobotMap.networkManager.getValue(subtable, key);
	}
	
	public static void log(String subTable, String message) {
		Logger.log(message, Logger.getLoggerType(subTable));
	}
}
