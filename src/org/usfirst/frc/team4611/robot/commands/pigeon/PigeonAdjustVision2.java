package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision2 extends Command {

	private double desiredAngle;
	private double startAngle;
	private double encoderSpeedAverage;
	private double speedLimit = 100;

	private double da;
	private Direction dir;
	 
	public PigeonAdjustVision2() {
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
		System.out.print("Desired Angle:" + desiredAngle);
		System.out.println(" Current Angle:" + status.heading);
		
		if(dir == Direction.RIGHT) {
			Robot.mecanum.rotate(Math.min(1040, 1040 * Math.abs(desiredAngle-status.heading)/5));
			
		}else if(dir == Direction.LEFT) {
			Robot.mecanum.rotate(Math.max(-1040, -1040 * Math.abs(desiredAngle-status.heading)/5));
	
		}
		
		encoderSpeedAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorVelocity(0)) +
		    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorVelocity(0)) +
		    	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorVelocity(0)) +
		    	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorVelocity(0))) / 4;
	}
	
	protected boolean isFinished(){
		if(encoderSpeedAverage < speedLimit) {
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}