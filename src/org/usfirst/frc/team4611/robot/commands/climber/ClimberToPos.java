package org.usfirst.frc.team4611.robot.commands.climber;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberToPos extends Command {

	double position;
	public ClimberToPos(double position){
		this.position = position;
	}
	
	protected void initialize() {
		RobotMap.climber_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.climber_Talon2.setSelectedSensorPosition(0, 0, 0);
	}
	
	protected void execute() {
		Robot.climber.moveToPos(position);
	}
	
	@Override
	protected boolean isFinished() {
		if((RobotMap.climber_Talon2.getSelectedSensorPosition(0) > position)) {
			RobotMap.climber_Talon.set(0);
			RobotMap.climber_Talon2.set(0);
			return true;
		}
		return false;
	}

}
