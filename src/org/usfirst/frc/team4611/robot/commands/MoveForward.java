package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {
	
	private int inches;
	
	public MoveForward(int inches) {
		this.requires(Robot.mecanum);
		this.inches = inches;
	}
	
	protected void execute() {
		Robot.mecanum.moveForward(inches);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}