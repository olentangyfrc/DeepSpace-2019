package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendSolenoid extends Command{

	public ExtendSolenoid() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		RobotMap.log(RobotMap.solenoidSubtable, "Extending solenoid" );
		Robot.sol.move(DoubleSolenoid.Value.kForward);
	}
	
	protected boolean isFinished() {
		RobotMap.log(RobotMap.solenoidSubtable, "Done extending solenoid" );
		return false;
	}
	
	protected void end() {
		Robot.sol.move(DoubleSolenoid.Value.kOff);
	}
}
