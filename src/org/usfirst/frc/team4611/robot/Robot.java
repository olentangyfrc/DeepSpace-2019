package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.MakeLight;
import org.usfirst.frc.team4611.robot.commands.auton.PigeonAuton;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Arm;
import org.usfirst.frc.team4611.robot.subsystems.BoxPusher;
import org.usfirst.frc.team4611.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.FancyLights;
import org.usfirst.frc.team4611.robot.subsystems.Solenoid;
import org.usfirst.frc.team4611.robot.subsystems.UltrasonicSensor;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain mecanum;
	public static Elevator el;
	public static Arm arm;
	public static UltrasonicSensor ultrasonic;
	public static Spark lights1;
	public static FancyLights fancyLight;
	public static Solenoid sol;
	public static NetworkTableInstance tableInstance;
	public static NetworkTable table;
	public static UsbCamera camera;
	public static OI oi;
	public static BoxPusher boxPusher;
	Command autonomousCommand;
	Command lightsCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); //Run the method "init" in RobotMap
		
		//Initialize the subsystems
		mecanum = new DriveTrain();
		el = new Elevator();
		arm = new Arm();
		sol = new Solenoid();
		boxPusher = new BoxPusher();
		ultrasonic = new UltrasonicSensor();
		lights1 = new Spark(6);
		fancyLight = new FancyLights();
		lights1.set(0.07);
		oi = new OI();
		CameraServer.getInstance().startAutomaticCapture();
		lightsCommand = new MakeLight(2);
		lightsCommand.setRunWhenDisabled(true);
		lightsCommand.start();
		camera = CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		RobotMap.defaults.saveProperties();
		Logger.robotDisabled();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();		
		((MakeLight)lightsCommand).setColor(4);
		lights1.set(0.87);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = new DriveBlock();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		autonomousCommand = new PigeonAuton();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	/**if( Math.abs((double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID)) <= 3 
		&& (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
	((MakeLight)lightsCommand).setColor(7);
	}else if((boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
		((MakeLight)lightsCommand).setColor(2);
	}else{
		((MakeLight)lightsCommand).setColor(5);
	}*/
	
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) 
			autonomousCommand.cancel();
		
		//Checks to see if the driver has updated the switch for which motors are being used
		if((boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherID)) {
			//If it's true, it starts talon setup
			RobotMap.setupTalon();
		}else if(!(boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherID)) {
			//If it's false, it starts victor setup
			RobotMap.setupVictor();
		}
		
		Logger.init("Logs");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		/*if( Math.abs((double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID)) <= 3 
				&& (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
			((MakeLight)lightsCommand).setColor(7);
		}else if((boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
			((MakeLight)lightsCommand).setColor(2);
		}else{
			((MakeLight)lightsCommand).setColor(5);
		}*/
		
		((MakeLight)lightsCommand).setColor(3);
		System.out.println("Time" + Timer.getMatchTime());
		if(Timer.getMatchTime() >= 20) {
			lights1.set(0.03);
		}else if(Timer.getMatchTime() >= 10) {
			lights1.set(0.05);
		}else {
			lights1.set(0.07);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
		
	
}
