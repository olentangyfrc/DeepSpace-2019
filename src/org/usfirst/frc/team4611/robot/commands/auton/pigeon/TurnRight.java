package org.usfirst.frc.team4611.robot.commands.auton.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRight extends Command {

	private double angle;
	
	private double desiredAngle;
	private double startingPigeonAngle;
	private double maxRPM = 1000;
	
	private final double rotationDifference = 0;
	private final double ANGLE_TOLERANCE = 1;
	
	public TurnRight(double angle) {
		this.angle = angle; 
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		// Gets the angle once the command begins
		startingPigeonAngle = Pigeon.getCurrentAngle();

		// Desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angle;
	
		// Account for drifting
		angle -= rotationDifference;

	}
	protected void execute() {
		// How far do we have to go b4 we get to the target?
		double errorAngle = Pigeon.getAbolsuteAngleError(desiredAngle);
		
		// How do we respond to that error?
		double pVal = errorAngle * .15;
		
		// Set our speed to that adjusted speed
		double speed = Math.min(maxRPM, maxRPM * pVal);

		// Check to see if we are where we need to be before we even move. we might be there. 
		if(!isFinished()) {
			Robot.mecanum.rotate(speed);
		 }
	}
	
	protected boolean isFinished(){
		// Checks to see if the bot is within the designated tolerance
		if(Pigeon.getAbolsuteAngleError(desiredAngle) <= ANGLE_TOLERANCE) {
			return true;
		}
		
		//Checks to see if the bot has turned farther than before
		if(Pigeon.getCurrentAngle() < desiredAngle) {
			return true;
		}
		return false;
	}
	
}