package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotDown extends Command{
	
	protected void execute() {
		Robot.arm.movePotDown((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedDownID), (double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedDownID));
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
