package org.usfirst.frc.team4611.robot;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team4611.robot.commands.auton.TestBlock;
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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	}

	@Override
	public void autonomousInit() {
		String path = getPath();
		
		Logger.log("Auton Final Decision [ "+path + "]", this.getClass().getName());
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
		Logger.log("Elevator position: "+ RobotMap.elevator_Talon.getSelectedSensorPosition(0));		
	}

	@Override
	public void testPeriodic() {
	}
	
	public String getPath() {
		String fms = driver.getGameSpecificMessage().trim();
		String sw = fms.substring(0, 1);
		String sc = fms.substring(1, 2);
		String key = "";
		boolean split = false;
		
		try {
			String strat = ((String) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.strategy)).trim().toUpperCase();
			Logger.log(strat, "Auton Strat");
			String position = strat.substring(0, 1).toUpperCase();  //L, R, C
			String mode = strat.substring(1, 2).toUpperCase(); //P, T
			String target1 = strat.substring(2, 4).toUpperCase(); //SW , SC
			String target1Side = getSide(target1); //L, R
			String target2 = strat.substring(4, 6).toUpperCase();//SW , SC
			String target2Side = getSide(target2); //L, R
			String oppTarget1 = strat.substring(6).toUpperCase();//SW , SC
			String oppTarget1Side = getSide(oppTarget1); //L, R
									
			if(!(target1Side.equals(target2Side))) { //Are targets (not (same side))
				split = true;
			}
			
			if (mode.equals("T")) { //Target construction follows L + S(T1) + T1 + S(T2) + T2
				key = position + target1Side + target1 + target2Side + target2;
			}
			
			if (mode.equals("P")) {
				if (split == true) { //If we're split
					if (sw.equals(position)) { //and switch is close
					key = position + sw + "SW" + position + "SC"; //go switch close then pick up box
					}
					else if (sc.equals(position)) { //or scale is close
						key = position + sc + "SC" + position + "SC"; //go scale then pick up box
					}
				}
				
				else { // not split
					if (sw.equals(position) && sc.equals(position)) { //on our side
						key = position + target1Side + target1 + target2Side + target2; //go for targets
					}
					
					else { //opp side
						if(oppTarget1.equals("XX") || oppTarget1.isEmpty()) {
							key = "DRIVEFORWARD";
						}
						else {
							key = position + oppTarget1Side + oppTarget1 + oppTarget1Side + "SC"; //go for opp target then scale
						}
						
					}
				}
			}
			
			return key;
			
		}catch(StringIndexOutOfBoundsException e) {
			Logger.log("String out of bounds at getPath substrings caught", "Auton selection");
		}
		
		
		if(!(key.equals(null))) {
			return key.trim().toUpperCase();
		}
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
