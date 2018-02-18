package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RetractSolenoid extends Command{

	public RetractSolenoid() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		Robot.sol.move(DoubleSolenoid.Value.kReverse);
	}
	
	protected boolean isFinished() {
		if(RobotMap.sol.get() == DoubleSolenoid.Value.kReverse){		
			return true;
		}
		return false;
	}
	
	protected void end() {
		RobotMap.log(RobotMap.solenoidSubtable, "Done retracting solenoid" );
	}
	
	protected void interrupted(){
	}
}
