package org.usfirst.frc.team4611.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.defaults.DefaultValues;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.LoggerType;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is where we declare what our constants are It's where we set
 * which PWM the Victors are plugged into and what ports are which joysticks
 * etc. This is where you're probably gonna end up when incorrect motors are
 * running or we change ports for certain motors.
 */
public class RobotMap {

	//Drive train Talons
	public static WPI_TalonSRX driveTrainFL_Talon;
	public static WPI_TalonSRX driveTrainFR_Talon;
	public static WPI_TalonSRX driveTrainBL_Talon;
	public static WPI_TalonSRX driveTrainBR_Talon;
	
	//Extra Talons
	public static WPI_TalonSRX elevator_Talon;
	public static WPI_TalonSRX climber_Talon;
	public static WPI_TalonSRX climber_Talon2;

	//Drive train Victors
	public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;
	
	//Extra Victors
	public static Victor linearActuator;
	public static Victor linearActuator2;
	
	//General Objects
	public static DoubleSolenoid grabber;
	public static MecanumDrive driveTrain;
	public static AnalogInput ultrasonicInput;
	public static DigitalInput limitSwitch = new DigitalInput(0);
	
	//Victor ports
	public static final int LINEAR_ACTUATOR_PORT = 4;
	public static final int VICTOR_FL_PORT = 1;
	public static final int VICTOR_FR_PORT = 0;
	public static final int VICTOR_BL_PORT = 2;
	public static final int VICTOR_BR_PORT = 3;
	
	//Joystick ports
	public static final int LEFT_JOY_PORT = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static final int RIGHT_JOY_PORT = 1; //But rather decided from the drivers station by the drivers
	public static final int THIRD_JOY_PORT = 2;
	
	//Button Ports
	public static final int SOL_TOGGLE_PORT = 10;
	public static final int SOL_EXTEND_PORT = 2;
	public static final int SOL_RETRACT_PORT = 11;
	public static final int AUTO_GRAB_PORT = 11;
	public static final int OPEN_PORT = 1;
	public static final int CLOSE_PORT = 0;

	//Networktable things
	public static final int teamID = 4611;
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static final String visionTableID = "Vision";
	public static NetworkTableManager networkManager = new NetworkTableManager();
	public static ArrayList<LoggerType> loggerTypes = new ArrayList<LoggerType>();
	public static final long systemStartupTime = System.currentTimeMillis();
	
	//Constants
	public static final int ULTRA_PORT = 3;
	public static final int UD_DISTANCE = 13; // distance for UltraDrive, pointless if it's less than 12 for now
	public static final int HALFWAY = 60;
	public static final int WAY = 120;
	public static final int MOREWAY = 300;
	public static final double POTMIN = .15;
	public static final double POTMAX = .8;
	public static final double POTSWITCH = .45;
	public static final double POTMIN2 = .15;
	public static final double POTMAX2 = .8;
	public static final double POTSWITCH2 = .45;
	private static final double VARIANCELIMIT = .02;
	
	//Default motor speeds
	public static final double LINEAR_ACTUATOR_SPEED = 0.5;
	public static final double ELEVATOR_SPEED_SCALAR = 0.75;
	public static final double LINEAR_ACTUATOR_UP_SPEED = 0.7;
	public static final double LINEAR_ACTUATOR_DOWN_SPEED = 0.7;

