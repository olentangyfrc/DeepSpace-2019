package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;
import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision2 extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 1500;
	private double speedLimit = 800;
	private boolean speedLimitReached = false;

	private double angleToBox;
	private Direction dir;
	 
	public PigeonAdjustVision2() {
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		Logger.log("init", this.getClass().getName());
		startingPigeonAngle = RobotMap.pigeon.getFusedHeading();

		/**
		 * the angle we want to go is in the opposite direction from the angle the 
		 * camera gives us
		 */
		angleToBox = (double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = angleToBox-startingPigeonAngle;

		Logger.log("angleToBox [" + angleToBox
					+ "] startingPigeonAngle [" + startingPigeonAngle + "]", this.getClass().getName());
	
		// which way do we need to go?
		if(desiredAngle > startingPigeonAngle) {
			dir = Direction.LEFT;
		}else{
			dir = Direction.RIGHT;
		}
	}
	protected void execute() {
		// where are we now?
		currentPigeonHeading = RobotMap.pigeon.getFusedHeading();
		
		// how far do we have to go b4 we get to the target?
		errorAngle = Math.abs(desiredAngle - currentPigeonHeading);
		
		// how do we respond to that error?
		double pVal = errorAngle * .1;
		
		// set our speed to that adjusted speed
		double speed = Math.min(maxRPM, maxRPM * pVal);

		/**
		 * check to see if we are where we need to be before
		 * we even move. we might be there.
		 */
		if(!isFinished()) {
			if(dir == Direction.RIGHT) {
				Robot.mecanum.rotate(speed);
				
			}else if(dir == Direction.LEFT) {
				Robot.mecanum.rotate(-speed);
			}
		 }
		
		if(Robot.mecanum.getAverageSpeed() <= speedLimit) {
			speedLimitReached = true;
		}
		
		Logger.log("desiredAngle {" + desiredAngle + "}" 
			+ "currentAngle {" + currentPigeonHeading + "}"
			+ "Average Speed {" + Robot.mecanum.getAverageSpeed() + "}"
			+ "Target Speed {" + speed + "}", this.getClass().getName());
	}
	
	protected boolean isFinished(){
		if(//speedLimitReached &&
				 Math.abs(this.desiredAngle-currentPigeonHeading) <= 1.5) {
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}
