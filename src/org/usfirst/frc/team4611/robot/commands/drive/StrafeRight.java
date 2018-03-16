package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeRight extends Command{
	private double speed;
	public StrafeRight(double sp){
		this.speed= sp;
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	protected void execute() { 
		double YVal = Robot.oi.strafeFilter(OI.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double ZVal = Robot.oi.filter(OI.rightJoy.getX());	

	    Robot.mecanum.move(-YVal, speed, -ZVal); 
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
