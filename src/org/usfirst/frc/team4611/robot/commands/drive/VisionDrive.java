package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class VisionDrive extends Command{
	//private int distance;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 * @param distance the value in inches the bot drives to
	 */
	public VisionDrive(int distance){
		//this.requires(Robot.mecanum); //This command uses this subsystem
		//this.distance = distance;
	}
	public void execute(){
		//RobotMap.driveTrain.driveCartesian(0, 0.6, 0);
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

}
