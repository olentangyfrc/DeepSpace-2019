package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.subsystems.Arm;
import org.usfirst.frc.team4611.robot.commands.MakeLight;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.FancyLights;
import org.usfirst.frc.team4611.robot.subsystems.Solenoid;
import org.usfirst.frc.team4611.robot.subsystems.UltrasonicSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
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
	public static Arm arm;
	public static UltrasonicSensor ultrasonic;
	public static Relay lights1;
	public static Relay lights2;
	public static FancyLights fancyLight;
	public static Solenoid sol;
	public static OI oi;

	public static NetworkTableInstance tableInstance;
	public static NetworkTable table;
	
	Command autonomousCommand;
	Command lightsCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	public Robot() {
		//tableInstance = NetworkTableInstance.getDefault();
		//table = tableInstance.getTable("Vision");		

		/*
		NetworkTableEntry entryAngle = Robot.table.getEntry("angle");
		NetworkTableEntry entryDistance = Robot.table.getEntry("distance");
		NetworkTableEntry entryStatus = Robot.table.getEntry("found");
		
		//Get from networktable (key in RobotMap) to get theh change, edit widgit to change values
		
		double angle = entryAngle.getDouble(0.0);
		double distance = entryDistance.getDouble(0.0);
		boolean found = entryStatus.getBoolean(false);
		*/
		
	}
	
	@Override
	public void robotInit() {
		RobotMap.init(); //Run the method "init" in RobotMap
		lightsCommand = new MakeLight(0);
		lightsCommand.setRunWhenDisabled(true);
		
		//Initialize the subsystems
		mecanum = new DriveTrain();
		arm = new Arm();
		sol = new Solenoid();
		ultrasonic = new UltrasonicSensor();
		lights1 = new Relay(0, Direction.kBoth);
		lights2 = new Relay(1, Direction.kBoth);
		fancyLight = new FancyLights();
		oi = new OI();
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
		if((boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
			lightsCommand.cancel();
			lightsCommand = new MakeLight(2);
		}
		if( Math.abs((double) RobotMap.networkManager.getVisionValue(RobotMap.distanceHorizontalID)) <= 1){
			lightsCommand.cancel();
			lightsCommand = new MakeLight(3);
		}
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
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		ultrasonic.getInches();
		if((boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
			lightsCommand.cancel();
			lightsCommand = new MakeLight(2);
		}
		if( Math.abs((double) RobotMap.networkManager.getVisionValue(RobotMap.distanceHorizontalID)) <= 1){
			lightsCommand.cancel();
			lightsCommand = new MakeLight(3);
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
		
	
}
