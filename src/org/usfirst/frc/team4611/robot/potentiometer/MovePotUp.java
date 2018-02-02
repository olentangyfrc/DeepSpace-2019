package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MovePotUp extends Command{
	
	private double speed;
	private double speed2;
	
	public MovePotUp(double speed, double speed2) {
		this.speed = speed;
		this.speed2 = speed2;
	}

	protected void execute() {
		Robot.arm.movePotUp(speed, speed2);
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
