package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorUp extends Command{
	
	double speed = (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed);
	public MoveElevatorUp(){
		this.requires(Robot.elevator); //This command uses this subsystem
	} 

	protected void execute() {
		double speed = (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed);
		Robot.elevator.move(-speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
		// TODO Auto-generated method stub
	}
	
	protected void end() {
		Robot.elevator.move(0);
	}

}
