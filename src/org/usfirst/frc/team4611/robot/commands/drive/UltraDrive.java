package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class UltraDrive extends Command{
	private double range;
	private double horizontalDistance;
	private boolean found;
	private Command driveComm;
	private boolean startedDriving;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 */
	public void initialize(){
		startedDriving = false;
		range = Robot.ultrasonic.getInches() - 3.0;
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		if(Math.abs(horizontalDistance) <= 3 && found)
		{
			System.out.println("Ultrasonic drive range: " + range);
			driveComm = new PositionDrive(range/12.0,"Forward");
			driveComm.start();
		}
		else {
			this.end();
		}
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
