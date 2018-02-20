package org.usfirst.frc.team4611.robot.commands.climber;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class WindUpClimber extends Command{
	
	public WindUpClimber() {
		this.requires(Robot.climber);
	}
	protected void execute() {
		double speed = (double)RobotMap.getValue(RobotMap.climberSubtable, RobotMap.climberSpeed);		
		Robot.climber.move(-speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		Robot.climber.move(0);
	}

}
