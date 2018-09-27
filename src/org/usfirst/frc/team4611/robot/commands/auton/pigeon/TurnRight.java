package org.usfirst.frc.team4611.robot.commands.auton.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRight extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 500;
	private double angle;
	private double speed;
	 
	private Pigeon pigeon;
	private final double rotationDifference = 0;

	public TurnRight(Pigeon pig, double angle) {
		this.angle = angle; 
		this.pigeon = pig;
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		startingPigeonAngle = pigeon.getCurrentAngle();

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angle;
	
		// Account for drifting
		angle -= rotationDifference;
		System.out.println("Starting to turn right with a esired angle of:" + desiredAngle);

	}
	protected void execute() {
		System.out.println(desiredAngle);
		System.out.println(pigeon.getCurrentAngle());
		// how far do we have to go b4 we get to the target?
		errorAngle = pigeon.getAbolsuteAngleError(desiredAngle);
		
		// how do we respond to that error?
		double pVal = errorAngle * .15;
		
		// set our speed to that adjusted speed
		speed = Math.min(maxRPM, maxRPM * pVal);

		/**
		 * check to see if we are where we need to be before
		 * we even move. we might be there.
		 */
		if(!isFinished()) {
			Robot.mecanum.rotate(speed);
		 }
	}
	
	protected boolean isFinished(){

		if(pigeon.getAbolsuteAngleError(desiredAngle) <= 1) {
			return true;
		}
		
		if(currentPigeonHeading < desiredAngle) {
			return true;
		}
		return false;
	}
	
}