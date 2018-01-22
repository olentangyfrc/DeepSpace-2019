package org.usfirst.frc.team4611.robot.commands.drive;

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
		
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		
		Robot.tankDrive.move(-YVal, speed, -ZVal);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
