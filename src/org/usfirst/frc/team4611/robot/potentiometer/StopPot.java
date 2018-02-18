package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopPot extends Command {

	public StopPot() {
		this.requires(Robot.arm);
	}
	
	protected void execute() {
		//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0.0);
		Robot.arm.stopPot();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	
}
