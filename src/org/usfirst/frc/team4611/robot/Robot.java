
package org.usfirst.frc.team4611.robot;

import java.util.logging.Level;

import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.blocks.Motion;
import org.usfirst.frc.team4611.robot.commands.auton.blocks.Roomba;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.mecanum.TalonMecanum;

import org.usfirst.frc.team4611.robot.subsystems.sensors.Optical;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Potentiometer;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Robot extends IterativeRobot {

	public String motorControllerType = "t";
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static MecanumBase mecanum;
	public static Optical opt;
	public static UsbCamera camera;	
	
	
	public static OI oi;
	
	@Override
	public void robotInit() {
		NetTableManager.startNetworkTables();
		Pigeon.resetAngle();
		OzoneJavaLogger.getInstance().init(Level.FINE);
		opt = new Optical(Port.kMXP);
		
		//Initialize the subsystems
		if(motorControllerType.toLowerCase().equals("t")) {
			mecanum = new TalonMecanum();
		}
		else {
			mecanum = new TalonMecanum();
		}
		oi = new OI();
		
		camera = CameraServer.getInstance().startAutomaticCapture();	
		camera.setResolution(320, 240);
		camera.setFPS(20);
		camera.setExposureManual(35);
		
		
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Waypoint[] points = new Waypoint[] {
			    new Waypoint(0, 0, 0),      
			    new Waypoint(1, 0, 0),                    
			    //new Waypoint(2, -1, Pathfinder.d2r(90)),
			};
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 0.02, .25, 1, 20);
		Trajectory traject = Pathfinder.generate(points, config);
		
		mecanum.followTrajectory(traject);
		
		/*autonomousCommand = new Motion();

		if (autonomousCommand != null)
			autonomousCommand.start();*/
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		mecanum.setTrajectorySpeeds();
		
	}

	Potentiometer pot = new Potentiometer(0);
	
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*if (autonomousCommand != null)
			autonomousCommand.cancel();*/
		pot = new Potentiometer(1);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		opt.update();
		System.out.println(pot.getValue());
	}


	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
