package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	
	public MoveElevator(){
		this.requires(Robot.elevator); //This command uses this subsystem
	}
	
	protected void execute() {
		double y = -OI.auxJoy.getY();
		
		if (y < 0) { //move up
			Robot.elevator.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed));
		}
		
		else { //move down
			Robot.elevator.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed));
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.elevator.isSwitchSet();
	}
}
