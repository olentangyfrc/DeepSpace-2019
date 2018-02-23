package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForward extends CommandGroup {

	public DriveForward() {
		addSequential(new AutonForward(RobotMap.WAY));
	}
}
