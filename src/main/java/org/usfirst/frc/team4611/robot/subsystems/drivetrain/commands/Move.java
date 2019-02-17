package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Move extends Command {

	private final Logger logger = Logger.getLogger(Move.class.getName());

	private DriveTrain driveTrain;
	private ShuffleboardTab tab;


	public Move() {
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
		this.requires(driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		driveTrain.move();
	}

	@Override
	protected boolean isFinished() {
		return false;
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