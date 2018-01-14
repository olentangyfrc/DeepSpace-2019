package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeLeft extends Command {
	private double speed;
	public StrafeLeft (double s)
	{
		speed=s;
		this.requires(Robot.tankDrive);
	}
	protected void execute ()
	{
		double YVal=Robot.oi.yFilter(Robot.oi.leftJoy.getY());
		double ZVal=Robot.oi.filter(Robot.oi.rightJoy.getX());
		Robot.tankDrive.move(-YVal, speed, -ZVal);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
