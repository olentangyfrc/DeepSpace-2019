package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjust extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 1500;
	private double speedLimit = 100;
	private double angle;
	private double speed;
	private Direction dir;
	 
	public PigeonAdjust(double angle) {
		this.angle = angle; 
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		
		startingPigeonAngle = RobotMap.pigeon.getFusedHeading();

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angle;
	
		// which way do we need to go?
		if(desiredAngle > startingPigeonAngle) {
			dir = Direction.LEFT;
			angle += RobotMap.rotationDifference;
		}else{
			dir = Direction.RIGHT;
			angle -= RobotMap.rotationDifference;;
		}
		
		Logger.log("angle passed [" + angle
				+ "] desired angle [" + desiredAngle
				+ "] direction[" + dir
				+ "] starting pigeon angle [" + startingPigeonAngle + "]", "PAV2 Init");
	}
	protected void execute() {
	
		// where are we now?
		currentPigeonHeading = RobotMap.pigeon.getFusedHeading();
		
		// how far do we have to go b4 we get to the target?
		errorAngle = Math.abs(desiredAngle - currentPigeonHeading);
		
		// how do we respond to that error?
		double pVal = errorAngle * .06;
		
		// set our speed to that adjusted speed
		speed = Math.min(maxRPM, maxRPM * pVal);

		/**
		 * check to see if we are where we need to be before
		 * we even move. we might be there.
		 */
		
		Logger.log("angle passed [" + angle
				+ "] current angle[" + currentPigeonHeading
				+ "] starting pigeon angle [" + startingPigeonAngle
				+ "] desired angle [" + desiredAngle + "]", "PAV2 Execute");
		
		if(!isFinished()) {

			if(dir == Direction.RIGHT) {
				Robot.mecanum.rotate(speed);
				
			}else if(dir == Direction.LEFT) {
				Robot.mecanum.rotate(-speed);
			}
		 }
		Logger.log("Speed [" + speed + "]", this.getClass().getName());
	}
	
	protected boolean isFinished(){//
		Logger.log("desired angle [" + desiredAngle + 
				" ]current angle" + currentPigeonHeading,"PAV2 isFinished");
		
		//stop if you go over
		/*if(dir == Direction.LEFT && currentPigeonHeading < desiredAngle) {
			return true;
		}
		if(dir == Direction.RIGHT && currentPigeonHeading > desiredAngle) {
			return true;
		}*/
		
		
		if(!Robot.mecanum.isTargetSpeedWithinThreshold(speed)) {
			return true;
		}
		if(Math.abs(this.desiredAngle-currentPigeonHeading) <= 1) {
			return true;
		}
		return false;
	}
	
	protected void end() {
		Logger.log("] current angle[" + currentPigeonHeading
				+ "] starting pigeon angle [" + startingPigeonAngle
				+ "] desired angle [" + desiredAngle + "]","end PAV2");
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}
