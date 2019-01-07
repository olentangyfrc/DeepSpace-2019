package org.usfirst.frc.team4611.robot;

import java.util.logging.Level;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TurboTankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Optical;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Pigeon;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	public String motorControllerType = "r";
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static boolean isOn;
	/// public Command autonomousCommand;

	public static OI oi;

	@Override
	public void robotInit() {
		NetTableManager.startNetworkTables();
		OzoneJavaLogger.getInstance().init(Level.FINE);
		SubsystemFactory.getInstance();
		oi = new OI();
	}

	@Override
	public void disabledInit() {
		isOn = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		isOn = true;
		/**
		 * Waypoint[] points = new Waypoint[] { new Waypoint(0, 0, 0), new Waypoint(0,
		 * 1, 0), // new Waypoint(3, 1, 0), }; Trajectory.Config config = new
		 * Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
		 * Trajectory.Config.SAMPLES_HIGH, 0.02, 1.212, 1.37, 5.6); Trajectory traject =
		 * Pathfinder.generate(points, config);
		 * 
		 * driveTrain.followTrajectory(traject);
		 */
		// autonomousCommand = new Roomba();

		// if (autonomousCommand != null)
		// autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// driveTrain.setTrajectorySpeeds();

	}

	Potentiometer pot = new Potentiometer(1);

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		isOn = true;
		// if (autonomousCommand != null)
		// autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}