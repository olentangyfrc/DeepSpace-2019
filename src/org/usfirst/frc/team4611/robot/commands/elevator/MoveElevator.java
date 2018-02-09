package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	
	public MoveElevator(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		double y = OI.thirdJoy.getY();
		
		if (y < 0) {
			Robot.el.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed));
		}
		
		else {
			Robot.el.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
		// TODO Auto-generated method stub
	}

}
