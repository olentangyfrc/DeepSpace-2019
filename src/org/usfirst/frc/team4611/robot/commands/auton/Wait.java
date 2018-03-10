package org.usfirst.frc.team4611.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command{

	private double time;
	private double initTime;
	
	public Wait(double time) {
		this.time = time;
	}
	
	protected void initialize() {
		initTime = Timer.getFPGATimestamp();
	}

	@Override
	protected boolean isFinished() {
		double remainingTime = Timer.getFPGATimestamp() - initTime;
		return remainingTime >= time;
	} 
	
	
}
