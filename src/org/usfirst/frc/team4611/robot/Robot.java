package org.usfirst.frc.team4611.robot;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.MakeLight;
import org.usfirst.frc.team4611.robot.commands.auton.DriveForward;
import org.usfirst.frc.team4611.robot.commands.auton.StartCenterSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartCenterSwitchRight;
import org.usfirst.frc.team4611.robot.commands.auton.StartLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartLeftScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.StartLeftSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartLeftSwitchRight;
import org.usfirst.frc.team4611.robot.commands.auton.StartRightScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartRightScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.StartRightSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartRightSwitchRight;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Arm;
import org.usfirst.frc.team4611.robot.subsystems.BoxPusher;
import org.usfirst.frc.team4611.robot.subsystems.Climber;
import org.usfirst.frc.team4611.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.FancyLights;
import org.usfirst.frc.team4611.robot.subsystems.Optical;
import org.usfirst.frc.team4611.robot.subsystems.Solenoid;
import org.usfirst.frc.team4611.robot.subsystems.UltrasonicSensor;
import org.usfirst.frc.team4611.robot.utilities.PiLights;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
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
	public static Elevator elevator;
	public static Arm arm;
	public static UltrasonicSensor ultrasonic;
	public static Spark lightController;
	public static FancyLights fancyLight;
	public static Solenoid sol;
	public static NetworkTableInstance tableInstance;
	public static NetworkTable table;
	public static Optical opt;
	public static UsbCamera camera;
	public static BoxPusher boxPusher;
	public static DriverStation driver;
	public static Climber climber;
	public static OI oi;
	public boolean hasInitialized = false;
	public String autonFinalDecision;
	public HashMap<String, Command> autonCommandGroup;

	Command autonomousCommand;
	public static Command lightsCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); //Run the method "init" in RobotMap
		//Initialize utilities
		PiLights.reset();
		//Initialize the subsystems
		mecanum = new DriveTrain();
		elevator = new Elevator();
		arm = new Arm();
		sol = new Solenoid();
		boxPusher = new BoxPusher();
		ultrasonic = new UltrasonicSensor();
		opt = new Optical(Port.kMXP);
		lightController = new Spark(9);
		fancyLight = new FancyLights();
		climber = new Climber();
		driver = DriverStation.getInstance();
		autonCommandGroup = new HashMap<String, Command>(); //POSITION.TARGET.GAMEDATA
		autonCommandGroup.put("RSWRRR", new StartRightSwitchRight()); 
		autonCommandGroup.put("RSWRLR", new StartRightSwitchRight()); 
		autonCommandGroup.put("RSWRRL", new StartRightSwitchRight()); 
		autonCommandGroup.put("RSWRLL", new StartRightSwitchRight()); 
		autonCommandGroup.put("RSWLRR", new StartRightSwitchLeft());
		autonCommandGroup.put("RSWLLR", new StartRightSwitchLeft()); 
		autonCommandGroup.put("RSWLRL", new StartRightSwitchLeft()); 
		autonCommandGroup.put("RSWLLL", new StartRightSwitchLeft()); 
		autonCommandGroup.put("RSCRRR", new StartRightScaleRight()); 
		autonCommandGroup.put("RSCLRR", new StartRightScaleRight()); 
		autonCommandGroup.put("RSCRRL", new StartRightScaleRight()); 
		autonCommandGroup.put("RSCLRL", new StartRightScaleRight()); 
		autonCommandGroup.put("RSCLLL", new StartRightScaleLeft()); 
		autonCommandGroup.put("RSCRLL", new StartRightScaleLeft()); 
		autonCommandGroup.put("RSCLLR", new StartRightScaleLeft()); 
		autonCommandGroup.put("RSCRLR", new StartRightScaleLeft());
		autonCommandGroup.put("LSWRRR", new StartLeftSwitchRight()); 
		autonCommandGroup.put("LSWRRL", new StartLeftSwitchRight());
		autonCommandGroup.put("LSWRLR", new StartLeftSwitchRight()); 
		autonCommandGroup.put("LSWRLL", new StartLeftSwitchRight()); 
		autonCommandGroup.put("LSWLLL", new StartLeftSwitchLeft()); 
		autonCommandGroup.put("LSWLLR", new StartLeftSwitchLeft()); 
		autonCommandGroup.put("LSWLRL", new StartLeftSwitchLeft()); 
		autonCommandGroup.put("LSWLRR", new StartLeftSwitchLeft()); 
		autonCommandGroup.put("LSCRRR", new StartLeftScaleRight()); 
		autonCommandGroup.put("LSCRRL", new StartLeftScaleRight()); 
		autonCommandGroup.put("LSCLRR", new StartLeftScaleRight()); 
		autonCommandGroup.put("LSCLRL", new StartLeftScaleRight()); 
		autonCommandGroup.put("LSCLLL", new StartLeftScaleLeft()); 
		autonCommandGroup.put("LSCLLR", new StartLeftScaleLeft()); 
		autonCommandGroup.put("LSCRLL", new StartLeftScaleLeft()); 
		autonCommandGroup.put("LSCRLR", new StartLeftScaleLeft()); 
		autonCommandGroup.put("CSWRRR", new StartCenterSwitchRight());
		autonCommandGroup.put("CSWRRL", new StartCenterSwitchRight());
		autonCommandGroup.put("CSWRLR", new StartCenterSwitchRight());
		autonCommandGroup.put("CSWRRR", new StartCenterSwitchRight());
		autonCommandGroup.put("CSWLLL", new StartCenterSwitchLeft());
		autonCommandGroup.put("CSWLLR", new StartCenterSwitchLeft());
		autonCommandGroup.put("CSWLRL", new StartCenterSwitchLeft());
		autonCommandGroup.put("CSWLRR", new StartCenterSwitchLeft());
		
		//Never go for scale in auton center
		autonCommandGroup.put("DRIVEFORWARD", new DriveForward());
			
		oi = new OI();
		
		camera = CameraServer.getInstance().startAutomaticCapture();	
		CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(20);
		camera.setExposureManual(35);
		
		lightsCommand = new MakeLight(1);
		lightsCommand.start();
		
		//Just creating the values in shuffleboard
		RobotMap.getValue(RobotMap.autonSubTable, RobotMap.sideKey);
		RobotMap.getValue(RobotMap.autonSubTable, RobotMap.targetKey);
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
		lightController.set(0.87);
	}

	@Override
	public void autonomousInit() {
		String a = (String) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.sideKey);
		String b = (String) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.targetKey);
		String c = driver.getGameSpecificMessage();
		
		boolean ignoreTarget = (boolean) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.targetAimID);
		
		autonFinalDecision = a.trim().toUpperCase() + b.trim().toUpperCase() + c.trim().toUpperCase();
		String key = autonFinalDecision;
		if(a == null || a.toLowerCase().equals("null") || a.isEmpty()) {
			key = "DRIVEFORWARD";
		}
		if(b == null || b.toLowerCase().equals("null") || b.isEmpty()) {
			key = "DRIVEFORWARD";
		}
		if(c == null || c.toLowerCase().equals("null") || c.isEmpty()) {
			key = "DRIVEFORWARD";
		}
		RobotMap.log(RobotMap.autonSubTable, "Auton Final Decision is: "+autonFinalDecision);
		String closeSwitch = c.substring(0, 1);
		String scale = c.substring(1, 2);
		String farSwitch = c.substring(2, 3);
		boolean isCloseSwitch = false;
		boolean isScale = false;
		boolean isFarSwitch = false;
		
		if(closeSwitch.equals(a)) {
			isCloseSwitch = true;
		}
		if(scale.equals(a)) {
			isScale = true;
		}
		if(farSwitch.equals(a)) {
			isFarSwitch = true;
		}
		
		
		//autonomousCommand = this.autonCommandGroup.get(key); if beneath if statement fails comment it out and re add this
		
		if(ignoreTarget == true) {
			if(isCloseSwitch) {
				autonomousCommand = this.autonCommandGroup.get(key);
			}
			else
			{
				autonomousCommand = this.autonCommandGroup.get("DRIVEFORWARD");
			}
		}
		else {
			autonomousCommand = this.autonCommandGroup.get(key);
		}
		System.out.println(autonomousCommand.getClass().getName());
		
		
		
		if (autonomousCommand == null) {
			autonomousCommand = this.autonCommandGroup.get("DRIVEFORWARD");
		}

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		
		RobotMap.log(RobotMap.autonSubTable, "Auton Final Decision is: "+autonFinalDecision);
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
		Robot.mecanum.setRampRate(0);
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
		
		if(!this.hasInitialized){
			Logger.init("Logs");
			this.hasInitialized = true;
		}
		Logger.init("Logs");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//PiLights.checkPiAlive();
		//PiLights.lightsFromPi();
	}

	@Override
	public void testPeriodic() {
	}
	
	public enum AutonCommands {
		TEST {
			public String toString() {
				return "TEST";
			}
		},
		RIGHT_SCALE {
			public String toString() {
				return "RIGHT_SCALE";
			}
	}
	}
}
