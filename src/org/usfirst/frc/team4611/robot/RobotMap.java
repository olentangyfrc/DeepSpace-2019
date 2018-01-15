package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	
	public static Victor motor;
	public static DigitalInput limitSwitch;
	public static Counter counter;
	public static DigitalInput limitSwitch2;
	public static Counter counter2;
	//Joystick ports
	public static int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static int rightJoyPort = 1; //But rather decided from the drivers station by the drivers
	public static int joyButtonPort = 3;
	
	public static final int teamID = 4611;
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static NetworkTableManager networkManager = new NetworkTableManager();
	
	public static DoubleSolenoid sol;
	public static int openPort = 1;
	public static int closePort = 0;
	
	public static double motorSpeed = 1;
	
	public static void init() {
		//PWM Ports
		//PWM ports are physically on the rio and the number on the port should match with the int in code
		driveTrainFL = new Victor(3);
		driveTrainFR = new Victor(0);
		driveTrainBL = new Victor(2);
		driveTrainBR = new Victor(1);

		/*driveTrainFL = new WPI_TalonSRX(0);
		driveTrainFR = new WPI_TalonSRX(1);
		driveTrainBL = new WPI_TalonSRX(2);
		driveTrainBR = new WPI_TalonSRX(3);*/
		
		//
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		//CAN Ports
		//CAN ports are decided via software in the roborio web interface 
		
		motor = new Victor(4);
		limitSwitch = new DigitalInput(5);
		counter = new Counter(limitSwitch);
		limitSwitch2 = new DigitalInput(6);
		counter2 = new Counter(limitSwitch2);
		
		//Objects
		//driveTrain =  new MecanumDrive(driveTrainFL, driveTrainBL, driveTrainFR, driveTrainBR);
		driveTrain = new MecanumDrive(driveTrainFL, driveTrainFR, driveTrainBL, driveTrainBR);
		//Constants
		sol = new DoubleSolenoid(RobotMap.openPort, RobotMap.closePort);

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
}
