package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {

	private int inches;
	public final double INCH_PU_MULT = 215.910640625;

	public MoveForward(int inches) {
		this.requires(Robot.driveTrain);
		this.inches = inches;
	}

	protected void initialize() {
		System.out.println("Hereer");
	}

	protected void execute() {
		System.out.println(Robot.driveTrain.getAverageSensorPos());
		//Robot.driveTrain.moveForward(inches);
	}

	@Override
	protected boolean isFinished() {
		System.out.println(Math.abs(
				(Robot.driveTrain.getAverageSensorPos() * (Robot.driveTrain.INCH_PU_MULT - inches))));
		return Math.abs((Robot.driveTrain.getAverageSensorPos()) - inches * this.INCH_PU_MULT) <= 70;
	}
}