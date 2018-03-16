package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command{
	private double speed;
	
	public MoveForward(double sp) {
		speed=sp;
		this.requires(Robot.mecanum);
	}
	protected void execute ()
	{
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());
		double ZVal=Robot.oi.filter(Robot.oi.rightJoy.getX());
		
		/*RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());*/
		
		Robot.mecanum.move(speed, -XVal, -ZVal);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
