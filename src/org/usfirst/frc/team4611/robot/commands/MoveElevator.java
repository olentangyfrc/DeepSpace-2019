package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	private double speed;
	public MoveElevator(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		System.out.println(Robot.el.isSwitchSet());
		double y = OI.thirdJoy.getY();
		if((Robot.el.isSwitchSet() && speed >= 0) || (!Robot.el.isSwitchSet() && speed <= 0) || (!Robot.el.isSwitchSet() && speed >= 0)){
			Robot.el.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorScalar));
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.el.isSwitchSet();
	}

}
