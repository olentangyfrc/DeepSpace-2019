package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjustVision extends Command {

	private double desiredAngle;
	private double startAngle;

	private double da;
	private Direction dir;
	 
	public PigeonAdjustVision() {
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
	}
	
	protected boolean isFinished(){
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);
		
		if((this.desiredAngle < status.heading
				&& dir == Direction.LEFT) 
				&& Math.abs(this.desiredAngle-status.heading) <= 1.2) {
			System.out.print("Finished Left");
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}else if((this .desiredAngle > status.heading
				&& dir == Direction.RIGHT) 
				&& Math.abs(this.desiredAngle-status.heading) <= 1.2) {
			System.out.println("Finished Right");
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}