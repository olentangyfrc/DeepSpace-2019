package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionDrive extends Command{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	private Command driveComm;
	private boolean startedDriving;
	
	public VisionDrive(){
	}
	
	public void initialize() {
		startedDriving = false;
		angle = (double) RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		distance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceID);
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		if(!found || Math.abs(horizontalDistance) <= 3){
			this.end();
			return;
		}
		
		if (angle < 0) {
			driveComm = new PositionDrive(horizontalDistance/12.0, "right");
		} else if (angle > 0){
			driveComm = new PositionDrive(horizontalDistance/12.0, "left");
		} else {
			this.end();
			return;
		}
		
		driveComm.start();
		
		/*for (int i = 0; i < 100; i++) {
			System.out.println("RUNNING!");
		}*/
	}
	
	public void execute(){
		if(driveComm != null && driveComm.isRunning()){
			startedDriving = true;
		}
	}
	
	protected boolean isFinished() {
		if(startedDriving && !driveComm.isRunning()){
			return true;
		}
		return false;
	}
}
