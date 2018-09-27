package org.usfirst.frc.team4611.robot.commands.auton.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.mecanum.TalonMecanum;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {
	
	private int inches;
	public final double INCH_PU_MULT = 215.910640625;
	
	public MoveForward(int inches) {
		this.requires(Robot.mecanum);
		this.inches = inches;
	}
	
	protected void execute() {
		Robot.mecanum.moveForward(inches);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs((Robot.mecanum.getAverageSensorPos()*(((TalonMecanum)Robot.mecanum)).INCH_PU_MULT)-inches) <= 10;
	}
}