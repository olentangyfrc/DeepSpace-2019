package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Optical;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistance extends Command {

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
}
