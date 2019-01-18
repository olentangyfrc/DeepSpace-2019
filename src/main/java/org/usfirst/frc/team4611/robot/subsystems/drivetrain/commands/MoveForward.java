package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {

	private int inches;
	public final double INCH_PU_MULT = 215.910640625;

	private DriveTrain driveTrain;

	public MoveForward(int inches) {
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
		this.requires(driveTrain);
		this.inches = inches;
	}

	protected void initialize() {
		System.out.println("Hereer");
	}

	protected void execute() {
		System.out.println(driveTrain.getAverageSensorPos());
		//driveTrain.moveForward(inches);
	}

	@Override
	protected boolean isFinished() {
		System.out.println(Math.abs(
				(driveTrain.getAverageSensorPos() * (driveTrain.INCH_PU_MULT - inches))));
		return Math.abs((driveTrain.getAverageSensorPos()) - inches * this.INCH_PU_MULT) <= 70;
	}
}