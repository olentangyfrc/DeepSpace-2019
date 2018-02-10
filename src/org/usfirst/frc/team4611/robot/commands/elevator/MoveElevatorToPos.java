package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command{
	
	public MoveElevatorToPos(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		Robot.el.moveToPos(47494);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
