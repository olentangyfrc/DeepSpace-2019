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

public class ReplayVelocityDriveRecording extends Command {
	
	/**
	 * these arrays needs to be filled in by captured velocities from Mecanum Command.
	 */
	private ArrayList<double[]> recordedVelocities = null;
	private Recording recording;

	
	/**
	 * read  recordings 1 time
	 */
	
	public void initialize() {
		
		try {
			if (recordedVelocities == null)
				recordedVelocities	= ArrayReader.getInstance().getNumbers(getRecordingFile(recording));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public ReplayVelocityDriveRecording(Recording rec) {
		requires(Robot.mecanum);
		recording	= rec;
	}

	public void execute() {
		
		// holds velocities for 1 iteration
		double [] velocities;
		
		Iterator<double[]>	it = recordedVelocities.iterator();
		while (it.hasNext()) {
			velocities	= it.next();
			Robot.mecanum.velocityDrive(velocities[0], velocities[1], velocities[2], velocities[3]);			
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	private String getRecordingFile(Recording recording) {
		String	file	= "";
			
		switch (recording) {
			case RightSwitchDropToNearBox:
				file	= "RightSwitchDropToNearBox.txt";
				break;
			case RightSwitchDropToFarBox:
				file	= "RightSwitchDropToFarBox.txt";
				break;
		}
		
		return file;
	}

	static public enum Recording {RightSwitchDropToNearBox,
									RightSwitchDropToFarBox};
}
