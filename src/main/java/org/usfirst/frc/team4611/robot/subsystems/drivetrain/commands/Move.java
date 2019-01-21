package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Move extends Command {

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
}