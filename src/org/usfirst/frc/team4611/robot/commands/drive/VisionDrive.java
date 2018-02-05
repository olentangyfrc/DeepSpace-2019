package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionDrive extends Command{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	PositionDrive moveCommand;
	
	public VisionDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	public void initialize() {

		angle = (double) RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		distance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceID);
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		//Line up
		//String direction = "right";
		if(!found)
			this.end();
		if (angle < 0) {
			moveCommand = new PositionDrive(horizontalDistance/12.0, "right");
			//direction = "right";
		} else if (angle > 0){
			moveCommand = new PositionDrive(horizontalDistance/12.0, "left");
			//direction = "left";
		} else {
			this.end();
		}
		
		moveCommand.start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println("RUNNING!");
			//System.out.println(angle);
			//System.out.println(horizontalDistance);
		}
		//new PositionDrive(2, "right").start();
	}
	
	public void execute(){
	}
	
	protected boolean isFinished() {
		//Right now run forever
		//add where if the angle is < 3 then end it
		return moveCommand.isFinished();
	}

	public boolean isInterruptable() {
		return true;
	}
	
	protected void end()
	{
		System.out.println("I've been killed!!!!!!!!!!!!!!!");
	}
}
