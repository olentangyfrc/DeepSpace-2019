package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {
	
	private MecanumBase subsystem;
	
	public MoveForward(MecanumBase base) {
		this.subsystem = base;
		this.requires(base);
	}
	
	protected void execute() {
		subsystem.moveForward(1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}