package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command{
	
	public MoveElevatorDown(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		
		Robot.el.move(-(double)RobotMap.getValue(RobotMap.elevatorSubtable, (RobotMap.elevatorDownSpeed)));
		

	}

	@Override
	protected boolean isFinished() {
		return false;
		// TODO Auto-generated method stub
	}
}