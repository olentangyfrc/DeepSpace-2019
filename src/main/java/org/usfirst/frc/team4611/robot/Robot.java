package org.usfirst.frc.team4611.robot;

import java.util.logging.Level;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {

	public String motorControllerType = "r";
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static boolean isOn;
	/// public Command autonomousCommand;

	@Override
	public void robotInit() {
		NetTableManager.startNetworkTables();
		OzoneJavaLogger.getInstance().init(Level.FINE);
		try {
			SubsystemFactory.getInstance().init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
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
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	Potentiometer pot = new Potentiometer(1);

	@Override
	public void teleopInit() {
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}