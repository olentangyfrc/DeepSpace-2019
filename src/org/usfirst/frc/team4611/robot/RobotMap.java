package org.usfirst.frc.team4611.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.defaults.DefaultValues;

import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.LoggerType;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
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
	
	public static WPI_TalonSRX driveTrainFL_Talon;
	public static WPI_TalonSRX driveTrainFR_Talon;
	public static WPI_TalonSRX driveTrainBL_Talon;
	public static WPI_TalonSRX driveTrainBR_Talon;
	
	public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;
	
	public static Victor elevatorMotor;
	public static DigitalInput elevatorTopSwitch;
	public static DigitalInput elevatorBotSwitch;
	
	public static boolean useXbox = false;

	//Joystick ports
	public static int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static int rightJoyPort = 1; //But rather decided from the drivers station by the drivers
	public static int armJoyPort=2;
	public static int joyButtonPort = 3;
	
	//FMS
	public static final int teamID = 4611;
	
	//Networktable shit
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static NetworkTableManager networkManager = new NetworkTableManager();
	
	public static ArrayList<LoggerType> loggerTypes = new ArrayList<LoggerType>();
	public static final long systemStartupTime = System.currentTimeMillis();
	
	//Solenoid
	public static DoubleSolenoid sol;
	public static int openPort = 1;
	public static int closePort = 0;
	
	//Ultrasonic sensor
	public static AnalogInput ultrasonicInput;
	public static int ultraPort = 2; 
	
	public static double motorSpeed = 1;
	
	//String keys
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
	public static String switcherID = "motor-switch-enabled";
	public static String straightRotationID = "straight-rotations-one";
	public static String strafeRotationID = "strafe-rotations-one";
	
	public static DefaultValues defaults;
	
	public static void init() {

		driveTrainFL = new Victor(1);
		driveTrainFR = new Victor(0);
		driveTrainBL = new Victor(2);
		driveTrainBR = new Victor(3);
		elevatorMotor = new Victor(4);
		
		//Sensors	
		elevatorTopSwitch = new DigitalInput(0);
		elevatorBotSwitch = new DigitalInput(1);

		
		//Ultrasonic sensor
		ultrasonicInput = new AnalogInput(ultraPort);

		//Talon ports
		driveTrainFL_Talon = new WPI_TalonSRX(12);
		driveTrainFR_Talon = new WPI_TalonSRX(13);
		driveTrainBL_Talon = new WPI_TalonSRX(10);
		driveTrainBR_Talon = new WPI_TalonSRX(11);

		
		//QuadEncoder 
		driveTrainFL_Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		driveTrainFR_Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		driveTrainBL_Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		driveTrainBL_Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		
		driveTrainFL_Talon.configMotionAcceleration(360, 0);
		driveTrainFL_Talon.configMotionCruiseVelocity(360, 0);
		driveTrainFR_Talon.configMotionAcceleration(360, 0);
		driveTrainFR_Talon.configMotionCruiseVelocity(360, 0);
		driveTrainBL_Talon.configMotionAcceleration(360, 0);
		driveTrainBL_Talon.configMotionCruiseVelocity(360, 0);
		driveTrainBR_Talon.configMotionAcceleration(360, 0);
		driveTrainBR_Talon.configMotionCruiseVelocity(360, 0);
		
		Logger.init("Logs");
		RobotMap.log("Test", "Logging");
		RobotMap.updateValue("Test", "Value", 0);

		//sol = new DoubleSolenoid(RobotMap.openPort, RobotMap.closePort);
		Logger.init("Logs");
		defaults = new DefaultValues();
		
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID, RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID, 0.65));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID, RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID, 0.5));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID, RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID, 0.15));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID, RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID, 0.15));
		RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherID, true);
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.straightRotationID, 0);
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.strafeRotationID, 0);
	}

	/**
	* Called at the beginning of teleop to setup everything necessary
	* that needs to be setup for Victors at the beginning of the match
	*/
	public static void setupVictor() {
		stopTalon();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up victor");
		driveTrain = new MecanumDrive(driveTrainFL, driveTrainFR, driveTrainBL, driveTrainBR);
	}
	
	/**
	* Called at the beginning of teleop to setup everything necessary
	* that needs to be setup for Talons at the beginning of the match
	*/
	public static void setupTalon() {
		stopVictor();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up talons");
		driveTrain = new MecanumDrive(driveTrainFL_Talon, driveTrainFR_Talon, driveTrainBL_Talon, driveTrainBR_Talon);
	}

	/**
	 * Called by the setupTalon and is meant as a safety to ensure
	 * anything that shouldn't be abruptly stopped is safely stopped
	 * before continuing the setup process
	 */	
	public static void stopVictor() {
		RobotMap.driveTrainBL.stopMotor();
		RobotMap.driveTrainBR.stopMotor();
		RobotMap.driveTrainFL.stopMotor();
		RobotMap.driveTrainFR.stopMotor();
	}
	
	/**
	 * Called by the setupVictor and is meant as a safety to ensure
	 * anything that shouldn't be abruptly stopped is safely stopped
	 * before continuing the setup process
	 */	
	public static void stopTalon() {
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
