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

	private static Logger logger;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		try {
			logger	= Logger.getLogger(Robot.class.getName());
			logger.entering(Robot.class.getName(), "robotInit()");

			NetTableManager.startNetworkTables();
			OzoneJavaLogger.getInstance().init(Level.FINE);

			SubsystemFactory.getInstance().init();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		} finally {
			logger.exiting(Robot.class.getName(), "robotInit()");
		}
	}

	@Override
	public void disabledInit() {
		Scheduler.getInstance().run();
		Scheduler.getInstance().disable();
		return;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Scheduler.getInstance().disable();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().enable();
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().enable();
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		Scheduler.getInstance().enable();
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
}