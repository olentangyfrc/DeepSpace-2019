package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionDrive extends Command{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	
	
	public VisionDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	public void initialize() {

		angle = (double) RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		distance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceID);
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceHorizontalID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		//Line up
		String direction = "right";
		if (angle < 0) {
			direction = "right";
			//addSequential(new PositionDrive(horizontalDistance/12.0, "right"));
		} else {
			direction = "left";
			//addSequential(new PositionDrive(horizontalDistance/12.0, "left"));
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(angle);
		}
		//addSequential(new PositionDrive(horizontalDistance/12.0, direction));
		new PositionDrive(horizontalDistance/12.0, direction).start();
		
		
		
		
	}
	
	public void execute(){
	}
	
	protected boolean isFinished() {
		//Right now run forever
		//add where if the angle is < 3 then end it
		
		//if( Robot.ultrasonic.getInches() < distance){
		//	RobotMap.driveTrain.driveCartesian(0, 0, 0);
		//	return true;
		//}
		//else{
			return false;
		//}
	}

	public boolean isInterruptable() {
		return true;
	}
}
