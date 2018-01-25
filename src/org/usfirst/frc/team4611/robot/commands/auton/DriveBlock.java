package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveBlock extends CommandGroup{
	
	public DriveBlock() {
		addSequential(new DriveStraight(2));	
	}
}
