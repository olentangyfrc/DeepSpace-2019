package org.usfirst.frc.team4611.robot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Optical;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;
import org.usfirst.frc.team4611.robot.OzoneJavaLogger;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {

	private static Logger logger;
	SendableChooser<Command> chooser = new SendableChooser<>();
	private Optical opt = new Optical(Port.kOnboard);
	private Optical opt1 = new Optical(Port.kMXP);

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
		logger.entering(Robot.class.getName(), "disabledInit()");
		logger.exiting(Robot.class.getName(), "disabledInit()");
	}

	@Override
	public void disabledPeriodic() {
		logger.entering(Robot.class.getName(), "disabledPeriodic()");
		Scheduler.getInstance().run();
		logger.exiting(Robot.class.getName(), "disabledPeriodic()");
	}

	@Override
	public void autonomousInit() {
		logger.entering(Robot.class.getName(), "autonomousInit()");
		logger.exiting(Robot.class.getName(), "autonomousInit()");
	}

	@Override
	public void autonomousPeriodic() {
		logger.entering(Robot.class.getName(), "autonomousPeriodic()");
		Scheduler.getInstance().run();
		logger.exiting(Robot.class.getName(), "autonomousPeriodic()");
	}

	@Override
	public void teleopInit() {
		logger.entering(Robot.class.getName(), "teleopInit()");
		logger.exiting(Robot.class.getName(), "teleopInit()");
	}

	@Override
	public void teleopPeriodic() {
		logger.entering(Robot.class.getName(), "teleopPeriodic()");
		Scheduler.getInstance().run();
		logger.exiting(Robot.class.getName(), "teleopPeriodic()");
		
	}

	@Override
	public void testInit() {
		logger.entering(Robot.class.getName(), "testInit()");
		logger.exiting(Robot.class.getName(), "testInit()");
	}

	@Override
	public void testPeriodic() {
		logger.entering(Robot.class.getName(), "testPeriodic()");
		logger.exiting(Robot.class.getName(), "testPeriodic()");
	}
}