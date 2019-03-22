package org.usfirst.frc.team4611.robot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.OzoneJavaLogger;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {

	private static Logger logger	= Logger.getLogger(Robot.class.getName());
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		try {

			NetTableManager.startNetworkTables();
			OzoneJavaLogger.getInstance().init(Level.FINE);

			SubsystemFactory.getInstance().init();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//Scheduler.getInstance().disable();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void disabledInit() {
		Scheduler.getInstance().run();
	}
}