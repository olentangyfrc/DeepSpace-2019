package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.VisionVerticalDrive;
import org.usfirst.frc.team4611.robot.commands.drive.VisionHorizontalDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGrab extends CommandGroup{
	
	/**
	 * Drives forward until a certain distance from a surface
	 */
	public AutoGrab(){
		addSequential(new VisionHorizontalDrive(),2);
		addSequential(new VisionVerticalDrive(),2);
		
	}
}
