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
		System.out.println(Robot.el.isSwitchSet());
		if((Robot.el.isSwitchSet() && speed >= 0) || (!Robot.el.isSwitchSet() && speed <= 0) || (!Robot.el.isSwitchSet() && speed >= 0)){
			Robot.el.move(speed);
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.el.isSwitchSet();
	}

}
