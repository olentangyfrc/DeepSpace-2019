package org.usfirst.frc.team4611.robot;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.MakeLight;
import org.usfirst.frc.team4611.robot.commands.auton.DriveForward;
import org.usfirst.frc.team4611.robot.commands.auton.RightScale;
import org.usfirst.frc.team4611.robot.commands.auton.StartCenterScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.StartCenterScaleRight;
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
import org.usfirst.frc.team4611.robot.commands.auton.TestBlock;
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
		autonCommandGroup.put("RSWRRR", new StartRightSwitchRight()); //Checked
		autonCommandGroup.put("RSWRLR", new StartRightSwitchRight()); //Checked
		autonCommandGroup.put("RSWRRL", new StartRightSwitchRight()); //Checked
		autonCommandGroup.put("RSWRLL", new StartRightSwitchRight()); //Checked
		autonCommandGroup.put("RSWLRR", new StartRightSwitchLeft()); //Wrong go for scale
		autonCommandGroup.put("RSWLLR", new StartRightSwitchLeft()); //Wrong go for edge of null
		autonCommandGroup.put("RSWLRL", new StartRightSwitchLeft()); //Wrong go for scale
		autonCommandGroup.put("RSWLLL", new StartRightSwitchLeft()); //Wrong go forward 
		autonCommandGroup.put("RSCRRR", new StartRightScaleRight()); //Checked
		autonCommandGroup.put("RSCLRR", new StartRightScaleRight()); //Checked
		autonCommandGroup.put("RSCRRL", new StartRightScaleRight()); //Checked
		autonCommandGroup.put("RSCLRL", new StartRightScaleRight()); //Checked
		autonCommandGroup.put("RSCLLL", new StartRightScaleLeft()); //Wrong go forward
		autonCommandGroup.put("RSCRLL", new StartRightScaleLeft()); //Wrong go for switch
		autonCommandGroup.put("RSCLLR", new StartRightScaleLeft()); //Wrong go for edge of null
		autonCommandGroup.put("RSCRLR", new StartRightScaleLeft()); //Wrong go for switch
		autonCommandGroup.put("LSWRRR", new StartLeftSwitchRight()); //Wrong go forward
		autonCommandGroup.put("LSWRRL", new StartLeftSwitchRight()); //Wrong go for edge of null
		autonCommandGroup.put("LSWRLR", new StartLeftSwitchRight()); //Wrong go for scale
		autonCommandGroup.put("LSWRLL", new StartLeftSwitchRight()); //Wrong go for scale
		autonCommandGroup.put("LSWLLL", new StartLeftSwitchLeft()); //Checked
		autonCommandGroup.put("LSWLLR", new StartLeftSwitchLeft()); //Checked
		autonCommandGroup.put("LSWLRL", new StartLeftSwitchLeft()); //Checked
		autonCommandGroup.put("LSWLRR", new StartLeftSwitchLeft()); //Checked
		autonCommandGroup.put("LSCRRR", new StartLeftScaleRight()); //Wrong go forward
		autonCommandGroup.put("LSCRRL", new StartLeftScaleRight()); //Wrong go edge of null
		autonCommandGroup.put("LSCLRR", new StartLeftScaleRight()); //Wrong go for switch
		autonCommandGroup.put("LSCLRL", new StartLeftScaleRight()); //Wrong go for switch
		autonCommandGroup.put("LSCLLL", new StartLeftScaleLeft()); //Checked
		autonCommandGroup.put("LSCLLR", new StartLeftScaleLeft()); //Checked
		autonCommandGroup.put("LSCRLL", new StartLeftScaleLeft()); //Checked
		autonCommandGroup.put("LSCRLR", new StartLeftScaleLeft()); //Checked
		autonCommandGroup.put("CSWRRR", new StartCenterSwitchRight());//Checked
		autonCommandGroup.put("CSWRRL", new StartCenterSwitchRight());//Checked
		autonCommandGroup.put("CSWRLR", new StartCenterSwitchRight());//Checked
		autonCommandGroup.put("CSWRRR", new StartCenterSwitchRight());//Checked
		autonCommandGroup.put("CSWLLL", new StartCenterSwitchLeft());//Checked
		autonCommandGroup.put("CSWLLR", new StartCenterSwitchLeft());//Checked
		autonCommandGroup.put("CSWLRL", new StartCenterSwitchLeft());//Checked
		autonCommandGroup.put("CSWLRR", new StartCenterSwitchLeft());//Checked
		
		//Never go for scale in auton center
		autonCommandGroup.put("DRIVEFORWARD", new DriveForward());
		autonCommandGroup.put("TEST", new TestBlock());
			
		oi = new OI();
		
		camera = CameraServer.getInstance().startAutomaticCapture();	
		CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		
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
		autonFinalDecision = a + b + c;
		String key = autonFinalDecision;
		if(a == null || a.toLowerCase().equals("null") || a.isEmpty())
			key = "DRIVEFORWARD";
		if(b == null || b.toLowerCase().equals("null") || b.isEmpty())
			key = "DRIVEFORWARD";
		if(c == null || c.toLowerCase().equals("null") || c.isEmpty())
			key = "DRIVEFORWARD";
		
		autonomousCommand = this.autonCommandGroup.get(key);
		
		autonomousCommand =new TestBlock();
		
		if (autonomousCommand == null) {
			autonomousCommand = this.autonCommandGroup.get("DRIVEFORWARD");
		}

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
		PiLights.checkPiAlive();
		PiLights.lightsFromPi();
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
