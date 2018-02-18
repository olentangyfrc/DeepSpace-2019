package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command{
	private double position;
	public MoveElevatorToPos(double position){
		this.position = position;
		this.requires(Robot.el); //This command uses this subsystem
	}
	
	protected void initialize() {
	}

	protected void execute() {
		Robot.el.moveToPos(position);
	}

	@Override
	protected boolean isFinished() {
		double variance = Math.abs(RobotMap.elevator_Talon.getSelectedSensorPosition(0)-position);
		if (variance < 1000) {
			return true;
		}
		else {
		return false;
		}
	}
	
	protected void end() {
		System.out.println("Move elevator command finished");//RobotMap.elevator_Talon.setSensorPhase(true);
		
	}

}
