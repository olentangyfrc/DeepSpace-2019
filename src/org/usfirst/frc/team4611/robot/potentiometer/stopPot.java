package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class stopPot extends Command {

	public stopPot() {
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0.0);
		RobotMap.linearActuator.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
