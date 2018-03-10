package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.command.Command;

public class VisionHorizontalDrive2 extends Command{
	
	double horizontalDistance = -1;
	double angle;
	public double converter = 206.243 * 3;
	private double horizontalThreshhold = 200.0;
	
	public VisionHorizontalDrive2(){
    	requires(Robot.mecanum);
	}
	
	public void initialize() {
		
		
		angle = (double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		Robot.mecanum.resetEncoders();
    	Robot.mecanum.config_kP(1);
    	Robot.mecanum.resetRampRate();
    	System.out.println("Initilizing Vision drive 2");
    	
    	Logger.log(" H Dist [" + horizontalDistance +"] "
				+ " Converter [" + converter +"] "
				+ " HDist*converter [" + (horizontalDistance * converter) +"] "
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]", "VisionHorizontalDrive2");
		
	}
	
	public void execute(){
		if(horizontalDistance == -1 && (boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) {
			horizontalDistance = (double)RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		}
		
		Logger.log(" H Dist [" + horizontalDistance +"] "
				+ " Converter [" + converter +"] "
				+ " HDist*converter [" + (horizontalDistance * converter) +"] "
				+ " H Thresh [" + horizontalThreshhold +"] "
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]"
				+ " Avg Speed: [" + Robot.mecanum.getAverageSpeed() + "]", "VisionHorizontalDrive2");
	
		if(horizontalDistance != -1) {
			if(angle > 0) {
				Robot.mecanum.motionMagicStrafe(-horizontalDistance * converter);
			}
			
			else {
				Robot.mecanum.motionMagicStrafe(horizontalDistance * converter);
			}
		}
		
		
	}
	
	protected boolean isFinished() {
		
    	if(horizontalGood() && (boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) {
    		Logger.log("VisionHorizontalDrive2 isFinished returning true", this.getClass().getName());
        	return true;
    	}
        else {
        	return false;
        }	
    }
	
	private boolean horizontalGood() {
		if ((horizontalDistance * converter) - horizontalThreshhold <= Robot.mecanum.getAveragePosition()) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end( ) {
		Robot.mecanum.config_kP(.65);
	}
}
