
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
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(3, 0, 0)                        // Waypoint @ x=-2, y=-2, exit angle=0 radians 												// Waypoint @ x=0, y=0,   exit angle=0 radians
			};
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 1.2, 2, 60);
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

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*if (autonomousCommand != null)
			autonomousCommand.cancel();*/
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		opt.update();
		System.out.println(opt.pidGet());
	}


	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
