package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class UltraDrive extends Command{
	private double range;
	private double horizontalDistance;
	private boolean found;
	private Command driveComm;
	private boolean startedDriving;
	private boolean dontRunMe;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 */
	public void initialize(){
		//System.out.println("Ultrasonic Range to box" + range);
		//System.out.println("Vision Distance to box " + RobotMap.networkManager.getVisionValue(RobotMap.distanceID));
		startedDriving = false;
		range = (double)RobotMap.networkManager.getVisionValue(RobotMap.distanceID);//Robot.ultrasonic.getInches() - 6.0;
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		if(range >= 3 && Math.abs(horizontalDistance) <= 3 && found)
		{
			dontRunMe = false;
			//System.out.println("Ultrasonic drive range: " + range);
			driveComm = new PositionDrive(range/12.0,"Forward");
			driveComm.start();
		}
		else {
			dontRunMe = true;
			this.end();
			return;
		}
	}
	
	public void execute(){
		if(driveComm != null && driveComm.isRunning()){
			startedDriving = true;
		}
	}
	
	protected boolean isFinished() {
		if(dontRunMe){
			return true;
		}
		if(startedDriving && !driveComm.isRunning()){
			return true;
		}
		return false;
	}

}
