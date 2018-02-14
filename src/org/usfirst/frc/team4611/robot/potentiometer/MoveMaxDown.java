package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveMaxDown extends Command{

	public MoveMaxDown() {
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		Robot.arm.movePotPos(.2);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
