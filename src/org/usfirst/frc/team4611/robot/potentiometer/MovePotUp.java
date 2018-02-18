package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotUp extends Command{
	
	
	public MovePotUp() {
		requires(Robot.arm);
	}

	protected void execute() {
		Robot.arm.movePotUp((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedUpID), (double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedUpID));
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
