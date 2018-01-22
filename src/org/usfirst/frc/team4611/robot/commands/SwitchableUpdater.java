package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchableUpdater extends Command{

	public SwitchableUpdater() {
		RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherTalonID, RobotMap.defaults.getDefaultMotorType() == 1);
		RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherVictorID, RobotMap.defaults.getDefaultMotorType() == 0);
	}
	
	protected void execute() {
		if((boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherTalonID)) {
			RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherVictorID, false);
			RobotMap.defaults.updateMotorType(1);
			Robot.oi.setupTalon();
		}else if((boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherVictorID)) {
			RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherTalonID, false);
			RobotMap.defaults.updateMotorType(0);
			Robot.oi.setupVictor();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}

enum MotorType {
	TALON, VICTOR
}