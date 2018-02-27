package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.SwitchableCommand;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

public class PigeonAdjust extends SwitchableCommand {

	private double desiredAngle;
	private double startAngle;
	private double prevAngle = 0;
	private double da;
	private double multi = 1;
	private boolean hasAdjusted = false;
	private Direction dir;
	 
	public PigeonAdjust(double da) {
		this.da = da;
	}
	
	protected void initialize() {
		startAngle = RobotMap.pigeon.getFusedHeading();
		if(da != 0) {
			this.desiredAngle = startAngle-da;
		}else if(da == 0) {
			this.desiredAngle = RobotMap.pigeon.getFusedHeading() - (RobotMap.pigeon.getFusedHeading()/360 - (int)RobotMap.pigeon.getFusedHeading()/360)*360;
		}
		
		if(desiredAngle > startAngle) {
			dir = Direction.LEFT;
		}else{
			dir = Direction.RIGHT;
		}
		prevAngle = RobotMap.pigeon.getFusedHeading();
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

		if(this.desiredAngle > status.heading && dir == Direction.RIGHT && Math.abs(status.heading-desiredAngle) > 1) {
			dir = Direction.LEFT;
		}else if(this.desiredAngle < status.heading && dir == Direction.LEFT && Math.abs(status.heading-desiredAngle) > 1) {
			dir = Direction.RIGHT;
		}
		
		if((this.desiredAngle < status.heading && dir == Direction.LEFT) && Math.abs(this.desiredAngle-status.heading) <= 1) {
			System.out.print("Finished Left");
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}else if((this.desiredAngle > status.heading && dir == Direction.RIGHT) && Math.abs(this.desiredAngle-status.heading) <= 1) {
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

	@Override
	public void executeTalon() {

		if(dir == Direction.RIGHT) {
			Robot.mecanum.rotate(multi * 800);
			
			if(prevAngle < RobotMap.pigeon.getFusedHeading() && !hasAdjusted) {
				multi = 1;
				hasAdjusted = true;
			}
			
			prevAngle = RobotMap.pigeon.getFusedHeading();
		}else if(dir == Direction.LEFT) {
			Robot.mecanum.rotate(multi * -800);
			
			if(prevAngle > RobotMap.pigeon.getFusedHeading() && !hasAdjusted) {
				multi = 1;
				hasAdjusted = true;
			}
			prevAngle = RobotMap.pigeon.getFusedHeading();
		}
	}

	@Override
	public void executeVictor() {
		double angleError = angleErrorFilter(Math.abs(this.desiredAngle-RobotMap.pigeon.getFusedHeading()));
		
		
		
		if(dir == Direction.LEFT) {
			RobotMap.driveTrainBL.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainBR.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainFL.set(Math.max(-1, -0.03*angleError));
			RobotMap.driveTrainFR.set(Math.max(-1, -0.03*angleError));
		}else if(dir == Direction.RIGHT) {
			RobotMap.driveTrainBL.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainBR.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainFL.set(Math.min(1, 0.03*angleError));
			RobotMap.driveTrainFR.set(Math.min(1, 0.03*angleError));
		}
	}
	
	private double angleErrorFilter(double error) {
		if(error < 30) {
			return 31;
		}
		return error;
	}
}