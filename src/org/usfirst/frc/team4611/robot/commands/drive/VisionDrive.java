package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionDrive extends CommandGroup{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	
	
	public VisionDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	public void initialize() {
		angle = (double) RobotMap.networkManager.getValue("Vision", "angle");
		distance = (double) RobotMap.networkManager.getValue("Vision", "distance");
		horizontalDistance = (double) RobotMap.networkManager.getValue("Vision", "horizontalDistance");
		found = (boolean) RobotMap.networkManager.getValue("Vision", "found");
		
		//Line up
		String direction = "right";
		if (angle < 0) {
			direction = "right";
			//addSequential(new PositionDrive(horizontalDistance/12.0, "right"));
		} else {
			direction = "left";
			//addSequential(new PositionDrive(horizontalDistance/12.0, "left"));
		}
		
		addSequential(new PositionDrive(horizontalDistance/12.0, direction));
		
		//Move forward
		//addSequential(new UltraDrive(),3);
		
		
		
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
