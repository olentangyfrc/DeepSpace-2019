package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;
import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision3 extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 1500;
	private double speedLimit = 100;
	private double angle;
	private Direction dir;
	 
	public PigeonAdjustVision3() {
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		this.angle = -(double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		startingPigeonAngle = RobotMap.pigeon.getFusedHeading();

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angle;

		Logger.log("angle [" + angle
					+ "] startingPigeonAngle [" + startingPigeonAngle + "]", this.getClass().getName());
	
		// which way do we need to go?
		if(desiredAngle > startingPigeonAngle) {
			dir = Direction.LEFT;
			if(Math.abs(desiredAngle) > 5) {
				desiredAngle -= 3;
			}
		}else{
			dir = Direction.RIGHT;
			if(Math.abs(desiredAngle) > 5) {
				desiredAngle += 3;
			}
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
			/**
			 * HARD CODED SPEEDS LEFT BEHIND FROM TINKERING. 
			 * NEED TO DO THIS CORRECTLY WHEN WE FIGURE OUT PRECISION
			 * THEN DELETE THIS COMMENT BLOCK
			 */

			if(dir == Direction.RIGHT) {
				Robot.mecanum.rotate(speed);
				
			}else if(dir == Direction.LEFT) {
				Robot.mecanum.rotate(-speed);
			}
		 }
		Logger.log("desiredAngle {" + desiredAngle + "}" 
				+ "currentAngle {" + currentPigeonHeading + "}"
				+ "Average Speed {" + Robot.mecanum.getAverageSpeed() + "}"
				+ "Target Speed {" + speed + "}", this.getClass().getName());
	}
	
	protected boolean isFinished(){
		if(Math.abs(this.desiredAngle-currentPigeonHeading) <= 1) {
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}
