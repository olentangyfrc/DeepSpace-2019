package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class VisionVerticalDrive extends Command{
	
	double verticalDistance;
	double angle;
	public double converter = 206.243;
	
	public VisionVerticalDrive(){
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
	
	public boolean isFinished() {
		return (Math.abs(verticalDistance * converter) < Robot.mecanum.getAveragePosition());
	}
	
	protected void end( ) {
		Robot.mecanum.config_kP(.65);
	}
}
