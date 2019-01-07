package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopAndRepositionTalons extends Command {

	protected void execute() {
		Robot.driveTrain.resetEncoders();
	}

	/**
	 * Checks to see if the command is done based on the specified criteria
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Called when the isFinished method returns true (which means the command is
	 * finished)
	 */
	protected void end() {
		Robot.driveTrain.moveVelocityAuton(0);
	}
}
