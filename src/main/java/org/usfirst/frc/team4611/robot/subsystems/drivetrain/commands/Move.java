package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {

	private DriveTrain driveTrain;

	public Move() {
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
		this.requires(driveTrain);
	}

	protected void execute() {
		driveTrain.move();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}