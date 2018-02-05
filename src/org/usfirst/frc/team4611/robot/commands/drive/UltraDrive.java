package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class UltraDrive extends Command{
	private double range;
	private double horizontalDistance;
	private boolean found;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 * @param distance the value in inches the bot drives to
	 */
	public UltraDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	public void initialize(){
		System.out.println("Ready to Grab!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		range = Robot.ultrasonic.getInches() - 6.0;
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		if(Math.abs(horizontalDistance) < 3.0 && found)
		{
			System.out.println(range);
			new PositionDrive(range/12.0,"Forward").start();
		}
		else {
			this.end();
		}
	}
	
	public void execute() {
		System.out.println("execution>:D");
	}

	
	protected boolean isFinished() {
			return true;
	}

}
