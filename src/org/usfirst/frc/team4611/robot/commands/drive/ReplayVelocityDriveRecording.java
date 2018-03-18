package org.usfirst.frc.team4611.robot.commands.drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.utilities.ArrayReader;

import edu.wpi.first.wpilibj.command.Command;

/**
 * this class will replay velocities captured when strafing
 * @author worthp
 *
 */

public class ReplayVelocityDriveRecording extends Command {
	
	/**
	 * these arrays needs to be filled in by captured velocities from Mecanum Command.
	 */
	private ArrayList<double[]> recordedVelocities = null;
	private Recording recording;
	Iterator<double []>	velocityIterator	= null;

	
	/**
	 * read  recordings 1 time
	 */
	
	public void initialize() {
		
		try {
			if (recordedVelocities == null) {
				recordedVelocities	= ArrayReader.getInstance().getNumbers(getRecordingFile(recording));
				velocityIterator	= recordedVelocities.iterator();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public ReplayVelocityDriveRecording(Recording rec) {
		requires(Robot.mecanum);
		recording	= rec;
	}

	/**
	 * send 1 set of velocities in each execute.
	 */
	public void execute() {
		
		double [] velocities;
		
		if (velocityIterator.hasNext()) {
			velocities	= velocityIterator.next(); 
			Robot.mecanum.velocityDrive(velocities[0], velocities[1], velocities[2], velocities[3]);
		}
	}
	
	/**
	 * we are finished when we have iteratated over all recorded velocities
	 */

	@Override
	protected boolean isFinished() {
		return !velocityIterator.hasNext();
	}
	
	private String getRecordingFile(Recording recording) {
		String directory	= "/home/lvuser/velocityrecordings/";
		String	file	= "";
			
		switch (recording) {
			case RightSwitchDropToNearBox:
				file	= directory + "RightSwitchDropToNearBox.txt";
				break;
			case RightSwitchDropToFarBox:
				file	= directory + "RightSwitchDropToFarBox.txt";
				break;
		}
		
		return file;
	}

	static public enum Recording {RightSwitchDropToNearBox,
									RightSwitchDropToFarBox};
}
