package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopAndRepositionTalons extends Command {
	protected void execute() {
    	Robot.driveTrain.resetEncoders();
    }

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
		Robot.driveTrain.moveVelocityAuton(0);
	}
}
