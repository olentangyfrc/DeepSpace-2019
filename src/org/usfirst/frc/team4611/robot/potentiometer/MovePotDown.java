package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MovePotDown extends Command{
	
	private double speed;
	
	public MovePotDown(double speed) {
		this.speed = -speed;
	}

	protected void execute() {
		Robot.arm.movePotDown(speed);
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
