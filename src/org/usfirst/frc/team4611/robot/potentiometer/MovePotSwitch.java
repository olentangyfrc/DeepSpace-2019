package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MovePotSwitch extends Command{
	
	private double speed;
	private double speed2;
	
	public MovePotSwitch(double speed, double speed2) {
		this.speed = speed;
		this.speed2 = speed2;
	}

	protected void execute() {
		Robot.arm.movePotSwitch(speed, speed2);
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
