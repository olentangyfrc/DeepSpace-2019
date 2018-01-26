package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.DriveStrafe;
import org.usfirst.frc.team4611.robot.commands.drive.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveBlock extends CommandGroup{
	
	public DriveBlock() {
		addSequential(new DriveStraight((double) RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.straightRotationID)), 4);
		addSequential(new DriveStrafe((double) RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafeRotationID)), 4);
		addSequential(new DriveStraight(4), 4);
	}
}
