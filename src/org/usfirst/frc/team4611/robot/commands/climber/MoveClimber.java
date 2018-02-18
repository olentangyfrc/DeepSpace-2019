package org.usfirst.frc.team4611.robot.commands.climber;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimber extends Command{
	public MoveClimber() {
		this.requires(Robot.climber);
	}
	protected void execute() {
		Robot.climber.move(1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
