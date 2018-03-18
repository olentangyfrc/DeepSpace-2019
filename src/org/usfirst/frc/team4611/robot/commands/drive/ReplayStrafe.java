package org.usfirst.frc.team4611.robot.commands.drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
	int iterations	= 0;
	Iterator<double[]>	velocityIterator	= null;
	int	velocityCount	= 0;
	
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
			if (tenFeetStrafeLeftVelocities == null) {
				tenFeetStrafeLeftVelocities		= ArrayReader.getInstance().getNumbers("TenFeetStrafeLeftRecording.txt");
				velocityCount	= tenFeetStrafeLeftVelocities.size();
			} if (tenFeetStrafeRightVelocities == null) {
				tenFeetStrafeRightVelocities	= ArrayReader.getInstance().getNumbers("TenFeetStrafeRightRecording.txt");
				velocityCount	= tenFeetStrafeRightVelocities.size();
			}
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
		dir	= direction;// choose the right replay sequence

		velocityIterator	= tenFeetStrafeLeftVelocities.iterator();
		if (dir == Direction.RIGHT) {
			velocityIterator	= tenFeetStrafeRightVelocities.iterator();
		} 
		
		// pro-rate replay array by how far this command wants to go
		iterations = distanceInInches / replayInches * velocityCount;
	}

	public void execute() {		

		// holds velocities for 1 iteration
		double [] velocities;
		
		if (velocityIterator.hasNext()) {
			velocities	= velocityIterator.next();
			Robot.mecanum.velocityDrive(velocities[0], velocities[1], velocities[2], velocities[3]);	
			iterations -= 1;
		}
		
	}

	@Override
	protected boolean isFinished() {
		return (iterations == 0 || !velocityIterator.hasNext());
	}

	static public enum Direction {RIGHT, LEFT};
}
