package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class PushBox extends Command{

	public PushBox() {
		this.requires(Robot.boxPusher);
		RobotMap.updateValue(RobotMap.pushBoxSubtable, RobotMap.pushBoxTimeID, 2);
	}
	
	protected void execute() {
		RobotMap.updateValue(RobotMap.pushBoxSubtable, RobotMap.pushBoxEnabledID, true);
		Robot.boxPusher.move(DoubleSolenoid.Value.kForward);
	}
	
	@Override
	protected boolean isFinished() {
		return this.timeSinceInitialized() >= (int)(double)RobotMap.getValue(RobotMap.pushBoxSubtable, RobotMap.pushBoxTimeID);
	}

	protected void end() {
		RobotMap.updateValue(RobotMap.pushBoxSubtable, RobotMap.pushBoxEnabledID, false);
		Robot.boxPusher.move(DoubleSolenoid.Value.kReverse);
	}
	
	protected void interrupted() {
		end();
	}
	
}
