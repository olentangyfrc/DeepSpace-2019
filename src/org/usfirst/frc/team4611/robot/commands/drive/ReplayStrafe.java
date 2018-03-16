package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * this class will replay velocities captured when strafing
 * @author worthp
 *
 */

public class ReplayStrafe extends Command {
	int replayInches	= 10 * 12; // 10 feet * 12 inches
	
	/**
	 * these arrays needs to be filled in by captured velocities from Mecanum Commannd of 10 feet of accurate Strafing.
	 * there is a left moving array and a right moving array. you will need to get an accurate driver
	 * and accurate distance.
	 */
	private double [][] tenFeetStrafeRightVelocities = new double[][] {
		 		{ 0.0, 0.0, 0.0, 0.0},
		 		{ 0.0, 0.0, 0.0, 0.0}
			};

			
	private double [][] tenFeetStrafeLeftVelocities = new double[][] {
		 		{ 0.0, 0.0, 0.0, 0.0},
		 		{ 0.0, 0.0, 0.0, 0.0}
			};
	
	int distanceInInches;
	Direction	dir;
	
	public ReplayStrafe(Direction direction, int inches) {
		requires(Robot.mecanum);
		
		distanceInInches = inches;
		dir	= direction;
	}

	public void execute() {
		
		// choose the right replay sequence
		double [][] replay = tenFeetStrafeLeftVelocities;
		if (dir == Direction.RIGHT) {
			replay	= tenFeetStrafeRightVelocities;
		} 
		
		// prorate replay array by how far this command wants to go
		int	howMuchToReplay = distanceInInches / replayInches * replay.length;

		// holds velocities for 1 iteration
		double [] velocities;
		
		for (int i = 0; i < howMuchToReplay; i++) {
			velocities	= replay[i];
			Robot.mecanum.velocityDrive(velocities[0], velocities[1], velocities[2], velocities[3]);			
		}
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	static public enum Direction {RIGHT, LEFT};
}
