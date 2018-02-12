package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.SwitchableCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

public class PigeonAdjust extends SwitchableCommand {

	private double desiredAngle;
	private double startAngle;
	private Direction dir;
	 
	public PigeonAdjust(double da) {
		//Resets the fused heading of the pigeon
		RobotMap.pigeon.setFusedHeading(0, 10);
		System.out.println("HEREE");
		this.desiredAngle = da;
		if(da > 0) {
			dir = Direction.RIGHT;
		}else{
			dir = Direction.LEFT;
		}
	}
	
	protected void execute() {
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);
		System.out.println("Current angle:" + status.heading);
		System.out.println("Desired angle: " + this.desiredAngle);
		super.execute();
	}
	
	protected boolean isFinished(){
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);
		if(this.desiredAngle < status.heading && dir == Direction.RIGHT) {
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}else if(this.desiredAngle > status.heading && dir == Direction.LEFT) {
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

	@Override
	public void executeTalon() {
		// TODO Auto-generated method stub
		double angleError = angleErrorFilter(Math.abs(this.desiredAngle-RobotMap.pigeon.getFusedHeading()));

		if(dir == Direction.LEFT) {
			System.out.println("Turning left");
			RobotMap.driveTrainBL_Talon.set(ControlMode.Follower, RobotMap.driveTrainFL_Talon.getDeviceID());
			RobotMap.driveTrainBL_Talon.setInverted(false);
			RobotMap.driveTrainBR_Talon.set(ControlMode.Follower, RobotMap.driveTrainFR_Talon.getDeviceID());
			RobotMap.driveTrainBR_Talon.setInverted(false);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, -0.01*angleError);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, -0.01*angleError);
		}else if(dir == Direction.RIGHT) {
			System.out.println("Turning right");
			RobotMap.driveTrainBL_Talon.set(ControlMode.Follower, RobotMap.driveTrainFL_Talon.getDeviceID());
			RobotMap.driveTrainBL_Talon.setInverted(false);
			RobotMap.driveTrainBR_Talon.set(ControlMode.Follower, RobotMap.driveTrainFR_Talon.getDeviceID());
			RobotMap.driveTrainBR_Talon.setInverted(false);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, 0.01*angleError);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, 0.01*angleError);
		}
	}

	@Override
	public void executeVictor() {
		// TODO Auto-generated method stub
		double angleError = angleErrorFilter(Math.abs(this.desiredAngle-RobotMap.pigeon.getFusedHeading()));
		
		
		
		if(dir == Direction.LEFT) {
			System.out.println("Turning left");
			RobotMap.driveTrainBL.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainBR.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainFL.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainFR.set(Math.max(-1, -0.03*angleError));
		}else if(dir == Direction.RIGHT) {
			System.out.println("Turning right");
			RobotMap.driveTrainBL.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainBR.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainFL.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainFR.set(Math.min(1, 0.03*angleError));
		}
	}
	
	private double angleErrorFilter(double error) {
		if(error < 20) {
			return 21;
		}
		return error;
	}
}