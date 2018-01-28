package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MovePotSwitch extends Command{
	
	private double speed;
	
	public MovePotSwitch(double speed) {
		this.speed = speed;
	}

	protected void execute() {
		Robot.arm.movePotSwitch(speed);
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
