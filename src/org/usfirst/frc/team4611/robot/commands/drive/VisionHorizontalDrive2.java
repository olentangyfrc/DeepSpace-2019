package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionHorizontalDrive2 extends Command{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	private boolean dontRunMe;
	
	public VisionHorizontalDrive2(){
		dontRunMe = false;
	}
	
	public void initialize() {
		dontRunMe = false;
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		RobotMap.driveTrainFL_Talon.config_kP(0, 2.5, 0);
		RobotMap.driveTrainFR_Talon.config_kP(0, 2.5, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, 2.5, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, 2.5, 0);
		RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void execute(){
		
		if(!found || Math.abs(horizontalDistance) <= 3){
			dontRunMe = true;
			
		if (angle < 0) {
			Robot.mecanum.motionMagicStrafe(horizontalDistance);
		} else if (angle > 0){
			Robot.mecanum.motionMagicStrafe(horizontalDistance);
		} else {
			dontRunMe = true;
		}
		}
	}
	
	protected boolean isFinished() {
		if(dontRunMe){
			return true;
		}
		return false;
	}
	
	protected void end( ) {
		RobotMap.driveTrainFL_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainFR_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, .65, 0);
	}
}
