package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;
import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision2 extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 780;
	private double speedLimit = 100;

	private double angleToBox;
	private Direction dir;
	 
	public PigeonAdjustVision2() {
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {

		startingPigeonAngle = RobotMap.pigeon.getFusedHeading();

		/**
		 * the angle we want to go is in the opposite direction from the angle the 
		 * camera gives us
		 */
		angleToBox = -(double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angleToBox;

		RobotMap.log(RobotMap.pigeonSubtable, "angleToBox [" + angleToBox
					+ "] startingPigeonAngle [" + startingPigeonAngle + "]");
	
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
		errorAngle = Math.abs(desiredAngle) - Math.abs(currentPigeonHeading);
		
		// how do we respond to that error?
		double pVal = errorAngle * .04;
		
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
		
		RobotMap.log(RobotMap.visionTableID, "desiredAngle {" + desiredAngle + "}");
		RobotMap.log(RobotMap.visionTableID, "currentAngle {" + currentPigeonHeading + "}");
		RobotMap.log(RobotMap.visionTableID, "Average Speed {" + Robot.mecanum.getAverageSpeed() + "}");
	}
	
	protected boolean isFinished(){
		if(Robot.mecanum.getAverageSpeed() <= speedLimit &&
				 Math.abs(this.desiredAngle-currentPigeonHeading) <= 1.2) {
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}
