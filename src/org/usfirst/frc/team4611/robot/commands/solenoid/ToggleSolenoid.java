package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleSolenoid extends Command{
	private boolean done;
	
	public ToggleSolenoid(){
		this.requires(Robot.sol);
		done = false;
	}
	
	public void execute(){
		if( Robot.sol.isRetracted ) {
			new ExtendSolenoid().start();
		}
		else{
			new RetractSolenoid().start();
		}
		done = true;
	}

	protected boolean isFinished() {
		return false;
	}

}
