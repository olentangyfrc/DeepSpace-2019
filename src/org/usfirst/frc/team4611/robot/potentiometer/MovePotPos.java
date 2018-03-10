package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotPos extends Command{

	private double position;
	
	public MovePotPos(double position) {
		requires(Robot.arm);
		this.position = position;
	}
	protected void initialize() {
		Logger.log("Moving to position: " + position, this.getClass().getName());
	}
	protected void execute() {
		Robot.arm.movePotPos(position);
	}
	
	@Override
	protected boolean isFinished() {
		double posError = Math.abs(RobotMap.linearActuatorPot.get()-position);
		if (posError < .05) {
			Logger.log("finished", this.getClass().getName());
			return true;
		}
		else {
			Logger.log("not finished", this.getClass().getName());
			return false;
		}

	}
	
	protected void end() {
		Robot.arm.stopPot();
	}

}
