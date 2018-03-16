package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ReleaseBox extends Command{

	public ReleaseBox() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		Robot.sol.move(DoubleSolenoid.Value.kReverse);
	}
	
	protected boolean isFinished() {
		if(RobotMap.grabber.get() == DoubleSolenoid.Value.kReverse){		
			return true;
		}
		return false;
	}
	
	protected void end() {
	}
	
	protected void interrupted(){
	}
}
