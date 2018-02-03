package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	private double speed;
	public MoveElevator(double sp){
		speed= sp;
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		Robot.el.move(speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
