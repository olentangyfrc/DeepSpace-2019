package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustDriveForward extends CommandGroup {

	public JustDriveForward() {
		addSequential(new ResetElevator());
		addSequential(new AutonForward(RobotMap.WAY));
	}
	protected void initialize() {
		Logger.log("DRIVEFORWARD initialized", this.getClass().getName());
	}
}
