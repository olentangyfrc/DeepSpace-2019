package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LowerElevator extends Command{

	public LowerElevator() {
		this.requires(Robot.elevatorSub);
	}
	
	protected void execute() {
		double input = 0; //temp val
		//double input = Robot.oi.something
		boolean limit = RobotMap.elevatorBotSwitch.get();
		
		Robot.elevatorSub.genericMovement(input);
		
		if (limit = true) {
			RobotMap.elevatorMotor.set(0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
