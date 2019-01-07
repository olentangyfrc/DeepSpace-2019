package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class StopAndRepositionTalons extends Command {

	private DriveTrain driveTrain;

	public StopAndRepositionTalons(){
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
	}

	protected void execute() {
		driveTrain.resetEncoders();
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
		.driveTrain.moveVelocityAuton(0);
	}
}
