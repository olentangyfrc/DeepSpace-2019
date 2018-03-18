package org.usfirst.frc.team4611.robot.commands.drive;

import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.utilities.ArrayReader;

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
	private ArrayList<double[]> tenFeetStrafeRightVelocities = null;
	private ArrayList<double []> tenFeetStrafeLeftVelocities = null;
	
	int distanceInInches;
	Direction	dir;
	
	/**
	 * read strafe recordings 1 time
	 */
	
	public void initialize() {
		
		try {
			if (tenFeetStrafeLeftVelocities == null)
				tenFeetStrafeLeftVelocities		= ArrayReader.getInstance().getNumbers("TenFeetStrafeLeftRecording.txt");
			if (tenFeetStrafeRightVelocities == null)
				tenFeetStrafeRightVelocities	= ArrayReader.getInstance().getNumbers("TenFeetStrafeRightRecording.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param direction - which way to strafe
	 * @param inches - how far
	 */
	public ReplayStrafe(Direction direction, int inches) {
		requires(Robot.mecanum);
		
		distanceInInches = inches;
		dir	= direction;
	}

	public void execute() {
		
		// choose the right replay sequence
		ArrayList<double[]> replay = tenFeetStrafeLeftVelocities;
		if (dir == Direction.RIGHT) {
			replay	= tenFeetStrafeRightVelocities;
		} 
		
		// pro-rate replay array by how far this command wants to go
		int	howMuchToReplay = distanceInInches / replayInches * replay.size();

		// holds velocities for 1 iteration
		double [] velocities;
		
		for (int i = 0; i < howMuchToReplay; i++) {
			velocities	= replay.get(i);
			Robot.mecanum.velocityDrive(velocities[0], velocities[1], velocities[2], velocities[3]);			
		}
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	static public enum Direction {RIGHT, LEFT};
}
