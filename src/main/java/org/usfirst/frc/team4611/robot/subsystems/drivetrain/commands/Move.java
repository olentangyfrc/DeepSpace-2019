package org.usfirst.frc.team4611.robot.commands.teleop.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {

	private DriveTrain mecanum;

	public Move(DriveTrain mecanum) {
		this.mecanum = mecanum;
		this.requires(Robot.driveTrain);
	}

	protected void execute() {
		mecanum.move();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}