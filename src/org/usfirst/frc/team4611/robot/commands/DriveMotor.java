package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveMotor extends Command{

	public DriveMotor() {
		this.requires(Robot.driver);
	}
	
	protected void execute() {
		Robot.driver.runMotor();
		RobotMap.updateValue("NetworkTable", "isPressed", Robot.driver.isSwitchSet());
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driver.isSwitchSet();
		
	}
	
	protected void end() {
		RobotMap.motor.set(0);
		RobotMap.updateValue("NetworkTable", "motorSpeed", 0) ;
	}

	
}
