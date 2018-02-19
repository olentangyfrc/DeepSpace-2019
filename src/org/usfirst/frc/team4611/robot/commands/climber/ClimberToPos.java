package org.usfirst.frc.team4611.robot.commands.climber;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberToPos extends Command {

	double position;
	public ClimberToPos(double position){
		this.position = position;
	}
	
	protected void execute() {
		Robot.climber.moveToPos(position);
	}
	
	@Override
	protected boolean isFinished() {
		if((RobotMap.climber_Talon.getSelectedSensorPosition(0) + RobotMap.climber_Talon2.getSelectedSensorPosition(0))/2 > position + 300) {
			return true;
		}
		return false;
	}

}
