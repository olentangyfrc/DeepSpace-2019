package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleSolenoid extends Command{
	private boolean done;
	
	public ToggleSolenoid(){
		this.requires(Robot.sol);
		done = false;
	}
	
	public void execute(){
		if(RobotMap.sol.get() == DoubleSolenoid.Value.kReverse) {
			Robot.sol.move(DoubleSolenoid.Value.kForward);
		}
		else{
			Robot.sol.move(DoubleSolenoid.Value.kReverse);
		}
		done = true;
	}

	protected boolean isFinished() {
		return true;
	}

}