	//String keys
	public static String joyStickSubTable = "Joysticks";
	public static String mecanumSubTable = "Mecanum";
	public static String defaultsSubTable = "Defaults";
	public static String switcherSubTable = "Switchable";
	public static String linearActuatorSubTable = "LA";
	public static String potentiometerSubTable = "Potentiometer";
	public static String ultraSubtable = "Ultrasonic";
	public static String solenoidSubtable = "Solenoid";
	public static String cameraSubTable = "Camera";
	public static String elevatorSubtable = "Elevator";
	public static String pushBoxSubtable = "Push Box";	
	public static String pigeonSubtable = "Pigeon";
	public static String climberSubtable = "Climber";
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
	public static String switcherID = "Talon Enabled";
	public static String LASpeedUpID = "LA-up-speed";
	public static String LASpeedDownID = "LA-down-speed";
	public static String potMinID = "Potentiometer Min";
	public static String potMaxID = "Potentiometer Max";
	public static String potSwitchID = "Potentiometer Switch";
	public static String potMin2ID = "Potentiometer 2 Min";
	public static String potMax2ID = "Potentiometer 2 Max";
	public static String potSwitch2ID = "Potentiometer 2 Switch";
	public static String varianceLimitID = "Variance Limit";
	public static String straightRotationID = "straight-rotations-one";
	public static String strafeRotationID = "strafe-rotations-one";
	public static String cameraFPSID = "Camera-FPS";
	public static String elevatorUpSpeed = "Elevator Up Speed";
	public static String elevatorDownSpeed = "Elevator Down Speed";
	public static String cameraxResID = "Camera-xResolution";
	public static String camerayResID = "Camera-yResolution";
	public static String rotateFilterID = "Rotate Filter";
	public static String LAFilterID = "LA Filter";
	public static String SensorVelocityID = "Sensor Velocity";
	public static String SensorAccelID = "Sensor Accel";
	public static String elevatorPos = "Elevator Position";
	public static String positionDistanceID = "PositionDistanceID";
	public static String angleID = "angle";
	public static String distanceID = "distance";
	public static String horizontalDistanceID = "horizontalDistance";
	public static String foundID = "found";
	public static String maxRPMID = "Max RPM";
	public static String rampTime = "Ramp Seconds: "; 
	public static int distance;
	
	public static String pushBoxTimeID = "Time Opened";
	public static String pushBoxEnabledID = "Push Box Enabled";
	public static String pigeonAutonP = "Pigeon-Auton-P";
	public static String autonStrafeScalarID = "StrafeScalar";
	public static String autonSubTable = "Auton Subtable";
	public static String climberSpeed = "Climber Speed";
	public static String targetKey = "Target";
	public static String sideKey = "Position";
	
	public static DefaultValues defaults;

	public static AnalogPotentiometer linearActuatorPot;
	public static AnalogPotentiometer linearActuatorPot2;
	
	public static DoubleSolenoid boxPusher;
	public static int boxPusherOpen = 2;
	public static int boxPusherClose = 3;
	
	public static PigeonIMU pigeon;

