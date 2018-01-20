package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeRight extends Command{
	private double speed;
	public StrafeRight(double sp){
		this.speed= sp;
		this.requires(Robot.tankDrive); //This command uses this subsystem
	}
	
	protected void execute() { //execute is called every 20 miliseconds
		double YVal = Robot.oi.yFilter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());
		
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		

	    Robot.tankDrive.move(-YVal, -speed, -ZVal); 
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
