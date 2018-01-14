package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class RetractSolenoid extends Command{
	
	public RetractSolenoid() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		Robot.sol.move(DoubleSolenoid.Value.kReverse);
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.sol.move(DoubleSolenoid.Value.kOff);
	}
}
