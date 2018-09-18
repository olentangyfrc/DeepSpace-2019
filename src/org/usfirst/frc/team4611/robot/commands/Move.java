package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Move extends Command {
	
	public double speed;
	
	private MecanumBase mecanum;
	
	public Move(MecanumBase mecanum) {
		this.speed = speed;
		this.mecanum = mecanum;
		this.requires(Robot.mecanum);
	}
	
	protected void execute() {
		Robot.mecanum.move();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}