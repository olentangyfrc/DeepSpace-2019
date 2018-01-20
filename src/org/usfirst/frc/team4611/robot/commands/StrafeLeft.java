package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

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
		
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		

		Robot.tankDrive.move(-YVal, speed, -ZVal);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
