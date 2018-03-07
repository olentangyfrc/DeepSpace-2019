package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision2 extends Command {

	private double desiredAngle;
	private double startAngle;
	private double encoderSpeedAverage;
	private double speedLimit = 10;

	private double da;
	private Direction dir;
	 
	public PigeonAdjustVision2() {
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
		startAngle = RobotMap.pigeon.getFusedHeading();
		da = -(double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		RobotMap.log(RobotMap.pigeonSubtable, "" + da);
		this.desiredAngle = startAngle-da;
	
		if(desiredAngle > startAngle) {
			dir = Direction.LEFT;
		}else{
			dir = Direction.RIGHT;
		}
	}
	protected void execute() {
	
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);
		
		if(dir == Direction.RIGHT) {
			Robot.mecanum.rotate(780);
			
		}else if(dir == Direction.LEFT) {
			Robot.mecanum.rotate(-780);
	
		}
		
		RobotMap.log(RobotMap.visionTableID, "desiredAngle {" + desiredAngle + "}");
		RobotMap.log(RobotMap.visionTableID, "currentAngle {" + RobotMap.pigeon.getFusedHeading() + "}");
		RobotMap.log(RobotMap.visionTableID, "Average Speed {" + Robot.mecanum.getAverageSpeed() + "}");


	}
	
	protected boolean isFinished(){
		if(Robot.mecanum.getAverageSpeed() <= speedLimit &&
				 Math.abs(this.desiredAngle-RobotMap.pigeon.getFusedHeading()) <= 1.2) {
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}