package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class StopAndRepositionTalons extends Command {

	private final Logger logger = Logger.getLogger(StopAndRepositionTalons.class.getName());

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
		driveTrain.moveVelocityAuton(0);
	}

	@Override
	public synchronized void cancel() {
		logger.info("cancel");
	}

	@Override
	protected void interrupted() {
		logger.info("interrupted");
	}

}
