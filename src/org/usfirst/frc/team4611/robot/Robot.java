package org.usfirst.frc.team4611.robot;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.CenterLeftReset;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.CenterRightReset;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftScaleRightScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightScaleRightScaleRight;
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
	//public SendableChooser chooser;

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
		//PiLights.reset();
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
		autonCommandGroup = new AutonCommandGroup<String, Command>();
			
		oi = new OI();
		
		camera = CameraServer.getInstance().startAutomaticCapture();	
		camera.setResolution(320, 240);
		camera.setFPS(20);
		camera.setExposureManual(35);
		
//		lightsCommand = new MakeLight(1);
//		lightsCommand.start();
		
		// Set up default values for auton
		RobotMap.updateValue(RobotMap.autonSubTable, RobotMap.strategy, "");
		RobotMap.updateValue(RobotMap.autonSubTable, RobotMap.velocityRecordingTag, "");
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		RobotMap.defaults.saveProperties();
		Logger.log("ROBOT DISABLED", "Robot");
		
		Logger.commit();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();		
//		((MakeLight)lightsCommand).setColor(4);
//		lightController.set(0.87);
	}

	@Override
	public void autonomousInit() {
		String path = getPath();
		
		Logger.log("Auton Final Decision [ "+path + "]", this.getClass().getName());
		
		//autonomousCommand = new StartRightScaleLeftScaleLeft();
		autonomousCommand = autonCommandGroup.get(path);
		
		if (autonomousCommand == null) {
			autonomousCommand = this.autonCommandGroup.get("DRIVEFORWARD");
		}

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		
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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();			
	}

	@Override
	public void testPeriodic() {
	}
	
	public String getPath() {
		String path = (String) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.strategy);
		path = path.trim().toUpperCase();
		String fms = driver.getGameSpecificMessage().trim();
		String strat = path;
		String key = "";
		boolean split = false;
		String sw = fms.substring(0, 1);
		String sc = fms.substring(1, 2);
		//parsing string
		try {
			String location = strat.substring(0, 1).toUpperCase();
			String mode = strat.substring(1, 2).toUpperCase();
			String target1 = strat.substring(2, 4).toUpperCase();
			String target1Side = getSide(target1);
			String target2 = strat.substring(4, 6).toUpperCase();
			String target2Side = getSide(target2);
			String oppTarget1 = strat.substring(6).toUpperCase();
			String oppTarget1Side = getSide(oppTarget1);
			
			Logger.log(location + mode + target1 + target2 + oppTarget1, "Auton key reconstruction");
						
			if(!(target1Side.equals(target2Side))) {
				split = true;	
			}
			
			if (mode.equals("T")) {
				key = location + target1Side + target1 + target2Side + target2;
			}
			
			if (mode.equals("P")) {
				if (split == true) {
					if (sw.equals(location)) {
					key = location + sw + "SW" + location + "SC"; //Just to pick up box
					}
					else if (sc.equals(location)) {
						key = location + sc + "SC" + location + "SC";
					}
				}
				
				else { // not split
					Logger.log("NOT SPLIT" , "Auton");
					if (sw.equals(location) && sc.equals(location)) { //on our side
						key = location + target1Side + target1 + target2Side + target2;
					}
					
					else { //opp side
						key = location + oppTarget1Side + oppTarget1 + oppTarget1Side + "SC";
					}
				}
			}
			
			return key;
			
		}catch(StringIndexOutOfBoundsException e) {
			Logger.log("NPE at getPath substrings caught", "Auton selection");
		}
		
		
		if(!(key.equals(null))) {
			return key.trim().toUpperCase();
		}
		// return "Empty";
		return key;
	}
	
	public String getSide(String target) {
		String fms = driver.getGameSpecificMessage();
		if (target.equals("SW")) {
			return fms.substring(0, 1);
		}
		if (target.equals("SC")) {
			return fms.substring(1, 2);
		}
		
		return null;
	}
}
