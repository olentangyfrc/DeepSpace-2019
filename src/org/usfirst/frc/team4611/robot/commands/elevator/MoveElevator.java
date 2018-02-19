package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	double speed;
	public MoveElevator(double sp){
		speed = sp;
		this.requires(Robot.elevator); //This command uses this subsystem
	}
	
	protected void execute() {
		Robot.elevator.move(speed);
	}
		

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
