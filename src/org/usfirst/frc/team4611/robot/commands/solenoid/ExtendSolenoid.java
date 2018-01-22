package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendSolenoid extends Command{

	public ExtendSolenoid() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		Robot.sol.move(DoubleSolenoid.Value.kForward);
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.sol.move(DoubleSolenoid.Value.kOff);
	}
}
