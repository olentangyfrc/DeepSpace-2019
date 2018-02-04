package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class UltraDrive extends Command{
	private double range;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 * @param distance the value in inches the bot drives to
	 */
	public UltraDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	public void initialize(){
		range = Robot.ultrasonic.getInches();
		new PositionDrive(range,"Forward").start();
	}
	
	public void execute() {
		
	}

	
	protected boolean isFinished() {
			return true;
	}

}
