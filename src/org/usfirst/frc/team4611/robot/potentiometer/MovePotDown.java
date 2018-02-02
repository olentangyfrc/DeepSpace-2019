package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MovePotDown extends Command{
	
	private double speed;
	private double speed2;
	
	public MovePotDown(double speed, double speed2) {
		this.speed = speed;
		this.speed2 = speed2;
	}

	protected void execute() {
		Robot.arm.movePotDown(speed, speed2);
		//System.out.println("Down");
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
