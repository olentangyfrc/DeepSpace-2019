package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotPos extends Command{

	private double position;
	
	public MovePotPos(double position) {
		requires(Robot.arm);
		this.position = position;
	}
	protected void initialize() {
		RobotMap.log(RobotMap.linearActuatorSubTable, "Moving to position: " + position);
	}
	protected void execute() {
		Robot.arm.movePotPos(position);
	}
	
	@Override
	protected boolean isFinished() {
		double posError = Math.abs(RobotMap.linearActuatorPot.get()-position);
		if (posError < .05) {
			RobotMap.log(RobotMap.linearActuatorSubTable, "Returning true");
			return true;
		}
		else {
			RobotMap.log(RobotMap.linearActuatorSubTable, "Returning false");
			return false;
		}

	}
	
	protected void end() {
		Robot.arm.stopPot();
	}

}
