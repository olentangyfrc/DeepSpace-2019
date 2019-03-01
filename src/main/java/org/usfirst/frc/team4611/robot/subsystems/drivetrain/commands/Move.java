package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {

	private final Logger logger = Logger.getLogger(Move.class.getName());

	private DriveTrain driveTrain;


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
	}

	@Override
	protected void interrupted() {
	}
}