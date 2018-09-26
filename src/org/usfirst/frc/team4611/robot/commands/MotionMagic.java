package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MotionMagic extends Command{

	private int inches;
	
	public MotionMagic(int inches) {
		this.requires(Robot.mecanum);
		this.inches = inches;
	}
	
	@Override
	protected void initialize() {
		Robot.mecanum.resetEncoders();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void execute() {
		Robot.mecanum.moveForward(inches);
	}
	
}
