package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Optical;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistance extends Command {

	private final Logger logger = Logger.getLogger(DriveUntilDistance.class.getName());

	private int centimeters;
	private double speed;

	private Optical opt;
	private DriveTrain driveTrain;

	public DriveUntilDistance(Optical o, int centi, double speed) {
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
		this.requires(driveTrain);
		this.centimeters = centi;
		this.opt = o;
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		driveTrain.moveVelocityAuton(speed);
	}

	@Override
	protected boolean isFinished() {
		opt.update();
		return opt.pidGet() < centimeters;
	}

	protected void end() {
		driveTrain.moveVelocityAuton(0);
	}

	@Override
	public synchronized void cancel() {
		logger.info("cancel");
	}

	@Override
	protected void interrupted() {
		logger.info("interrupted");
	}
}
