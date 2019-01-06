package org.usfirst.frc.team4611.robot.commands.auton.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Optical;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistance extends Command {

	private int centimeters;
	private double speed;

	private Optical opt;

	public DriveUntilDistance(Optical o, int centi, double speed) {
		this.requires(Robot.driveTrain);
		this.centimeters = centi;
		this.opt = o;
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.driveTrain.moveVelocityAuton(speed);
	}

	@Override
	protected boolean isFinished() {
		opt.update();
		return opt.pidGet() < centimeters;
	}

	protected void end() {
		Robot.driveTrain.moveVelocityAuton(0);
	}
}
