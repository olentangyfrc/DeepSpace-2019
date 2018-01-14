package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

public class DriveMotor extends SpecialCommand{

	public DriveMotor() {
		this.requires(Robot.driver);
	}
	
	protected void execute() {
		Robot.driver.runMotor();
	}
	@Override
	protected boolean isFinished() {
		return false;
	}

}
