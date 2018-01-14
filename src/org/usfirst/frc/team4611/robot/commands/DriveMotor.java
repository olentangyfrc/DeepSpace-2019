package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveMotor extends Command{

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
