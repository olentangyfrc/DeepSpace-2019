package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopAndRepositionTalons extends Command {
	protected void execute() {
    	Robot.mecanum.resetEncoders();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
}
