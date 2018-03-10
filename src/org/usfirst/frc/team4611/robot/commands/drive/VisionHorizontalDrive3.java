package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class VisionHorizontalDrive3 extends Command{
	
	double horizontalDistance = -1;
	double angle;
	public double converter = 206.243 * (double)RobotMap.getValue(RobotMap.autonSubTable, RobotMap.converterID);
	private double horizontalThreshhold = 200.0;
	
	public VisionHorizontalDrive3(){
    	requires(Robot.mecanum);
	}
	
	public void initialize() {
		
		double converter = 206.243 * (double)RobotMap.getValue(RobotMap.autonSubTable, RobotMap.converterID);
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
		
		Logger.log(" H Dist [" + horizontalDistance +"] "
				+ " Converter [" + converter +"] "
				+ " HDist*converter [" + (horizontalDistance * converter) +"] "
				+ " H Thresh [" + horizontalThreshhold +"] "
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]"
				+ " Avg Speed: [" + Robot.mecanum.getAverageSpeed() + "]", "VisionHorizontalDrive2");

		if(horizontalDistance == -1 && (boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) {
			horizontalDistance = (double)RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		} else if (!(boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
			return;
		} else {
			horizontalDistance = (double)RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID); 
			
			if(angle > 0) {
				Robot.mecanum.motionMagicStrafe(-horizontalDistance * converter);
			} else {
				Robot.mecanum.motionMagicStrafe(horizontalDistance * converter);
			}
		}
		
	}
	
	protected boolean isFinished() {
    	if(horizontalGood()) {
    		Logger.log("VisionHorizontalDrive3 isFinished returning true", this.getClass().getName());
        	return true;
    	}
        else {
        	return false;
        }	
    }
	
	private boolean horizontalGood() {
		if (horizontalDistance == -1)
				return false;
		
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
