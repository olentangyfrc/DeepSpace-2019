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
			case CenterToRightSwitch:
				file	= directory + "CenterToRightSwitch.txt";
				break;
			case CenterToLeftSwitch:
				file	= directory + "CenterToLeftSwitch.txt";
				break;
			case RightStartToSwitch:
				file	= directory + "RightStartToSwitch.txt";
				break;
			case RightStartToScale:
				file	= directory + "RightStartToScale.txt";
				break;
			case RightSwitchToBox2:
				file	= directory + "RightSwitchToBox2.txt";
				break;
			case RightScaleToBox2:
				file	= directory + "RightScaleToBox2.txt";
				break;
			case Box2ToRightSwitch:
				file	= directory + "Box2ToRightSwitch.txt";
				break;
			case Box2ToRightScale:
				file	= directory + "Box2ToRightScale.txt";
				break;
			case LeftStartToSwitch:
				file	= directory + "LeftStartToSwitch.txt";
				break;
			case LeftStartToScale:
				file	= directory + "LeftStartToScale.txt";
				break;
			case LeftSwitchToBox2:
				file	= directory + "LeftSwitchToBox2.txt";
				break;
			case LeftScaleToBox2:
				file	= directory + "LeftScaleToBox2.txt";
				break;
			case Box2ToLeftSwitch:
				file	= directory + "Box2ToLeftSwitch.txt";
				break;
			case Box2ToLeftScale:
				file	= directory + "Box2ToLeftScale.txt";
				break;
				
		}
		
		return file;
	}

	static public enum Recording {	CenterToRightSwitch,
									CenterToLeftSwitch,
									RightStartToSwitch,
									RightStartToScale,
									RightSwitchToBox2,
									RightScaleToBox2,
									Box2ToRightSwitch,
									Box2ToRightScale,
									LeftStartToSwitch,
									LeftStartToScale,
									LeftSwitchToBox2,
									LeftScaleToBox2,
									Box2ToLeftSwitch,
									Box2ToLeftScale};
}