	public static void init() {
		
		//Drive Train Victors
		driveTrainFL = new Victor(VICTOR_FL_PORT);
		driveTrainFR = new Victor(VICTOR_FR_PORT);
		driveTrainBL = new Victor(VICTOR_BL_PORT);
		driveTrainBR = new Victor(VICTOR_BR_PORT);

		// Ultrasonic sensor
		ultrasonicInput = new AnalogInput(ULTRA_PORT);
		
		//Pigeon
		pigeon = new PigeonIMU(21);
		pigeon.setFusedHeading(0, 10);
		
		//Linear Actuator
		linearActuator = new Victor(LINEAR_ACTUATOR_PORT);
		linearActuator2 = new Victor(5);
		linearActuatorPot = new AnalogPotentiometer(0);
		linearActuatorPot2 = new AnalogPotentiometer(1);
		
		//Solenoid
		grabber = new DoubleSolenoid(RobotMap.OPEN_PORT, RobotMap.CLOSE_PORT);
		boxPusher = new DoubleSolenoid(RobotMap.boxPusherOpen, RobotMap.boxPusherClose);
		
		//Default Values
		defaults = new DefaultValues();

		//Drive train Talons
		driveTrainFL_Talon = new WPI_TalonSRX(12);
		driveTrainFR_Talon = new WPI_TalonSRX(13);
		driveTrainBL_Talon = new WPI_TalonSRX(10);
		driveTrainBR_Talon = new WPI_TalonSRX(11);
		
		//Elevator Talons
		elevator_Talon = new WPI_TalonSRX(35);
		elevator_Talon.setSensorPhase(true);
		
		//Climber Talons
		climber_Talon = new WPI_TalonSRX(31);
		climber_Talon2 = new WPI_TalonSRX(32);
		
		climber_Talon.configPeakCurrentLimit(50, 250);
		climber_Talon2.configPeakCurrentLimit(50, 250);
		climber_Talon.enableCurrentLimit(true);
		climber_Talon2.enableCurrentLimit(true);
		climber_Talon2.setSensorPhase(true);
		
		//Talon Configuration
		driveTrainFL_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		driveTrainFR_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		driveTrainBL_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		driveTrainBR_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevator_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		climber_Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		climber_Talon2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		//Limits
		elevator_Talon.configForwardSoftLimitEnable(false, 0);
		elevator_Talon.configReverseSoftLimitEnable(false, 0);
		
		//Startup Position Values
		driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
			
		//Startup PID Values
		driveTrainFL_Talon.config_kP(0, .65, 0);
		driveTrainFR_Talon.config_kP(0, .65, 0);
		driveTrainBL_Talon.config_kP(0, .65, 0);
		driveTrainBR_Talon.config_kP(0, .65, 0);
			
		driveTrainFL_Talon.config_kI(0, 0.000, 0);
		driveTrainFR_Talon.config_kI(0, 0.000, 0);
		driveTrainBL_Talon.config_kI(0, 0.000, 0);
		driveTrainBR_Talon.config_kI(0, 0.000, 0);
			
		driveTrainFL_Talon.config_kD(0, 0, 0);
		driveTrainFR_Talon.config_kD(0, 0, 0);
		driveTrainBL_Talon.config_kD(0, 0, 0);
		driveTrainBR_Talon.config_kD(0, 0, 0);
		
		//Startup Motion Magic Values
		driveTrainFL_Talon.configMotionAcceleration(1440, 0);
		driveTrainFL_Talon.configMotionCruiseVelocity(1440, 0);
		driveTrainFR_Talon.configMotionAcceleration(1440, 0);
		driveTrainFR_Talon.configMotionCruiseVelocity(1440, 0);
		driveTrainBL_Talon.configMotionAcceleration(1440, 0);
		driveTrainBL_Talon.configMotionCruiseVelocity(1440, 0);
		driveTrainBR_Talon.configMotionAcceleration(1440, 0);
		driveTrainBR_Talon.configMotionCruiseVelocity(1440, 0);
			
		//Startup Sensorphase Values
		driveTrainFL_Talon.setSensorPhase(true);
		driveTrainFR_Talon.setSensorPhase(true);
		driveTrainBL_Talon.setSensorPhase(true);
		driveTrainBR_Talon.setSensorPhase(true);
			
		Logger.init("Logs");

		//Mecanum Values
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID, 1));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID, 1));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.rotateFilterID, 
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.rotateFilterID, 1));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID, 0.15));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID, 0.15));
		RobotMap.updateValue(RobotMap.mecanumSubTable, maxRPMID, RobotMap.defaults.getDoubleDefaultValue(mecanumSubTable, maxRPMID, 650));
		RobotMap.updateValue(RobotMap.mecanumSubTable, rampTime, RobotMap.defaults.getDoubleDefaultValue(mecanumSubTable, rampTime, 0.2));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.autonStrafeScalarID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.autonStrafeScalarID, 0));
		
		//Switcher Values
		RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherID, true);
		
		//Elevator Values
		RobotMap.updateValue(RobotMap.elevatorSubtable, elevatorUpSpeed, 
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed, 0.75));
		RobotMap.updateValue(RobotMap.elevatorSubtable, elevatorDownSpeed, 
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed, 0.75));
		RobotMap.updateValue(RobotMap.elevatorSubtable, RobotMap.elevatorPos, 0);
		
		//Climber Values
		RobotMap.updateValue(RobotMap.climberSubtable, climberSpeed, RobotMap.defaults.getDoubleDefaultValue(climberSubtable, climberSpeed, (1845.703)));
		
		//Linear Acutator Values
		RobotMap.updateValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedUpID, RobotMap.defaults.getDoubleDefaultValue(linearActuatorSubTable, LASpeedUpID, LINEAR_ACTUATOR_UP_SPEED));
		RobotMap.updateValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedDownID, RobotMap.defaults.getDoubleDefaultValue(linearActuatorSubTable, LASpeedDownID, LINEAR_ACTUATOR_DOWN_SPEED));
		RobotMap.updateValue(RobotMap.linearActuatorSubTable, RobotMap.LAFilterID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.linearActuatorSubTable, RobotMap.LAFilterID, 0.75));
		
		//Potentiometer Values
		RobotMap.updateValue(potentiometerSubTable, potMaxID, 
				RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, potMaxID, POTMAX));
		RobotMap.updateValue(potentiometerSubTable, potMinID, RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, potMinID, POTMIN));
		RobotMap.updateValue(potentiometerSubTable, potSwitchID, POTSWITCH);
		RobotMap.updateValue(potentiometerSubTable, potMax2ID, RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, potMax2ID, POTMAX2));
		RobotMap.updateValue(potentiometerSubTable, potMin2ID, RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, potMin2ID, POTMIN2));
		RobotMap.updateValue(potentiometerSubTable, potSwitch2ID, POTSWITCH2);
		RobotMap.updateValue(potentiometerSubTable, varianceLimitID,
				RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, varianceLimitID, VARIANCELIMIT));
		RobotMap.updateValue(pigeonSubtable, pigeonAutonP, 0.009);
		RobotMap.updateValue(RobotMap.autonSubTable, RobotMap.sideKey, "Null");
		RobotMap.updateValue(RobotMap.autonSubTable, RobotMap.targetKey, "Null");
		//Which type of drive train do you have?
		if(!(boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherID)) {
			setupVictor();
		}else{
			RobotMap.setupTalon();
		}
	}	
	
	//Setup Victor DriveTrain
	public static void setupVictor() {
		stopTalon();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up victor");
		driveTrain = new MecanumDrive(driveTrainFL, driveTrainFR, driveTrainBL, driveTrainBR);
		driveTrain.setSafetyEnabled(false);
	}

	//Setup Talon DriveTrain
	public static void setupTalon() {
		stopVictor();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up talons");
		driveTrain = new MecanumDrive(driveTrainFL_Talon, driveTrainFR_Talon, driveTrainBL_Talon, driveTrainBR_Talon);
		driveTrain.setSafetyEnabled(false);
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
		driveTrainBL.disable();
		driveTrainBR.disable();
		driveTrainFL.disable();
		driveTrainFR.disable();
	}
	
	/**
	 * Updates or adds a new value to the NetworkTable 
	 * in a subtable based on the subTable name
	 * @param key The identifer for the value
	 * @param value The value of the key
	 * Updates or adds a new value to the NetworkTable in a subtable based on
	 * the subTable name
	 * 
	 * @param key
	 *            The identifer for the value
	 * @param value
	 *            The value of the key
	 */
	public static void updateValue(String subtable, String key, Object value) {
		// Tries to add value to the networktable
		if (!RobotMap.networkManager.updateValue(subtable, key, value)) {
			// If it's unsuccessful, it logs there was a problem
			System.out.println("Unable to update value with key: " + key + " on subtable NetworkTable");
			// TODO: Replace System.out.println with Logging functions once
			// merged
		}
	}

	public static void updateVisionValue(String key, Object value) {
		// Tries to add value to the networktable
		if (!RobotMap.networkManager.updateVisionValue(key, value)) {
			// If it's unsuccessful, it logs there was a problem
			System.out.println("Unable to update vision value with key: " + key + " on subtable NetworkTable");
			// TODO: Replace System.out.println with Logging functions once
			// merged
		}
	}	
	
	/**
	 * 
	 * @param subtable
	 *            The subtable the value is on
	 * @param key
	 *            the key the value is binded to
	 * @return the value connected to the key, or null otherwise
	 */
	public static Object getValue(String subtable, String key) {
		return RobotMap.networkManager.getValue(subtable, key);
	}

	public static void log(String subTable, String message) {
		Logger.log(message, Logger.getLoggerType(subTable));
	}
}
