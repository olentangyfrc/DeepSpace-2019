package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionVerticalDrive2 extends Command{
	
	double verticalDistance;
	double angle;
	public double converter = 206.243 * 2;
	
	public VisionVerticalDrive2(){
    	requires(Robot.mecanum);
	}
	
	public void initialize() {
		verticalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceID);
		angle = (double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		Robot.mecanum.resetEncoders();
    	Robot.mecanum.config_kP(1);
    	Robot.mecanum.resetRampRate();
	}
	
	public void execute(){	
		Robot.mecanum.motionMagicStraight(verticalDistance * converter);	
	}
	
	protected boolean isFinished() {
		
    	if(verticalGood()) {
    		RobotMap.log(RobotMap.visionTableID, "VisionVerticalDrive2 isFinished returning true");
        	return true;
    	}
        else {
        	return false;
        }	
    }
	
	private boolean angleGood() {
		if (Math.abs(angle) < 3) {
			return true;
		}
		
		else 
			return false;
	}
	
	private boolean verticalGood() {
		if (Math.abs(verticalDistance * converter) >= Robot.mecanum.getAveragePosition()) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	protected void end( ) {
		Robot.mecanum.config_kP(.65);
	}
}
